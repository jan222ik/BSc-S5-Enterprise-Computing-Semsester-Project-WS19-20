package at.fhv.itb17.s5.teamb.dtos;

import java.io.Serializable;

public class AddressDTO implements Serializable {
    private Long addressId;
    private String country;
    private String zip;
    private String city;
    private String street;
    private String house;

    public AddressDTO() {
    }

    public AddressDTO(Long addressId, String country, String zip, String city, String street, String house) {
        this.addressId = addressId;
        this.country = country;
        this.zip = zip;
        this.city = city;
        this.street = street;
        this.house = house;
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
}
