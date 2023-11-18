package structure.Graph1;

import java.util.ArrayList;
public class Node<K> {
    private K key;
    private ArrayList<Edge<K>> edges;

    public Node(K key){
        this.key = key;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Edge<K> e){
        edges.add(e);
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

    @Override
    public String toString() {
        String result = "Key: " +  key + "\nEdges: ";
        for (Edge<K> e: edges ) {
            result += "\n     " + e.toString();
        }
        return result;
    }
}