package at.fhv.itb17.s5.teamb.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DatabaseMain {

    private static final Logger logger = LogManager.getLogger(DatabaseMain.class);

    public static void main(String[] args) {
        DatabaseConnector connector = DatabaseConnector.INSTANCE;
        logger.info("Connection: {}", connector.hasConnection());
        List<?> resultList = connector.getSession().createSQLQuery("SELECT * FROM test").getResultList();
        resultList.forEach(logger::info);

    }
}
