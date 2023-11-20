package model;

import model.map.Map;
import structure.List.ListGraph;

public class GameController {

    public GameController(){

    }

    public String verifyClosestNodeDFS(int closestNode,int playerNode, Map mapUsing){
        ListGraph<Integer> map=mapUsing.getMap();
        return map.DFS(closestNode,playerNode);
    }

}
