package at.fhv.itb17.s5.teamb.persistence.util;

public class WhereClause {

    public enum WhereOperator{
        equals, greater, greaterThan, smaller, smallerThan, notEquals, like
    }

    private String attributeName;
    private WhereOperator operator;
    private Comparable value;

    public WhereClause(String attributeName, WhereOperator operator, Comparable value) {
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

    public Comparable getValue() {
        return value;
    }

    public void setValue(Comparable value) {
        this.value = value;
    }
}
