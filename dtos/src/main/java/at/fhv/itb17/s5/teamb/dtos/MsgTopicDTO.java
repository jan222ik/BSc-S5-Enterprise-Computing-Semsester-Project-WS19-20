package at.fhv.itb17.s5.teamb.dtos;

import java.io.Serializable;

public class MsgTopicDTO implements Serializable {
    private Long topicId;
    private String name;
    private boolean rssOrigin;

    public MsgTopicDTO(Long topicId, String name, boolean rssOrigin) {
        this.topicId = topicId;
        this.name = name;
        this.rssOrigin = rssOrigin;
    }

    public Long getTopicId() {
        return topicId;
    }

    public String getName() {
        return name;
    }

    public boolean isRssOrigin() {
        return rssOrigin;
    }

    @Override
    public String toString() {
        return "MsgTopicDTO{" +
                "topicId=" + topicId +
                ", name='" + name + '\'' +
                ", rssOrigin=" + rssOrigin +
                '}';
    }
}
