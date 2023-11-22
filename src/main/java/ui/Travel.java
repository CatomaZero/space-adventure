package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.entities.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class Travel implements Initializable {

    @FXML
    private TextField travelField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StringBuilder travel= new StringBuilder();
        for(String t:Player.getInstance().getTravel()){
            travel.append(t).append(" ");
        }
        travelField.setText(String.valueOf(travel));
    }

}
