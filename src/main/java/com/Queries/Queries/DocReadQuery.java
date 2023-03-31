package com.Queries.Queries;

import com.Action.ReadDocAction;
import com.Queries.QueriesManegers.QueryExecutor;

public class DocReadQuery extends Query{
    public DocReadQuery(String collectionName , String id  ){

        addActionToQueue(new ReadDocAction(collectionName  , id));
    }

}
