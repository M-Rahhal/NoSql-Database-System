package com.Action;

import com.Queries.QueriesManegers.QueryExecutor;

public abstract class Action {


    private String databaseName;

    public Action(String databaseName ){
        this.databaseName = databaseName.intern();
    }

    public abstract String doAction() throws Exception;

    public String getDatabaseName() {
        return databaseName;
    }


}
