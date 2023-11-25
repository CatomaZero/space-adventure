package model.map;

import javafx.scene.image.Image;
import model.entities.Player;
import structure.IGraph;
import structure.List.Edge;
import structure.List.ListGraph;
import structure.Matrix.MatrixGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    public static final int SIZE = 50;
    public static final int MAX_EDGES = 1;
    private IGraph<Integer> map;
    private ArrayList<Enviroment> enviroments;


    public Map(TypeImplementation imp) {
        enviroments = new ArrayList<>();
        initializeMap(imp);
    }

    public Enviroment getEnviroment(int num){
        for(Enviroment e : enviroments){
            if(e.getKey() == num){
                return e;
            }
        }
        return null;
    }

    public void initializeMap(TypeImplementation imp) {
        if(imp == TypeImplementation.LIST){
            map = new ListGraph<>();
        } else {
            map = new MatrixGraph<>();
        }

        int canvasWidth = 1000 - 100;
        int canvasHeight = 620 - 100;
        int minDistance = 50;

        Random random = new Random();

        for (int i = 0; i < SIZE; i++) {
            int nodeId = i;
            int x, y;

            do {
                x = random.nextInt(canvasWidth) + 50;
                y = random.nextInt(canvasHeight) + 50;
            } while (!isValidPosition(x, y, enviroments, minDistance));

            map.addNode(nodeId);


            if (nodeId % 5 == 0 ){
                Image img = new Image(getClass().getResource(("/assets/station/0.png")).toExternalForm());
                enviroments.add(new Station(nodeId, x, y, img));
            } else {
                Image img = new Image(getClass().getResource(("/assets/planet/" + i + ".png")).toExternalForm());
                enviroments.add(new Planet(nodeId, x, y, img));
            }
        }

        for (int i = 0; i < SIZE; i++) {
            int numConnections = 1 + random.nextInt(MAX_EDGES);
            for (int j = 0; j < numConnections; j++) {
                int targetNode;
                do {
                    targetNode = random.nextInt(SIZE);
                } while (i == targetNode || map.hasEdge(i, targetNode));

                double weight = random.nextInt(15) + 1;
                map.addEdge(i, targetNode, weight);
            }
        }

        // Naranjita
        int indexNarajita = 0;
        do{
            indexNarajita = random.nextInt(size());
            enviroments.get(indexNarajita).setNaranjita(true);
        } while (indexNarajita % 5 == 0);

        // Ramiel
        int indexRamiel = 0;
        do{
            indexRamiel = random.nextInt(size());
        } while (indexRamiel % 5 == 0 || indexRamiel == indexNarajita);

        Player.getInstance().setCoordinates(enviroments.get(indexRamiel).getX(), enviroments.get(indexRamiel).getY());
    }

    private boolean isValidPosition(double x, double y, ArrayList<Enviroment> existingEnviroments, int minDistance) {
        for (Enviroment env : existingEnviroments) {
            double distance = Math.sqrt(Math.pow(x - env.getX(), 2) + Math.pow(y - env.getY(), 2));
            if (distance < minDistance) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Enviroment> getEnviroments() {
        return enviroments;
    }

    public ArrayList<Integer> getNeighbors(int nodeId) {
        return map.getNeighbors(nodeId);
    }

    public int size(){
        return map.size();
    }

    public IGraph<Integer> getMap() {
        return map;
    }
}