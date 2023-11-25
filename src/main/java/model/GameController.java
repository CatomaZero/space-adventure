package model;

import model.map.Map;
import structure.IGraph;
import structure.INode;
import structure.List.ListGraph;
import structure.List.Node;

public class GameController {

    private String[] way;

    public GameController(){
    }

    public String verifyClosestNodeDFS(int closestNode,int playerNode, Map mapUsing){
        IGraph<Integer> map=mapUsing.getMap();
        return map.DFS(closestNode,playerNode,3);
    }

    //Metodo que inicializa variables y arranca los metodos
    public void doMove(String way,int key,IGraph<Integer> map){
        System.out.println("Impresión 1, camino: "+way);
        this.way=new String[20];
        String[] waySplit=way.split(" ");
        makeAWay(waySplit,key,map);
        verifyMovement(map);
    }

    //Metodo que hace un camino
    public void makeAWay(String[] way,int key,IGraph<Integer> map){
        boolean[] visited=new boolean[20];
        int times=3;
        for (int i=0;i<way.length;i++) {
            INode<Integer> currentNode = map.searchNode(Integer.parseInt(way[i]));
            if (verifyWay(currentNode, key, times,map,visited)){
                this.way[i]= String.valueOf(currentNode.getKey());
                times--;
            }
        }
    }

    //Metodo que verifique que el camino lleva al destinogit pu
    public boolean verifyWay(INode<Integer> currentNode, int key, int times,IGraph<Integer> map,boolean[] visited){
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

    public void verifyMovement(IGraph<Integer> map){
        String movement1="";
        String movement2="";

        for(int i=0;i<way.length-1;i++){
            System.out.println("Movement: "+way[i]);
            if(way[i]!=null&&movement1.isEmpty()){
                movement1=way[i];
            }else if(way[i]!=null&&movement2.isEmpty()){
                movement2=way[i];
            }
            System.out.println("Movement 1: "+movement1+" Movement 2:"+movement2);
            if(!movement1.isEmpty()&&!movement2.isEmpty()){
                if (!map.hasEdge(Integer.parseInt(movement1), Integer.parseInt(movement2))) {
                    removeMovement(movement1);
                }
                movement1=movement2;
                movement2="";
            }
        }
    }

    private void removeMovement(String movement){
        for (int i = 0; i < way.length; i++) {
            if (way[i] != null && way[i].equals(movement)) {
                way[i] = null;
                break;
            }
        }
    }

    public String[] getWay() {
        return way;
    }
}
