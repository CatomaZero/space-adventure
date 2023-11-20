package structure.List;

import structure.IGraph;

import java.util.*;

public class ListGraph<K> {
    private ArrayList<Node<K>> adjacency;

    public ListGraph(){
        this.adjacency = new ArrayList<>();
    }

    public String addNode(K key){
        if(searchNode(key)!=null){
            return "The addition of this node is not possible as there is one with the same key.";
        }

        Node<K> v = new Node<>(key);
        adjacency.add(v);

        return "Node added successfully.";
    }

    public String addEdge(K keyInitial, K keyTerminal, Double weight) {
        if (adjacency.isEmpty()) {
            return "Empty graph";
        }

        Node<K> initial = searchNode(keyInitial);
        Node<K> terminal = searchNode(keyTerminal);

        if (initial == null || terminal == null) {
            return "One or both nodes not found.";
        }

        Edge<K> edge = new Edge<>(weight, initial, terminal);
        initial.addEdge(edge);
        terminal.addEdge(edge);

        return "Edge added successfully.";
    }


    public String deleteNode(K key){
        Node<K> node = searchNode(key);
        if(node == null){
            return "Node not found";
        }

        ArrayList<Edge<K>> edges = node.getEdges();
        for (Edge<K> e : edges) {
            Node<K> currentV = node==e.getTerminal()?e.getInitial():e.getTerminal();
            ArrayList<Edge<K>> edg = currentV.getEdges();
            boolean flag = true;
            for (int i = 0; flag && i < edg.size(); i++) {
                if(edg.get(i).getTerminal() == node || edg.get(i).getInitial() == node){
                    edg.remove(edg.get(i));
                    flag = false;
                }
            }
        }

        return "Node deleted successfully.";
    }

    public String deleteEdge(K keyInitial, K keyTerminal) {
        Node<K> initial = searchNode(keyInitial);
        Node<K> terminal = searchNode(keyTerminal);

        if (initial == null || terminal == null) {
            return "One or both nodes not found.";
        }

        if (deleteEdgeAux(initial, terminal) && deleteEdgeAux(terminal, initial)) {
            return "Edge deleted successfully.";
        } else {
            return "Edge not found";
        }
    }

    private boolean deleteEdgeAux(Node<K> node1, Node<K> node2) {
        ArrayList<Edge<K>> edges = node1.getEdges();
        for (int i = 0; i < edges.size(); i++) {
            Edge<K> edge = edges.get(i);
            if ((edge.getInitial() == node1 && edge.getTerminal() == node2) || (edge.getInitial() == node2 && edge.getTerminal() == node1)) {
                edges.remove(i);
                return true;
            }
        }
        return false;
    }

    public String consult(){
        String result = "Nodes: ";
        for (Node<K> node : adjacency) {
            result += node.getKey() + " ";
        }
        result.trim();

        return result.trim();
    }

    public String consultNode(K key){
        Node<K> node = searchNode(key);
        return node!=null?node.toString():"Node not found";
    }

    public Edge<K> consultEdge(K keyInitial, K keyTerminal){
        Node<K> initial = searchNode(keyInitial);
        Node<K> terminal = searchNode(keyTerminal);

        for(Node<K> node : adjacency){
            for(Edge<K> edge : node.getEdges()){
                if((edge.getInitial() == initial && edge.getTerminal() == terminal) || (edge.getInitial() == terminal && edge.getTerminal() == initial)){
                    return edge;
                }
            }
        }

        return null;
    }

    public String BFS(K key){
        String result = "";
        if(adjacency.isEmpty()){
            return "Empty graph";
        }

        Node<K> node = searchNode(key);
        if(node == null){
            return "Node not found";
        }

        int numNode = adjacency.size();
        boolean[] visited = new boolean[numNode];
        LinkedList<Node<K>> queue = new LinkedList<>();

        visited[adjacency.indexOf(node)] = true;
        queue.add(node);

        while(!queue.isEmpty()){
            node = queue.poll();
            result += node.getKey() + " ";
            for(Edge<K> edge : node.getEdges()){
                Node<K> currentV = node==edge.getTerminal()?edge.getInitial():edge.getTerminal();
                // Node<K, D> currentV = edge.getTerminal();
                if(!visited[adjacency.indexOf(currentV)]){
                     visited[adjacency.indexOf(currentV)] = true;
                     queue.add(currentV);
                }
            }
        }

        return result.trim();
    }

    public String DFS(K key,K root){
        if(adjacency.isEmpty()){
            return "Empty graph";
        }

        Node<K> node = searchNode(key);
        if(node == null){
            return "Node not found";
        }

        boolean[] visited = new boolean[adjacency.size()];

        return DFSAux(key,root,visited,3).trim();
    }

