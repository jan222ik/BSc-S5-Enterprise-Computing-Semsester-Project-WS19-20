package at.fhv.itb17.s5.teamb.persistence.repository;

import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;

import java.util.LinkedList;
import java.util.List;

public class MsgRepository {
    private EntityRepository ep;

    public MsgRepository(EntityRepository ep) {
        this.ep = ep;
    }

    public List<MsgTopic> getAllTopics() {
        return ep.getAll(MsgTopic.class, new LinkedList<>());
    }
}
