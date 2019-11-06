package at.fhv.itb17.s5.teamb.dtos.mapper;

import at.fhv.itb17.s5.teamb.dtos.LocationSeatDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationSeat;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public final class LocationSeatMapper {

    private LocationSeatMapper() {
    }

    public static List<LocationSeatDTO> toDTOs(@NotNull List<LocationSeat> seats) {
        return seats.stream().map(LocationSeatMapper::toDTO).collect(Collectors.toList());
    }

    @NotNull
    @Contract("_ -> new")
    public static LocationSeatDTO toDTO(@NotNull LocationSeat seat) {
        return new LocationSeatDTO(seat.getSeatId(), seat.getSeatIdentifier());
    }
}
