package at.fhv.itb17.s5.teamb.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EvOccurrenceDTO implements Serializable {
    @JsonProperty("occurrenceId")
    private Long occurrenceId;
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("time")
    private LocalTime time;
    @JsonProperty("priceCategories")
    private List<EvCategoryInterfaceDTO> priceCategories;

    @JsonProperty("addressId")
    private Long addressId;
    @JsonProperty("country")
    private String country;
    @JsonProperty("zip")
    private String zip;
    @JsonProperty("city")
    private String city;
    @JsonProperty("street")
    private String street;
    @JsonProperty("house")
    private String house;
    @JsonProperty("categoryCalcDataDTO")
    private CategoryCalcDataDTO categoryCalcDataDTO;

    @SuppressWarnings("squid:S00107") //Size of param list
    public EvOccurrenceDTO(Long occurrenceId, LocalDate date, LocalTime time, List<EvCategoryInterfaceDTO> priceCategories, Long addressId, String country, String zip, String city, String street, String house, CategoryCalcDataDTO categoryCalcDataDTO) {
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
        this.categoryCalcDataDTO = categoryCalcDataDTO;
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

    public List<EvCategoryInterfaceDTO> getPriceCategories() {
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

    public CategoryCalcDataDTO getCategoryCalcDataDTO() {
        return categoryCalcDataDTO;
    }
}
