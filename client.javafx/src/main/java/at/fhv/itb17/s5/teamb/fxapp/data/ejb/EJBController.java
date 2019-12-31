package at.fhv.itb17.s5.teamb.fxapp.data.ejb;

import at.fhv.itb17.s5.teamb.core.controllers.general.BookingService;
import at.fhv.itb17.s5.teamb.core.controllers.general.IFrontEndClient;
import at.fhv.itb17.s5.teamb.core.controllers.general.MsgTopicService;
import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;
import at.fhv.itb17.s5.teamb.core.controllers.rmi.IConnectionFactoryRMI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Properties;

public class EJBController {
    private static final Logger logger = LogManager.getLogger(EJBController.class);

    private IConnectionFactoryRMI stub = null;
    private InitialContext initialContext;

    private InitialContext initContext(String host) throws NamingException {
        Properties jndiProps = new Properties();
        jndiProps.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProps.put("jboss.naming.client.ejb.context", true);
        jndiProps.put("java.naming.provider.url", "http-remoting://" + host + ":8081");
        jndiProps.put(Context.SECURITY_PRINCIPAL, "user");
        jndiProps.put(Context.SECURITY_CREDENTIALS, "user");
        return new InitialContext(jndiProps);
    }

    public SearchService createSearchService() throws NamingException {
        logger.info("EJB: Creating SearchService");
        if (initialContext != null) {
            SearchService searchService = (SearchService) initialContext.lookup("ejb:/core-beans-1.0-jar-with-dependencies/SearchServiceEJB!at.fhv.itb17.s5.teamb.core.controllers.general.SearchService");
            logger.info("EJB: Created SearchService");
            return searchService;
        } else {
            logger.info("EJB: ERROR CREATION SearchService");
            return null;
        }
    }

    public BookingService createBookingService(IFrontEndClient frontEndClient, String username, String password) throws NamingException {
        logger.info("EJB: Creating BookingService");
        if (initialContext != null) {
            BookingService bookingService = (BookingService) initialContext.lookup("ejb:/core-beans-1.0-jar-with-dependencies/BookingServiceEJB!at.fhv.itb17.s5.teamb.core.controllers.general.BookingService");
            logger.debug("Created bookingService = {}", bookingService);
            return bookingService;
        }
        return null;
    }

    public MsgTopicService createMsgTopicService(String username, String password) throws NamingException {
        logger.info("RMI: Creating MsgTopicService");
        if (initialContext != null) {
            MsgTopicService topicService = (MsgTopicService) initialContext.lookup("ejb:/core-beans-1.0-jar-with-dependencies/MsgTopicServiceEJB!at.fhv.itb17.s5.teamb.core.controllers.general.MsgTopicService");
            logger.debug("Created topicService = {}", topicService);
            try {
                topicService.setUserForEJB(username, password);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return topicService;
        }
        return null;
    }

    public boolean connect(String host) {
        try {
            initialContext = initContext(host);
            MsgTopicService topicService = (MsgTopicService) initialContext.lookup("ejb:/core-beans-1.0-jar-with-dependencies/MsgTopicServiceEJB!at.fhv.itb17.s5.teamb.core.controllers.general.MsgTopicService");
            topicService.getAllTopics();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
