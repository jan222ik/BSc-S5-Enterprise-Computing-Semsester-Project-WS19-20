package at.fhv.itb17.s5.teamb.dtos.mapper;

import at.fhv.itb17.s5.teamb.dtos.ClientRoleDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.ClientRole;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public final class ClientRoleMapper {

    private ClientRoleMapper() {
    }

    public static List<ClientRoleDTO> toDTOs(@NotNull List<ClientRole> roles) {
        return roles.stream().map(ClientRoleMapper::toDTO).collect(Collectors.toList());
    }

    @NotNull
    @Contract("_ -> new")
    public static ClientRoleDTO toDTO(@NotNull ClientRole role) {
        return new ClientRoleDTO(role.getRoleName(), role.getMayReadMsg(), role.getMayWriteMsg(), role.getPriority());
    }
}
