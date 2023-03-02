import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Dynasty;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DynastyDetailController implements Initializable {
    @FXML
    Label IdLabel;
    @FXML
    Label NameLabel;
    @FXML
    Label KingNameLabel;
    @FXML
    Label LunarLabel;
    @FXML
    Label DateLabel;
    @FXML
    Label ChineseNameLabel;
    @FXML
    Label ChinesePeriodLabel;
    @FXML
    Label desLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void SetDynasty(Dynasty dynasty){
        IdLabel.setText(String.valueOf(dynasty.getId()));
        NameLabel.setText(dynasty.getName());
        KingNameLabel.setText(dynasty.getKingname());
        LunarLabel.setText(dynasty.getLunardate());
        DateLabel.setText(dynasty.getDate());
        ChineseNameLabel.setText(dynasty.getChinesename());
        ChinesePeriodLabel.setText(dynasty.getChineseperiod());
        desLabel.setText(dynasty.getDescription());
    }

    public void changeScreenBacktoEv(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("DynastyTableView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}