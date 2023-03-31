package com.Queries.Queries;

import com.Action.CreateIndexAction;
import com.Queries.QueriesManegers.QueryExecutor;

public class IndexCreateQuery extends Query{
    public IndexCreateQuery(String databaseName , String indexingParam){
        addActionToQueue(new CreateIndexAction(databaseName , indexingParam));
    }
}
