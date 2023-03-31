package com.Queries.Queries;



import com.Action.Action;
import com.Queries.QueriesManegers.QueryExecutor;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Query {
    private Queue<Action> actions;


    protected Query(){
        actions = new LinkedList<>();


    }

    protected void addActionToQueue(Action action){
        actions.add(action);
    }
    public Action getActionFromQueue(){return actions.remove();}
    public boolean isEmptyQueue(){
        return actions.isEmpty();
    }

    public String executeQuery() throws Exception {
        String message = "";
            while (!isEmptyQueue())
                message = getActionFromQueue().doAction();

        return message;
    }

}
