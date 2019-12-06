package at.fhv.itb17.s5.teamb.persistence.util;

public class WhereClause {

    @SuppressWarnings("squid:S00115")
    public enum WhereOperator{
        equals, greater, greaterThan, smaller, smallerThan, notEquals, like
    }

    private String attributeName;
    private WhereOperator operator;
    @SuppressWarnings("rawtypes")
    private Comparable value;

    public WhereClause(String attributeName, WhereOperator operator, @SuppressWarnings("rawtypes") Comparable value) {
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

    @SuppressWarnings("rawtypes")
    public Comparable getValue() {
        return value;
    }

    public void setValue(@SuppressWarnings("rawtypes") Comparable value) {
        this.value = value;
    }
}
