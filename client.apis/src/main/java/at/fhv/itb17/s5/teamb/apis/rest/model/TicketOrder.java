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
 * TicketOrder
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-09T21:29:17.187Z[GMT]")
public class TicketOrder   {
  @JsonProperty("amount")
  private Long amount = null;

  @JsonProperty("rowseats")
  @Valid
  private List<RowSeat> rowseats = new ArrayList<RowSeat>();

  @JsonProperty("bookingInfo")
  private PaymentInfo bookingInfo = null;

  public TicketOrder amount(Long amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public TicketOrder rowseats(List<RowSeat> rowseats) {
    this.rowseats = rowseats;
    return this;
  }

  public TicketOrder addRowseatsItem(RowSeat rowseatsItem) {
    this.rowseats.add(rowseatsItem);
    return this;
  }

  /**
   * Get rowseats
   * @return rowseats
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull
    @Valid
    public List<RowSeat> getRowseats() {
    return rowseats;
  }

  public void setRowseats(List<RowSeat> rowseats) {
    this.rowseats = rowseats;
  }

  public TicketOrder bookingInfo(PaymentInfo bookingInfo) {
    this.bookingInfo = bookingInfo;
    return this;
  }

  /**
   * Get bookingInfo
   * @return bookingInfo
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public PaymentInfo getBookingInfo() {
    return bookingInfo;
  }

  public void setBookingInfo(PaymentInfo bookingInfo) {
    this.bookingInfo = bookingInfo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TicketOrder ticketOrder = (TicketOrder) o;
    return Objects.equals(this.amount, ticketOrder.amount) &&
        Objects.equals(this.rowseats, ticketOrder.rowseats) &&
        Objects.equals(this.bookingInfo, ticketOrder.bookingInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, rowseats, bookingInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TicketOrder {\n");
    
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    rowseats: ").append(toIndentedString(rowseats)).append("\n");
    sb.append("    bookingInfo: ").append(toIndentedString(bookingInfo)).append("\n");
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
