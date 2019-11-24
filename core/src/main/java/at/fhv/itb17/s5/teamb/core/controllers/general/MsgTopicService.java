package at.fhv.itb17.s5.teamb.core.controllers.general;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MsgTopicService extends Remote {
    List<MsgTopicDTO> getAllTopics() throws RemoteException;

    boolean publishMsg(MsgTopicDTO msgTopicDTO, String header, String body) throws RemoteException;

    boolean mayPublish() throws RemoteException;
}