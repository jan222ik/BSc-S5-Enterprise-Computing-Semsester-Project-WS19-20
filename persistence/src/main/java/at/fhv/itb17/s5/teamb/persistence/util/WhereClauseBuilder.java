package at.fhv.itb17.s5.teamb.persistence.util;

import at.fhv.itb17.s5.teamb.persistence.search.SearchCategories;
import at.fhv.itb17.s5.teamb.persistence.search.SearchPair;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class WhereClauseBuilder {

    private static final Logger logger = LogManager.getLogger(WhereClauseBuilder.class);
    private List<WhereClause> clauses;

    public WhereClauseBuilder() {
        clauses = new LinkedList<>();
    }

    @SuppressWarnings("squid:S2629")
    public WhereClauseBuilder equals(String attribute, @SuppressWarnings("rawtypes") Comparable value) {
        clauses.add(new WhereClause(attribute, WhereClause.WhereOperator.equals, value));
        logger.info(LogMarkers.DB, String.format("Whereclause \"equals\" (%s, %s) added", attribute, value.toString()));
        return this;
    }

    @SuppressWarnings("squid:S2629")
    public WhereClauseBuilder greater(String attribute, @SuppressWarnings("rawtypes") Comparable value) {
        clauses.add(new WhereClause(attribute, WhereClause.WhereOperator.greater, value));
        logger.info(LogMarkers.DB, String.format("Whereclause \"greater\" (%s, %s) added", attribute, value.toString()));
        return this;
    }

    @SuppressWarnings({"UnusedReturnValue", "squid:S2629"})
    public WhereClauseBuilder greaterThan(String attribute, @SuppressWarnings("rawtypes") Comparable value) {
        clauses.add(new WhereClause(attribute, WhereClause.WhereOperator.greaterThan, value));
        logger.info(LogMarkers.DB, String.format("Whereclause \"greater than\" (%s, %s) added", attribute, value.toString()));
        return this;
    }

    @SuppressWarnings("squid:S2629")
    public WhereClauseBuilder smaller(String attribute, @SuppressWarnings("rawtypes") Comparable value) {
        clauses.add(new WhereClause(attribute, WhereClause.WhereOperator.smaller, value));
        logger.info(LogMarkers.DB, String.format("Whereclause \"smaller\" (%s, %s) added", attribute, value.toString()));
        return this;
    }

    @SuppressWarnings({"UnusedReturnValue", "squid:S2629"})
    public WhereClauseBuilder smallerThan(String attribute, @SuppressWarnings("rawtypes") Comparable value) {
        clauses.add(new WhereClause(attribute, WhereClause.WhereOperator.smallerThan, value));
        logger.info(LogMarkers.DB, String.format("Whereclause \"smaller than\" (%s, %s) added", attribute, value.toString()));
        return this;
    }

    @SuppressWarnings("squid:S2629")
    public WhereClauseBuilder notEquals(String attribute, @SuppressWarnings("rawtypes") Comparable value) {
        clauses.add(new WhereClause(attribute, WhereClause.WhereOperator.notEquals, value));
        logger.info(LogMarkers.DB, String.format("Whereclause \"not equals\" (%s, %s) added", attribute, value.toString()));
        return this;
    }

    @SuppressWarnings({"UnusedReturnValue", "squid:S2629"})
    public WhereClauseBuilder like(String attribute, @SuppressWarnings("rawtypes") Comparable value) {
        clauses.add(new WhereClause(attribute, WhereClause.WhereOperator.like, value));
        logger.info(LogMarkers.DB, String.format("Whereclause \"like\" (%s, %s) added", attribute, value.toString()));
        return this;
    }

    public WhereCondition build() {
        return new WhereCondition(clauses);
    }

    public WhereCondition build(List<SearchPair> searchPairs) {
        for (SearchPair searchPair : searchPairs) {
            addClause(searchPair);
        }
        return new WhereCondition(clauses);
    }

    private void addClause(SearchPair searchPair) {
        SearchCategories searchCategory = searchPair.component1();
        switch (searchCategory) {
            case DATE_FROM:
                greaterThan(searchCategory.getIdf(), searchPair.getValue());
                break;
            case DATE_UNTIL:
                smallerThan(searchCategory.getIdf(), searchPair.getValue());
                break;
            case EVENT_NAME:
            case ARTIST_NAME:
            case GENRE:
            case LOCATION:
                like(searchCategory.getIdf(), searchPair.getValue());
                break;
        }
    }


}
