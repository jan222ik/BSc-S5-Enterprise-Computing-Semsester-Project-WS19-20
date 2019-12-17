package at.fhv.itb17.s5.teamb.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class MsgTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long topicId;
    private String name;
    private boolean rssOrigin;

    public MsgTopic(String name, boolean rssOrigin) {
        this.name = name;
        this.rssOrigin = rssOrigin;
    }

    public MsgTopic() {
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRssOrigin() {
        return rssOrigin;
    }

    public void setRssOrigin(boolean rssOrigin) {
        this.rssOrigin = rssOrigin;
    }
}
