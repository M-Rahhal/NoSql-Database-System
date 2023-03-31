package com.Action;

import com.FileSystemManegers.DatabaseFileManager;
import com.FileSystemManegers.FileReader;
import com.google.gson.Gson;

import java.io.File;

public class ReadConfigFileAction extends Action{

    public ReadConfigFileAction(String databaseName){
        super(databaseName);
    }
    @Override
    public String doAction() throws Exception {
        Gson gson = new Gson();
        FileReader reader = new FileReader();
        String  config = reader.readFileAsString(new DatabaseFileManager().getConfigFile(getDatabaseName()));
        return config;
    }
}
