package com.Server;

import com.Control.JWTController;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class BroadCaster {
    private Map<String , Node> nodesAddresses;

    public BroadCaster(Map<String , Node> nodesAddresses){
        this.nodesAddresses= nodesAddresses;
    }
    public void broadCast(String data) throws IOException {
        for (String s : nodesAddresses.keySet()) {
            String address = nodesAddresses.get(s).getAddress();
            int port = nodesAddresses.get(s).getPortNumber();
            Socket socket = new Socket(address , port);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(data);
            out.flush();
            out.close();
            socket.close();
        }
    }




}
