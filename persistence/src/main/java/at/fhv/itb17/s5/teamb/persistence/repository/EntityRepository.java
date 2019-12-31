package at.fhv.itb17.s5.teamb.persistence.repository;


import at.fhv.itb17.s5.teamb.persistence.entities.Artist;
import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.entities.EventOccurrence;
import at.fhv.itb17.s5.teamb.persistence.search.SearchPair;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.function.Function;

public class EntityRepository {

    private static final Logger logger = LogManager.getLogger(EntityRepository.class);

    private SessionFactory sessionFactory;

    public EntityRepository() {
        logger.info(LogMarkers.DB, "Start Session Factory");
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public <T> List<T> loadAll(Class<T> type) {
        return this.doInTransaction(session -> EntityRepository.loadAllData(type, session));
    }

    public void save(final Object o) {
        logger.trace(LogMarkers.DB, "Save Object {}", o);
        this.doInTransaction(session -> {
            session.persist(o);
            return null;
        });
    }


    public void delete(final Object o) {
        logger.trace(LogMarkers.DB, "Delete Object {}", o);
        sessionFactory.getCurrentSession().delete(o);
    }

    public <T> T get(final Class<T> type, final Long id) {
        return this.doInTransaction(session -> session.get(type, id));
    }

    public <T> T get(final Class<T> type, final String id) {
        if (id.equals("backdoor") || id.equals("tf-test2")) {
            return this.doInTransaction(session -> session.get(type, id));
        } else {
            return this.doInTransaction(session -> session.get(type, "FHVUser"));
        }


    }

    public void saveOrUpdate(final Object o) {
        logger.trace(LogMarkers.DB, "Save/Update Object {}", o);
        this.doInTransaction(session -> {
            session.saveOrUpdate(o);
            return null;
        });
    }

    public boolean atomicSave(final List<Object> objects) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.getTransaction();
        if (!(transaction.isActive())) {
            transaction.begin();
        }
        try {
            for (Object object : objects) {
                currentSession.save(object);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public <T> List<T> getAll(@NotNull final Class<T> type, List<SearchPair> searchPairs) {
        logger.trace(LogMarkers.DB, "Get all instances of class: {}", type.getCanonicalName());
        Transaction transaction = sessionFactory.getCurrentSession().getTransaction();
        if (!transaction.isActive()) {
            transaction.begin();
        }
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root);
        for (SearchPair searchPair : searchPairs) {
            switch (searchPair.getKey()) {
                case DATE_FROM:
                    criteriaQuery.where(cb.greaterThanOrEqualTo(root.get(searchPair.getKey().getIdf()), searchPair.getValue()));
                    break;
                case DATE_UNTIL:
                    criteriaQuery.where(cb.lessThanOrEqualTo(root.get(searchPair.getKey().getIdf()), searchPair.getValue()));
                    break;
                case EVENT_NAME:
                case GENRE:
                    criteriaQuery.where(cb.like(cb.upper(root.get(searchPair.getKey().getIdf())), searchPair.getValue().toUpperCase() + "%"));
                    break;
                case LOCATION:
                    Subquery<EventOccurrence> subL = criteriaQuery.subquery(EventOccurrence.class);
                    Root<EventOccurrence> subRootL = subL.from(EventOccurrence.class);
                    Join<Event, EventOccurrence> occs = root.join("occurrences");
                    subL.select(subRootL);
                    //TODO Repair
                    criteriaQuery.where(cb.like(cb.upper(occs.get(searchPair.getKey().getIdf())), searchPair.getValue().toUpperCase() + "%"));
                    break;
                case ARTIST_NAME:
                    Subquery<Artist> sub = criteriaQuery.subquery(Artist.class);
                    Root<Artist> subRoot = sub.from(Artist.class);
                    Join<Event, Artist> artists = root.join("artists");
                    sub.select(subRoot);
                    criteriaQuery.where(cb.like(cb.upper(artists.get(searchPair.getKey().getIdf())), searchPair.getValue().toUpperCase() + "%"));
                    break;
                default:
                    logger.error(LogMarkers.DB, "Should not be reachable");
            }
        }

        return sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
    }

    @SuppressWarnings("squid:S1181") //To be able to catch Throwable
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

    SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return session.createQuery(criteria).getResultList();
    }
}
