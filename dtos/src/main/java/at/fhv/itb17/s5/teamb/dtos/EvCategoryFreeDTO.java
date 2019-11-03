package at.fhv.itb17.s5.teamb.dtos;

public class EvCategoryFreeDTO implements EvCategoryInterface {
    private Long eventCategoryId;
    private String categoryName;
    private int priceInCent;
    private Integer totalSpace;
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
