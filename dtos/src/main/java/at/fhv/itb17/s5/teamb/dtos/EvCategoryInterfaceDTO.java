package at.fhv.itb17.s5.teamb.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=EvCategorySeatsDTO.class, name = "Seat"),
        @JsonSubTypes.Type(value=EvCategoryFreeDTO.class, name = "Free")
})
public interface EvCategoryInterfaceDTO extends Serializable {

    Long getEventCategoryId();

    String getCategoryName();

    @JsonIgnore
    int getTotalTickets();
    @JsonIgnore
    int getUsedTickets();

    int getPriceInCent();
}
