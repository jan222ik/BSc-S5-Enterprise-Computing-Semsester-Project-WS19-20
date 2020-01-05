package at.fhv.itb17.s5.teamb.corebeans;

import at.fhv.itb17.s5.teamb.core.controllers.general.ClientSessionRMI;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.controllers.general.MsgTopicService;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjector;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjectorImpl;
import at.fhv.itb17.s5.teamb.core.domain.msg.MsgServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.msg.rss.RSSReader;
import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.dtos.mapper.MsgTopicMapper;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.ClientRole;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.io.FeedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
public class MsgTopicServiceEJB implements MsgTopicService {
    private static final Logger logger = LogManager.getLogger(MsgTopicServiceEJB.class);

    private ClientSessionRMI client;
    private final MsgServiceCore topicService;
    private final EntityDTORepo entityDTORepo;

    List<String> feeds = new LinkedList<>(Collections.singletonList("https://www.ots.at/rss/kultur"));
    private CoreServiceInjector injector;

    public MsgTopicServiceEJB(MsgServiceCore topicService, ClientSessionRMI client, EntityDTORepo entityDTORepo) {
        this.topicService = topicService;
        this.entityDTORepo = entityDTORepo;
        this.client = client;
    }

    public MsgTopicServiceEJB() {
        injector = CoreServiceInjectorImpl.getInstance(true);
        this.topicService = injector.getMsgTopicServiceCore();
        this.entityDTORepo = injector.getEntityRepo();
        client = null;
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
        if(client != null) {
            Client role = client.getClient();
            if (role != null) {
                ClientRole clientRole = ClientRole.calcEffectiveRole(role.getRole());
                logger.info("clientRole = {}", clientRole);
                return clientRole.getMayWriteMsg();
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public List<MsgTopicDTO> getSubscribedTopics() {
        Set<MsgTopic> topics = new HashSet<>();
        if (client != null) {
            topics = client.getClient().getSubscribedTopics();
        }
        return MsgTopicMapper.toDTOs(topics);
    }

    @Override
    public boolean publishFromFeed(MsgTopicDTO msgTopicDTO, String feedURL) {
        try {
            RSSReader rssReader = new RSSReader(feedURL);
            List<?> allEntries1 = rssReader.getAllEntries();
            if (!allEntries1.isEmpty() && allEntries1.get(0) instanceof SyndEntryImpl) {
                List<SyndEntryImpl> allEntries = (List<SyndEntryImpl>) rssReader.getAllEntries();
                for (SyndEntryImpl entry : allEntries) {
                    publishMsg(msgTopicDTO, entry.getTitle(), entry.getDescription().getValue());
                }

            } else {
                return false;
            }
        } catch (IOException | FeedException e) {
            logger.catching(e);
            return false;
        }
        return true;
    }

    @Override
    public List<String> getRSSFeedURLs() {
        return feeds.stream().map(s -> new String(s)).collect(Collectors.toList());
    }

    @Override
    public void setUserForEJB(String username, String password) {
        this.client = new ClientSessionRMI(username, "", null, injector.getAuthManagerCore().checkAndQuery(username, password));
    }
}
