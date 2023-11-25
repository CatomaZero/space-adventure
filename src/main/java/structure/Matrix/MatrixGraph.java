package structure.Matrix;

import structure.IGraph;
import structure.INode;

import java.security.interfaces.EdECKey;
import java.util.*;

public class MatrixGraph<K> implements IGraph<K> {
    private List<List<Double>> adjacencyMatrix;
    private ArrayList<Node<K>> nodes;
    private ArrayList<Edge<K>> edges;

    private boolean founded;

    public MatrixGraph() {
        this.adjacencyMatrix = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public String addNode(K key) {
        if(searchNode(key) != null){
            return "The addition of this node is not possible as there is one with the same key.";

        }

        Node<K> newNode = new Node<>(key);
        nodes.add(newNode);
        for (List<Double> row : adjacencyMatrix) {
            row.add(Double.POSITIVE_INFINITY);
        }
        ArrayList<Double> newRow = new ArrayList<>(nodes.size());
        for (int i = 0; i < nodes.size(); i++) {
            newRow.add(Double.POSITIVE_INFINITY);
        }
        adjacencyMatrix.add(newRow);
        return "Node added successfully.";
    }

    public String addEdge(K keyInitial, K keyTerminal, Double weight) {
        if(isEmptyGraph()){
            return "Empty graph";
        }

        Node<K> initialNode = searchNode(keyInitial);
        Node<K> terminalNode = searchNode(keyTerminal);

        if (initialNode != null && terminalNode != null) {
            Edge<K> newEdge = new Edge<>(weight, initialNode, terminalNode);
            edges.add(newEdge);
            int initialIndex = nodes.indexOf(initialNode);
            int terminalIndex = nodes.indexOf(terminalNode);
            adjacencyMatrix.get(initialIndex).set(terminalIndex, weight);
            adjacencyMatrix.get(terminalIndex).set(initialIndex, weight);
            return "Edge added successfully.";
        } else {
            return "One or both nodes not found.";
        }
    }

    public boolean isEmptyGraph() {
        for (List<Double> row : adjacencyMatrix) {
            for (Double value : row) {
                if (value != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public String deleteNode(K key) {
        Node<K> nodeToDelete = searchNode(key);

        if (nodeToDelete != null) {
            int index = nodes.indexOf(nodeToDelete);
            nodes.remove(index);
            adjacencyMatrix.remove(index);

            for (List<Double> row : adjacencyMatrix) {
                row.remove(index);
            }

            edges.removeIf(edge -> edge.getInitial().equals(nodeToDelete) || edge.getTerminal().equals(nodeToDelete));

            return "Node deleted successfully.";
        } else {
            return "Node not found";
        }
    }

    public String deleteEdge(K keyInitial, K keyTerminal) {
        Node<K> initialNode = searchNode(keyInitial);
        Node<K> terminalNode = searchNode(keyTerminal);

        if(initialNode == null || terminalNode == null){
            return "One or both nodes not found.";
        }

        int initialIndex = nodes.indexOf(initialNode);
        int terminalIndex = nodes.indexOf(terminalNode);
        if(deleteEdgeAux(initialNode, terminalNode)){
            adjacencyMatrix.get(initialIndex).set(terminalIndex, Double.POSITIVE_INFINITY);
            adjacencyMatrix.get(terminalIndex).set(initialIndex, Double.POSITIVE_INFINITY);

            return "Edge deleted successfully.";
        } else {
            return "Edge not found";
        }
    }

    private boolean deleteEdgeAux(Node<K> node1, Node<K> node2) {
        for (int i = 0; i < edges.size(); i++) {
            Edge<K> edge = edges.get(i);
            if ((edge.getInitial() == node1 && edge.getTerminal() == node2) || (edge.getInitial() == node2 && edge.getTerminal() == node1)) {
                edges.remove(i);
                return true;
            }
        }
        return false;
    }

    public String consult() {
        if(isEmptyGraph()){
            return "";
        }

        String result = "";
        for (Node<K> node : nodes) {
            result += consultNode(node.getKey()) + "\n";
        }
        return result.trim();
    }

    public String consultNode(K key) {
        Node<K> node = searchNode(key);
        if (node != null) {
            String result = key + "\n";
            for(K e : getNeighbors(key)){
                Node<K> neighbor = searchNode(e);
                if(neighbor!=null){
                    Edge<K> edge = findEdge(node, neighbor);
                    if(edge!=null){
                        result += edge.toString() +"\n";
                    }
                }
            }
            return result.trim();
        } else {
            return "Node not found.";
        }
    }

    public String consultEdge(K keyInitial, K keyTerminal) {
        Node<K> initialNode = searchNode(keyInitial);
        Node<K> terminalNode = searchNode(keyTerminal);

        if (initialNode != null && terminalNode != null) {
            Edge<K> edge = findEdge(initialNode, terminalNode);
            if (edge != null) {
                return edge.toString();
            } else {
                return "Edge not found.";
            }
        } else {
            return "One or both nodes not found.";
        }
    }

    public String BFS(K key) {
        Node<K> startNode = searchNode(key);

        if (startNode != null) {
            StringBuilder result = new StringBuilder();
            boolean[] visited = new boolean[nodes.size()];
            Queue<Node<K>> queue = new LinkedList<>();
            queue.add(startNode);
            visited[nodes.indexOf(startNode)] = true;

            while (!queue.isEmpty()) {
                Node<K> current = queue.poll();
                result.append(current.getKey()).append(" ");

                for (int i = 0; i < nodes.size(); i++) {
                    if (adjacencyMatrix.get(nodes.indexOf(current)).get(i) != Double.POSITIVE_INFINITY && !visited[i]) {
                        queue.add(nodes.get(i));
                        visited[i] = true;
                    }
                }
            }

            return result.toString().trim();
        } else {
            return "Node not found.";
        }
    }

    public String DFS(K key, K root, int times) {
        if (isEmptyGraph()) {
            return "Empty graph";
        }

        Node<K> node = searchNode(key);
        if (node == null) {
            return "Node not found";
        }

        boolean[] visited = new boolean[nodes.size()];

        return DFSAux(key, root, visited, times).trim();
    }

    private String DFSAux(K key, K root, boolean[] visited, int times) {
        String result = "";
        Node<K> current = searchNode(root);
        if (current.getKey().equals(key)) {
            result += current.getKey() + " ";
        }
        if (times > 0 && !current.getKey().equals(key)) {
            int index = nodes.indexOf(current);

            visited[index] = true;
            result = current.getKey() + " ";

            for (int i = 0; i < nodes.size(); i++) {
                if (!visited[i] && adjacencyMatrix.get(index).get(i) != Double.POSITIVE_INFINITY) {
                    Node<K> currentV = nodes.get(i);
                    result += DFSAux(key, currentV.getKey(), visited, times - 1);
                }
            }
            return result;
        }
        return result;
    }

    public double[] dijkstra(K key) {
        Node<K> startNode = searchNode(key);

        if (startNode != null) {
            int size = nodes.size();
            double[] distance = new double[size];
            boolean[] visited = new boolean[size];

            for (int i = 0; i < size; i++) {
                distance[i] = Double.POSITIVE_INFINITY;
                visited[i] = false;
            }

            distance[nodes.indexOf(startNode)] = 0;

            for (int count = 0; count < size - 1; count++) {
                int u = minDistance(distance, visited);
                visited[u] = true;

                for (int v = 0; v < size; v++) {
                    if (!visited[v] && adjacencyMatrix.get(u).get(v) != Double.POSITIVE_INFINITY &&
                            distance[u] != Double.POSITIVE_INFINITY && distance[u] + adjacencyMatrix.get(u).get(v) < distance[v]) {
                        distance[v] = distance[u] + adjacencyMatrix.get(u).get(v);
                    }
                }
            }

            return distance;
        } else {
            return new double[0];
        }
    }

    private int minDistance(double[] distance, boolean[] visited) {
        double min = Double.POSITIVE_INFINITY;
        int minIndex = -1;

        for (int v = 0; v < nodes.size(); v++) {
            if (!visited[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    public double[][] floydWarshall() {
        int numNodes = nodes.size();
        double[][] dist = new double[numNodes][numNodes];

        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                dist[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        for (int i = 0; i < numNodes; i++) {
            dist[i][i] = 0;

            for (int j = 0; j < numNodes; j++) {
                if (adjacencyMatrix.get(i).get(j) != Double.POSITIVE_INFINITY) {
                    dist[i][j] = adjacencyMatrix.get(i).get(j);
                }
            }
        }

        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    if (dist[i][k] != Double.POSITIVE_INFINITY && dist[k][j] != Double.POSITIVE_INFINITY) {
                        double temp = dist[i][k] + dist[k][j];
                        if (temp < dist[i][j]) {
                            dist[i][j] = temp;
                        }
                    }
                }
            }
        }

        return dist;
    }

    public String getFloydWarshallResultString() {
        double[][] result = floydWarshall();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (result[i][j] == Double.MAX_VALUE) {
                    stringBuilder.append("INF  ");
                } else {
                    stringBuilder.append(result[i][j]).append("  ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString().trim();
    }

    private String printMST(ArrayList<Edge<K>> mstEdges) {
        StringBuilder result = new StringBuilder();
        for (Edge<K> edge : mstEdges) {
            result.append(edge.toString()).append("\n");
        }
        return result.toString().trim();
    }

    @Override
    public String primMST(K startNodeKey, double gas) {
        int numNodes = nodes.size();
        ArrayList<Edge<K>> mstEdges = new ArrayList<>();
        boolean[] visited = new boolean[numNodes];

        PriorityQueue<Edge<K>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Edge::getWeight));
        Node<K> startNode = nodes.get(0);
        visited[nodes.indexOf(startNode)] = true;

        while (mstEdges.size() < numNodes - 1) {
            int startNodeIndex = nodes.indexOf(startNode);
            for (int i = 0; i < numNodes; i++) {
                if (!visited[i] && adjacencyMatrix.get(startNodeIndex).get(i) != Double.POSITIVE_INFINITY) {
                    Edge<K> edge = new Edge<>(adjacencyMatrix.get(startNodeIndex).get(i), startNode, nodes.get(i));
                    priorityQueue.add(edge);
                }
            }

            Edge<K> minEdge = priorityQueue.poll();
            Node<K> nextNode = minEdge.getTerminal();

            int nextNodeIndex = nodes.indexOf(nextNode);

            if (!visited[nextNodeIndex]) {
                mstEdges.add(minEdge);
                visited[nextNodeIndex] = true;
                startNode = nextNode;
            }
        }

        return printMST(mstEdges);
    }

    public String kruskalMST() {
        ArrayList<Edge<K>> mstEdges = new ArrayList<>();

        // ArrayList<Edge<K>> edges = getAllEdges();
        edges.sort(Comparator.comparingDouble(Edge::getWeight));

        Map<K, K> parent = new HashMap<>();
        for (Node<K> node : nodes) {
            parent.put(node.getKey(), node.getKey());
        }

        for (Edge<K> edge : edges) {
            Node<K> initial = edge.getInitial();
            Node<K> terminal = edge.getTerminal();
            K rootInitial = find(parent, initial.getKey());
            K rootTerminal = find(parent, terminal.getKey());

            if (!rootInitial.equals(rootTerminal)) {
                mstEdges.add(edge);
                union(parent, rootInitial, rootTerminal);
            }
        }

        return printMST(mstEdges);
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public boolean hasEdge(K i, K targetNode) {
        return false;
    }

    @Override
    public ArrayList<K> getNeighbors(K id) {
        ArrayList<K> neighbors = new ArrayList<>();
        for (Edge<K> edge : edges){
            if ((edge.getInitial().getKey() == id || (edge.getTerminal().getKey() == id))) {
                K neighborId = (edge.getInitial().getKey() == id) ? edge.getTerminal().getKey() : edge.getInitial().getKey();
                neighbors.add(neighborId);
            }
        }
        return neighbors;
    }

    private K find(Map<K, K> parent, K nodeKey) {
        if (!parent.get(nodeKey).equals(nodeKey)) {
            parent.put(nodeKey, find(parent, parent.get(nodeKey)));
        }
        return parent.get(nodeKey);
    }

    private void union(Map<K, K> parent, K x, K y) {
        K rootX = find(parent, x);
        K rootY = find(parent, y);
        parent.put(rootX, rootY);
    }

    public Node<K> searchNode(K key) {
        for (Node<K> node : nodes) {
            if (node.getKey().equals(key)) {
                return node;
            }
        }
        return null;
    }

    private Edge<K> findEdge(Node<K> initial, Node<K> terminal) {
        for (Edge<K> edge : edges) {
            if ((edge.getInitial().equals(initial) && edge.getTerminal().equals(terminal)) || (edge.getInitial().equals(terminal) && edge.getTerminal().equals(initial))) {
                return edge;
            }
        }
        return null;
    }
}