package structure;

import structure.List.Edge;

import java.util.ArrayList;

public interface INode<K> {
    void setKey(K key);
    K getKey();

    ArrayList<Edge<K>> getEdges();
}
