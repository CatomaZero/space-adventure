package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.GameController;
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

    private GameController controller;

    private Tooltip planetTooltip;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GraphicsContext gc = mapCanvas.getGraphicsContext2D();

        gc.setFill(Color.web("#001020"));
        gc.fillRect(0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());

        map = new Map();
        controller = new GameController();
        drawGraph();

        planetTooltip = new Tooltip();

        mapCanvas.setOnMouseClicked(this::handleMouseClick);
        mapCanvas.setOnMouseMoved(this::handleMouseMoved);
    }

    private void handleMouseMoved(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        int closestNode = findClosestNode(mouseX, mouseY);

        if(closestNode != -1){
            showPlanetInfoTooltip(map.getEnviroments().get(closestNode), event.getScreenX(), event.getScreenY());
        } else {
            planetTooltip.hide();
        }
    }

    private void showPlanetInfoTooltip(Enviroment e, double screenX, double screenY) {
        planetTooltip.setText(e.getName());
        planetTooltip.show(mapCanvas, screenX, screenY);
    }

    private void handleMouseClick(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        // Encontrar el nodo más cercano a las coordenadas del clic
        int closestNode = findClosestNode(mouseX, mouseY);

        // Se busca donde está el jugador para poder hacer la busquedad
        int playerPlace=findClosestNode(Player.getInstance().getX(),Player.getInstance().getY());

        //Marca el camino de conexión desde el planeta actual hasta 3 planetas de distancia para luego verificar si existe un camino para llegar al planeta destino
        String way=verifyClosestNodeDFS(closestNode,playerPlace);

        //Verificar que en el caso que sí haya conexiones entre planetas, como se deberá hacer el viaje
        doMove(way,closestNode);

        System.out.println("lugar de inicio: "+playerPlace+" lugar destino: "+closestNode);

        if(howToMove(closestNode,playerPlace)) {
            // Mover al jugador a la posición del nodo más cercano
            movePlayerToNode(closestNode);
        }

        // Volver a dibujar el gráfico
        drawGraph();
    }

    private int findClosestNode(double mouseX, double mouseY) {
        int closestNode = -1;
        double minDistance = Double.MAX_VALUE;

        for (Enviroment environment : map.getEnviroments()) {
            double distance = Math.sqrt(Math.pow(mouseX - environment.getX(), 2) + Math.pow(mouseY - environment.getY(), 2));

            // Verifica si la distancia es menor o igual a 40
            if (distance <= 40 && distance < minDistance) {
                minDistance = distance;
                closestNode = environment.getKey();
            }
        }

        return closestNode;
    }

    public String verifyClosestNodeDFS(int closestNode, int playerNode){
        return controller.verifyClosestNodeDFS(closestNode,playerNode,map);
    }

    public void doMove(String way, int key){
        controller.doMove(way,key,map.getMap());
    }

    public boolean howToMove(int key,int playerPlace){
        String[] ways=controller.getWay();
        int cont=0;

        for (String way : ways) {
            if (way != null) {
                cont++;
                System.out.println(way);
            }
        }
        System.out.println(cont);
        String[] movement=new String[cont];

        int j=0;

        for (String way : ways) {
            if (way != null&&cont>0) {
                movement[j]=way;
                j++;
                cont--;
            }
        }
        if(j>0) {
            if (movement[0] != null && movement[movement.length - 1] != null && Integer.parseInt(movement[0]) == playerPlace && Integer.parseInt(movement[movement.length - 1]) == key) {
                Player.getInstance().setTravel(movement);
                HelloApplication.showWindow("travel", null);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("You can not travel that far :b");
                alert.setHeaderText("Nuh huh :b");
                alert.setContentText("You can only select trips to planets that are no more than 3 planets away.");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("You can not travel that far :b");
            alert.setHeaderText("Nuh huh :b");
            alert.setContentText("You can only select trips to planets that are no more than 3 planets away.");
            alert.showAndWait();
        }
        return false;
    }

    private void movePlayerToNode(int nodeId) {
        // Mover al jugador a la posición del nodo
        Enviroment node = map.getEnviroments().get(nodeId);
        Player.getInstance().setCoordinates(node.getX(), node.getY());
    }

    private void drawGraph() {
        GraphicsContext gc = mapCanvas.getGraphicsContext2D();

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

        // Ajustar los valores RGB para obtener colores más claros
        int red = random.nextInt(128) + 128;     // Rango: 128-255
        int green = random.nextInt(128) + 128;   // Rango: 128-255
        int blue = random.nextInt(128) + 128;    // Rango: 128-255

        return Color.rgb(red, green, blue);
    }
}