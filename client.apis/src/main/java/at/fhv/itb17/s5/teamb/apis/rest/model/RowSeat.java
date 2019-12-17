package at.fhv.itb17.s5.teamb.apis.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * RowSeat
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-09T21:29:17.187Z[GMT]")
public class RowSeat   {
  @JsonProperty("rowID")
  private Long rowID = null;

  @JsonProperty("seatID")
  private Long seatID = null;

  public RowSeat rowID(Long rowID) {
    this.rowID = rowID;
    return this;
  }

  /**
   * Get rowID
   * @return rowID
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getRowID() {
    return rowID;
  }

  public void setRowID(Long rowID) {
    this.rowID = rowID;
  }

  public RowSeat seatID(Long seatID) {
    this.seatID = seatID;
    return this;
  }

  /**
   * Get seatID
   * @return seatID
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getSeatID() {
    return seatID;
  }

  public void setSeatID(Long seatID) {
    this.seatID = seatID;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RowSeat rowSeat = (RowSeat) o;
    return Objects.equals(this.rowID, rowSeat.rowID) &&
        Objects.equals(this.seatID, rowSeat.seatID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rowID, seatID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RowSeat {\n");
    
    sb.append("    rowID: ").append(toIndentedString(rowID)).append("\n");
    sb.append("    seatID: ").append(toIndentedString(seatID)).append("\n");
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
