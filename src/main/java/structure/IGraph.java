package structure;

public interface IGraph<K> {
    String addNode(K key);

    String addEdge(K keyInitial, K keyTerminal, Double weight);

    String deleteNode(K key);

    String deleteEdge(K keyInitial, K keyTerminal);

    String consult();

    String consultNode(K key);

    String consultEdge(K keyInitial, K keyTerminal);

    String BFS(K key);

    String DFS(K key);

    double[] dijkstra(K key);

    double[][] floydWarshall();

    String getFloydWarshallResultString();

    String primMST();

    String kruskalMST();
}