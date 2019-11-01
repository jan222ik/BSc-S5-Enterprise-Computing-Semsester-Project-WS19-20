package at.fhv.itb17.s5.teamb.persistence.util;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class WhereCondition {

    private final List<WhereClause> clauses;

    public WhereCondition(List<WhereClause> clauses) {
        this.clauses = clauses;
    }

    public Predicate[] createPredicate(Root root) {
        CriteriaBuilderImpl criteriaBuilder = new CriteriaBuilderImpl(null);
        List<Predicate> predicates = new LinkedList<>();
        for (WhereClause clause : clauses) {
            switch (clause.getOperator()) {
                case equals:
                    predicates.add(criteriaBuilder.equal(root.get(clause.getAttributeName()), clause.getValue()));
                    break;
                case greater:
                    predicates.add(criteriaBuilder.greaterThan(root.get(clause.getAttributeName()), clause.getValue()));
                    break;
                case greaterThan:
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(clause.getAttributeName()), clause.getValue()));
                    break;
                case smaller:
                    predicates.add(criteriaBuilder.lessThan(root.get(clause.getAttributeName()), clause.getValue()));
                    break;
                case smallerThan:
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(clause.getAttributeName()), clause.getValue()));
                    break;
                case notEquals:
                    predicates.add(criteriaBuilder.notEqual(root.get(clause.getAttributeName()), clause.getValue()));
                    break;
                case like:
                    predicates.add(criteriaBuilder.like(root.get(clause.getAttributeName()), (String) clause.getValue()));
                    break;
            }
        }

        return predicates.toArray(new Predicate[0]);
    }
}
