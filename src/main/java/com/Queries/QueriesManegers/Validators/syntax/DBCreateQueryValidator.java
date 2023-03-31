package com.Queries.QueriesManegers.Validators.syntax;

public class DBCreateQueryValidator implements QueryValidator{
    private String[] queryAttributes;
    private String[] schemaAttributes;

    public DBCreateQueryValidator(String query) throws Exception {
        try {
            this.queryAttributes = query.split(" ");
            this.schemaAttributes = this.queryAttributes[3].split(",");
        }
        catch (Exception e){throw new Exception("Invalid Query");}
    }

    private boolean isValidLength() {
        if (queryAttributes.length!=4 || schemaAttributes.length<1)
            return false;
        return true;
    }


    @Override
    public boolean isValidQuery() {
        if (!isValidLength())
            return false;
        if (!queryAttributes[0].equals("create"))
            return false;
        if (!queryAttributes[1].equals("database"))
            return false;

        for (String s : schemaAttributes) {
            if (s.equals("id"))
                return true;
        }
        return false;
    }
}
