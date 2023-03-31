package com.Server.Handlers;

import com.Queries.Queries.Query;
import com.Queries.QueriesManegers.QueryExecutor;
import com.Server.Server;
import io.jsonwebtoken.Claims;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class BroadCastingHandler implements RequestHandler{

    private Socket socket;
    private Server server;
    private Claims claims;

    public BroadCastingHandler(Server server , Socket socket , Claims claims){
        this.socket = socket;
        this.server=server;
        this.claims = claims;
    }


    public void handle(){
            String query =(String) claims.get("QUERY");
            QueryExecutor executor = new QueryExecutor();
            System.out.println(executor.execute(query));
    }
}
