package at.fhv.itb17.s5.teamb.dtos;

import java.util.List;

public class EvCategorySeatsDTO {
    private Long eventCategoryId;
    private String categoryName;
    private int priceInCent;
    private List<LocationRowDTO> seatingRows;

    public EvCategorySeatsDTO(Long eventCategoryId, String categoryName, int priceInCent, List<LocationRowDTO> seatingRows) {
        this.eventCategoryId = eventCategoryId;
        this.categoryName = categoryName;
        this.priceInCent = priceInCent;
        this.seatingRows = seatingRows;
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

    public List<LocationRowDTO> getSeatingRows() {
        return seatingRows;
    }
}
