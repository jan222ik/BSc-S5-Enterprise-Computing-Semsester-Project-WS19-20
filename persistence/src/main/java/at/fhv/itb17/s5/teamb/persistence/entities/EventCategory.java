package at.fhv.itb17.s5.teamb.persistence.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class EventCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long eventCategoryId;
    private boolean isFreeSeating;
    private String categoryName;
    private int priceInCent;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<LocationRow> seatingRows;
    private Integer totalSpace;
    private Integer usedSpace;

    public EventCategory() {

    }

    private EventCategory(boolean isFreeSeating, String categoryName, int priceInCent) {
        this.isFreeSeating = isFreeSeating;
        this.categoryName = categoryName;
        this.priceInCent = priceInCent;
    }

    public EventCategory(String categoryName, int priceInCent, List<LocationRow> seatingRows) {
        this(false, categoryName, priceInCent);
        this.seatingRows = seatingRows;
    }

    public EventCategory(String categoryName, int priceInCent, Integer totalSpace, Integer usedSpace) {
        this(true, categoryName, priceInCent);
        this.totalSpace = totalSpace;
        this.usedSpace = usedSpace;
    }

    public Long getEventCategoryId() {
        return eventCategoryId;
    }

    public void setEventCategoryId(Long eventCategoryId) {
        this.eventCategoryId = eventCategoryId;
    }

    public boolean isFreeSeating() {
        return isFreeSeating;
    }

    public void setFreeSeating(boolean freeSeating) {
        isFreeSeating = freeSeating;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getPriceInCent() {
        return priceInCent;
    }

    public void setPriceInCent(int priceInCent) {
        this.priceInCent = priceInCent;
    }

    public List<LocationRow> getSeatingRows() {
        return seatingRows;
    }

    public void setSeatingRows(List<LocationRow> seatingRows) {
        this.seatingRows = seatingRows;
    }

    public Integer getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(Integer totalSpace) {
        this.totalSpace = totalSpace;
    }

    public Integer getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(Integer usedSpace) {
        this.usedSpace = usedSpace;
    }

    public void incUsed() {
        usedSpace++;
    }
}
