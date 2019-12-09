package at.fhv.itb17.s5.teamb.apis.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EventOcc
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-09T21:29:17.187Z[GMT]")
public class EventOcc   {
  @JsonProperty("occurrenceId")
  private Long occurrenceId = null;

  @JsonProperty("date")
  private Long date = null;

  @JsonProperty("time")
  private Long time = null;

  @JsonProperty("priceCategories")
  @Valid
  private List<OccCat> priceCategories = new ArrayList<OccCat>();

  @JsonProperty("addressId")
  private Long addressId = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("zip")
  private String zip = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("street")
  private String street = null;

  @JsonProperty("house")
  private String house = null;

  @JsonProperty("categoryCalcDataDTO")
  private CategoryCalcDataDto categoryCalcDataDTO = null;

  public EventOcc occurrenceId(Long occurrenceId) {
    this.occurrenceId = occurrenceId;
    return this;
  }

  /**
   * Get occurrenceId
   * @return occurrenceId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getOccurrenceId() {
    return occurrenceId;
  }

  public void setOccurrenceId(Long occurrenceId) {
    this.occurrenceId = occurrenceId;
  }

  public EventOcc date(Long date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getDate() {
    return date;
  }

  public void setDate(Long date) {
    this.date = date;
  }

  public EventOcc time(Long time) {
    this.time = time;
    return this;
  }

  /**
   * Get time
   * @return time
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public EventOcc priceCategories(List<OccCat> priceCategories) {
    this.priceCategories = priceCategories;
    return this;
  }

  public EventOcc addPriceCategoriesItem(OccCat priceCategoriesItem) {
    this.priceCategories.add(priceCategoriesItem);
    return this;
  }

  /**
   * Get priceCategories
   * @return priceCategories
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull
    @Valid
    public List<OccCat> getPriceCategories() {
    return priceCategories;
  }

  public void setPriceCategories(List<OccCat> priceCategories) {
    this.priceCategories = priceCategories;
  }

  public EventOcc addressId(Long addressId) {
    this.addressId = addressId;
    return this;
  }

  /**
   * Get addressId
   * @return addressId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  public EventOcc country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public EventOcc zip(String zip) {
    this.zip = zip;
    return this;
  }

  /**
   * Get zip
   * @return zip
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public EventOcc city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public EventOcc street(String street) {
    this.street = street;
    return this;
  }

  /**
   * Get street
   * @return street
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public EventOcc house(String house) {
    this.house = house;
    return this;
  }

  /**
   * Get house
   * @return house
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getHouse() {
    return house;
  }

  public void setHouse(String house) {
    this.house = house;
  }

  public EventOcc categoryCalcDataDTO(CategoryCalcDataDto categoryCalcDataDTO) {
    this.categoryCalcDataDTO = categoryCalcDataDTO;
    return this;
  }

  /**
   * Get categoryCalcDataDTO
   * @return categoryCalcDataDTO
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public CategoryCalcDataDto getCategoryCalcDataDTO() {
    return categoryCalcDataDTO;
  }

  public void setCategoryCalcDataDTO(CategoryCalcDataDto categoryCalcDataDTO) {
    this.categoryCalcDataDTO = categoryCalcDataDTO;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventOcc eventOcc = (EventOcc) o;
    return Objects.equals(this.occurrenceId, eventOcc.occurrenceId) &&
        Objects.equals(this.date, eventOcc.date) &&
        Objects.equals(this.time, eventOcc.time) &&
        Objects.equals(this.priceCategories, eventOcc.priceCategories) &&
        Objects.equals(this.addressId, eventOcc.addressId) &&
        Objects.equals(this.country, eventOcc.country) &&
        Objects.equals(this.zip, eventOcc.zip) &&
        Objects.equals(this.city, eventOcc.city) &&
        Objects.equals(this.street, eventOcc.street) &&
        Objects.equals(this.house, eventOcc.house) &&
        Objects.equals(this.categoryCalcDataDTO, eventOcc.categoryCalcDataDTO);
  }

  @Override
  public int hashCode() {
    return Objects.hash(occurrenceId, date, time, priceCategories, addressId, country, zip, city, street, house, categoryCalcDataDTO);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventOcc {\n");
    
    sb.append("    occurrenceId: ").append(toIndentedString(occurrenceId)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    priceCategories: ").append(toIndentedString(priceCategories)).append("\n");
    sb.append("    addressId: ").append(toIndentedString(addressId)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    street: ").append(toIndentedString(street)).append("\n");
    sb.append("    house: ").append(toIndentedString(house)).append("\n");
    sb.append("    categoryCalcDataDTO: ").append(toIndentedString(categoryCalcDataDTO)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
