package com.Action;


import com.FileSystemManegers.DatabaseFileManager;
import com.Intities.Document;


public class CreateDocFileAction extends Action {
    private String id;
    private DatabaseFileManager manager;
    private Document document;
    public CreateDocFileAction(String databaseName , String id) {
        super(databaseName);
        this.id=id;
        this.manager=new DatabaseFileManager();
        this.document = new Document(databaseName , id ,"{}");
    }

    @Override
    public String doAction() throws Exception{
        String operationName = (getDatabaseName()+"/"+id).intern();
        synchronized (operationName) {
            manager.createDocument(getDatabaseName(), id);
        }
        document.save();
        return "OK";
    }
}
