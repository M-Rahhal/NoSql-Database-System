package com.Queries.Queries;

import com.Action.UpdateDocAction;
import com.Queries.QueriesManegers.QueryExecutor;

public class DocUpdateQuery extends Query{

    private String id;
    private String propertyName;
    private String propertyValue;
    private String databaseName;

    public DocUpdateQuery(String databaseName  , String propertyName , String propertyValue , String id){
        addActionToQueue(new UpdateDocAction(databaseName , propertyName , propertyValue , id));
        this.id=id;
        this.propertyName = propertyName;
        this.databaseName = databaseName;
        this.propertyValue = propertyValue;
    }


    public String getPropertyName() {
        return propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getId() {
        return id;
    }
}
