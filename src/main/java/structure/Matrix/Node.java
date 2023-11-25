package structure.Matrix;

import structure.INode;
import structure.List.Edge;

import java.util.ArrayList;

public class Node<K> implements INode<K> {
    private K key;
    public Node(K key){
        this.key = key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    @Override
    public ArrayList<Edge<K>> getEdges() {
        return null;
    }
}