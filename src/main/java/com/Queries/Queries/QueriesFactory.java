package com.Queries.Queries;

import com.Intities.Schema;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class QueriesFactory {
    public Query getInstance(QueriesTypes type , String query) throws Exception {
        switch (type){
            case CREATE_DATABASE: return getDataBaseCreateQueryInstance(query);
            case DELETE_DATABASE: return getDataBaseDeleteQueryInstance(query);
            case CREATE_DOCUMENT: return getDocumentCreateQueryInstance(query);
            case DELETE_DOCUMENT: return getDocumentDeleteQueryInstance(query);
            case READE_DOCUMENT:  return getDocumentReadQueryInstance(query);
            case UPDATE_DOCUMENT: return getDocumentUpdateQueryInstance(query);
            case CREATE_INDEX:    return getIndexCreateQueryInstance(query);
        }
        throw new Exception("Invalid Query type");
    }

    private Query getDataBaseCreateQueryInstance(String query  ){
        String[] words = query.split(" ");
        String databaseName = words[2];
        String[] schema = words[3].split(",");
        return new DBCreateQuery(databaseName , new Schema(schema) );
    }

    private Query getDataBaseDeleteQueryInstance(String query ){
        String[] words = query.split(" ");
        String databaseName = words[2];
        return new DBDeleteQuery(databaseName);
    }

    private Query getDocumentCreateQueryInstance(String query   ){
        Gson gson = new Gson();
        String[] words = query.split(" ");
        String databaseName = words[3];
        Map<String , String> map = new HashMap<>();
        String[] properties = words[4].split(",");
        for (String s : properties)
            map.put(s.split("=")[0] , s.split("=")[1]);
        return new DocCreateQuery(databaseName , gson.toJson(map));
    }

    private Query getDocumentDeleteQueryInstance(String query ){
        String[] words = query.split(" ");
        String databaseName = words[3];
        String id = words[4];
        return new DocDeleteQuery(databaseName , id);
    }

    private Query getDocumentReadQueryInstance(String query  ){
        String[] words = query.split(" ");
        String databaseName = words[3];
        String id = words[4];
        return new DocReadQuery(databaseName , id);
    }

    private Query getDocumentUpdateQueryInstance(String query ){
        String[] words = query.split(" ");
        String databaseName = words[3];
        String id = words[4];
        String propertyName = words[5].split("=")[0];
        String propertyValue = words[5].split("=")[1];
        return new DocUpdateQuery(databaseName , propertyName , propertyValue , id );
    }

    private Query getIndexCreateQueryInstance(String query ){
        String[] words = query.split(" ");
        String databaseName = words[3];
        String indexingName = words[4];
        return new IndexCreateQuery(databaseName,indexingName);
    }
}
