package at.fhv.itb17.s5.teamb.dtos;

import java.io.Serializable;
import java.util.List;

public class ClientDTO implements Serializable {

    private String username;
    private String name;
    private List<ClientRoleDTO> role;
    private AddressDTO address;
    private List<MsgTopicDTO> subscribedTopics;

    public ClientDTO() {
    }

    public ClientDTO(String username, String name, List<ClientRoleDTO> role, AddressDTO address, List<MsgTopicDTO> subscribedTopics) {
        this.username = username;
        this.name = name;
        this.role = role;
        this.address = address;
        this.subscribedTopics = subscribedTopics;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClientRoleDTO> getRole() {
        return role;
    }

    public void setRole(List<ClientRoleDTO> role) {
        this.role = role;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<MsgTopicDTO> getSubscribedTopics() {
        return subscribedTopics;
    }

    public void setSubscribedTopics(List<MsgTopicDTO> subscribedTopics) {
        this.subscribedTopics = subscribedTopics;
    }
}
