package at.fhv.itb17.s5.teamb.dtos;


import java.io.Serializable;

public interface EvCategoryInterfaceDTO extends Serializable {

    Long getEventCategoryId();

    String getCategoryName();

    int getTotalTickets();

    int getUsedTickets();

    int getPriceInCent();
}
