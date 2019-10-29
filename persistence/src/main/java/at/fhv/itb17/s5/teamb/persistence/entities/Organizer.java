package at.fhv.itb17.s5.teamb.persistence.entities;

import javax.persistence.*;

@Entity
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long organizerId;

    private String name;
    private String email;
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;

    public Organizer(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Organizer() {
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
