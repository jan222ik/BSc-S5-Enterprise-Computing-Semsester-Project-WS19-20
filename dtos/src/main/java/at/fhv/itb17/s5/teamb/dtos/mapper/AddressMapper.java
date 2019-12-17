package at.fhv.itb17.s5.teamb.dtos.mapper;

import at.fhv.itb17.s5.teamb.dtos.AddressDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Address;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public final class AddressMapper {

    private AddressMapper() {
    }

    public static List<AddressDTO> toDTOs(@NotNull List<Address> addresses) {
        return addresses.stream().map(AddressMapper::toDTO).collect(Collectors.toList());
    }

    @NotNull
    @Contract("_ -> new")
    public static AddressDTO toDTO(@NotNull Address address) {
        return new AddressDTO(address.getAddressId(), address.getCountry(), address.getZip(),
                address.getCity(), address.getStreet(), address.getHouse());
    }
}
