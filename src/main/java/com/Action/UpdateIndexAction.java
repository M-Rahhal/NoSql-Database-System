package com.Action;

import com.FileSystemManegers.DatabaseFileManager;
import com.Intities.Document;
import com.Intities.Indexing;
import com.Control.IndexingController;

import java.io.File;

public class UpdateIndexAction extends Action{
    private boolean isDeletion;
    private DatabaseFileManager manager;
    private String id;
    private String databaseName;
    private IndexingController controller;
    public UpdateIndexAction(String databaseName , boolean isDeletion , String id) {

        super(databaseName);
        this.isDeletion=isDeletion;
        this.manager=new DatabaseFileManager();
        this.id=id;
        this.databaseName=databaseName;
        this.controller = IndexingController.getInstance();
    }

    @Override
    public String doAction() throws Exception {
        if (isDeletion)
            return doDeletion();
        return doAddition();
    }

    private String doAddition() throws Exception {
        String jsonDocument = new ReadDocAction(databaseName , id).doAction();
        Document document = new Document(databaseName , id , jsonDocument);

        File[] files = manager.listIndexingFiles(getDatabaseName());
        for (File file: files){
            String indexingParam = file.getName().split("[.]")[0];
            Indexing indexing = controller.getIndexingFile(databaseName , indexingParam);
            String indexValue = document.readProperty(indexingParam);
            indexing.addToTheTree(indexValue , id);
        }

        return "OK";
    }

    private String doDeletion() throws Exception {
        String jsonDocument = new ReadDocAction(databaseName , id).doAction();
        Document document = new Document(databaseName , id , jsonDocument);

        File[] files = manager.listIndexingFiles(getDatabaseName());
        for (File file: files){
            String indexingParam = file.getName().split("[.]")[0];
            Indexing indexing = controller.getIndexingFile(databaseName , indexingParam);
            String indexValue = document.readProperty(indexingParam);
            indexing.removeFromTheTree(indexValue ,id);
        }
        return "OK";
    }



}

