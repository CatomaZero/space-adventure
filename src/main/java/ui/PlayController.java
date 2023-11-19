package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.map.Enviroment;
import model.map.Map;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class PlayController implements Initializable {

    @FXML
    private Canvas indicators;

    @FXML
    private Canvas mapCanvas;

    private Map map;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        map = new Map();
        drawGraph();
    }

    private void drawGraph() {
        GraphicsContext gc = mapCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());

        for (int i = 0; i < map.size(); i++) {
            Enviroment sourceEnv = map.getEnviroments().get(i);

            for (Integer neighbor : map.getNeighbors(i)) {
                if (i < neighbor) { // Dibujar solo una arista por par de nodos
                    Enviroment targetEnv = map.getEnviroments().get(neighbor);

                    double sourceX = sourceEnv.getX();
                    double sourceY = sourceEnv.getY();
                    double targetX = targetEnv.getX();
                    double targetY = targetEnv.getY();

                    gc.setLineDashes(10);
                    gc.setStroke(generateRandomColor());
                    gc.setLineWidth(2);
                    gc.strokeLine(sourceX, sourceY, targetX, targetY);
                }
            }
        }

        for (Enviroment environment : map.getEnviroments()) {
            double x = environment.getX();
            double y = environment.getY();

            gc.setFill(Color.RED);
            gc.fillOval(x-10, y-10, 20, 20);

            gc.setFill(Color.BLACK);
            gc.fillText(environment.getName(), x, y - 5);
        }
    }


    private Color generateRandomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}