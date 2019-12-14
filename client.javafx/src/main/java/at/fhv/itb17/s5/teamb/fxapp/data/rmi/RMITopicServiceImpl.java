package at.fhv.itb17.s5.teamb.fxapp.data.rmi;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgTopicService;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class RMITopicServiceImpl implements MsgTopicService {

    private static final Logger logger = LogManager.getLogger(RMITopicServiceImpl.class);
    private static final String RMI_ERROR_MSG = "RMI Remote Exception";
    private RMIController rmi;
    private at.fhv.itb17.s5.teamb.core.controllers.general.MsgTopicService msgTopicService;

    @SuppressWarnings({"RedundantThrows", "squid:RedundantThrowsDeclarationCheck"})
    public RMITopicServiceImpl(RMIController rmi) throws RemoteException {
        this.rmi = rmi;
    }


    @Override
    public RMIConnectionStatus doLoginMsgTopic(String username, String password) {
        try {
            msgTopicService = rmi.createMsgTopicService(username, password);
            if (msgTopicService != null) {
                return RMIConnectionStatus.CONNECTED;
            } else {
                return RMIConnectionStatus.CREDENTIALS_INVALID;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            logger.error(RMI_ERROR_MSG);
        }
        return RMIConnectionStatus.NO_CONNECTION;
    }

    @Override
    public void logout() {
        msgTopicService = null;
    }

    @Override
    @Nullable
    @SuppressWarnings("squid:S1168")
    public List<MsgTopicDTO> getAll() {
        logger.info("Get all Topics");
        if (msgTopicService != null) {
            try {
                return msgTopicService.getAllTopics();
            } catch (RemoteException e) {
                e.printStackTrace();
                logger.error(RMI_ERROR_MSG);
                return new LinkedList<>();
            }
        } else {
            logger.fatal("Not logged in!");
            return null;
        }
    }

    @Override
    public boolean publishMsg(MsgTopicDTO msgTopicDTO, String header, String body) {
        if (msgTopicService != null) {
            try {
                return msgTopicService.publishMsg(msgTopicDTO, header, body);
            } catch (RemoteException e) {
                e.printStackTrace();
                logger.error(RMI_ERROR_MSG);
            }
        }
        return false;
    }

    @Override
    public boolean mayPublish() {
        if (msgTopicService != null) {
            try {
                return msgTopicService.mayPublish();
            } catch (RemoteException e) {
                e.printStackTrace();
                logger.error(RMI_ERROR_MSG);
            }
        }
        return false;
    }

    @Override
    public List<MsgTopic> getSubscribedTopics() {
        if (msgTopicService != null) {
            logger.info("Trying to get SubscribedTopics...");
            try {
                List<MsgTopic> subscribedTopics = msgTopicService.getSubscribedTopics();
                logger.info("SubscribedTopics size is {}", subscribedTopics.size());
                return subscribedTopics;
            } catch (RemoteException e) {
                logger.catching(e);
            }
        }
        return new LinkedList<>();
    }

    @Override
    public boolean publishFromFeed(MsgTopicDTO msgTopicDTO, String feedURL) {
        if (msgTopicService != null) {
            try {
                return msgTopicService.publishFromFeed(msgTopicDTO, feedURL);
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<String> getRSSFeedURLs() {
        try {
            return msgTopicService.getRSSFeedURLs();
        } catch (RemoteException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }
}
