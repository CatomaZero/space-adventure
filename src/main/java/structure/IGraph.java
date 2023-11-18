package structure;

import structure.Graph1.Node;
import structure.Graph2.Node2;

public interface IGraph<K> {
    String addNode(K key);
    String addEdge(K keyInitial, K keyTerminal, Double weight);
    String deleteNode(K key);
    String deleteEdge(K keyInitial, K keyTerminal);
    String consultNode(K key);
    String consultEdge(K keyInitial, K keyTerminal);
    String BFS(K key);
    boolean isStronglyConnected();
}