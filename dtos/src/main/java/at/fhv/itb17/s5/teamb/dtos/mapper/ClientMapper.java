package at.fhv.itb17.s5.teamb.dtos.mapper;

import at.fhv.itb17.s5.teamb.dtos.ClientDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public final class ClientMapper {

    private ClientMapper() {
    }

    public static List<ClientDTO> toDTOs(@NotNull List<Client> clients) {
        return clients.stream().map(ClientMapper::toDTO).collect(Collectors.toList());
    }

    @NotNull
    @Contract("_ -> new")
    public static ClientDTO toDTO(@NotNull Client client) {
        return new ClientDTO(client.getUsername(), client.getName(), ClientRoleMapper.toDTOs(client.getRole()),
                AddressMapper.toDTO(client.getAddress()), MsgTopicMapper.toDTOs(client.getSubscribedTopics()));
    }
}
