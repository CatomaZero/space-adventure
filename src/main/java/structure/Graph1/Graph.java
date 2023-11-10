package structure.Graph1;

import structure.Graph2.Node2;
import structure.IGraph;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<K, D> implements IGraph<K, D> {
    private ArrayList<Node<K, D>> adjacency;
    private ArrayList<ArrayList<Integer>> adjacencyMatrix;

    public Graph(){
        this.adjacency = new ArrayList<>();
        this.adjacencyMatrix = new ArrayList<>();
    }

    @Override
    public String addNode(K key, D data){
        if(searchNode(key)!=null){
            return "The addition of this node is not possible as there is one with the same key.";
        }

        // List
        Node<K, D> v = new Node<>(key, data);
        adjacency.add(v);

        // Matrix
        ArrayList<Integer> row = new ArrayList<>();
        row.add(0);
        if(!adjacencyMatrix.isEmpty()){
            for(ArrayList<Integer> current : adjacencyMatrix){
                current.add(0);
                row.add(0);
            }
        }
        adjacencyMatrix.add(row);

        return "Node added successfully.";
    }

    @Override
    public String addEdge(K keyInitial, K keyTerminal, Double weight){
        if(adjacency.isEmpty()){
            return "Empty graph";
        }

        Node<K, D> initial = searchNode(keyInitial);
        Node<K, D> terminal = searchNode(keyTerminal);

        if(initial == null){
            return "Initial node not found";
        } else if (terminal == null){
            return "Terminal node not found";
        }

        // List
        Edge<K, D> edge = new Edge<>(weight, initial, terminal);
        initial.addEdge(edge);
        terminal.addEdge(edge);

        // Matrix
        int indexInitial = adjacency.indexOf(initial);
        int indexTerminal = adjacency.lastIndexOf(terminal);
        adjacencyMatrix.get(indexInitial).set(indexTerminal, 1);
        adjacencyMatrix.get(indexTerminal).set(indexInitial, 1);

        return "Edge added successfully.";
    }

    @Override
    public String deleteNode(K key){
        Node<K,D> node = searchNode(key);
        if(node == null){
            return "Node not found";
        }

        // List
        // Obtener los aristas del nodo que se desea eliminar
        ArrayList<Edge<K,D>> edges = node.getEdges();
        // Recorremos la lista de aristas
        for (Edge<K,D> e : edges) {
            // En cada arista, obtenemos su nodo terminal o inicial
            Node<K, D> currentV = node==e.getTerminal()?e.getInitial():e.getTerminal();
            // Obtenemos los aristas de ese nodo
            ArrayList<Edge<K,D>> edg = currentV.getEdges();
            // Lo recorremos para saber cual es que esta conectado al nodo que deseamos eliminar
            boolean flag = true;
            for (int i = 0; flag && i < edg.size(); i++) {
                // Si esta conectado lo eliminamos y rompemos el ciclo
                if(edg.get(i).getTerminal() == node || edg.get(i).getInitial() == node){
                    edg.remove(edg.get(i));
                    flag = false;
                }
            }
        }

        // Matrix
        int index = adjacency.indexOf(node);
        System.out.println(index);
        for (ArrayList<Integer> i: adjacencyMatrix) {
            i.remove(index);
        }
        adjacencyMatrix.remove(index);
        adjacency.remove(node);

        return "Node deleted successfully.";
    }

    @Override
    public String deleteEdge(K keyInitial, K keyTerminal) {
        Node<K, D> initial = searchNode(keyInitial);
        Node<K, D> terminal = searchNode(keyTerminal);

        if (initial == null || terminal == null) {
            return "Node not found";
        }

        if (deleteEdgeAux(initial, terminal) && deleteEdgeAux(terminal, initial)) {
            return "Edge deleted successfully.";
        } else {
            return "Edge not found";
        }
    }

    private boolean deleteEdgeAux(Node<K, D> node1, Node<K, D> node2) {
        ArrayList<Edge<K, D>> edges = node1.getEdges();
        for (int i = 0; i < edges.size(); i++) {
            Edge<K, D> edge = edges.get(i);
            if ((edge.getInitial() == node1 && edge.getTerminal() == node2) || (edge.getInitial() == node2 && edge.getTerminal() == node1)) {
                edges.remove(i);
                return true;
            }
        }
        return false;
    }

    public String consult(){
        String result = "";
        for (Node<K,D> node : adjacency) {
            result += consultNode(node.getKey()) + "\n";
        }
        return result.trim();
    }

    @Override
    public String consultNode(K key){
        Node<K,D> node = searchNode(key);
        return node!=null?node.toString():"Node not found";
    }

    @Override
    public String consultEdge(K keyInitial, K keyTerminal){
        Node<K,D> initial = searchNode(keyInitial);
        Node<K,D> terminal = searchNode(keyTerminal);

        if(initial == null || terminal == null){
            return "Node not found";
        }

        for(Node<K, D> node : adjacency){
            for(Edge<K, D> edge : node.getEdges()){
                if((edge.getInitial() == initial && edge.getTerminal() == terminal) || (edge.getInitial() == terminal && edge.getTerminal() == initial)){
                    return edge.toString();
                }
            }
        }

        return "Edge not found";
    }

    @Override
    public String BFS(K key){
        String result = "";
        if(adjacency.isEmpty()){
            return "Empty graph";
        }

        Node<K, D> node = searchNode(key);
        if(node == null){
            return "Node not found";
        }

        int numNode = adjacency.size();
        boolean[] visited = new boolean[numNode];
        LinkedList<Node<K, D>> queue = new LinkedList<>();

        visited[adjacency.indexOf(node)] = true;
        queue.add(node);

        while(!queue.isEmpty()){
            node = queue.poll();
            result += node.getKey() + " ";
            for(Edge<K, D> edge : node.getEdges()){
                Node<K, D> currentV = node==edge.getTerminal()?edge.getInitial():edge.getTerminal();
                // Node<K, D> currentV = edge.getTerminal();
                if(!visited[adjacency.indexOf(currentV)]){
                     visited[adjacency.indexOf(currentV)] = true;
                     queue.add(currentV);
                }
            }
        }

        return result.trim();
    }

    public String DFS(K key){
        if(adjacency.isEmpty()){
            return "Empty graph";
        }

        Node<K, D> node = searchNode(key);
        if(node == null){
            return "Node not found";
        }

        boolean[] visited = new boolean[adjacency.size()];

        return DFSAux(key, visited).trim();
    }

    private String DFSAux(K key, boolean[] visited){
        Node<K, D> current = searchNode(key);
        int index = adjacency.indexOf(current);

        visited[index] = true;
        String result = current.getKey() + " ";

        for(Edge<K, D> edge : current.getEdges()){
            Node<K, D> currentV = current==edge.getTerminal()?edge.getInitial():edge.getTerminal();
            // Node<K, D> currentV = edge.getTerminal();
            if(!visited[adjacency.indexOf(currentV)]){
                result += DFSAux(currentV.getKey(), visited);
            }
        }

        return result;
    }

    @Override
    public boolean isStronglyConnected() {
        for(Node<K, D> v : adjacency){
            String[] keys = BFS(v.getKey()).split(" ");
            if(keys.length < adjacency.size()){
                return false;
            }
        }
        return true;
    }

    @Override
    public Node<K, D> searchNode(K key){
        for (Node<K, D> node : adjacency) {
            if(node.getKey().equals(key)){
                return node;
            }
        }
        return null;
    }

    @Override
    public Node2<K, D> searchNode2(K key) {
        return null;
    }

    //  Temporal method
    public void printAdjacencyMatrix(){
        for (ArrayList<Integer> matrix : adjacencyMatrix) {
            for (Integer integer : matrix) {
                System.out.print(integer);
            }
            System.out.println();
        }
    }
}