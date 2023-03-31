package com.Action;

import com.FileSystemManegers.DatabaseFileManager;
import com.FileSystemManegers.FileWriter;
import com.Intities.Indexing;
import com.Control.IndexingController;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.util.*;

public class CreateIndexAction extends Action{
    private String indexingParam;
    private DatabaseFileManager manager;
    private IndexingController pool;


    public CreateIndexAction(String databaseName , String indexingParam) {
        super(databaseName);
        this.indexingParam=indexingParam;
        manager = new DatabaseFileManager();
        this.pool = IndexingController.getInstance();

    }

    @Override
    public String doAction() throws Exception{
        String operationName = (getDatabaseName()+"/"+indexingParam);
        synchronized (operationName) {
            manager.createIndexingFile(getDatabaseName(), indexingParam);
        }
        File[] docs = manager.listDocuments(getDatabaseName());

        TreeMap<String , LinkedList<String>> indexing = new TreeMap<>();
        Gson gson = new Gson();
        for (File file : docs){
            Map<String, Object> map = gson.fromJson(new FileReader(file) , HashMap.class);
            String id = (String)map.get("id");
            String index = (String)map.get(indexingParam);
            if (!indexing.containsKey(index))
                indexing.put(index, new LinkedList<>());
            indexing.get(index).add(id);
        }

        FileWriter fileWriter = new FileWriter();
        fileWriter.writeFile(manager.getIndexingFile(getDatabaseName() , indexingParam) ,gson.toJson(indexing));

        Indexing indexObject = new Indexing(manager.getIndexingFile(getDatabaseName() , indexingParam));
        pool.addIndexingObjectToThePool(getDatabaseName() , indexingParam , indexObject);
        return "OK";


    }



}
