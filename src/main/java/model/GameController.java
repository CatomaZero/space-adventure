package model;

import model.map.Map;
import structure.List.Edge;
import structure.List.ListGraph;
import structure.List.Node;

import java.util.Arrays;


public class GameController {

    private String[] way;

    public GameController(){
        way=new String[20];
    }

    public String verifyClosestNodeDFS(int closestNode,int playerNode, Map mapUsing){
        ListGraph<Integer> map=mapUsing.getMap();
        return map.DFS(closestNode,playerNode,3);
    }

    public void doMove(String way,int key, ListGraph<Integer> map){
        String[] nodes = way.split(" ");
        System.out.println(Arrays.toString(nodes));
        if(allowMove(nodes,key)){
            System.out.println("entre");
            makeAWay(nodes,Integer.parseInt(nodes[0]),key,3,map,1);
        }
    }

    private boolean allowMove(String[] nodes, int key){
        for (String node : nodes) {
            if(!node.isEmpty()&&!verifyWay(key)) {
                if (Integer.parseInt(node) == key) {
                    return true;
                }
            }
        }
        return false;
    }

    public void makeAWay(String[] nodes,int root ,int key,int times,ListGraph<Integer> map, int place){
        if(place<nodes.length) {
            System.out.println("entre2");
            Node<Integer> current = map.searchNode(root);
            System.out.println(current.getKey());
            for (Edge<Integer> edge : current.getEdges()) {
                if (allowMove(map.DFS(key, root, times).split(" "), key)) {
                    System.out.println("entre 3 "+root);
                    add(map.DFS(key, root, times).split(" "));
                }
                makeAWay(nodes, Integer.parseInt(nodes[place]), key, times - 1, map, place + 1);
            }
        }
    }

    private void add(String[] nodes){
        for(int i=0; i<nodes.length;i++){
            if(way[i]==null){
                System.out.println("entra a escribir");
                way[i]=nodes[i];
            }
        }
    }

    private boolean verifyWay(int key){
        for (String s : way) {
            if(s!=null) {
                if (Integer.parseInt(s) == key) {
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
