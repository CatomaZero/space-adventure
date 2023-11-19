package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Button exit;

    @FXML
    private Button play;

    @FXML
    private Button howToPlay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void onPlay(ActionEvent event) {
        // Alert para que el usuario elija si desea usar la implementación con lista de adyacencia o matriz
        HelloApplication.hideWindow((Stage) play.getScene().getWindow());
        HelloApplication.showWindow("play", null);
        System.out.println("Play button clicked");
    }

    @FXML
    private void onHowToPlay(ActionEvent event) {
        HelloApplication.hideWindow((Stage) howToPlay.getScene().getWindow());
        HelloApplication.showWindow("how-to-play", null);
        System.out.println("How to play button clicked");
    }

    @FXML
    private void onExit(ActionEvent event) {
        HelloApplication.hideWindow((Stage) exit.getScene().getWindow());
    }
}