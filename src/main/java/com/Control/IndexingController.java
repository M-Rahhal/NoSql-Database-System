package com.Control;

import com.FileSystemManegers.DatabaseFileManager;
import com.Intities.Indexing;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class IndexingController {
    private static IndexingController indexingPoolObject;
    private Map<String , Map<String  , Indexing>> allIndexingFiles;

    private IndexingController(){
        this.allIndexingFiles = new HashMap<>();
        try {
            raedAllIndexingFiles();
        }catch (Exception e){}
    }
    public synchronized static IndexingController getInstance(){
        if (IndexingController.indexingPoolObject ==null) {
            IndexingController.indexingPoolObject =new IndexingController();
        }
        return IndexingController.indexingPoolObject;
    }

    private void raedAllIndexingFiles() throws Exception {
        DatabaseFileManager manager = new DatabaseFileManager();
        File[] databases = manager.listDatabases();

        for (File file : databases){
            String databaseName = file.getName();
            for (File f2: manager.listIndexingFiles(databaseName)){
                Indexing obj  = new Indexing(f2);
                addIndexingObjectToThePool(databaseName , f2.getName().split("[.]")[0] , obj);
            }
        }
    }
    public void addIndexingObjectToThePool(String databaseName , String indexingName , Indexing indexing){
        if (allIndexingFiles.get(databaseName) == null)
            allIndexingFiles.put(databaseName , new HashMap<>());
        allIndexingFiles.get(databaseName).put(indexingName , indexing);
    }
    public void deleteDatabaseFromThePool(String databaseName){
        allIndexingFiles.remove(databaseName);

    }

    public Indexing getIndexingFile(String databaseName , String indexingName){
        return allIndexingFiles.get(databaseName).get(indexingName);
    }
}
