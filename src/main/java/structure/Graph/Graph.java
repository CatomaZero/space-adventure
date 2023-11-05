package structure.Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<K, D> implements IGraph<K, D>{
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

        printAdjacencyMatrix();

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

        printAdjacencyMatrix();

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
    public String deleteEdge(K keyInitial, K keyTerminal){
        Node<K,D> initial = searchNode(keyInitial);
        Node<K,D> terminal = searchNode(keyTerminal);
        if(initial == null || terminal == null){
            return "Node not found";
        }

        ArrayList<Edge<K,D>> edges = initial.getEdges();
        boolean flag = true;
        for (int i = 0; flag && i < edges.size(); i++) {
            if((edges.get(i).getInitial() == initial && edges.get(i).getTerminal() == terminal) || (edges.get(i).getInitial() == terminal && edges.get(i).getTerminal() == initial)){
                edges.remove(edges.get(i));
                flag = false;
            }
        }

        boolean flag2 = true;
        edges = terminal.getEdges();
        for (int i = 0; flag2 && i < edges.size(); i++) {
            if((edges.get(i).getInitial() == initial && edges.get(i).getTerminal() == terminal) || (edges.get(i).getInitial() == terminal && edges.get(i).getTerminal() == initial)){
                edges.remove(edges.get(i));
                flag2 = false;
            }
        }

        return flag&&flag2?"Edge not found":"Edge deleted successfully.";
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