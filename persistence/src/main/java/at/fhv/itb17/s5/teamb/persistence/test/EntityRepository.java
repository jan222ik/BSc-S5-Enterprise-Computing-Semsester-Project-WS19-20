package at.fhv.itb17.s5.teamb.persistence.test;


import at.fhv.itb17.s5.teamb.persistence.util.HibernateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.List;

public class EntityRepository {

    private static final Logger logger = LogManager.getLogger(EntityRepository.class);

    private SessionFactory sessionFactory;

    public EntityRepository() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public <T> T save(final T o, Class<T> clazz) {
        return clazz.cast(sessionFactory.getCurrentSession().save(o));
    }


    public void delete(final Object object) {
        sessionFactory.getCurrentSession().delete(object);
    }

    public <T> T get(final Class<T> type, final Long id) {
        return HibernateUtils.doInTransaction(session -> session.get(type, id));

    }

    public void saveOrUpdate(final Object o) {

        HibernateUtils.doInTransaction(session -> {
            session.saveOrUpdate(o);
            return null;
        });

    }

    public <T> List<T> getAll(final Class<T> type, Predicate... whereClauses) {

        Transaction transaction = sessionFactory.getCurrentSession().getTransaction();
        if (!transaction.isActive()) {
            transaction.begin();
        }
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        criteria.where(whereClauses);
        return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
    }
}
