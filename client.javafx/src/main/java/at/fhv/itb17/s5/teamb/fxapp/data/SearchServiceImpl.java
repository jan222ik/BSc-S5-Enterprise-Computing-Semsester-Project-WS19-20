package at.fhv.itb17.s5.teamb.fxapp.data;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryFreeDTO;
import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterface;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;

@Deprecated
public class SearchServiceImpl implements SearchService {
    @Override
    public LinkedList<EventDTO> searchFor(String searchQuery) {
        EvCategoryFreeDTO evCat0 = new EvCategoryFreeDTO(1123649L, "cat_name_ev0", 99 * 100, 5000, 4711);
        LinkedList<EvCategoryInterface> cats = new LinkedList<>(Arrays.asList(evCat0));
        EvOccurrenceDTO evOccurrenceDTO0 = new EvOccurrenceDTO(12789L, LocalDate.now(), LocalTime.now(), cats, 1231234L, "evt_country", "evt_zip", "evt_city", "evt_street", "evt_house");
        LinkedList<EvOccurrenceDTO> occurrences = new LinkedList<>(Arrays.asList(evOccurrenceDTO0));
        EventDTO eventDTO = new EventDTO(123L, "Demo Concert", "A very descriptive description", "08/15",
                occurrences, 90L, "org_name", "org_email",
                131L, "org_country", "org_zip", "org_city",
                "org_street", "org_house");
        return new LinkedList<>(Arrays.asList(eventDTO));
    }
}
