package com;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler extends Thread{
    private DataOutputStream out;
    private DataInputStream in;
    private Socket socket;
    private Server server;
    public ClientHandler(Socket socket , Server server) throws IOException {
        this.socket = socket;
        this.out = new DataOutputStream(socket.getOutputStream());
        this.in = new DataInputStream(socket.getInputStream());
        this.server = server;
    }

    @Override
    public void run(){
        String clientAddress = socket.getInetAddress().getHostAddress();
        int nodeNumber = 1+(Math.abs(clientAddress.hashCode())%server.getClusterNodes().size());
        Node node = server.getClusterNodes().get(String.valueOf(nodeNumber));
        String nodeInfo;
        if(node.getAddress().equals("host.docker.internal"))
            nodeInfo = "127.0.0.1/"+node.getPortNumber();
        else
            nodeInfo =node.getAddress()+"/"+node.getPortNumber();

        try {
            String userName = in.readUTF();
            String password = in.readUTF();
            if (!isValid(userName , password)){
                out.writeUTF("Invalid username");
                out.flush();
                out.writeUTF("try again!");
                out.flush();
                out.close();
                socket.close();
                return;
            }
            out.writeUTF(nodeInfo);
            out.flush();
            out.writeUTF(generateToken(nodeNumber));
            out.flush();
            out.close();
            socket.close();
        } catch (IOException e) {
            return;
        }

    }


    private String generateToken(int nodeNumber){
        Map<String , Object> claims = new HashMap<>();
        claims.put("SENDER" , "CLIENT");
        claims.put("NODE_NUMBER" , nodeNumber);
        JWTController controller = new JWTController();
        return controller.createJWT(claims , server.getSECRET_KEY() ,864000000 );
    }


    public boolean isValid(String userName , String password){

        /***
         * There must be predefined users
         */
        if (userName.equals("admin")&& password.equals("admin"))
            return true;
        return false;
    }

}
