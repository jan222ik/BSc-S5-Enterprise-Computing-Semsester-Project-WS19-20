package at.fhv.itb17.s5.teamb.persistence.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.function.Function;
import java.util.function.Supplier;

public class HibernateUtils {

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static <T> T doInTransaction(Function<Session, T> supplier){
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.getTransaction();
        if(!(transaction.isActive())){
            transaction.begin();
        }
        try {
            T result = supplier.apply(currentSession);
            transaction.commit();
            return result;
        } catch (Throwable e){
            e.printStackTrace();
            transaction.rollback();
        }
        return null;
    }
}
