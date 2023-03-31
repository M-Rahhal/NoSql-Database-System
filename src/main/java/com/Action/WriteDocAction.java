package com.Action;

import com.FileSystemManegers.FileWriter;
import com.Intities.Document;

public class WriteDocAction extends Action{

    private String id;
    private String newDocument;
    private FileWriter writer;

    public WriteDocAction(String databaseName , String id , String newDocument) {
        super(databaseName);
        this.id=id;
        this.newDocument=newDocument;
        writer = new FileWriter();
    }

    @Override
    public String doAction() throws Exception{
        Document document = new Document(getDatabaseName() , id, newDocument);
        document.save();
        return id;
    }
}