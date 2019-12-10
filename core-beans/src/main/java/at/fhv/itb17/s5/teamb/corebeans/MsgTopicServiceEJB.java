package at.fhv.itb17.s5.teamb.corebeans;

import at.fhv.itb17.s5.teamb.core.controllers.general.ClientSessionRMI;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.controllers.general.MsgTopicService;
import at.fhv.itb17.s5.teamb.core.controllers.rmi.MsgTopicServiceRMI;
import at.fhv.itb17.s5.teamb.core.domain.msg.MsgServiceCore;
import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.ClientRole;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MsgTopicServiceEJB implements MsgTopicService {
    private static final Logger logger = LogManager.getLogger(MsgTopicServiceEJB.class);

    private final ClientSessionRMI client;
    private final MsgServiceCore topicService;
    private final EntityDTORepo entityDTORepo;

    List<String> feeds = new LinkedList<>(Collections.singletonList("https://www.ots.at/rss/kultur"));

    public MsgTopicServiceEJB(MsgServiceCore topicService, ClientSessionRMI client, EntityDTORepo entityDTORepo) {
        this.topicService = topicService;
        this.entityDTORepo = entityDTORepo;
        this.client = client;
    }

    @Override
    public List<MsgTopicDTO> getAllTopics() {
        return entityDTORepo.toMsgTopicDTOs(topicService.getAllTopics());
    }

    @Override
    public boolean publishMsg(MsgTopicDTO msgTopicDTO, String header, String body) {
        MsgTopic msgTopic = entityDTORepo.toMsgTopic(msgTopicDTO);
        return topicService.createMessage(msgTopic, header, body);
    }

    @Override
    public boolean mayPublish() {
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
    public List<MsgTopic> getSubscribedTopics() {
        List<MsgTopic> topics = new LinkedList<>();
        if (client != null) {
            topics = client.getClient().getSubscribedTopics();
        }
        return topics;
    }

    @Override
    public boolean publishFromFeed(MsgTopicDTO msgTopicDTO, String feedURL) {
        //TODO Implement
        return true;
    }

    @Override
    public List<String> getRSSFeedURLs() {
        return feeds.stream().map(s -> new String(s)).collect(Collectors.toList());
    }
}
