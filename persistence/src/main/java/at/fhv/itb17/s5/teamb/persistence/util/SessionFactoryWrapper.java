package at.fhv.itb17.s5.teamb.persistence.util;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;

public class SessionFactoryWrapper {
    private SessionFactory sessionFactory;

    public SessionFactoryWrapper() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public Session getCurrentSession() throws HibernateException {
        Session session = sessionFactory.getCurrentSession();
        if(!session.isOpen()){
            session = sessionFactory.openSession();
        }
        return session;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return sessionFactory.getCriteriaBuilder();
    }
}
