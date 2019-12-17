package at.fhv.itb17.s5.teamb.persistence.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Set;

@Entity
public class Client {

    @Id
    private String username;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ClientRole> role;
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;

    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<MsgTopic> subscribedTopics;

    public Client(String username, String name, List<ClientRole> role, Set<MsgTopic> topics, Address address) {
        this.username = username;
        this.name = name;
        this.role = role;
        this.subscribedTopics = topics;
        this.address = address;
    }

    public Client() {
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

    public List<ClientRole> getRole() {
        return role;
    }

    public void setRole(List<ClientRole> role) {
        this.role = role;
    }

    public Set<MsgTopic> getSubscribedTopics() {
        return subscribedTopics;
    }

    public void setSubscribedTopics(Set<MsgTopic> subscribedTopics) {
        this.subscribedTopics = subscribedTopics;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
