package at.fhv.itb17.s5.teamb.fxapp.data;

import java.util.LinkedList;

public interface SearchService {
    LinkedList<PlaceholderDTO> searchFor(String searchQuery);
}
