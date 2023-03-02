import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Person_WarHero;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HeroDetailController implements Initializable {
    @FXML
    Label IdLabel;
    @FXML
    Label NameLabel;
    // @FXML
    // Label heroNameLabel;
    // @FXML
    // Label LunarLabel;
    @FXML
    Label DateLabel;
    @FXML
    Label EthLabel;
    @FXML
    Label ReleaseLabel;
    @FXML
    Label BirthLabel;
    @FXML
    Label AchieveLabel;
    @FXML
    Label DesLabel;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void SetHero(Person_WarHero hero){
        IdLabel.setText(String.valueOf(hero.getId()));
        NameLabel.setText(hero.getName());
        // heroNameLabel.setText(hero.getheroname());
        // LunarLabel.setText(hero.getLunardate());
        DateLabel.setText(hero.getDate());
        // ChineseNameLabel.setText(hero.getChinesename());
        // ChinesePeriodLabel.setText(hero.getChineseperiod());
        EthLabel.setText(hero.getEthnicity());
        ReleaseLabel.setText(hero.getReleased_year());
        BirthLabel.setText(hero.getBirthplace());
        AchieveLabel.setText(hero.getAchivement());
        DesLabel.setText(hero.getDescription());

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