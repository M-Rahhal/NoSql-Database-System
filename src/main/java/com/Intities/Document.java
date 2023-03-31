package com.Intities;

import com.FileSystemManegers.DatabaseFileManager;
import com.FileSystemManegers.FileWriter;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Document {

    private Map<String , Object> properties;
    private String fileName;
    private String id;
    private String databaseName;
    private String jsonDocument;


    public Document(String databaseName , String id , String jsonDocument){
        Gson gson = new Gson();
        properties = gson.fromJson(jsonDocument , HashMap.class);
        this.databaseName =databaseName;
        this.id = id;
        this.fileName = (databaseName+"/"+id).intern();
        this.jsonDocument = jsonDocument;
    }

    public synchronized void updateProperty(String key  , String value) throws Exception {
        synchronized (fileName) {
            properties.put(key, value);
            jsonDocument = new Gson().toJson(properties);
            this.save();
        }
    }
    public synchronized void save() throws Exception {
        File file = new DatabaseFileManager().getDocumentFile(databaseName , id);
        FileWriter writer = new FileWriter();
        writer.writeFile(file , new Gson().toJson(properties));
    }
    public synchronized String readProperty(String key){
        return (String)properties.get(key);
    }

    public String getJsonDocument() {
        return jsonDocument;
    }
}
