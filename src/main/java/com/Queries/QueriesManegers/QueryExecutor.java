package com.Queries.QueriesManegers;

import com.Queries.Queries.QueriesFactory;
import com.Queries.Queries.QueriesTypes;
import com.Queries.Queries.Query;
import com.Queries.QueriesManegers.Validators.syntax.QueryValidator;
import com.Queries.QueriesManegers.Validators.syntax.ValidatorsFactory;

public class QueryExecutor {
    private ValidatorsFactory validatorsFactory;
    private QueryFetcher fetcher;
    private QueriesFactory queriesFactory;



    public QueryExecutor(){
        this.queriesFactory = new QueriesFactory();
        this.fetcher = new QueryFetcher();
        this.validatorsFactory = new ValidatorsFactory();

    }

    public String execute(String query){
        try {
            QueriesTypes queryType = fetcher.fetch(query);

            QueryValidator validator = validatorsFactory.getInstance(queryType, query);


            if (!validator.isValidQuery()) {
                throw new Exception("Invalid query");
            }

            Query queryObject = queriesFactory.getInstance(queryType, query);
            return queryObject.executeQuery();
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String execute(Query query){
        try {
            return query.executeQuery();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
