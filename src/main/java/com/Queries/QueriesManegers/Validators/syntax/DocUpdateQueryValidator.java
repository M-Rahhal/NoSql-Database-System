package com.Queries.QueriesManegers.Validators.syntax;

import java.util.HashMap;
import java.util.Map;

public class DocUpdateQueryValidator implements QueryValidator{

    private String[] queryAttributes;

    private Map< String , String > docProperties;

    public DocUpdateQueryValidator(String query) throws Exception {
        this.docProperties = new HashMap<>();
            this.queryAttributes = query.split(" ");
            if (queryAttributes[5].split("=").length != 2)
                throw new Exception("Syntax error , the update query only can update one property!");
            docProperties.put(queryAttributes[5].split("=")[0], queryAttributes[5].split("=")[0]);

    }

    private boolean isValidLength() {
        if (queryAttributes.length!=6)
            return false;
        if (docProperties.size()<1)
            return false;
        return true;
    }


    @Override
    public boolean isValidQuery() {
        if (!isValidLength())
            return false;
        if (!queryAttributes[0].equals("update"))
            return false;
        if (!queryAttributes[1].equals("document"))
            return false;
        if (!queryAttributes[2].equals("from"))
            return false;
        try {
            Integer.parseInt(queryAttributes[4]);
        }
        catch (NumberFormatException e){
            return false;
        }

        if (docProperties.keySet().contains("id"))
            return false;
        return true;
    }
}
