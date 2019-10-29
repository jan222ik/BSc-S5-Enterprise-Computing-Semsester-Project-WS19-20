package at.fhv.itb17.s5.teamb.persistence.util;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.predicate.ComparisonPredicate;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WhereClauseBuilder {

    private List<WhereClause> clauses;

    public WhereClauseBuilder() {
        clauses = new LinkedList<>();
    }

    public WhereClauseBuilder equals(String attribute, Comparable value){
        clauses.add(new WhereClause(attribute, WhereClause.WhereOperator.equals, value));
        return this;
    }

    public WhereClauseBuilder greater(String attribute, Comparable value){
        clauses.add(new WhereClause(attribute, WhereClause.WhereOperator.greater, value));
        return this;
    }

    public WhereClauseBuilder greaterThan(String attribute, Comparable value){
        clauses.add(new WhereClause(attribute, WhereClause.WhereOperator.greaterThan, value));
        return this;
    }

    public WhereClauseBuilder smaller(String attribute, Comparable value){
        clauses.add(new WhereClause(attribute, WhereClause.WhereOperator.smaller, value));
        return this;
    }

    public WhereClauseBuilder smallerThan(String attribute, Comparable value){
        clauses.add(new WhereClause(attribute, WhereClause.WhereOperator.smallerThan, value));
        return this;
    }

    public WhereClauseBuilder notEquals(String attribute, Comparable value){
        clauses.add(new WhereClause(attribute, WhereClause.WhereOperator.notEquals, value));
        return this;
    }

    public WhereClauseBuilder like(String attribute, Comparable value){
        clauses.add(new WhereClause(attribute, WhereClause.WhereOperator.like, value));
        return this;
    }

    public Predicate[] createPredicate(Root root){
        CriteriaBuilderImpl criteriaBuilder = new CriteriaBuilderImpl(null);
        List<Predicate> predicates = new LinkedList<>();
        for (WhereClause clause : clauses) {
            switch (clause.getOperator()) {
                case equals:
                    predicates.add(criteriaBuilder.equal(root.get(clause.getAttributeName()), clause.getValue()));
                    break;
                case greater:
                    predicates.add(criteriaBuilder.ge(root.get(clause.getAttributeName()), (Number) clause.getValue()));
                    break;
                case greaterThan:
                    predicates.add(criteriaBuilder.gt(root.get(clause.getAttributeName()), (Number) clause.getValue()));
                    break;
                case smaller:
                    predicates.add(criteriaBuilder.le(root.get(clause.getAttributeName()), (Number) clause.getValue()));
                    break;
                case smallerThan:
                    predicates.add(criteriaBuilder.lt(root.get(clause.getAttributeName()), (Number) clause.getValue()));
                    break;
                case notEquals:
                    predicates.add(criteriaBuilder.notEqual(root.get(clause.getAttributeName()), clause.getValue()));
                    break;
                case like:
                    predicates.add(criteriaBuilder.like(root.get(clause.getAttributeName()), (String) clause.getValue()));
                    break;
            }
        }
        predicates.get(0).getOperator();

        return predicates.toArray(new Predicate[0]);
    }
}
