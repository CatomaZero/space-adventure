package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HowToPlayController implements Initializable {

    @FXML
    private Canvas canvas;

    @FXML
    private Button home;

    @FXML
    private Button play;

    @FXML
    private void onHome() {
        HelloApplication.hideWindow((Stage) play.getScene().getWindow());
        HelloApplication.showWindow("hello-view", null);
    }

    @FXML
    private void onPlay() {
        HelloApplication.hideWindow((Stage) play.getScene().getWindow());
        HelloApplication.showWindow("play", null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.web("#001020"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(new Image(getClass().getResource(("/assets/background/howToPlay.png")).toExternalForm()), 0, 0, 1000, 700);

    }
}