package at.fhv.itb17.s5.teamb.fxapp.data.mock;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryFreeDTO;
import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterface;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;


public class MockSearchServiceImpl implements SearchService {

    @Override
    public LinkedList<EventDTO> searchFor(String searchQuery) {
        EvCategoryFreeDTO evCat0 = new EvCategoryFreeDTO(1123649L, "cat_name_ev0", 99 * 100, 5000, 4711);
        EvCategoryFreeDTO evCat1 = new EvCategoryFreeDTO(111233649L, "cat_name_ev1", 20 * 100, 800, 11);
        LinkedList<EvCategoryInterface> cats = new LinkedList<>(Arrays.asList(evCat0, evCat1));
        EvOccurrenceDTO evOccurrenceDTO0 = new EvOccurrenceDTO(12789L, LocalDate.now(), LocalTime.now(), cats, 1231234L, "evt_country", "evt_zip", "evt_city", "evt_street", "evt_house");
        EvOccurrenceDTO evOccurrenceDTO1 = new EvOccurrenceDTO(127123L, LocalDate.now().plusDays(3), LocalTime.now(), cats, 1231234L, "evt_country", "evt_zip", "evt_city", "evt_street", "evt_house");
        LinkedList<EvOccurrenceDTO> occurrences = new LinkedList<>(Arrays.asList(evOccurrenceDTO0, evOccurrenceDTO1));
        LinkedList<String> artistNames = new LinkedList<>(Arrays.asList("Hugo Hugo", "Franz Peter Werner"));
        EventDTO eventDTO0 = new EventDTO(123L, "Demo Concert", "A very descriptive description", "08/15",
                artistNames, occurrences, 90L, "org_name", "org_email",
                131L, "org_country", "org_zip", "org_city",
                "org_street", "org_house");
        EventDTO eventDTO1 = new EventDTO(123L, "Demo Concert1", "A very descriptive description1", "08/15 1",
                artistNames, occurrences, 90L, "org_name", "org_email",
                131L, "org_country", "org_zip", "org_city",
                "org_street", "org_house");
        return new LinkedList<>(Arrays.asList(eventDTO0, eventDTO1));
    }
}
