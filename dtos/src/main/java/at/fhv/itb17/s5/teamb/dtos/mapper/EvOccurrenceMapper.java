package at.fhv.itb17.s5.teamb.dtos.mapper;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterface;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Address;
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
        List<EvCategoryInterface> cats = EvCategoryMapper.toDTOs(e.getPriceCategories());
        Address a = e.getAddress();
        return new EvOccurrenceDTO(e.getOccurrenceId(), e.getDate(), e.getTime(), cats, a.getAddressId(), a.getCountry(), a.getZip(), a.getCity(), a.getStreet(), a.getHouse());
    }
}
