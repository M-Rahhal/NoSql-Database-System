package com.Server;

import java.io.*;
import java.net.Socket;

public class Dispatcher{

    private String address;
    private int port;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    public Dispatcher(String address , int port) throws IOException {
        this.address = address;
        this.port=port;
        this.socket = new Socket(address , port);
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    public String forward(String data) throws IOException {
        out.writeUTF(data);
        out.flush();
        String response = in.readUTF();
        in.close();
        out.close();
        socket.close();
        return response;
    }



}
