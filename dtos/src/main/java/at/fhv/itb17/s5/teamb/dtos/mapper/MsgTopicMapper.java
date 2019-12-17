package at.fhv.itb17.s5.teamb.dtos.mapper;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class MsgTopicMapper {

    private MsgTopicMapper() {
    }

    public static List<MsgTopicDTO> toDTOs(@NotNull Set<MsgTopic> topics) {
        return topics.stream().map(MsgTopicMapper::toDTO).collect(Collectors.toList());
    }

    @NotNull
    @Contract("_ -> new")
    public static MsgTopicDTO toDTO(@NotNull MsgTopic topic) {
        return new MsgTopicDTO(topic.getTopicId(), topic.getName(), topic.isRssOrigin());
    }
}
