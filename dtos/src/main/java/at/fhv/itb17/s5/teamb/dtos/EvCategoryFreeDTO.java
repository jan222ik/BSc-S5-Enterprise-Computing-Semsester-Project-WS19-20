package at.fhv.itb17.s5.teamb.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class EvCategoryFreeDTO implements EvCategoryInterfaceDTO, Serializable {

    @JsonProperty("eventCategoryId")
    private Long eventCategoryId;
    @JsonProperty("categoryName")
    private String categoryName;
    @JsonProperty("priceInCents")
    private int priceInCent;
    @JsonProperty("totalTickets")
    private Integer totalSpace;
    @JsonProperty("usedTickets")
    private Integer usedSpace;

    public EvCategoryFreeDTO(Long eventCategoryId, String categoryName, int priceInCent, Integer totalSpace, Integer usedSpace) {
        this.eventCategoryId = eventCategoryId;
        this.categoryName = categoryName;
        this.priceInCent = priceInCent;
        this.totalSpace = totalSpace;
        this.usedSpace = usedSpace;
    }

    public Long getEventCategoryId() {
        return eventCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getPriceInCent() {
        return priceInCent;
    }

    public Integer getTotalSpace() {
        return totalSpace;
    }

    public Integer getUsedSpace() {
        return usedSpace;
    }

    @Override
    public int getTotalTickets() {
        return getTotalSpace();
    }

    @Override
    public int getUsedTickets() {
        return getUsedSpace();
    }
}
