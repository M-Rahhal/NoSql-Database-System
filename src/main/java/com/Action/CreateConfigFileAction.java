package com.Action;

import com.FileSystemManegers.DatabaseFileManager;
import com.FileSystemManegers.FileWriter;
import com.Intities.Configuration;
import com.google.gson.Gson;

public class CreateConfigFileAction extends Action{
    private Configuration configuration;
    private DatabaseFileManager manager;

    public CreateConfigFileAction(String databaseName , Configuration configuration ){
        super(databaseName);
        this.configuration =configuration;
        this.manager=new DatabaseFileManager();
    }
    @Override
    public String doAction() throws Exception {
        Gson gson = new Gson();
        manager.createConfigFile(getDatabaseName());
        FileWriter writer = new FileWriter();
        writer.writeFile(manager.getConfigFile(getDatabaseName()), gson.toJson(configuration));
        return "OK";

    }
}