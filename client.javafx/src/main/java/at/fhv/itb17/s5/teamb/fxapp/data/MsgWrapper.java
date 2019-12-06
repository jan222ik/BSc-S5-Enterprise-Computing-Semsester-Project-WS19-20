package at.fhv.itb17.s5.teamb.fxapp.data;

import javax.jms.TextMessage;
import java.time.LocalDateTime;

public class MsgWrapper {
    private String topicName;
    private String msgText;
    private TextMessage textMessage;
    private LocalDateTime timestamp;
    private boolean ack;
    private String header;

    public MsgWrapper(String topicName, String msgText, TextMessage textMessage, LocalDateTime timestamp, boolean ack, String header) {
        this.topicName = topicName;
        this.msgText = msgText;
        this.textMessage = textMessage;
        this.timestamp = timestamp;
        this.ack = ack;
        this.header = header;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public TextMessage getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(TextMessage textMessage) {
        this.textMessage = textMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isAck() {
        return ack;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "MsgWrapper{" +
                "topicName='" + topicName + '\'' +
                ", msgText='" + msgText + '\'' +
                ", textMessage=" + textMessage +
                ", timestamp=" + timestamp +
                ", ack=" + ack +
                ", header='" + header + '\'' +
                '}';
    }
}
