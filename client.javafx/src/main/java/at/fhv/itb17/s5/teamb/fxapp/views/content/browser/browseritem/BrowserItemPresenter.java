package at.fhv.itb17.s5.teamb.fxapp.views.content.browser.browseritem;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryFreeDTO;
import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterface;
import at.fhv.itb17.s5.teamb.dtos.EvCategorySeatsDTO;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class BrowserItemPresenter implements Initializable {

    @FXML
    private Label evtTitleL;
    @FXML
    private Label dateFromL;
    @FXML
    private Label dateUntilL;
    @FXML
    private Label artistNameL;
    @FXML
    private TableView<EvOccurrenceDTO> occurrenceTable;
    @FXML
    private TableColumn<EvOccurrenceDTO, LocalDate> dateCol;
    @FXML
    private TableColumn<EvOccurrenceDTO, LocalTime> timeCol;
    @FXML
    private TableColumn<EvOccurrenceDTO, String> locationCol;
    @FXML
    private TableColumn<EvOccurrenceDTO, String> ticketsCol;
    @FXML
    private TableColumn<EvOccurrenceDTO, String> typeCol;
    @FXML
    private TableColumn<EvOccurrenceDTO, String> priceRangeCol;
    @FXML
    private TableColumn<EvOccurrenceDTO, String> actionCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateCol.setCellValueFactory(dto -> new SimpleObjectProperty<>(dto.getValue().getDate()));
        timeCol.setCellValueFactory(dto -> new SimpleObjectProperty<>(dto.getValue().getTime()));
        locationCol.setCellValueFactory(dto -> new SimpleStringProperty(this.getDisplayableAddressString(dto.getValue())));
        ticketsCol.setCellValueFactory(dto -> new SimpleStringProperty(this.getTicketQuantities(dto.getValue())));
        typeCol.setCellValueFactory(dto -> new SimpleStringProperty(this.getTicketTypes(dto.getValue())));
        priceRangeCol.setCellValueFactory(dto -> new SimpleStringProperty(this.getPriceRange(dto.getValue())));
        actionCol.setCellValueFactory(dto -> new SimpleStringProperty("TODO ADD Button for Details"));
    }

    private String getPriceRange(EvOccurrenceDTO value) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (EvCategoryInterface cat : value.getPriceCategories()) {
            cur = cat.getPriceInCent();
            min = Integer.min(min, cur);
            max = Integer.max(max, cur);
        }
        return min/100.0 + "€ / " + max/100.0 + "€";
    }

    @NotNull
    private String getTicketQuantities(@NotNull EvOccurrenceDTO value) {
        int totalSpace = 0;
        int usedSpace = 0;
        for (EvCategoryInterface cat : value.getPriceCategories()) {
            totalSpace += cat.getTotalTickets();
            usedSpace += cat.getUsedTickets();
        }
        return usedSpace + "/" + totalSpace;
    }

    @NotNull
    private String getTicketTypes(@NotNull EvOccurrenceDTO value) {
        boolean[] occs = new boolean[]{false, false};
        for (EvCategoryInterface cat : value.getPriceCategories()) {
            if (!occs[0] && cat instanceof EvCategoryFreeDTO) {
                occs[0] = true;
            }
            if (!occs[1] && cat instanceof EvCategorySeatsDTO) {
                occs[1] = true;
            }
            if (occs[0] && occs[1]) {
                break;
            }
        }
        return ((occs[0]) ? "Free" : "") + ((occs[0] && occs[1]) ? " & " : "") + ((occs[1]) ? "Specific" : "") + " Seating";
    }

    @NotNull
    private String getDisplayableAddressString(@NotNull EvOccurrenceDTO value) {
        return value.getCountry() + " " + value.getCity();
    }

    public void setEventData(@NotNull EventDTO evt) {
        List<EvOccurrenceDTO> occurrences = evt.getOccurrences();
        occurrenceTable.setItems(FXCollections.observableList(occurrences));
        evtTitleL.setText(evt.getTitle());
        //TODO SET ARTIST FIELD
        occurrences.sort(Comparator.comparing(EvOccurrenceDTO::getDate));
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        dateFromL.setText(occurrences.get(0).getDate().format(f));
        dateUntilL.setText(occurrences.get(occurrences.size() - 1).getDate().format(f));
    }
}
