package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        showWindow("hello-view",stage);
    }
    public static void showWindow(String fxml, Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml+".fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1000, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(stage==null) stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Space Adventure ******");
        stage.show();
    }
    public static void hideWindow(Stage stage){
        stage.hide();
    }
    public static void main(String[] args) {
        launch();
    }
}
