package at.fhv.itb17.s5.teamb.dtos.mapper;

import at.fhv.itb17.s5.teamb.dtos.LocationRowDTO;
import at.fhv.itb17.s5.teamb.dtos.LocationSeatDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationRow;

import java.util.List;
import java.util.stream.Collectors;

public final class LocationRowMapper {

    private LocationRowMapper() {
    }

    public static List<LocationRowDTO> toDTOs(List<LocationRow> rows) {
        return rows.stream().map(LocationRowMapper::toDTO).collect(Collectors.toList());
    }

    public static LocationRowDTO toDTO(LocationRow row) {
        if (row == null) return null;
        List<LocationSeatDTO> seats = LocationSeatMapper.toDTOs(row.getSeats());
        return new LocationRowDTO(row.getRowId(), row.getRowIdentifier(), seats);
    }
}
