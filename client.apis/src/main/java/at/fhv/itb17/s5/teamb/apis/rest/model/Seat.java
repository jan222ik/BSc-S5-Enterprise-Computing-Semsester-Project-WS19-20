package at.fhv.itb17.s5.teamb.apis.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * Seat
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-09T21:29:17.187Z[GMT]")
public class Seat   {
  @JsonProperty("seatId")
  private Long seatId = null;

  @JsonProperty("seatIdf")
  private String seatIdf = null;

  @JsonProperty("isFree")
  private Boolean isFree = null;

  public Seat seatId(Long seatId) {
    this.seatId = seatId;
    return this;
  }

  /**
   * Get seatId
   * @return seatId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getSeatId() {
    return seatId;
  }

  public void setSeatId(Long seatId) {
    this.seatId = seatId;
  }

  public Seat seatIdf(String seatIdf) {
    this.seatIdf = seatIdf;
    return this;
  }

  /**
   * Get seatIdf
   * @return seatIdf
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getSeatIdf() {
    return seatIdf;
  }

  public void setSeatIdf(String seatIdf) {
    this.seatIdf = seatIdf;
  }

  public Seat isFree(Boolean isFree) {
    this.isFree = isFree;
    return this;
  }

  /**
   * Get isFree
   * @return isFree
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Boolean isIsFree() {
    return isFree;
  }

  public void setIsFree(Boolean isFree) {
    this.isFree = isFree;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Seat seat = (Seat) o;
    return Objects.equals(this.seatId, seat.seatId) &&
        Objects.equals(this.seatIdf, seat.seatIdf) &&
        Objects.equals(this.isFree, seat.isFree);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seatId, seatIdf, isFree);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Seat {\n");
    
    sb.append("    seatId: ").append(toIndentedString(seatId)).append("\n");
    sb.append("    seatIdf: ").append(toIndentedString(seatIdf)).append("\n");
    sb.append("    isFree: ").append(toIndentedString(isFree)).append("\n");
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
