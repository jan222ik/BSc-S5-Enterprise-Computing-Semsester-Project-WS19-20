package at.fhv.itb17.s5.teamb.dtos.mapper;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryFreeDTO;
import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterface;
import at.fhv.itb17.s5.teamb.dtos.EvCategorySeatsDTO;
import at.fhv.itb17.s5.teamb.dtos.LocationRowDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.EventCategory;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public final class EvCategoryMapper {
    private EvCategoryMapper() {
    }

    public static List<EvCategoryInterface> toDTOs(@NotNull List<EventCategory> cats) {
        return cats.stream().map(EvCategoryMapper::toDTO).collect(Collectors.toList());
    }

    public static EvCategoryInterface toDTO(@NotNull EventCategory cat) {
        return (cat.isFreeSeating()) ? toFreeDTO(cat) : toSeatDTO(cat);
    }

    @NotNull
    private static EvCategorySeatsDTO toSeatDTO(@NotNull EventCategory cat) {
        List<LocationRowDTO> rows = LocationRowMapper.toDTOs(cat.getSeatingRows());
        return new EvCategorySeatsDTO(cat.getEventCategoryId(), cat.getCategoryName(), cat.getPriceInCent(), rows);
    }

    @NotNull
    @Contract("_ -> new")
    private static EvCategoryFreeDTO toFreeDTO(@NotNull EventCategory cat) {
        return new EvCategoryFreeDTO(cat.getEventCategoryId(), cat.getCategoryName(), cat.getPriceInCent(), cat.getTotalSpace(), cat.getUsedSpace());
    }
}
