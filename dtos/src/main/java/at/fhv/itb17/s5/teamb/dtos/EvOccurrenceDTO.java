package at.fhv.itb17.s5.teamb.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EvOccurrenceDTO {
    private Long occurrenceId;
    private LocalDate date;
    private LocalTime time;
    private List<EvCategoryInterface> priceCategories;

    private Long addressId;
    private String country;
    private String zip;
    private String city;
    private String street;
    private String house;

    public EvOccurrenceDTO(Long occurrenceId, LocalDate date, LocalTime time, List<EvCategoryInterface> priceCategories, Long addressId, String country, String zip, String city, String street, String house) {
        this.occurrenceId = occurrenceId;
        this.date = date;
        this.time = time;
        this.priceCategories = priceCategories;
        this.addressId = addressId;
        this.country = country;
        this.zip = zip;
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public Long getOccurrenceId() {
        return occurrenceId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public List<EvCategoryInterface> getPriceCategories() {
        return priceCategories;
    }

    public Long getAddressId() {
        return addressId;
    }

    public String getCountry() {
        return country;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }
}
