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
 * OccCat
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-09T21:29:17.187Z[GMT]")
public class OccCat   {
  @JsonProperty("eventCategoryId")
  private Long eventCategoryId = null;

  @JsonProperty("categoryName")
  private String categoryName = null;

  @JsonProperty("totalTickets")
  private Long totalTickets = null;

  @JsonProperty("usedTickets")
  private Long usedTickets = null;

  @JsonProperty("priceInCents")
  private Long priceInCents = null;

  @JsonProperty("rows")
  @Valid
  private List<Row> rows = new ArrayList<Row>();

  public OccCat eventCategoryId(Long eventCategoryId) {
    this.eventCategoryId = eventCategoryId;
    return this;
  }

  /**
   * Get eventCategoryId
   * @return eventCategoryId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getEventCategoryId() {
    return eventCategoryId;
  }

  public void setEventCategoryId(Long eventCategoryId) {
    this.eventCategoryId = eventCategoryId;
  }

  public OccCat categoryName(String categoryName) {
    this.categoryName = categoryName;
    return this;
  }

  /**
   * Get categoryName
   * @return categoryName
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public OccCat totalTickets(Long totalTickets) {
    this.totalTickets = totalTickets;
    return this;
  }

  /**
   * Get totalTickets
   * @return totalTickets
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getTotalTickets() {
    return totalTickets;
  }

  public void setTotalTickets(Long totalTickets) {
    this.totalTickets = totalTickets;
  }

  public OccCat usedTickets(Long usedTickets) {
    this.usedTickets = usedTickets;
    return this;
  }

  /**
   * Get usedTickets
   * @return usedTickets
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getUsedTickets() {
    return usedTickets;
  }

  public void setUsedTickets(Long usedTickets) {
    this.usedTickets = usedTickets;
  }

  public OccCat priceInCents(Long priceInCents) {
    this.priceInCents = priceInCents;
    return this;
  }

  /**
   * Get priceInCents
   * @return priceInCents
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getPriceInCents() {
    return priceInCents;
  }

  public void setPriceInCents(Long priceInCents) {
    this.priceInCents = priceInCents;
  }

  public OccCat rows(List<Row> rows) {
    this.rows = rows;
    return this;
  }

  public OccCat addRowsItem(Row rowsItem) {
    this.rows.add(rowsItem);
    return this;
  }

  /**
   * Get rows
   * @return rows
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull
    @Valid
    public List<Row> getRows() {
    return rows;
  }

  public void setRows(List<Row> rows) {
    this.rows = rows;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OccCat occCat = (OccCat) o;
    return Objects.equals(this.eventCategoryId, occCat.eventCategoryId) &&
        Objects.equals(this.categoryName, occCat.categoryName) &&
        Objects.equals(this.totalTickets, occCat.totalTickets) &&
        Objects.equals(this.usedTickets, occCat.usedTickets) &&
        Objects.equals(this.priceInCents, occCat.priceInCents) &&
        Objects.equals(this.rows, occCat.rows);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventCategoryId, categoryName, totalTickets, usedTickets, priceInCents, rows);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OccCat {\n");
    
    sb.append("    eventCategoryId: ").append(toIndentedString(eventCategoryId)).append("\n");
    sb.append("    categoryName: ").append(toIndentedString(categoryName)).append("\n");
    sb.append("    totalTickets: ").append(toIndentedString(totalTickets)).append("\n");
    sb.append("    usedTickets: ").append(toIndentedString(usedTickets)).append("\n");
    sb.append("    priceInCents: ").append(toIndentedString(priceInCents)).append("\n");
    sb.append("    rows: ").append(toIndentedString(rows)).append("\n");
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
