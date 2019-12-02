package at.fhv.itb17.s5.teamb.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long addressId;
    private String country;
    private String zip;
    private String city;
    private String street;
    private String house;

    public Address(String country, String zip, String city, String street, String house) {
        this.country = country;
        this.zip = zip;
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public Address() {
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", country='" + country + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                '}';
    }

    public String asComparableString() {
        return country + zip + city + street + house;
    }
}
