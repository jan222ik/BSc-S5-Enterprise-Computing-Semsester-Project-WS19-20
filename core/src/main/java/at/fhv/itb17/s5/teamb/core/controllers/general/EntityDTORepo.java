package at.fhv.itb17.s5.teamb.core.controllers.general;

import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;

import java.util.List;

public interface EntityDTORepo {
    EventDTO toEventDTO(Event event);

    Event toEvent(EventDTO eventDTO);

    List<EventDTO> toEventDTOs(List<Event> event);

    List<Event> toEvents(List<EventDTO> eventDTOs);

    TicketDTO toTicketDTO(Ticket ticket);

    Ticket toTicket(TicketDTO ticketDTO, Client client);

    List<TicketDTO> toTicketDTOs(List<Ticket> tickets);

    List<Ticket> toTickets(List<TicketDTO> ticketDTOs, Client client);

    List<MsgTopicDTO> toMsgTopicDTOs(List<MsgTopic> topics);

    MsgTopicDTO toMsgTopicDTO(MsgTopic topic);

    List<MsgTopic> toMsgTopics(List<MsgTopicDTO> topicDTOs);

    MsgTopic toMsgTopic(MsgTopicDTO topicDTO);

    EventDTO getEventDTOByID(Long eventID);
}
