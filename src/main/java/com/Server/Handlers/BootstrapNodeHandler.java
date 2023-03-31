package com.Server.Handlers;

import com.Control.JWTController;
import com.Server.Node;
import com.Server.Server;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class BootstrapNodeHandler implements RequestHandler{
    private Server server;
    private Socket socket;
    private JWTController jwtManager;
    private DataOutputStream out;
    private DataInputStream in;
    private boolean isInitializedSuccessfully;

    public BootstrapNodeHandler(Server server , Socket socket) throws IOException {
        this.server=server;
        this.socket=socket;
        this.jwtManager = new JWTController();
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }
    @Override
    public void handle() throws IOException {
        isInitializedSuccessfully = bootstrap();
    }
    public boolean bootstrap() throws IOException {
        try {
            Gson gson = new Gson();
            String token = in.readUTF();
            Claims claims;

            try{
                claims = jwtManager.decodeJWT(token , server.getSECRET_KEY());
            }catch (ExpiredJwtException e){

                return false;
            }



            if (!(claims.get("SENDER")).equals("BOOTSTRAP_NODE")){
                return false;
            }

            String nodeNumber = String.valueOf((claims.get("NODE_NUMBER")));
            server.setNodeNumber(Integer.parseInt(nodeNumber));

            if (claims.get("WRITE_AFFINITY")!=null && (claims.get("WRITE_AFFINITY").equals("TRUE"))){

                server.setWriteAffinity(true);
                Map<String , Object> otherNodesInfoInJson = gson.fromJson((String)claims.get("OTHER_NODES_INFO") , HashMap.class);


                Map<String , Node> otherNodes = new HashMap<>();

                for (String s :otherNodesInfoInJson.keySet()) {
                    String data = otherNodesInfoInJson.get(s).toString();
                    otherNodes.put(s, gson.fromJson(data.replace("\n", ""), Node.class));
                }
                server.setOtherNodes(otherNodes);
                return true;
            }
            else if (claims.get("WRITE_AFFINITY")!=null && claims.get("WRITE_AFFINITY").equals("FALSE")){
                server.setWriteAffinity(false);

                Node writerNode = gson.fromJson((String) claims.get("WRITER_NODE") , Node.class);
                server.setWriterNode(writerNode);
                return true;
            }

            return false;
        }
        catch (SocketException  socketException){return false;}
    }


    public boolean isInitializedSuccessfully() {
        return isInitializedSuccessfully;
    }
}
