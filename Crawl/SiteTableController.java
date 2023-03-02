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
import model.HistoricalSite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;

import com.google.gson.Gson;

public class SiteTableController implements Initializable {

    private String Stringresult;

    @FXML
    private TextField filterField;

    @FXML
    private TableView<HistoricalSite> table;

    @FXML
    private TableColumn<HistoricalSite, Integer> idColumn;

    @FXML
    private TableColumn<HistoricalSite, String> nameColumn;

    // @FXML
    // private TableColumn<HistoricalSite, String> KingnameColumn;

    // @FXML
    // private TableColumn<HistoricalSite, String> LunarColumn;

    @FXML
    private TableColumn<HistoricalSite, String> DateColumn;

    @FXML
    private TableColumn<HistoricalSite, String> LocationColumn;

    // @FXML
    // private TableColumn<HistoricalSite, String> ChinesePeriodColumn;

    // @FXML
    // private TableColumn<HistoricalSite, String> DesColumn;

    private ObservableList<HistoricalSite> HistoricalSiteList;
    private JSONArray jsonArray;
    private Gson gson;
    HistoricalSite[] ThoidaiList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jsonArray = ReadJSONandObject.readDataFromJSON("historical_site.json");
        Stringresult = jsonArray.toString(4);
        gson = new Gson();
        ThoidaiList = gson.fromJson(Stringresult, HistoricalSite[].class);

        HistoricalSiteList = FXCollections.observableArrayList(ThoidaiList);
        idColumn.setCellValueFactory(new PropertyValueFactory<HistoricalSite, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<HistoricalSite, String>("name"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<HistoricalSite, String>("location"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<HistoricalSite, String>("date"));
        // detailColumn.setCellValueFactory(new PropertyValueFactory<HistoricalSite,
        // String>("detail"));
        // noteColumn.setCellValueFactory(new PropertyValueFactory<HistoricalSite,
        // String>("Note"));
        // nvatColumn.setCellValueFactory(new PropertyValueFactory<HistoricalSite,
        // String>("relatedNvat"));
        // FirstDateColumn.setCellValueFactory(new PropertyValueFactory<HistoricalSite,
        // String>("firstDate"));
        FilteredList<HistoricalSite> filteredData = new FilteredList<>(HistoricalSiteList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(HistoricalSite -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (HistoricalSite.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (HistoricalSite.getLocation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (HistoricalSite.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
        });
        SortedList<HistoricalSite> sortedData = new SortedList<>(filteredData);
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SiteDetail.fxml"));
        Parent tableViewParent = loader.load();
        SiteDetailController controller = loader.getController();
        HistoricalSite selected = table.getSelectionModel().getSelectedItem();
        controller.SetSite(selected);
        // This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene tableViewScene = new Scene(tableViewParent);
        window.setScene(tableViewScene);
        window.show();
    }

}
