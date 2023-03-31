package com.Action;

import com.FileSystemManegers.DatabaseFileManager;


public class CreateCollectionAction extends Action{
    private DatabaseFileManager manager;

    public CreateCollectionAction(String databaseName){
        super(databaseName);
        this.manager=new DatabaseFileManager();
    }
    @Override
    public String doAction() throws Exception {
        String operationName = getDatabaseName().intern();
        synchronized (operationName) {
            manager.createDatabase(getDatabaseName());
        }
        manager.createDirectoryInDatabase(getDatabaseName(), "documents");
        manager.createDirectoryInDatabase(getDatabaseName() , "indexing");
        return "OK";
    }
}
