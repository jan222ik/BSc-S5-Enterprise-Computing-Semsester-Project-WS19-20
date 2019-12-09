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
 * Event
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-09T21:29:17.187Z[GMT]")
public class Event   {
  @JsonProperty("eventId")
  private Long eventId = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("genere")
  private String genere = null;

  @JsonProperty("artistNames")
  @Valid
  private List<String> artistNames = new ArrayList<String>();

  @JsonProperty("occurrences")
  @Valid
  private List<EventOcc> occurrences = new ArrayList<EventOcc>();

  public Event eventId(Long eventId) {
    this.eventId = eventId;
    return this;
  }

  /**
   * Get eventId
   * @return eventId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getEventId() {
    return eventId;
  }

  public void setEventId(Long eventId) {
    this.eventId = eventId;
  }

  public Event title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Event description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Event genere(String genere) {
    this.genere = genere;
    return this;
  }

  /**
   * Get genere
   * @return genere
  **/
  @ApiModelProperty(value = "")
  
    public String getGenere() {
    return genere;
  }

  public void setGenere(String genere) {
    this.genere = genere;
  }

  public Event artistNames(List<String> artistNames) {
    this.artistNames = artistNames;
    return this;
  }

  public Event addArtistNamesItem(String artistNamesItem) {
    this.artistNames.add(artistNamesItem);
    return this;
  }

  /**
   * Get artistNames
   * @return artistNames
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public List<String> getArtistNames() {
    return artistNames;
  }

  public void setArtistNames(List<String> artistNames) {
    this.artistNames = artistNames;
  }

  public Event occurrences(List<EventOcc> occurrences) {
    this.occurrences = occurrences;
    return this;
  }

  public Event addOccurrencesItem(EventOcc occurrencesItem) {
    this.occurrences.add(occurrencesItem);
    return this;
  }

  /**
   * Get occurrences
   * @return occurrences
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull
    @Valid
    public List<EventOcc> getOccurrences() {
    return occurrences;
  }

  public void setOccurrences(List<EventOcc> occurrences) {
    this.occurrences = occurrences;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return Objects.equals(this.eventId, event.eventId) &&
        Objects.equals(this.title, event.title) &&
        Objects.equals(this.description, event.description) &&
        Objects.equals(this.genere, event.genere) &&
        Objects.equals(this.artistNames, event.artistNames) &&
        Objects.equals(this.occurrences, event.occurrences);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventId, title, description, genere, artistNames, occurrences);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Event {\n");
    
    sb.append("    eventId: ").append(toIndentedString(eventId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    genere: ").append(toIndentedString(genere)).append("\n");
    sb.append("    artistNames: ").append(toIndentedString(artistNames)).append("\n");
    sb.append("    occurrences: ").append(toIndentedString(occurrences)).append("\n");
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
