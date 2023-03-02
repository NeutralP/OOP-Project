import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Festival;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FestivalDetailController implements Initializable {
    @FXML
    Label IdLabel;
    @FXML
    Label NameLabel;
    @FXML
    Label DateLabel;
    @FXML
    Label LocationLabel;
    @FXML
    Label DetailLabel;
    @FXML
    Label NoteLabel;
    @FXML
    Label NvatLabel;
    @FXML
    Label FirstDateLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void SetFestival(Festival festival){
        IdLabel.setText(String.valueOf(festival.getId()));
        NameLabel.setText(festival.getName());
        DateLabel.setText(festival.getDate());
        LocationLabel.setText(festival.getLocation());
        DetailLabel.setText(festival.getDetail());
        NoteLabel.setText(festival.getNote());
        NvatLabel.setText(festival.getRelatedNvat());
        FirstDateLabel.setText(festival.getFirstDate());
    }

    public void changeScreenBacktoFes(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("FestivalTableView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}