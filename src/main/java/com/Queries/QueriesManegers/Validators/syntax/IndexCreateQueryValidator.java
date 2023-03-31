package com.Queries.QueriesManegers.Validators.syntax;

public class IndexCreateQueryValidator implements QueryValidator{
    private String[] queryAttributes;

    public IndexCreateQueryValidator(String query) throws Exception {
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
        if (!queryAttributes[0].equals("create"))
            return false;
        if (!queryAttributes[1].equals("index"))
            return false;
        if (!queryAttributes[2].equals("in"))
            return false;
        return true;
    }
}
