package com.Server.Handlers;

import com.Control.JWTController;
import com.Server.Server;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

import java.io.*;
import java.net.Socket;

public class GeneralHandler extends Thread implements RequestHandler{

    private Socket socket;
    private DataInputStream in;
    private JWTController jwtManager;
    private Server server;


    public GeneralHandler(Server server , Socket socket) throws IOException {
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.jwtManager = new JWTController();
        this.server = server;
    }


    @Override
    public void run() {
        try {
            handle();
        }
        catch (IOException e){}
    }

    @Override
    public void handle() throws IOException{
            String token = in.readUTF();
            Claims claims;


            try {
                claims = jwtManager.decodeJWT(token, server.getSECRET_KEY());
            } catch (SignatureException | ExpiredJwtException e) {
                System.out.println(e.getMessage());
                socket.close();
                return;
            }

            String sender = (String) claims.get("SENDER");
            if (sender.equals("READER_NODE")) {
                System.out.println("Handling write request...");
                WriteRequestHandler handler = new WriteRequestHandler(server , socket , claims);
                handler.handle();
                return;
            }
            else if (sender.equals("WRITER_NODE")) {
                System.out.println("Handling broadcasting...");

                BroadCastingHandler handler = new BroadCastingHandler(server , socket , claims);
                handler.handle();
                return;

            }
            else if (sender.equals("CLIENT")) {
                System.out.println("Handling client request...");

                ClientHandler handler = new ClientHandler(server , socket);
                handler.handle();
                return;
            }
            else {
                socket.close();
                return;
            }

    }
}
