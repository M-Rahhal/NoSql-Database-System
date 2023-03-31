package com.Action;

import com.FileSystemManegers.DatabaseFileManager;

public class DeleteDocAction extends Action{

    private String id;
    private DatabaseFileManager manager;
    public DeleteDocAction(String databaseName , String id) {
        super(databaseName);
        this.id = id;
        this.manager = new DatabaseFileManager();
    }

    @Override
    public String doAction() throws Exception {
        manager.deleteDocument(getDatabaseName() , id);
        return "OK";
    }
}
