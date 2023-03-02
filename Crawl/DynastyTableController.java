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
import model.Dynasty;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;

import com.google.gson.Gson;

public class DynastyTableController implements Initializable {

    private String Stringresult;

    @FXML
    private TextField filterField;

    @FXML
    private TableView<Dynasty> table;

    @FXML
    private TableColumn<Dynasty, Integer> idColumn;

    @FXML
    private TableColumn<Dynasty, String> nameColumn;

    @FXML
    private TableColumn<Dynasty, String> KingnameColumn;

    @FXML
    private TableColumn<Dynasty, String> LunarColumn;

    @FXML
    private TableColumn<Dynasty, String> DateColumn;

    @FXML
    private TableColumn<Dynasty, String> ChineseNameColumn;

    @FXML
    private TableColumn<Dynasty, String> ChinesePeriodColumn;

    @FXML
    private TableColumn<Dynasty, String> DesColumn;

    private ObservableList<Dynasty> DynastyList;
    private JSONArray jsonArray;

    private Gson gson;
    Dynasty[] ThoidaiList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jsonArray = ReadJSONandObject.readDataFromJSON("historical_dynasty.json");
        Stringresult = jsonArray.toString(4);
        gson = new Gson();
        ThoidaiList = gson.fromJson(Stringresult, Dynasty[].class);

        DynastyList = FXCollections.observableArrayList(ThoidaiList);
        idColumn.setCellValueFactory(new PropertyValueFactory<Dynasty, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Dynasty, String>("name"));
        KingnameColumn.setCellValueFactory(new PropertyValueFactory<Dynasty, String>("kingname"));
        LunarColumn.setCellValueFactory(new PropertyValueFactory<Dynasty, String>("lunardate"));
        FilteredList<Dynasty> filteredData = new FilteredList<>(DynastyList, p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(dynasty -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (dynasty.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (dynasty.getKingname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (dynasty.getLunardate().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Dynasty> sortedData = new SortedList<>(filteredData);
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

    public void changeScreentoDyDetail(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DynastyDetail.fxml"));
        Parent tableViewParent = loader.load();
        DynastyDetailController controller = loader.getController();
        Dynasty selected = table.getSelectionModel().getSelectedItem();
        controller.SetDynasty(selected);
        // This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene tableViewScene = new Scene(tableViewParent);
        window.setScene(tableViewScene);
        window.show();
    }

}
