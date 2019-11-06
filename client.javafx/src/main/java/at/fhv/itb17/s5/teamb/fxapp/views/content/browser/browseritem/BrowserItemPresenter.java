package at.fhv.itb17.s5.teamb.fxapp.views.content.browser.browseritem;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterface;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Function;

public class BrowserItemPresenter implements Initializable {

    @FXML
    private Label genreL;
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
    private TableColumn<EvOccurrenceDTO, String> dateCol;
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
    private TableColumn<EvOccurrenceDTO, Button> actionCol;

    private Consumer<EvOccurrenceDTO> current;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        dateCol.setCellValueFactory(dto -> new SimpleStringProperty(dto.getValue().getDate().format(f)));
        timeCol.setCellValueFactory(dto -> new SimpleObjectProperty<>(dto.getValue().getTime()));
        locationCol.setCellValueFactory(dto -> new SimpleStringProperty(this.getDisplayableAddressString(dto.getValue())));
        ticketsCol.setCellValueFactory(dto -> new SimpleStringProperty(this.getTicketQuantities(dto.getValue())));
        typeCol.setCellValueFactory(dto -> new SimpleStringProperty(dto.getValue().getCategoryCalcDataDTO().getTicketTypes()));
        priceRangeCol.setCellValueFactory(dto -> new SimpleStringProperty(dto.getValue().getCategoryCalcDataDTO().getPriceRangeString()));
        actionCol.setCellFactory(ActionButtonTableCell.forTableColumn("Show Details", (EvOccurrenceDTO dto) -> current.accept(dto)));
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
    private String getDisplayableAddressString(@NotNull EvOccurrenceDTO value) {
        return value.getCountry() + " " + value.getCity();
    }

    public void setEventData(@NotNull EventDTO evt, Consumer<EvOccurrenceDTO> nav) {
        current = nav;
        List<EvOccurrenceDTO> occurrences = evt.getOccurrences();
        occurrenceTable.setItems(FXCollections.observableList(occurrences));
        evtTitleL.setText(evt.getTitle());
        artistNameL.setText(String.join(", ", evt.getArtistNames()));
        genreL.setText(evt.getGenre());
        occurrences.sort(Comparator.comparing(EvOccurrenceDTO::getDate));
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        if (!occurrences.isEmpty()) {
            dateFromL.setText(occurrences.get(0).getDate().format(f));
            dateUntilL.setText(occurrences.get(occurrences.size() - 1).getDate().format(f));
        }
    }


    public static class ActionButtonTableCell<S> extends TableCell<S, Button> {

        private final Button actionButton;

        public ActionButtonTableCell(String label, Consumer<S> consumer) {
            this.getStyleClass().add("action-button-table-cell");

            this.actionButton = new Button(label);
            this.actionButton.setOnAction((ActionEvent e) -> consumer.accept(getCurrentItem()));
            this.actionButton.setMaxWidth(Double.MAX_VALUE);
        }

        public S getCurrentItem() {
            return (S) getTableView().getItems().get(getIndex());
        }

        public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> forTableColumn(String label, Consumer<S> consumer) {
            return param -> new ActionButtonTableCell<>(label, consumer);
        }

        @Override
        public void updateItem(Button item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(actionButton);
            }
        }
    }
}
