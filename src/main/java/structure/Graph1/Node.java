package structure.Graph1;

import structure.Graph1.Edge;

import java.util.ArrayList;
public class Node<K, D> {
    private K key;
    private D data;
    private ArrayList<Edge<K, D>> edges;

    public Node(K key, D data){
        this.key = key;
        this.data = data;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Edge<K, D> e){
        edges.add(e);
    }

    public K getKey() {
        return key;
    }

    public D getData() {
        return data;
    }

    public ArrayList<Edge<K, D>> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        String result = "Key: " +  key + "\nData: " + data.toString() + "\nEdges: ";
        for (Edge<K,D> e: edges ) {
            result += "\n     " + e.toString();
        }
        return result;
    }
}