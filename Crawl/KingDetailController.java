import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Person_King2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KingDetailController implements Initializable {
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
    Label NienhieuLabel;
    @FXML
    Label MienhieuLabel;
    @FXML
    Label ThuyhieuLabel;
    @FXML
    Label PeriodLabel;
    // @FXML
    // Label ChinesePeriodLabel;
    // @FXML
    // Label desLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void SetKing(Person_King2 king){
        IdLabel.setText(String.valueOf(king.getId()));
        NameLabel.setText(king.getName());
        // KingNameLabel.setText(king.getKingname());
        // LunarLabel.setText(king.getLunardate());
        DateLabel.setText(king.getDate());
        // ChineseNameLabel.setText(king.getChinesename());
        // ChinesePeriodLabel.setText(king.getChineseperiod());
        NienhieuLabel.setText(king.getNienhieu());
        MienhieuLabel.setText(king.getMienhieu());
        ThuyhieuLabel.setText(king.getThuyhieu());
        PeriodLabel.setText(king.getPeriod());


    }

    public void changeScreenBacktoChar(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CharacterTableView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}