package at.fhv.itb17.s5.teamb.persistence.entities;

import javax.persistence.*;

@Entity
public class Client {
    @Id
    private String username;
    private String name;
    @Enumerated
    private ClientRoles role;
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;

    public Client(String username, String name, ClientRoles role, Address address) {
        this.username = username;
        this.name = name;
        this.role = role;
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

    public ClientRoles getRole() {
        return role;
    }

    public void setRole(ClientRoles role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
