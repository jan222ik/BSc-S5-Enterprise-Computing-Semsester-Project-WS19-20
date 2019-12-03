package at.fhv.itb17.s5.teamb.core.controllers.general;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MsgTopicService extends Remote {
    List<MsgTopicDTO> getAllTopics() throws RemoteException;

    boolean publishMsg(MsgTopicDTO msgTopicDTO, String header, String body) throws RemoteException;

    boolean mayPublish() throws RemoteException;

    List<MsgTopic> getSubscribedTopics() throws RemoteException;

    boolean publishFromFeed(MsgTopicDTO msgTopicDTO, String feedURL) throws RemoteException;

    List<String> getRSSFeedURLs() throws RemoteException;
}
