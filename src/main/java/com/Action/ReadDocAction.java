package com.Action;

import com.FileSystemManegers.DatabaseFileManager;
import com.FileSystemManegers.FileReader;

public class ReadDocAction extends Action{
    private FileReader reader;
    private String id;
    public ReadDocAction(String databaseName , String id) {
        super(databaseName);
        this.id=id;
        this.reader=new FileReader();
    }

    @Override
    public String doAction() throws Exception {
        return reader.readFileAsString(new DatabaseFileManager().getDocumentFile(getDatabaseName() , id));
    }
}
