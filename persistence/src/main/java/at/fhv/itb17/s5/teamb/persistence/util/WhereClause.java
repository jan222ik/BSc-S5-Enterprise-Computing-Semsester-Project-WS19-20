package at.fhv.itb17.s5.teamb.persistence.util;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;

public class WhereClause {

    public enum WhereOperator{
        equals, greater, greaterThan, smaller, smallerThan, notEquals, like
    }

    private String attributeName;
    private WhereOperator operator;
    private Object value;

    public WhereClause(String attributeName, WhereOperator operator, Object value) {
        this.attributeName = attributeName;
        this.operator = operator;
        this.value = value;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public WhereOperator getOperator() {
        return operator;
    }

    public void setOperator(WhereOperator operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
