package com.Queries.Queries;
import com.Action.DeleteCollectionAction;
import com.Queries.QueriesManegers.QueryExecutor;;

public class DBDeleteQuery extends Query{
    private String collectionName;

    public DBDeleteQuery(String collectionName ) {

        this.collectionName = collectionName;
        addActionToQueue(new DeleteCollectionAction(collectionName));

    }


}
