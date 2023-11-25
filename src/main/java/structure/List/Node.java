package structure.List;

import structure.INode;

import java.util.ArrayList;
public class Node<K> implements INode<K> {
    private K key;
    private ArrayList<Edge<K>> edges;

    public Node(K key){
        this.key = key;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Edge<K> e){
        edges.add(e);
    }

    @Override
    public void setKey(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public ArrayList<Edge<K>> getEdges() {
        return edges;
    }

    public Edge<K> searchEdge(Node<K> node){
        for(Edge<K> e : edges){
            if(e.getInitial().equals(node) || e.getTerminal().equals(node)){
                return e;
            }
        }
        return null;
    }

    public String toString(){
        String result = key + "\n";
        for (Edge<K> e : edges) {
            result += e.toString() + "\n";
        }
        return result.trim();
    }
}