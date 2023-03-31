package com;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private final String SECRET_KEY;
    private final int PORT_NUMBER;

    private ServerSocket serverSocket;
    private String nodeWithWriteAffinity;

    private Map<String , Node> clusterNodes;



    public Server() throws UnknownHostException {
        Map<String , String> env = System.getenv();

        clusterNodes = new HashMap<>();

        PORT_NUMBER = Integer.parseInt(env.get("PORT_NUMBER"));
        SECRET_KEY = env.get("SECRET_KEY");
        prepareNodesInfo();

    }

    private void prepareNodesInfo(){
        Map<String , String> env = System.getenv();

        int numberOfNodes = Integer.parseInt(env.get("NUMBER_OF_NODES"));
        for (int i = 1 ; i<= numberOfNodes;i++){
            String nodeInfo = env.get("NODE_"+i);
            String nodeAddress= nodeInfo.split("/")[0];
            int nodePort = Integer.parseInt(nodeInfo.split("/")[1]);
            clusterNodes.put(String.valueOf(i) , new Node(nodeAddress , i , nodePort));
        }
        this.nodeWithWriteAffinity =env.get("WRITE_AFFINITY_NODE_NUMBER");
        clusterNodes.get(nodeWithWriteAffinity).setWriteAffinity(true);;

    }

    public void startServer() throws IOException {
        try {
            bootstrapNodes();
        }
        catch (ConnectException e){
            startServer();
        }
        this.serverSocket = new ServerSocket(PORT_NUMBER);
        while (true) {

            System.out.println("The server has started listening on port " + PORT_NUMBER);
            System.out.println("Waiting for clients...");
            ClientHandler handler = new ClientHandler(serverSocket.accept() , this);
            System.out.println("Client connected!");
            handler.start();
        }
    }

    private void bootstrapNodes() throws IOException , ConnectException {
        for (String s : clusterNodes.keySet()) {
            Node node = clusterNodes.get(s);
            String token;

            if (node.hasWriteAffinity())
                token = writeNodeToken(node);
            else token = readNodeToken(node);


            Socket socket = new Socket(node.getAddress() , node.getPortNumber());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(token);
            out.flush();
            out.close();
            socket.close();

        }
        return;
    }

    private String writeNodeToken(Node node){
        Map<String,Node> otherNodesInfo = new HashMap<>();
        for (int j = 1 ; j<= clusterNodes.size() ; j++){
            if (j== node.getNumber())
                continue;
            Node otherNode = clusterNodes.get(String.valueOf(j));
            otherNodesInfo.put(String.valueOf(otherNode.getNumber()) , otherNode);
        }

        Map<String , Object> claims = new HashMap<>();
        Gson gson = new Gson();

        String jsonMap = gson.toJson(otherNodesInfo);

        claims.put("SENDER" , "BOOTSTRAP_NODE");
        claims.put("NODE_NUMBER" , String.valueOf(node.getNumber()));
        claims.put("WRITE_AFFINITY" , "TRUE");
        claims.put("OTHER_NODES_INFO" , jsonMap);


        String token = new JWTController().createJWT(claims , SECRET_KEY , 120000);
        return token;
    }

    private String readNodeToken(Node node) {
        Gson gson = new Gson();
        Map<String , Object> claims = new HashMap<>();
        Node writerNode = clusterNodes.get(nodeWithWriteAffinity);
        claims.put("WRITER_NODE" ,gson.toJson(writerNode));
        claims.put("SENDER" , "BOOTSTRAP_NODE");
        claims.put("WRITE_AFFINITY" , "FALSE");
        claims.put("NODE_NUMBER" , node.getNumber());

        String token = new JWTController().createJWT(claims , SECRET_KEY , 120000);
        return token;
    }

    public Map<String, Node> getClusterNodes() {
        return clusterNodes;
    }

    public String getSECRET_KEY() {
        return SECRET_KEY;
    }

    public static void main(String[] args) throws IOException {
        Server server  = new Server();
        server.startServer();
    }
}
