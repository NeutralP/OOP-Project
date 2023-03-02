import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Event;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventDetailController implements Initializable {
    @FXML
    Label IdLabel;
    @FXML
    Label NameLabel;
    @FXML
    Label StartLabel;
    @FXML
    Label EndLabel;
    @FXML
    Label EraLabel;
    @FXML
    Label LittleLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void SetEvent(Event event){
        IdLabel.setText(String.valueOf(event.getId()));
        NameLabel.setText(event.getName());
        StartLabel.setText(event.getStartYear());
        EndLabel.setText(event.getEndYear());
        EraLabel.setText(event.getTrieuDai());
        LittleLabel.setText(event.getTieuTrieuDai());
    }

    public void changeScreenBacktoEv(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EventTableView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}