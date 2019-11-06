package at.fhv.itb17.s5.teamb.dtos.mapper;

import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Address;
import at.fhv.itb17.s5.teamb.persistence.entities.Artist;
import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.entities.Organizer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public final class EventMapper {

    private EventMapper() {
    }

    public static List<EventDTO> toDTOs(@NotNull List<Event> events) {
        return events.stream().map(EventMapper::toDTO).collect(Collectors.toList());
    }

    @NotNull
    public static EventDTO toDTO(@NotNull Event e) {
        List<String> artists = e.getArtists().stream().map(Artist::getName).collect(Collectors.toList());
        List<EvOccurrenceDTO> occurrences = EvOccurrenceMapper.toDTOs(e.getOccurrences());
        Organizer o = e.getOrganizer();
        Address oa = o.getAddress();
        return new EventDTO(
                e.getEventId(), e.getTitle(), e.getDescription(), e.getGenre(), artists, occurrences,
                o.getOrganizerId(), o.getName(), o.getEmail(),
                oa.getAddressId(), oa.getCountry(), oa.getZip(), oa.getCity(), oa.getStreet(), oa.getHouse()
        );
    }
}
