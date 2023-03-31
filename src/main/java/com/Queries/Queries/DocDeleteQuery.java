package com.Queries.Queries;


import com.Action.DeleteDocAction;
import com.Action.UpdateIndexAction;
import com.Queries.QueriesManegers.QueryExecutor;

public class DocDeleteQuery extends Query{
    public DocDeleteQuery(String collectionName , String docID ){
        addActionToQueue(new UpdateIndexAction(collectionName , true , docID));
        addActionToQueue(new DeleteDocAction(collectionName , docID));
    }
}
