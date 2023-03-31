package com.Queries.QueriesManegers.Validators.syntax;

public class DBDeleteQueryValidator implements QueryValidator{
    private String[] queryAttributes;
    public DBDeleteQueryValidator(String query) throws Exception {
        try {
            this.queryAttributes = query.split(" ");
        }catch (Exception e){throw new Exception("Invalid Query");}

    }

    private boolean isValidLength() {
        if (queryAttributes.length!=3)
            return false;
        return true;
    }


    @Override
    public boolean isValidQuery() {
        if (!isValidLength())
            return false;
        if (!queryAttributes[0].equals("delete"))
            return false;
        if (!queryAttributes[1].equals("database"))
            return false;

        return true;
    }
}
