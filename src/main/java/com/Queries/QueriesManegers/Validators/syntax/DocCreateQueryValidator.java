package com.Queries.QueriesManegers.Validators.syntax;

import com.Action.Action;
import com.Action.ReadConfigFileAction;
import com.Intities.Configuration;
import com.Intities.Schema;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class DocCreateQueryValidator implements QueryValidator {

    private String[] queryAttributes;
    private Map< String , String > docProperties;
    private Schema schema;
    public DocCreateQueryValidator(String query) throws Exception {
        this.docProperties = new HashMap<>();
        try {
            this.queryAttributes = query.split(" ");
            for (String s : queryAttributes[4].split(",")) {
                if (s.split("=").length != 2)
                    throw new Exception("Syntax error , some properties are invalid");
                docProperties.put(s.split("=")[0], s.split("=")[1]);
            }
        }catch (Exception e){throw new Exception("Invalid Query");}
    }

    private boolean isValidLength() {
        if (queryAttributes.length!=5)
            return false;
        if (docProperties.size()<1)
            return false;
        return true;
    }
    private boolean isValidSchema(){
        Action action = new ReadConfigFileAction(queryAttributes[3]);
        try {
             this.schema= new Gson().fromJson(action.doAction() , Configuration.class).getSchema();
        } catch (Exception e) {
            return false;
        }
        if (docProperties.size()!=schema.getAttributes().length)
            return false;

        for (String s: schema.getAttributes())
            docProperties.put(s , "");

        if (docProperties.size()!=schema.getAttributes().length)
            return false;
        return true;
    }


    @Override
    public boolean isValidQuery() {
        if (!isValidLength())
            return false;
        if (!queryAttributes[0].equals("create"))
            return false;
        if (!queryAttributes[1].equals("document"))
            return false;
        if (!queryAttributes[2].equals("in"))
            return false;
        if (!docProperties.keySet().contains("id"))
            return false;
        try {
            Integer.parseInt(docProperties.get("id"));
        }
        catch (NumberFormatException e){
            return false;
        }
        if (!isValidSchema())
            return false;
        return true;
    }
}