    private String DFSAux(K key,K root,boolean[] visited,int times) {
        String result = "";
        Node<K> current = searchNode(root);
        if (times>0&&current.getKey()!=key) {
            int index = adjacency.indexOf(current);

            visited[index] = true;
            result = current.getKey() + " ";

            for (Edge<K> edge : current.getEdges()) {
                Node<K> currentV = current == edge.getTerminal() ? edge.getInitial() : edge.getTerminal();
                // Node<K, D> currentV = edge.getTerminal();
                if (!visited[adjacency.indexOf(currentV)]) {
                    result += DFSAux(key, currentV.getKey(), visited, times - 1);
                }
            }
            return result;
        }
        return result;
    }

    public double[] dijkstra(K key) {
        Node<K> sourceNode = searchNode(key);
        if (sourceNode == null) {
            return null;
        }

        int numNodes = adjacency.size();
        double[] dist = new double[numNodes];
        boolean[] processedNodes = new boolean[numNodes];
        Arrays.fill(dist, Double.MAX_VALUE);

        int sourceIndex = adjacency.indexOf(sourceNode);
        dist[sourceIndex] = 0;

        for (int count = 0; count < numNodes - 1; count++) {
            int u = minDistance(dist, processedNodes);
            processedNodes[u] = true;

            Node<K> uNode = adjacency.get(u);

            for (int v = 0; v < numNodes; v++) {
                Edge<K> edgeUV = uNode.searchEdge(adjacency.get(v));
                if (!processedNodes[v] && edgeUV != null) {
                    double temp = dist[u] + edgeUV.getWeight();
                    if (temp < dist[v]) {
                        dist[v] = temp;
                    }
                }
            }
        }

        return dist;
    }

    public int minDistance(double[] dist, boolean[] flags) {
        double min = Double.MAX_VALUE;
        int min_index = -1;
 
        for (int v = 0; v < adjacency.size(); v++)
            if (flags[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }

    public double[][] floydWarshall() {
        int numNodes = adjacency.size();
        double[][] dist = new double[numNodes][numNodes];

        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                Edge<K> edge = consultEdge(adjacency.get(i).getKey(), adjacency.get(j).getKey());
                if (i == j) {
                    dist[i][j] = 0;
                } else if (edge != null) {
                    dist[i][j] = edge.getWeight();
                } else {
                    dist[i][j] = Double.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
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

    public String primMST() {
        K startNodeKey = adjacency.get(0).getKey();
        ArrayList<Edge<K>> mstEdges = new ArrayList<>();
        int numNodes = adjacency.size();
        boolean[] visited = new boolean[numNodes];

        PriorityQueue<Edge<K>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Edge::getWeight));
        Node<K> startNode = searchNode(startNodeKey);

        visited[adjacency.indexOf(startNode)] = true;

        while (mstEdges.size() < numNodes - 1) {
            ArrayList<Edge<K>> edges = startNode.getEdges();
            for (Edge<K> edge : edges) {
                Node<K> neighbor = (startNode == edge.getTerminal()) ? edge.getInitial() : edge.getTerminal();

                if (!visited[adjacency.indexOf(neighbor)]) {
                    priorityQueue.add(edge);
                }
            }

            Edge<K> minEdge = priorityQueue.poll();
            Node<K> nextNode = (startNode == minEdge.getTerminal()) ? minEdge.getInitial() : minEdge.getTerminal();

            if (!visited[adjacency.indexOf(nextNode)]) {
                mstEdges.add(minEdge);
                visited[adjacency.indexOf(nextNode)] = true;
                startNode = nextNode;
            }
        }

        return printMST(mstEdges);
    }

    public String kruskalMST() {
        ArrayList<Edge<K>> mstEdges = new ArrayList<>();
        int numNodes = adjacency.size();

        ArrayList<Edge<K>> edges = new ArrayList<>();
        for (Node<K> node : adjacency) {
            edges.addAll(node.getEdges());
        }

        edges.sort(Comparator.comparingDouble(Edge::getWeight));

        Map<K, K> parent = new HashMap<>();
        for (Node<K> node : adjacency) {
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

    public Node<K> searchNode(K key){
        for (Node<K> node : adjacency) {
            if(node.getKey().equals(key)){
                return node;
            }
        }
        return null;
    }

    public boolean hasEdge(K keyInitial, K keyTerminal){
        Node<K> initial = searchNode(keyInitial);
        Node<K> terminal = searchNode(keyTerminal);

        if(initial == null || terminal == null){
            return false;
        }

        for (Node<K> node : adjacency) {
            for (Edge<K> edge : node.getEdges()){
                if((edge.getInitial() == initial && edge.getTerminal() == terminal) || (edge.getInitial() == terminal && edge.getTerminal() == initial)){
                    return true;
                }
            }
        }
        return false;
    }

    public int size(){
        return adjacency.size();
    }
}