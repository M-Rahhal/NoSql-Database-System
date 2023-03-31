package com.Queries.Queries;


import com.Action.*;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class DocCreateQuery extends Query{

    public DocCreateQuery(String collectionName , String doc  ) {
        Gson gson = new Gson();
        Map<String ,String> map = gson.fromJson(doc , HashMap.class);
        String id  = map.get("id");
        addActionToQueue(new CreateDocFileAction(collectionName , id));
        addActionToQueue(new WriteDocAction(collectionName , id , gson.toJson(map) ));
        addActionToQueue(new UpdateIndexAction(collectionName , false , id));
    }
    public DocCreateQuery(String collectionName , String doc  , String id ) {

        addActionToQueue(new CreateDocFileAction(collectionName , id));
        addActionToQueue(new WriteDocAction(collectionName , id , doc ));
        addActionToQueue(new UpdateIndexAction(collectionName , false , id));
    }

}
