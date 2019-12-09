package at.fhv.itb17.s5.teamb.apis.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Row
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-09T21:29:17.187Z[GMT]")
public class Row   {
  @JsonProperty("rowId")
  private Long rowId = null;

  @JsonProperty("rowIdf")
  private String rowIdf = null;

  @JsonProperty("seats")
  private Seat seats = null;

  public Row rowId(Long rowId) {
    this.rowId = rowId;
    return this;
  }

  /**
   * Get rowId
   * @return rowId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getRowId() {
    return rowId;
  }

  public void setRowId(Long rowId) {
    this.rowId = rowId;
  }

  public Row rowIdf(String rowIdf) {
    this.rowIdf = rowIdf;
    return this;
  }

  /**
   * Get rowIdf
   * @return rowIdf
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getRowIdf() {
    return rowIdf;
  }

  public void setRowIdf(String rowIdf) {
    this.rowIdf = rowIdf;
  }

  public Row seats(Seat seats) {
    this.seats = seats;
    return this;
  }

  /**
   * Get seats
   * @return seats
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Seat getSeats() {
    return seats;
  }

  public void setSeats(Seat seats) {
    this.seats = seats;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Row row = (Row) o;
    return Objects.equals(this.rowId, row.rowId) &&
        Objects.equals(this.rowIdf, row.rowIdf) &&
        Objects.equals(this.seats, row.seats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rowId, rowIdf, seats);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Row {\n");
    
    sb.append("    rowId: ").append(toIndentedString(rowId)).append("\n");
    sb.append("    rowIdf: ").append(toIndentedString(rowIdf)).append("\n");
    sb.append("    seats: ").append(toIndentedString(seats)).append("\n");
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
