package com.Action;

import com.Intities.Document;

public class UpdateDocAction extends Action{
    private String id;
    private String propertyName;
    private String propertyValue;

    public UpdateDocAction(String databaseName  , String propertyName , String propertyValue , String id) {
        super(databaseName);
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.id =id;
    }

    @Override
    public String doAction() throws Exception {

        String operationName = (getDatabaseName()+"/"+propertyName+"/"+id).intern();

        synchronized (operationName) {
            String doc = new ReadDocAction(getDatabaseName(), id).doAction();
            Document document = new Document(getDatabaseName(), id, doc);
            new UpdateIndexAction(getDatabaseName() , true , id).doAction();
            document.updateProperty(propertyName, propertyValue);
            new UpdateIndexAction(getDatabaseName() , false , id).doAction();
            return document.getJsonDocument();
        }
    }

}
