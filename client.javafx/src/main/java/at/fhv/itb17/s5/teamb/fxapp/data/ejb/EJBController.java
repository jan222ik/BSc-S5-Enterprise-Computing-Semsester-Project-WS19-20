package at.fhv.itb17.s5.teamb.fxapp.data.ejb;

import at.fhv.itb17.s5.teamb.core.controllers.general.BookingService;
import at.fhv.itb17.s5.teamb.core.controllers.general.IFrontEndClient;
import at.fhv.itb17.s5.teamb.core.controllers.general.MsgTopicService;
import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;
import at.fhv.itb17.s5.teamb.core.controllers.rmi.IConnectionFactoryRMI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Properties;

public class EJBController {
    private static final Logger logger = LogManager.getLogger(EJBController.class);

    private IConnectionFactoryRMI stub = null;
    private InitialContext initialContext;

    @SuppressWarnings("RedundantThrows")
    public EJBController() {
        try {
            initialContext = initContext();
        } catch (NamingException e) {
            logger.error("Failed to initialize Initial Context", e);
        }
    }

    private InitialContext initContext() throws NamingException {
        Properties jndiProps = new Properties();
        jndiProps.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProps.put("jboss.naming.client.ejb.context", true);
        jndiProps.put("java.naming.provider.url", "http-remoting://localhost:8080");
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
        if(initialContext != null) {
            MsgTopicService topicService = (MsgTopicService) initialContext.lookup("ejb:/core-beans-1.0-jar-with-dependencies/MsgTopicServiceEJB!at.fhv.itb17.s5.teamb.core.controllers.general.MsgTopicService");
            logger.debug("Created topicService = {}", topicService);
            return topicService;
        }
        return null;
    }
}
