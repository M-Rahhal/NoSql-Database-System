package com.Action;

import com.FileSystemManegers.DatabaseFileManager;
import com.Control.IndexingController;

public class DeleteCollectionAction extends Action{
    private DatabaseFileManager manager;
    private IndexingController controller;
    public DeleteCollectionAction(String databaseName ) {
        super(databaseName);
        this.manager = new DatabaseFileManager();
        this.controller = IndexingController.getInstance();
    }

    @Override
    public String doAction() throws Exception {
        manager.deleteDataBase(getDatabaseName());
        controller.deleteDatabaseFromThePool(getDatabaseName());
        return "OK";
    }
}
