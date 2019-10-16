package at.fhv.itb17.s5.teamb.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class DatabaseMain {

    private static final Logger logger = LogManager.getLogger(DatabaseMain.class);

    public static void main(String[] args) {
        DatabaseConnector connector = DatabaseConnector.INSTANCE;
        logger.info("Connection: {}", connector.hasConnection());
        try(Session session = connector.createSession()) {
            List<?> resultList = session.createSQLQuery("SELECT * FROM test").getResultList();
            resultList.forEach(logger::info);
        } catch (HibernateException e) {
            logger.error("Database Error", e);
        }

    }
}
