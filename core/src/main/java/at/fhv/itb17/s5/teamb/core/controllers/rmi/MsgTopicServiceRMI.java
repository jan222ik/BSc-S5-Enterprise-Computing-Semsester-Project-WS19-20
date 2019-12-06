package at.fhv.itb17.s5.teamb.core.controllers.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.ClientSessionRMI;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.controllers.general.MsgTopicService;
import at.fhv.itb17.s5.teamb.core.domain.msg.MsgServiceCore;
import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.ClientRole;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("RedundantThrows")
public class MsgTopicServiceRMI extends UnicastRemoteObject implements MsgTopicService {

    private static final Logger logger = LogManager.getLogger(MsgTopicServiceRMI.class);

    private final ClientSessionRMI client;
    private final MsgServiceCore topicService;
    private final EntityDTORepo entityDTORepo;

    List<String> feeds = new LinkedList<>(Collections.singletonList("https://www.ots.at/rss/kultur"));

    public MsgTopicServiceRMI(MsgServiceCore topicService, ClientSessionRMI client, EntityDTORepo entityDTORepo) throws RemoteException {
        this.topicService = topicService;
        this.entityDTORepo = entityDTORepo;
        this.client = client;
    }

    @Override
    public List<MsgTopicDTO> getAllTopics() throws RemoteException {
        return entityDTORepo.toMsgTopicDTOs(topicService.getAllTopics());
    }

    @Override
    public boolean publishMsg(MsgTopicDTO msgTopicDTO, String header, String body) throws RemoteException {
        MsgTopic msgTopic = entityDTORepo.toMsgTopic(msgTopicDTO);
        return topicService.createMessage(msgTopic, header, body);
    }

    @Override
    public boolean mayPublish() throws RemoteException {
        Client role = client.getClient();
        if (role != null) {
            ClientRole clientRole = ClientRole.calcEffectiveRole(role.getRole());
            logger.info("clientRole = {}", clientRole);
            return clientRole.getMayWriteMsg();
        } else {
            return false;
        }
    }

    @Override
    public List<MsgTopic> getSubscribedTopics() throws RemoteException {
        List<MsgTopic> topics = new LinkedList<>();
        if (client != null) {
            topics = client.getClient().getSubscribedTopics();
        }
        return topics;
    }

    @Override
    public boolean publishFromFeed(MsgTopicDTO msgTopicDTO, String feedURL) throws RemoteException {
        //TODO Implement
        return true;
    }

    @Override
    public List<String> getRSSFeedURLs() throws RemoteException {
        return feeds.stream().map(s -> new String(s)).collect(Collectors.toList());
    }
}
