package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.entities.Player;
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

        // Agregar un EventHandler para capturar clics del mouse en el mapCanvas
        mapCanvas.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        // Encontrar el nodo más cercano a las coordenadas del clic
        int closestNode = findClosestNode(mouseX, mouseY);

        // Mover al jugador a la posición del nodo más cercano
        movePlayerToNode(closestNode);

        // Volver a dibujar el gráfico
        drawGraph();
    }

    private int findClosestNode(double mouseX, double mouseY) {
        // Encontrar el nodo más cercano a las coordenadas del clic
        int closestNode = -1;
        double minDistance = Double.MAX_VALUE;

        for (Enviroment environment : map.getEnviroments()) {
            double distance = Math.sqrt(Math.pow(mouseX - environment.getX(), 2) + Math.pow(mouseY - environment.getY(), 2));
            if (distance < minDistance) {
                minDistance = distance;
                closestNode = environment.getKey();
            }
        }

        return closestNode;
    }

    private void movePlayerToNode(int nodeId) {
        // Mover al jugador a la posición del nodo
        Enviroment node = map.getEnviroments().get(nodeId);
        Player.getInstance().setCoordinates(node.getX(), node.getY());
    }

    private void drawGraph() {
        GraphicsContext gc = mapCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());

        for (int i = 0; i < map.size(); i++) {
            Enviroment sourceEnv = map.getEnviroments().get(i);

            for (Integer neighbor : map.getNeighbors(i)) {
                if (i < neighbor) {
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
            environment.drawEnviroment(gc);
        }

        Player.getInstance().drawPlayer(gc);
    }

    private Color generateRandomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}