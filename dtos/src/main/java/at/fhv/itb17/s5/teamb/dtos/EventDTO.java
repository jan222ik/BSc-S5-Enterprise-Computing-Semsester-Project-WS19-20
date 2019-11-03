package at.fhv.itb17.s5.teamb.dtos;

import java.io.Serializable;
import java.util.List;

public class EventDTO implements Serializable {
    private long eventId;
    private String title;
    private String description;
    private String genre;

    private List<String> artistNames;

    private List<EvOccurrenceDTO> occurrences;

    //Organizer Flattened
    private Long organizerId;
    private String org_name;
    private String org_email;
    private Long org_addressId;
    private String org_country;
    private String org_zip;
    private String org_city;
    private String org_street;
    private String org_house;

    public EventDTO(long eventId, String title, String description, String genre, List<String> artistNames, List<EvOccurrenceDTO> occurrences, Long organizerId, String org_name, String org_email, Long org_addressId, String org_country, String org_zip, String org_city, String org_street, String org_house) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.artistNames = artistNames;
        this.occurrences = occurrences;
        this.organizerId = organizerId;
        this.org_name = org_name;
        this.org_email = org_email;
        this.org_addressId = org_addressId;
        this.org_country = org_country;
        this.org_zip = org_zip;
        this.org_city = org_city;
        this.org_street = org_street;
        this.org_house = org_house;
    }

    public long getEventId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public List<EvOccurrenceDTO> getOccurrences() {
        return occurrences;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    public String getOrg_name() {
        return org_name;
    }

    public String getOrg_email() {
        return org_email;
    }

    public Long getOrg_addressId() {
        return org_addressId;
    }

    public String getOrg_country() {
        return org_country;
    }

    public String getOrg_zip() {
        return org_zip;
    }

    public String getOrg_city() {
        return org_city;
    }

    public String getOrg_street() {
        return org_street;
    }

    public String getOrg_house() {
        return org_house;
    }

    public List<String> getArtistNames() {
        return artistNames;
    }
}
