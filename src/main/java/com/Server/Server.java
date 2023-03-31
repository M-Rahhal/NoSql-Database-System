package com.Server;

import com.Server.Handlers.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Server {

    private int nodeNumber;
    private final int PORT_NUMBER;
    private final String SECRET_KEY;

    private boolean isInitialized = false;

    private Map<String , Node> otherNodes;
    private ServerSocket socket;
    private boolean writeAffinity;
    private Node writerNode;


    public Server() throws IOException {


        Map<String , String> environments = System.getenv();
        otherNodes = new HashMap<>();
        this.nodeNumber = Integer.parseInt(environments.get("NODE_NUMBER"));
        this.PORT_NUMBER = Integer.parseInt(environments.get("PORT_NUMBER"));
        this.SECRET_KEY = environments.get("SECRET_KEY");
        this.socket = new ServerSocket(PORT_NUMBER);

    }

    public void setOtherNodes(Map<String , Node> nodes){
        this.otherNodes = nodes;
    }
    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }
    public String getSECRET_KEY() {
        return SECRET_KEY;
    }



    public static void main(String[] args) {

        try {

            Server server = new Server();

            while (true) {

                while (!server.isInitialized()) {

                    System.out.println("Waiting for Bootstrap node...");
                    Socket bootstrap = server.socket.accept();



                    System.out.println("Bootstrap node connected!");
                    System.out.println("Initializing...");

                    BootstrapNodeHandler bootstrapNodeHandler = new BootstrapNodeHandler(server , bootstrap);
                    bootstrapNodeHandler.handle();
                    server.setInitialized(bootstrapNodeHandler.isInitializedSuccessfully());
                    if (!server.isInitialized)
                        continue;
                    System.out.println("Bootstrap ended!");
                }

                System.out.println("Waiting for clients...");
                Socket socket = server.socket.accept();
                GeneralHandler handler = new GeneralHandler( server ,socket);
                System.out.println("Client connected!");
                handler.start();

            }
        }
        catch (IOException e) {
                e.printStackTrace();
        }


    }

    public void setWriteAffinity(boolean writeAffinity) {
        this.writeAffinity = writeAffinity;
    }

    public boolean hasWriteAffinity() {
        return writeAffinity;
    }

    public Map<String, Node> getOtherNodes() {
        return otherNodes;
    }



    public Node getWriterNode() {
        return writerNode;
    }

    public void setWriterNode(Node writerNode) {
        this.writerNode = writerNode;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }
}
