package com.Action;

import com.FileSystemManegers.DatabaseFileManager;
import com.FileSystemManegers.FileReader;
import com.Intities.Document;
import com.google.gson.Gson;

public class ReadDocPropertyAction extends Action{
    private DatabaseFileManager manager;
    private FileReader reader;
    private String id;
    private String propertyName;
    public ReadDocPropertyAction(String databaseName , String id , String propertyName) {
        super(databaseName);
        this.id = id;
        this.propertyName = propertyName;
        this.manager = new DatabaseFileManager();
        this.reader = new FileReader();
    }

    @Override
    public String doAction() throws Exception {
        Gson gson = new Gson();

        String jsonDocument = reader.readFileAsString(manager.getDocumentFile(getDatabaseName() , id));
        Document document = new Document(getDatabaseName() , id , jsonDocument);

        return document.readProperty(propertyName);
    }
}
