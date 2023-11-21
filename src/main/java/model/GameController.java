package model;

import model.map.Map;
import structure.List.Edge;
import structure.List.ListGraph;
import structure.List.Node;

import java.util.Arrays;


public class GameController {

    private String[] way;

    public GameController(){
    }

    public String verifyClosestNodeDFS(int closestNode,int playerNode, Map mapUsing){
        ListGraph<Integer> map=mapUsing.getMap();
        return map.DFS(closestNode,playerNode,3);
    }

    //Metodo que inicializa variables y arranca los metodos
    public void doMove(String way,int key,ListGraph<Integer> map){
        System.out.println("Impresión 1, camino: "+way);
        this.way=new String[20];
        String[] waySplit=way.split(" ");
        makeAWay(waySplit,key,map);
    }

    //Metodo que hace un camino
    public void makeAWay(String[] way,int key,ListGraph<Integer> map){
        int times=3;
        for (int i=0;i<way.length;i++) {
            Node<Integer> currentNode = map.searchNode(Integer.parseInt(way[i]));
            if (verifyWay(currentNode, key, times,map)){
                this.way[i]= String.valueOf(currentNode.getKey());
                times--;
            }
        }
    }

    //Metodo que verifique que el camino lleva al destino
    public boolean verifyWay(Node<Integer> currentNode, int key, int times,ListGraph<Integer> map){
        String[] mayWay=map.DFS(key,currentNode.getKey(),times).split(" ");
        for(String m:mayWay) {
            if (!m.isEmpty()) {
                if (Integer.parseInt(m) == key) {
                    System.out.println(currentNode.getKey() + " " + key);
                    return true;
                }
            }
        }
        return false;
    }

    public String[] getWay() {
        return way;
    }
}
