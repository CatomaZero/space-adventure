package structure;

import structure.Graph1.Node;
import structure.Graph2.Node2;

public interface IGraph<K, D> {
    String addNode(K key, D data);
    String addEdge(K keyInitial, K keyTerminal, Double weight);
    String deleteNode(K key);
    String deleteEdge(K keyInitial, K keyTerminal);
    String consultNode(K key);
    String consultEdge(K keyInitial, K keyTerminal);
    String BFS(K key);
    boolean isStronglyConnected();
    Node<K, D> searchNode(K key);
    Node2<K, D> searchNode2(K key);
}