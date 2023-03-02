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
import model.Festival;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;

import com.google.gson.Gson;

public class FestivalTableController implements Initializable {

    private String Stringresult;

    @FXML
    private TextField filterField;

    @FXML
    private TableView<Festival> table;

    @FXML
    private TableColumn<Festival, Integer> idColumn;

    @FXML
    private TableColumn<Festival, String> nameColumn;

    @FXML
    private TableColumn<Festival, String> dateColumn;

    @FXML
    private TableColumn<Festival, String> locationColumn;

    @FXML
    private TableColumn<Festival, String> detailColumn;

    @FXML
    private TableColumn<Festival, String> noteColumn;

    @FXML
    private TableColumn<Festival, String> nvatColumn;

    @FXML
    private TableColumn<Festival, String> FirstDateColumn;

    private ObservableList<Festival> festivalList;
    private JSONArray jsonArray;
    private Gson gson;
    Festival[] LehoiList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jsonArray = ReadJSONandObject.readDataFromJSON("historical_festival.json");
        Stringresult = jsonArray.toString(4);
        gson = new Gson();
        LehoiList = gson.fromJson(Stringresult, Festival[].class);

        festivalList = FXCollections.observableArrayList(LehoiList);
        idColumn.setCellValueFactory(new PropertyValueFactory<Festival, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Festival, String>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Festival, String>("date"));
        // locationColumn.setCellValueFactory(new PropertyValueFactory<Festival,
        // String>("location"));
        // detailColumn.setCellValueFactory(new PropertyValueFactory<Festival,
        // String>("detail"));
        // noteColumn.setCellValueFactory(new PropertyValueFactory<Festival,
        // String>("Note"));
        // nvatColumn.setCellValueFactory(new PropertyValueFactory<Festival,
        // String>("relatedNvat"));
        // FirstDateColumn.setCellValueFactory(new PropertyValueFactory<Festival,
        // String>("firstDate"));
        FilteredList<Festival> filteredData = new FilteredList<>(festivalList, p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(festival -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (festival.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (festival.getDate().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Festival> sortedData = new SortedList<>(filteredData);
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FestivalDetail.fxml"));
        Parent tableViewParent = loader.load();
        FestivalDetailController controller = loader.getController();
        Festival selected = table.getSelectionModel().getSelectedItem();
        controller.SetFestival(selected);
        // This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene tableViewScene = new Scene(tableViewParent);
        window.setScene(tableViewScene);
        window.show();
    }

}
