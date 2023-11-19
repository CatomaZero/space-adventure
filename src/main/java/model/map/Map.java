package model.map;

import structure.List.ListGraph;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    private final int SIZE = 50;
    private ListGraph<Integer> map;
    private ArrayList<Enviroment> enviroments;

    public Map(){
        map = new ListGraph<>();
        initializeMap();
    }

    public void initializeMap(){
        for(int i = 0; i < SIZE; i++){
            map.addNode(i);
            if(((i+1) % 5) == 0){
                enviroments.add(new Station(i));
            } else {
                enviroments.add(new Planet(i));
            }
        }

        Random random = new Random();
        for (int i = 1; i <= SIZE; i++) {
            int numEdges = 1 + random.nextInt(3);
            for (int j = 0; j < numEdges; j++) {
                int targetNode = random.nextInt(50);
                double weight = random.nextDouble();
                map.addEdge(i, targetNode, weight);
            }
        }
    }
}
