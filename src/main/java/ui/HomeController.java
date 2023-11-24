package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Canvas canvas;

    @FXML
    private Button exit;

    @FXML
    private Button play;

    @FXML
    private Button howToPlay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.web("#001020"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(new Image(getClass().getResource(("/assets/background/home.png")).toExternalForm()), 0, 0, 1000, 700);

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/font/Fontspring-DEMO-greycliffcf-regular.otf"), 30);
        play.setFont(customFont);
        howToPlay.setFont(customFont);
        exit.setFont(customFont);

        styleButton(play);
        styleButton(howToPlay);
        styleButton(exit);
    }

    private void styleButton(Button btn){
        btn.setOnMouseEntered(event -> {
            btn.setStyle("-fx-background-color: #132D47; -fx-background-radius: 40;-fx-border-color: white; -fx-border-radius: 40; -fx-border-width: 2;");
        });
        btn.setOnMouseExited(event -> {
            btn.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 40; -fx-border-width: 2;");
        });
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