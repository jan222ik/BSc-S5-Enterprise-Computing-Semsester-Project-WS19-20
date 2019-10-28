package at.fhv.itb17.s5.teamb.persistence.repository;


import at.fhv.itb17.s5.teamb.util.LogMarkers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.function.Function;

public class EntityRepository {

    private static final Logger logger = LogManager.getLogger(EntityRepository.class);

    private SessionFactory sessionFactory;

    public EntityRepository() {
        logger.info(LogMarkers.DB, "Start Session Factory");
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void save(final Object o) {
        logger.trace(LogMarkers.DB, "Save Object {}", o);
        this.doInTransaction(session -> {session.persist(o); return null;});
    }


    public void delete(final Object o) {
        logger.trace(LogMarkers.DB, "Delete Object {}", o);
        sessionFactory.getCurrentSession().delete(o);
    }

    public <T> T get(final Class<T> type, final Long id) {
        return this.doInTransaction(session -> session.get(type, id));

    }

    public void saveOrUpdate(final Object o) {
        logger.trace(LogMarkers.DB, "Save/Update Object {}", o);
        this.doInTransaction(session -> {
            session.saveOrUpdate(o);
            return null;
        });

    }

    public <T> List<T> getAll(@NotNull final Class<T> type, Predicate... whereClauses) {
        logger.trace(LogMarkers.DB, "Get all instances of class: {}", type.getCanonicalName());
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

    private <T> T doInTransaction(Function<Session, T> supplier) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.getTransaction();
        if (!(transaction.isActive())) {
            transaction.begin();
        }
        try {
            T result = supplier.apply(currentSession);
            transaction.commit();
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return null;
    }
}
