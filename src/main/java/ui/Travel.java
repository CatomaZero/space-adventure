package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class Travel implements Initializable {

    @FXML
    private TextField travelField;

    @FXML
    private Button acceptButton;

    @FXML
    private Button noAcceptButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StringBuilder travel= new StringBuilder();
        for(String t:Player.getInstance().getTravel()){
            travel.append(t).append(" ");
        }
        travelField.setText(String.valueOf(travel));
    }

    public void onAccept(ActionEvent actionEvent) {
        Player.getInstance().setMove(true);
        HelloApplication.hideWindow((Stage) acceptButton.getScene().getWindow());
    }

    public void onNoAccept(ActionEvent actionEvent) {
        Player.getInstance().setMove(false);
        HelloApplication.hideWindow((Stage) noAcceptButton.getScene().getWindow());
    }
}
