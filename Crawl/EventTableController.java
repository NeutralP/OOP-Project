import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Event;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;

import com.google.gson.Gson;

public class EventTableController implements Initializable {

    private String Stringresult;

    @FXML
    private TextField filterField;

    @FXML
    private TableView<Event> table;

    @FXML
    private TableColumn<Event, Integer> idColumn;

    @FXML
    private TableColumn<Event, String> nameColumn;

    @FXML
    private TableColumn<Event, String> StartColumn;

    @FXML
    private TableColumn<Event, String> EndColumn;

    @FXML
    private TableColumn<Event, String> EraColumn;

    @FXML
    private TableColumn<Event, String> LittleColumn;

    private ObservableList<Event> EventList;
    private JSONArray jsonArray;
    private Gson gson;
    Event[] SukienList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jsonArray = ReadJSONandObject.readDataFromJSON("historical_event.json");
        Stringresult = jsonArray.toString(4);
        gson = new Gson();
        SukienList = gson.fromJson(Stringresult, Event[].class);

        EventList = FXCollections.observableArrayList(SukienList);
        idColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        StartColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("startYear"));
        EndColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("endYear"));
        // detailColumn.setCellValueFactory(new PropertyValueFactory<Event,
        // String>("detail"));
        // noteColumn.setCellValueFactory(new PropertyValueFactory<Event,
        // String>("Note"));
        // nvatColumn.setCellValueFactory(new PropertyValueFactory<Event,
        // String>("relatedNvat"));
        // FirstDateColumn.setCellValueFactory(new PropertyValueFactory<Event,
        // String>("firstDate"));
        FilteredList<Event> filteredData = new FilteredList<>(EventList, p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(event -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (event.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (event.getStartYear().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (event.getEndYear().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Event> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

    }

    public void changeScreenBack(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        // This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void changeScreentoFesDetail(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventDetail.fxml"));
        Parent tableViewParent = loader.load();
        EventDetailController controller = loader.getController();
        Event selected = table.getSelectionModel().getSelectedItem();
        controller.SetEvent(selected);
        // This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene tableViewScene = new Scene(tableViewParent);
        window.setScene(tableViewScene);
        window.show();
    }

}
