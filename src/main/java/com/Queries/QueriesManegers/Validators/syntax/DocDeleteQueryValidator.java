package com.Queries.QueriesManegers.Validators.syntax;

public class DocDeleteQueryValidator implements QueryValidator {

    private String[] queryAttributes;

    public DocDeleteQueryValidator(String query) throws Exception {
        try {
            this.queryAttributes = query.split(" ");
        }catch (Exception e){throw new Exception("Invalid Query");}
    }

    private boolean isValidLength() {
        if (queryAttributes.length!=5)
            return false;
        return true;
    }


    @Override
    public boolean isValidQuery() {
        if (!isValidLength())
            return false;
        if (!queryAttributes[0].equals("delete"))
            return false;
        if (!queryAttributes[1].equals("document"))
            return false;
        if (!queryAttributes[2].equals("from"))
            return false;
        return true;
    }
}
