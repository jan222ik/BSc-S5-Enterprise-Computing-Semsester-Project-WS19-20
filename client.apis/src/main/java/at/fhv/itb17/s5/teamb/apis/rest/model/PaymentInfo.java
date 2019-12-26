package at.fhv.itb17.s5.teamb.apis.rest.model;

import java.util.Objects;

import at.fhv.itb17.s5.teamb.persistence.entities.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * PaymentInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-09T21:29:17.187Z[GMT]")
public class PaymentInfo   {
  @JsonProperty("nameF")
  private String nameF = null;

  @JsonProperty("nameL")
  private String nameL = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("zip")
  private String zip = null;

  @JsonProperty("street")
  private String street = null;

  @JsonProperty("house")
  private String house = null;

  @JsonProperty("cardNr")
  private Long cardNr = null;

  public PaymentInfo nameF(String nameF) {
    this.nameF = nameF;
    return this;
  }

  /**
   * Get nameF
   * @return nameF
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getNameF() {
    return nameF;
  }

  public void setNameF(String nameF) {
    this.nameF = nameF;
  }

  public PaymentInfo nameL(String nameL) {
    this.nameL = nameL;
    return this;
  }

  /**
   * Get nameL
   * @return nameL
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getNameL() {
    return nameL;
  }

  public void setNameL(String nameL) {
    this.nameL = nameL;
  }

  public PaymentInfo country(String country) {
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

  public PaymentInfo city(String city) {
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

  public PaymentInfo zip(String zip) {
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

  public PaymentInfo street(String street) {
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

  public PaymentInfo house(String house) {
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

  public PaymentInfo cardNr(Long cardNr) {
    this.cardNr = cardNr;
    return this;
  }

  /**
   * Get cardNr
   * @return cardNr
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getCardNr() {
    return cardNr;
  }

  public void setCardNr(Long cardNr) {
    this.cardNr = cardNr;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentInfo paymentInfo = (PaymentInfo) o;
    return Objects.equals(this.nameF, paymentInfo.nameF) &&
        Objects.equals(this.nameL, paymentInfo.nameL) &&
        Objects.equals(this.country, paymentInfo.country) &&
        Objects.equals(this.city, paymentInfo.city) &&
        Objects.equals(this.zip, paymentInfo.zip) &&
        Objects.equals(this.street, paymentInfo.street) &&
        Objects.equals(this.house, paymentInfo.house) &&
        Objects.equals(this.cardNr, paymentInfo.cardNr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nameF, nameL, country, city, zip, street, house, cardNr);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentInfo {\n");
    
    sb.append("    nameF: ").append(toIndentedString(nameF)).append("\n");
    sb.append("    nameL: ").append(toIndentedString(nameL)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
    sb.append("    street: ").append(toIndentedString(street)).append("\n");
    sb.append("    house: ").append(toIndentedString(house)).append("\n");
    sb.append("    cardNr: ").append(toIndentedString(cardNr)).append("\n");
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

  public Address toAddressEntity() {
    return new Address(country, zip, city, street, house);
  }
}
