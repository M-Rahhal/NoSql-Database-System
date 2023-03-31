package com.Queries.QueriesManegers;

import com.Queries.Queries.QueriesTypes;

public class QueryFetcher {

    public QueriesTypes fetch(String query) throws Exception {
        String queryType = query.split(" ")[0];
        String word = query.split(" ")[1];

        if (queryType.equals("create"))
            return fetchCreateQueries(word);
        if (queryType.equals("delete"))
            return fetchDeleteQueries(word);
        if (queryType.equals("update"))
            return fetchUpdateQueries(word);
        if (queryType.equals("read"))
            return fetchReadQueries(word);

        throw new Exception("Query type is not a CRUD");
    }


    private QueriesTypes fetchCreateQueries(String word) throws Exception {

        if (word.equals("database"))
            return QueriesTypes.CREATE_DATABASE;
        if (word.equals("document"))
            return QueriesTypes.CREATE_DOCUMENT;
        if (word.equals("index"))
            return QueriesTypes.CREATE_INDEX;

        throw new Exception("Invalid create query!");
    }

    private QueriesTypes fetchReadQueries(String word) throws Exception {

        if (word.equals("document"))
            return QueriesTypes.READE_DOCUMENT;

        throw new Exception("Invalid read query!");
    }

    private QueriesTypes fetchUpdateQueries(String word) throws Exception {

        if (word.equals("document"))
            return QueriesTypes.UPDATE_DOCUMENT;

        throw new Exception("Invalid update query!");

    }

    private QueriesTypes fetchDeleteQueries(String word) throws Exception {

        if (word.equals("database"))
            return QueriesTypes.DELETE_DATABASE;
        if (word.equals("document"))
            return QueriesTypes.DELETE_DOCUMENT;

        throw new Exception("Invalid delete query!");
    }

}
