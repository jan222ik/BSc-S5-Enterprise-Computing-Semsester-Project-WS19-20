package at.fhv.itb17.s5.teamb.core.controllers.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.ClientSessionRMI;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.controllers.general.MsgTopicService;
import at.fhv.itb17.s5.teamb.core.domain.msg.MsgServiceCore;
import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.ClientRole;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("RedundantThrows")
public class MsgTopicServiceRMI extends UnicastRemoteObject implements MsgTopicService {

    private final ClientSessionRMI client;
    private final MsgServiceCore topicService;
    private final EntityDTORepo entityDTORepo;

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
            System.out.println("clientRole = " + clientRole);
            return clientRole.getMayWriteMsg();
        } else {
            return false;
        }
    }

    @Override
    public List<MsgTopic> getSubscribedTopics() {
        List<MsgTopic> topics = new LinkedList<>();
        if(client != null) {
            topics = client.getClient().getSubscribedTopics();
        }
        return topics;
    }
}
