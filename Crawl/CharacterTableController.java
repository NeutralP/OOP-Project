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
import model.Person_King2;
import model.Person_WarHero;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;

import com.google.gson.Gson;

public class CharacterTableController implements Initializable {

    private String Stringresult;
    private String Stringresult2;

    @FXML
    private TextField filterField;

    @FXML
    private TextField filterField2;

    @FXML
    private TableView<Person_King2> table;

    @FXML
    private TableColumn<Person_King2, Integer> idColumn;

    @FXML
    private TableColumn<Person_King2, String> nameColumn;

    @FXML
    private TableColumn<Person_King2, String> DateColumn;

    @FXML
    private TableColumn<Person_King2, String> NienhieuColumn;

    @FXML
    private TableColumn<Person_King2, String> MienhieuColumn;

    @FXML
    private TableColumn<Person_King2, String> ThuyColumn;

    @FXML
    private TableColumn<Person_King2, String> PeriodColumn;

    @FXML
    private TableView<Person_WarHero> table2;

    @FXML
    private TableColumn<Person_WarHero, Integer> id2Column;

    @FXML
    private TableColumn<Person_WarHero, String> name2Column;

    @FXML
    private TableColumn<Person_WarHero, String> Date2Column;

    @FXML
    private TableColumn<Person_WarHero, String> Eth2Column;

    @FXML
    private TableColumn<Person_WarHero, String> Release2Column;

    @FXML
    private TableColumn<Person_WarHero, String> Bitrh2Column;

    @FXML
    private TableColumn<Person_WarHero, String> Achieve2Column;

    @FXML
    private TableColumn<Person_WarHero, String> Des2Column;

    // @FXML
    // private TableColumn<Person_King2, String> ChinesePeriodColumn;

    // @FXML
    // private TableColumn<Person_King2, String> DesColumn;

    private ObservableList<Person_King2> Person_King2List;
    private JSONArray jsonArray;
    private Gson gson;
    Person_King2[] KingList;

    private ObservableList<Person_WarHero> Person_WarHeroList;
    private JSONArray jsonArray2;
    private Gson gson2;
    Person_WarHero[] WarHeroList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jsonArray = ReadJSONandObject.readDataFromJSON("historical_king.json");
        Stringresult = jsonArray.toString(4);
        gson = new Gson();
        KingList = gson.fromJson(Stringresult, Person_King2[].class);

        Person_King2List = FXCollections.observableArrayList(KingList);
        idColumn.setCellValueFactory(new PropertyValueFactory<Person_King2, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Person_King2, String>("name"));
        NienhieuColumn.setCellValueFactory(new PropertyValueFactory<Person_King2, String>("nienhieu"));
        // DateColumn.setCellValueFactory(new PropertyValueFactory<Person_King2,
        // String>("date"));
        // detailColumn.setCellValueFactory(new PropertyValueFactory<Person_King2,
        // String>("detail"));
        // noteColumn.setCellValueFactory(new PropertyValueFactory<Person_King2,
        // String>("Note"));
        // nvatColumn.setCellValueFactory(new PropertyValueFactory<Person_King2,
        // String>("relatedNvat"));
        // FirstDateColumn.setCellValueFactory(new PropertyValueFactory<Person_King2,
        // String>("firstDate"));

        FilteredList<Person_King2> filteredData = new FilteredList<>(Person_King2List, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (person.getNienhieu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        SortedList<Person_King2> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

        jsonArray2 = ReadJSONandObject.readDataFromJSON("historical_person.json");
        Stringresult2 = jsonArray2.toString(4);
        gson2 = new Gson();
        WarHeroList = gson2.fromJson(Stringresult2, Person_WarHero[].class);

        Person_WarHeroList = FXCollections.observableArrayList(WarHeroList);
        id2Column.setCellValueFactory(new PropertyValueFactory<Person_WarHero, Integer>("id"));
        name2Column.setCellValueFactory(new PropertyValueFactory<Person_WarHero, String>("name"));
        Date2Column.setCellValueFactory(new PropertyValueFactory<Person_WarHero, String>("date"));
        // Eth2Column.setCellValueFactory(new PropertyValueFactory<Person_WarHero,
        // String>("eth"));

        FilteredList<Person_WarHero> filteredData2 = new FilteredList<>(Person_WarHeroList, b -> true);
        filterField2.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData2.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (person.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        SortedList<Person_WarHero> sortedData2 = new SortedList<>(filteredData2);
        sortedData2.comparatorProperty().bind(table2.comparatorProperty());
        table2.setItems(sortedData2);

    }

    public void changeScreenBack(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        // This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void changeScreentoKingDetail(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("KingDetail.fxml"));
        Parent tableViewParent = loader.load();
        KingDetailController controller = loader.getController();
        Person_King2 selected = table.getSelectionModel().getSelectedItem();
        controller.SetKing(selected);
        // This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene tableViewScene = new Scene(tableViewParent);
        window.setScene(tableViewScene);
        window.show();
    }

    public void changeScreentoHeroDetail(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HeroDetail.fxml"));
        Parent tableViewParent = loader.load();
        HeroDetailController controller = loader.getController();
        Person_WarHero selected = table2.getSelectionModel().getSelectedItem();
        controller.SetHero(selected);
        // This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene tableViewScene = new Scene(tableViewParent);
        window.setScene(tableViewScene);
        window.show();
    }

}
