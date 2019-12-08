package at.fhv.itb17.s5.teamb.dtos.mapper;

import at.fhv.itb17.s5.teamb.dtos.CategoryCalcDataDTO;
import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterfaceDTO;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Address;
import at.fhv.itb17.s5.teamb.persistence.entities.EventCategory;
import at.fhv.itb17.s5.teamb.persistence.entities.EventOccurrence;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public final class EvOccurrenceMapper {

    private EvOccurrenceMapper() {
    }

    public static List<EvOccurrenceDTO> toDTOs(@NotNull List<EventOccurrence> occurrences) {
        return occurrences.stream().map(EvOccurrenceMapper::toDTO).collect(Collectors.toList());
    }

    @NotNull
    @Contract("_ -> new")
    public static EvOccurrenceDTO toDTO(EventOccurrence e) {
        List<EvCategoryInterfaceDTO> cats = EvCategoryMapper.toDTOs(e.getPriceCategories());
        Address a = e.getAddress();
        return new EvOccurrenceDTO(e.getOccurrenceId(), e.getDate(), e.getTime(), cats, a.getAddressId(), a.getCountry(), a.getZip(), a.getCity(), a.getStreet(), a.getHouse(), calcData(e));
    }

    private static CategoryCalcDataDTO calcData(@NotNull EventOccurrence value) {
        CategoryCalcDataDTO d = new CategoryCalcDataDTO();
        boolean[] occs = new boolean[]{false, false};
        int curPrice;
        for (EventCategory cat : value.getPriceCategories()) {
            curPrice = cat.getPriceInCent();
            d.setMinPrice(curPrice);
            d.setMaxPrice(curPrice);
            if (!occs[0] && cat.isFreeSeating()) {
                occs[0] = true;
            } else if (!occs[1] && !cat.isFreeSeating()) {
                occs[1] = true;
            }
        }
        d.setTicketTypes(((occs[0]) ? "Free" : "") + ((occs[0] && occs[1]) ? " & " : "") + ((occs[1]) ? "Specific" : "") + " Seating");
        return d;
    }
}
