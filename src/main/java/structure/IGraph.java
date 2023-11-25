package structure;
import java.util.ArrayList;

public interface IGraph<K> {
    String addNode(K key);
    String addEdge(K keyInitial, K keyTerminal, Double weight);
    String deleteNode(K key);
    String deleteEdge(K keyInitial, K keyTerminal);
    String consultNode(K key);
    String consultEdge(K keyInitial, K keyTerminal);
    String BFS(K key);
    String DFS(K key,K root,int times);
    double[] dijkstra(K key);
    double[][] floydWarshall();
    String getFloydWarshallResultString();
    String primMST(K startNodeKey, double gas);
    String kruskalMST();
    int size();
    boolean hasEdge(K i,K targetNode);
    ArrayList<K> getNeighbors(K id);
    INode<K> searchNode(K key);
}