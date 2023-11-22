package model;

import model.map.Map;
import structure.List.Edge;
import structure.List.ListGraph;
import structure.List.Node;

import java.util.Arrays;


public class GameController {

    private String[] way;

    private String playerPLace;

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
        this.playerPLace=waySplit[0];
        makeAWay(waySplit,key,map);
        verifyMovement(map);
    }

    //Metodo que hace un camino
    public void makeAWay(String[] way,int key,ListGraph<Integer> map){
        boolean[] visited=new boolean[20];
        int times=3;
        for (int i=0;i<way.length;i++) {
            Node<Integer> currentNode = map.searchNode(Integer.parseInt(way[i]));
            if (verifyWay(currentNode, key, times,map,visited)){
                this.way[i]= String.valueOf(currentNode.getKey());
                times--;
            }
        }
    }

    //Metodo que verifique que el camino lleva al destino
    public boolean verifyWay(Node<Integer> currentNode, int key, int times,ListGraph<Integer> map,boolean[] visited){
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

    public void verifyMovement(ListGraph<Integer> map){
        String movement1="";
        String movement2="";

        for(int i=0;i<way.length-1;i++){
            if(way[i]!=null&&movement1.isEmpty()){
                movement1=way[i];
            }else if(way[i+1]!=null&&movement2.isEmpty()){
                movement2=way[i+1];
            }
            if(!movement1.isEmpty()&&!movement2.isEmpty()){
                if (!map.hasEdge(Integer.parseInt(movement1), Integer.parseInt(movement2))) {
                    removeMovement(movement1);
                }
                movement1="";
                movement2="";
            }
        }
    }

    private void removeMovement(String movement){
        for(String w:way){
            if(w!=null&&w.equals(movement)){
                w=null;
                break;
            }
        }
    }

    public String[] getWay() {
        return way;
    }
}
