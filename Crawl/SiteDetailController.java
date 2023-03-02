import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.HistoricalSite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SiteDetailController implements Initializable {
    @FXML
    Label IdLabel;
    @FXML
    Label NameLabel;
    // @FXML
    // Label KingNameLabel;
    // @FXML
    // Label LunarLabel;
    @FXML
    Label DateLabel;
    @FXML
    Label LocationLabel;
    // @FXML
    // Label ChinesePeriodLabel;
    // @FXML
    // Label desLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void SetSite(HistoricalSite site){
        IdLabel.setText(String.valueOf(site.getId()));
        NameLabel.setText(site.getName());
        // KingNameLabel.setText(site.getKingname());
        // LunarLabel.setText(site.getLunardate());
        DateLabel.setText(site.getDate());
        // ChineseNameLabel.setText(site.getChinesename());
        // ChinesePeriodLabel.setText(site.getChineseperiod());
        LocationLabel.setText(site.getLocation());
    }

    public void changeScreenBacktoSite(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("SiteTableView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}