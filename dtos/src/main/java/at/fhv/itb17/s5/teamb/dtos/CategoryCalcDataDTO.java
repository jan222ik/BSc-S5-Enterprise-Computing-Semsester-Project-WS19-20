package at.fhv.itb17.s5.teamb.dtos;

import java.io.Serializable;

public class CategoryCalcDataDTO implements Serializable {

    private int minPrice = Integer.MAX_VALUE;
    private int maxPrice = Integer.MIN_VALUE;
    private String ticketTypes = "";

    public String getPriceRangeString() {
        return minPrice / 100.0 + "€ / " + maxPrice / 100.0 + "€";
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = Integer.min(this.minPrice, minPrice);
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = Integer.max(this.maxPrice, maxPrice);
    }

    public String getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(String ticketTypes) {
        this.ticketTypes = ticketTypes;
    }
}
