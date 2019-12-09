package at.fhv.itb17.s5.teamb.apis.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * CategoryCalcDataDto
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-09T21:29:17.187Z[GMT]")
public class CategoryCalcDataDto   {
  @JsonProperty("minPrice")
  private Integer minPrice = null;

  @JsonProperty("maxPrice")
  private Integer maxPrice = null;

  @JsonProperty("ticketTypes")
  private String ticketTypes = null;

  public CategoryCalcDataDto minPrice(Integer minPrice) {
    this.minPrice = minPrice;
    return this;
  }

  /**
   * Get minPrice
   * @return minPrice
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Integer getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(Integer minPrice) {
    this.minPrice = minPrice;
  }

  public CategoryCalcDataDto maxPrice(Integer maxPrice) {
    this.maxPrice = maxPrice;
    return this;
  }

  /**
   * Get maxPrice
   * @return maxPrice
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Integer getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(Integer maxPrice) {
    this.maxPrice = maxPrice;
  }

  public CategoryCalcDataDto ticketTypes(String ticketTypes) {
    this.ticketTypes = ticketTypes;
    return this;
  }

  /**
   * Get ticketTypes
   * @return ticketTypes
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getTicketTypes() {
    return ticketTypes;
  }

  public void setTicketTypes(String ticketTypes) {
    this.ticketTypes = ticketTypes;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CategoryCalcDataDto categoryCalcDataDto = (CategoryCalcDataDto) o;
    return Objects.equals(this.minPrice, categoryCalcDataDto.minPrice) &&
        Objects.equals(this.maxPrice, categoryCalcDataDto.maxPrice) &&
        Objects.equals(this.ticketTypes, categoryCalcDataDto.ticketTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(minPrice, maxPrice, ticketTypes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CategoryCalcDataDto {\n");
    
    sb.append("    minPrice: ").append(toIndentedString(minPrice)).append("\n");
    sb.append("    maxPrice: ").append(toIndentedString(maxPrice)).append("\n");
    sb.append("    ticketTypes: ").append(toIndentedString(ticketTypes)).append("\n");
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
