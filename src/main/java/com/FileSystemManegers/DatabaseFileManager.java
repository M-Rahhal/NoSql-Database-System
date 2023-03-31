package com.FileSystemManegers;


import java.io.File;
import java.io.FileNotFoundException;

public class DatabaseFileManager {

    private final String HOME_PATH ="/databases";

    public void deleteDocument(String collection , String id) throws Exception{
        String directory = HOME_PATH+"/"+collection+"/documents";
        if (!exists(directory))
            throw new Exception("could not find database!");
        if (!exists(directory+"/_" , id+".json"))
            throw new Exception("could not find file!");

        File file = new File(directory+"/_"+id+".json");
        if (!file.delete())
            throw new Exception("could not delete file"+file.toString()+"!");

    }

    private void deleteDirectoryWithContent(File directory) throws Exception {


        if (!directory.exists())
            throw new Exception("could not find database!");

        File[] files = directory.listFiles();
        for (File f: files){
            if (f.isDirectory())
                deleteDirectoryWithContent(f);
            if (f.isFile())
                f.delete();
            if (f.delete())
                throw new Exception("could not delete file "+f.toString()+"!");
        }
        directory.delete();
    }


    public void deleteDataBase(String database) throws Exception {
        File file = new File(HOME_PATH+"/"+database);
        deleteDirectoryWithContent(file);
    }

    public void createDatabase(String database) throws Exception {
        File file = new File(HOME_PATH+"/"+ database);
        if (file.exists())
            throw new Exception("DataBase name is already defined in the server");
        file.mkdirs();
    }
    public void createDirectoryInDatabase(String database , String directory) throws Exception {
        File file = new File(HOME_PATH+"/"+ database+"/"+directory);
        if (file.exists())
            throw new Exception("Folder is already defined in the database");
        file.mkdirs();
    }
    public void createDocument(String database , String id) throws Exception {
        database=HOME_PATH+"/"+database+"/documents";
        id="_"+id+".json";
        if (!exists(database))
            throw new Exception("could not find database!");
        if (exists(database , id))
            throw new Exception("ID is already defined");
        File file = new File(database+"/"+id);
        file.createNewFile();
    }
    public void createConfigFile(String directory) throws Exception {
        directory=HOME_PATH+"/"+directory;
        if (!exists(directory))
            throw new Exception("could not find database!");
        if (exists(directory , "config.json"))
            throw new Exception("file is already defined");
        File file = new File(directory+"/"+"config.json");
        file.createNewFile();
    }
    public void createIndexingFile(String database  ,  String indexingParam) throws Exception {
        String directory =HOME_PATH+"/"+database+"/indexing";
        if (!exists(directory))
            throw new Exception("could not find database!");
        if (exists(directory , indexingParam+".json"))
            throw new Exception("indexing is already defined");
        File file = new File(directory+"/"+indexingParam+".json");
        file.createNewFile();
    }
    public File[] listDocuments(String database) throws Exception {
        String directory = HOME_PATH+"/"+database;
        if (!exists(directory))
            throw new Exception("could not find database");
        directory+="/documents";
        File file  = new File(directory);
        return file.listFiles();
    }
    public File[] listIndexingFiles(String database) throws Exception {
        String directory = HOME_PATH+"/"+database;
        if (!exists(directory))
            throw new Exception("could not find database");
        directory+="/indexing";
        File file  = new File(directory);
        return file.listFiles();
    }

    public File[] listDatabases() throws Exception {
        String directory = HOME_PATH;
        File file  = new File(directory);
        return file.listFiles();
    }
    public File getDocumentFile(String database , String id) throws Exception {
        File file = new File(HOME_PATH+"/"+database+"/documents/_"+id+".json");
        if (!file.exists())
            throw new Exception("file not found");
        return file;
    }
    public File getConfigFile(String database) throws Exception {
        File file = new File(HOME_PATH+"/"+database+"/config.json");
        if (!file.exists())
            throw new Exception("file not found");
        return file;
    }
    public File getIndexingFile(String database , String indexing) throws Exception {
        File file = new File(HOME_PATH+"/"+database+"/indexing/"+indexing+".json");
        if (!file.exists())
            throw new Exception("file not found");
        return file;
    }
    private boolean exists(String directory){
        File file = new File(directory);
        return file.exists();
    }
    private boolean exists (String directory , String file){
        File f = new File(directory+"/"+file);
        return f.exists();
    }

}
