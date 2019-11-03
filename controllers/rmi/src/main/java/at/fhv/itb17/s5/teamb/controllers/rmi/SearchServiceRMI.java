package at.fhv.itb17.s5.teamb.controllers.rmi;

import at.fhv.itb17.s5.teamb.controllers.SearchService;
import at.fhv.itb17.s5.teamb.dtos.EvCategoryFreeDTO;
import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterface;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;

public class SearchServiceRMI implements SearchService, Serializable {
    @Override
    public LinkedList<EventDTO> searchFor(String queryString) {
        EvCategoryFreeDTO evCat0 = new EvCategoryFreeDTO(1123649L, "cat_name_ev0", 99 * 100, 5000, 4711);
        EvCategoryFreeDTO evCat1 = new EvCategoryFreeDTO(111233649L, "cat_name_ev1", 20 * 100, 800, 11);
        LinkedList<EvCategoryInterface> cats = new LinkedList<>(Arrays.asList(evCat0, evCat1));
        EvOccurrenceDTO evOccurrenceDTO0 = new EvOccurrenceDTO(12789L, LocalDate.now(), LocalTime.now(), cats, 1231234L, "evt_country", "evt_zip", "evt_city", "evt_street", "evt_house");
        EvOccurrenceDTO evOccurrenceDTO1 = new EvOccurrenceDTO(127123L, LocalDate.now().plusDays(3), LocalTime.now(), cats, 1231234L, "evt_country", "evt_zip", "evt_city", "evt_street", "evt_house");
        LinkedList<EvOccurrenceDTO> occurrences = new LinkedList<>(Arrays.asList(evOccurrenceDTO0, evOccurrenceDTO1));
        EventDTO eventDTO0 = new EventDTO(123L, "Demo Concert", "A very descriptive description", "08/15",
                occurrences, 90L, "org_name", "org_email",
                131L, "org_country", "org_zip", "org_city",
                "org_street", "org_house");
        EventDTO eventDTO1 = new EventDTO(123L, "Demo Concert1", "A very descriptive description1", "08/15 1",
                occurrences, 90L, "org_name", "org_email",
                131L, "org_country", "org_zip", "org_city",
                "org_street", "org_house");
        return new LinkedList<>(Arrays.asList(eventDTO0, eventDTO1));
    }

    @Override
    public Object updateTicketAvailability(Object ticket) {
        return null;
    }
}