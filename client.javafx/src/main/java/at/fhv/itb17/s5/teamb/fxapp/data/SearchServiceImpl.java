package at.fhv.itb17.s5.teamb.fxapp.data;

import java.util.Arrays;
import java.util.LinkedList;

public class SearchServiceImpl implements SearchService {
    @Override
    public LinkedList<PlaceholderDTO> searchFor(String searchQuery) {
        return new LinkedList<>(Arrays.asList(new PlaceholderDTO(), new PlaceholderDTO()));
    }
}
