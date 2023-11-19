package model.map;

import structure.List.Edge;
import structure.List.ListGraph;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    public static final int SIZE = 50;
    public static final int MAX_EDGES = 1;
    private ListGraph<Integer> map;
    private ArrayList<Enviroment> enviroments;

    public Map() {
        map = new ListGraph<>();
        enviroments = new ArrayList<>();
        initializeMap();
    }

    public void initializeMap() {
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
            enviroments.add(new Planet(nodeId, x, y));
        }

        for (int i = 0; i < SIZE; i++) {
            int numConnections = 1 + random.nextInt(MAX_EDGES);
            for (int j = 0; j < numConnections; j++) {
                int targetNode;
                do {
                    targetNode = random.nextInt(SIZE);
                } while (i == targetNode || map.hasEdge(i, targetNode));

                double weight = random.nextDouble();
                map.addEdge(i, targetNode, weight);
            }
        }
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
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (Edge<Integer> edge : map.searchNode(nodeId).getEdges()) {
            int neighborId = (edge.getInitial().getKey() == nodeId) ? edge.getTerminal().getKey() : edge.getInitial().getKey();
            neighbors.add(neighborId);
        }
        return neighbors;
    }

    public int size(){
        return map.size();
    }
}