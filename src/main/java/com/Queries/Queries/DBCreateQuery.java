package com.Queries.Queries;

//import com.Actions.CreateCollectionAction;
//import com.Actions.CreateDBConfigAction;
import com.Action.CreateCollectionAction;
import com.Action.CreateConfigFileAction;
import com.Intities.Configuration;
import com.Intities.Schema;

public class DBCreateQuery extends Query{
    private String collectionName;
    private Schema schema;
    public DBCreateQuery(String collectionName , Schema schema ){
        this.schema=schema;
        this.collectionName=collectionName;

        addActionToQueue(new CreateCollectionAction(collectionName));
        addActionToQueue(new CreateConfigFileAction(collectionName , new Configuration(schema)));

    }

}
