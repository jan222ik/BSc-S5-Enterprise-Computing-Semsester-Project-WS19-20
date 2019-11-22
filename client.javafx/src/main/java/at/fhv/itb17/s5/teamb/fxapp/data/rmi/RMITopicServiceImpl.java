package at.fhv.itb17.s5.teamb.fxapp.data.rmi;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgTopicService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class RMITopicServiceImpl implements MsgTopicService {

    private static final Logger logger = LogManager.getLogger(RMITopicServiceImpl.class);
    private RMIController rmi;
    private at.fhv.itb17.s5.teamb.core.controllers.general.MsgTopicService msgTopicService;

    public RMITopicServiceImpl(RMIController rmi) throws RemoteException {
        this.rmi = rmi;
    }


    @Override
    public RMIConnectionStatus doLoginMsgTopic(String username, String password) {
        try {
            msgTopicService = rmi.createMsgTopicService(username, password);
            if (msgTopicService != null) {
                return RMIConnectionStatus.CONNECTED;
            }else {
                return RMIConnectionStatus.CREDENTIALS_INVALID;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            logger.error("RMI Remote Exception");
        }
        return RMIConnectionStatus.NO_CONNECTION;
    }

    @Override
    public void logout() {
        msgTopicService = null;
    }

    @Override
    public List<MsgTopicDTO> getAll() {
        logger.info("Get all Topics");
        if (msgTopicService != null) {
            try {
                return msgTopicService.getAllTopics();
            } catch (RemoteException e) {
                e.printStackTrace();
                logger.error("RMI Remote Exception");
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
                logger.error("RMI Remote Exception");
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
                logger.error("RMI Remote Exception");
            }
        }
        return false;
    }
}
