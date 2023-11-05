package structure.Graph2;

import structure.Graph1.Edge;
import structure.Graph1.Node;

import java.util.ArrayList;

public class Node2<K,D>{
    private ArrayList<Node2<K,D>> adjacencyList;
    private ArrayList<Double> weights;
    private D data;
    private K key;
    private double weight;
    public Node2(K key, D data){
        this.data=data;
        this.key=key;
        weights=new ArrayList<>();
        adjacencyList=new ArrayList<>();
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public ArrayList<Node2<K,D>> getAdjacencyList() {
        return adjacencyList;
    }
    public D getData() {
        return data;
    }

    public K getKey() {
        return key;
    }

    public void setAdjacencyList(ArrayList<Node2<K,D>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }
    public void setData(D data) {
        this.data = data;
    }
    public Node2<K,D> searchAdjacencyList(Node2<K,D> terminalNode){
        for(Node2<K,D> n:adjacencyList){
            if(n.getKey()==terminalNode.getKey()){
                return n;
            }
        }
        return null;
    }
    public void addInAdjacencyList(Node2<K,D> terminalNode, double weight){
        adjacencyList.add(terminalNode);
        weights.add(weight);
    }
    public String getAdjacencyListToString() {
        String txt = "[";
        for (Node2<K,D> n : adjacencyList) {
            txt+=n.getKey()+" ";
        }
        txt+="]";
        return txt;
    }
    public void deleteReferences(K key){
        for(Node2<K,D> n:adjacencyList){
            if(n.getKey()==key){
                adjacencyList.remove(n);
                break;
            }
        }
    }
    public double searchWeight(K key){
        for (int i=0;i<adjacencyList.size();i++){
            if(adjacencyList.get(i).getKey()==key){
                return weights.get(i);
            }
        }
        return 0;
    }
    @Override
    public String toString() {
        String result = "Key: " +  key + "\nData: " + data.toString() + "\nEdges:";
        return result;
    }
    public String oneEdgeToString(Node2<K,D> terminal){
        return "(" + key + ", " + terminal.getKey() + ")   Weight: " + searchWeight(terminal.getKey());
    }
    public String edgesToString(){
        String txt="";
        for(Node2<K,D> edges:adjacencyList) {
            txt+="\n     (" + key + ", " + edges.getKey() + ")   Weight: " + searchWeight(edges.getKey());
        }
        return txt;
    }
}
