package structure.Graph2;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<K, D> {
    private ArrayList<ArrayList<Integer>> adjacencyMatrix;
    private ArrayList<Node2<K,D>> nodes;

    public Graph(){
        adjacencyMatrix=new ArrayList<>();
        nodes=new ArrayList<>();
    }
    public String addNode(K key, D data) {
        if (searchNode2(key)!=null) {
            return "The node already exist in the graph";
        }
        Node2<K,D> newNode=new Node2<K,D>(key,data);
        nodes.add(newNode);
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            adjacencyMatrix.get(i).add(0);
        }
        ArrayList<Integer> newRow = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            newRow.add(0);
        }
        adjacencyMatrix.add(newRow);
        return "Node added correctly.";
    }
    public String addEdge(K keyInitial, K keyTerminal, Double weight) {
        int initialIndex = nodes.indexOf(searchNode2(keyInitial));
        int terminalIndex = nodes.indexOf(searchNode2(keyTerminal));
        if (initialIndex != -1 && terminalIndex != -1 && nodes.get(initialIndex).searchAdjacencyList(nodes.get(terminalIndex))==null){
            int currentValue1 = adjacencyMatrix.get(initialIndex).get(terminalIndex);
            int currentValue2 = adjacencyMatrix.get(terminalIndex).get(initialIndex);
            adjacencyMatrix.get(initialIndex).set(terminalIndex, currentValue1+ 1);
            adjacencyMatrix.get(terminalIndex).set(initialIndex, currentValue2 + 1);
            nodes.get(initialIndex).addInAdjacencyList(nodes.get(terminalIndex),weight);
            nodes.get(terminalIndex).addInAdjacencyList(nodes.get(initialIndex),weight);
            return "Edge added correctly.";
        }
        return "That edge already exist in the graph";
    }
    public String deleteNode(K key) {
        Node2<K,D> node=searchNode2(key);
        if (node==null) {
            return "The node does not exist in the graph.";
        }
        int index = nodes.indexOf(node);
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            adjacencyMatrix.get(i).remove(index);
        }
        adjacencyMatrix.remove(index);
        nodes.remove(index);
        deleteReferences(node.getKey());
        return "The node were deleted successfully.";
    }
    public String deleteEdge(K keyInitial, K keyTerminal) {
        int initialIndex = nodes.indexOf(searchNode2(keyInitial));
        int terminalIndex = nodes.indexOf(searchNode2(keyTerminal));
        if (initialIndex != -1 && terminalIndex != -1&&nodes.get(initialIndex).searchAdjacencyList(nodes.get(terminalIndex))!=null){
            int valorActual1 = adjacencyMatrix.get(initialIndex).get(terminalIndex);
            int valorActual2 = adjacencyMatrix.get(terminalIndex).get(initialIndex);
            adjacencyMatrix.get(initialIndex).set(terminalIndex, valorActual1 - 1);
            adjacencyMatrix.get(terminalIndex).set(initialIndex, valorActual2 - 1);
            nodes.get(initialIndex).deleteReferences(keyTerminal);
            nodes.get(terminalIndex).deleteReferences(keyInitial);
            return "The edge were deleted successfully";
        }
        return "The destination or source node does not exist or does not have a connecting edge";
    }
    public Node2<K, D> searchNode2(K key) {
        for(Node2<K,D> n: nodes){
            if(n.getKey()==key){
                return n;
            }
        }
        return null;
    }
    public String consultNode(K key) {
        Node2<K,D> node = searchNode2(key);
        if(node!=null){
            return node.toString() +" "+node.edgesToString();
        }
        return "Node not found";
    }

    public String consultEdge(K keyInitial, K keyTerminal) {
        Node2<K,D> initial = searchNode2(keyInitial);
        Node2<K,D> terminal = searchNode2(keyTerminal);
        if(initial == null || terminal == null){
            return "Edge not found";
        }
        for(Node2<K, D> node : nodes){
            if(initial.getKey()==node.getKey()&&node.searchAdjacencyList(terminal)!=null){
                return node.oneEdgeToString(terminal);
            }
        }
        return "Edge not found";
    }
    public String BFS(K key) {
        boolean[] visited=new boolean[nodes.size()];
        LinkedList<Node2<K,D>> queue=new LinkedList<>();
        return BFSearch(key,visited,queue,nodes.get(0));
    }
    public String BFSearch(K key, boolean[] visited, LinkedList<Node2<K,D>> queue, Node2<K,D> currentNode){
        visited[nodes.indexOf(currentNode)] = true;
        queue.add(currentNode);
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            if (currentNode.getKey() == key) {
                return currentNode.toString();
            }
            for (Node2<K,D> neighbor : currentNode.getAdjacencyList()) {
                if (!visited[nodes.indexOf(neighbor)]) {
                    visited[nodes.indexOf(neighbor)] = true;
                    queue.add(neighbor);
                }
            }
        }
        return null;
    }
    public String showAdjacencyMatrix() {
        StringBuilder matrixString=new StringBuilder();
        matrixString.append("\t");
        for (Node2<K,D> node: nodes) {
            matrixString.append(node.getKey()).append("\t");
        }
        matrixString.append("\n");
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            matrixString.append(nodes.get(i).getKey()).append("\t");
            for (int j = 0; j < adjacencyMatrix.get(i).size(); j++) {
                matrixString.append(adjacencyMatrix.get(i).get(j)).append("\t");
            }
            matrixString.append("\n");
        }
        return matrixString.toString();
    }

    public String getAdjacencyList(K key){
        Node2<K,D> node=null;
        if(searchNode2(key)!=null){
            node=nodes.get(nodes.indexOf(searchNode2(key)));
        }
        if(node!=null){
            return node.getAdjacencyListToString();
        }
        return "The node does not exist";
    }
    public void deleteReferences(K key){
        for (Node2<K,D> n: nodes) {
            n.deleteReferences(key);
        }
    }
    public String consult(){
        String result = "";
        for (Node2<K,D> node : nodes) {
            result += consultNode(node.getKey()) + "\n";
        }
        return result.trim();
    }
    public boolean isStronglyConnected() {
        return false;
    }

    /*
    public void recorrerVerticeBfs(){
        boolean[] visitado=new boolean[vertices.size()];
        LinkedList<Vertice<E>> queue=new LinkedList<>();
        recorrerVerticeBfs(visitado,queue,vertices.get(0));
    }
    private void recorrerVerticeBfs(boolean[] visitado,LinkedList<Vertice<E>> queue, Vertice<E> v){
        visitado[vertices.indexOf(v)]=true;
        queue.add(v);
        while(!queue.isEmpty()) {
            v=queue.poll();
            System.out.print(v.getVertice()+" ");
            for(Vertice<E> vecino: v.getListaAdyacencia()){
                if(!visitado[vertices.indexOf(vecino)]){
                    visitado[vertices.indexOf(vecino)]=true;
                    queue.add(vecino);
                }
            }
        }
    }
    public void recorrerVerticeDfs(){
        boolean[] visitado=new boolean[vertices.size()];
        recorrerVerticeDfs(visitado,vertices.get(0));
    }
    private void recorrerVerticeDfs(boolean[] visitado,Vertice<E> v){
        if (!visitado[vertices.indexOf(v)]) {
            System.out.print(v.getVertice() + " ");
            visitado[vertices.indexOf(v)] = true;
            for (Vertice<E> vecino : v.getListaAdyacencia()) {
                recorrerVerticeDfs(visitado, vecino);
            }
        }
    }
    public Vertice buscarVerticeDfs(char vertice){
        boolean[] visitado=new boolean[vertices.size()];
        return buscarVerticeDfs(vertice,visitado,vertices.get(0));
    }
    private Vertice<E> buscarVerticeDfs(char vertice, boolean[] visitado, Vertice<E> v) {
        if (!visitado[vertices.indexOf(v)]) {
            visitado[vertices.indexOf(v)] = true;
            if (v.getVertice() == vertice) {
                return v;
            }
            for (Vertice<E> vecino : v.getListaAdyacencia()) {
                Vertice<E> resultado = buscarVerticeDfs(vertice, visitado, vecino);
                if (resultado != null) {
                    return resultado;
                }
            }
        }
        return null;
    }
    public String algoritmoDijkstra(char verticeMeta){
        boolean[] visitado=new boolean[vertices.size()];
        int[] pesos=new int[vertices.size()];
        char[] camino=caminoDijkstra(verticeMeta,visitado,pesos,vertices.get(0));
        for(char c:camino){
            return c+" ";
        }
        return null;
    }
    /*public char[] caminoDijkstra(char verticeMeta,boolean[] visitado,int[] pesos,Vertice<E> verticePuntero){
        visitado[vertices.indexOf(verticePuntero)] = true;
        for(int i=0;i<vertices.size();i++){
            pesos[i]=Integer.MAX_VALUE;
        }
        pesos[vertices.indexOf(verticePuntero)]=0;
        for(int i= 0; i< vertices.size()-1; i++) {
            int m = mininaDistancia(pesos, visitado);
            visitado[m] = true;

            for (int j = 0; j < vertices.size(); j++) {
                if (!visitado[j] && graph.getWeight(m, v) != 0 && dist[u] != Integer.MAX_VALUE && dist[m] + graph.getWeight(u, v) < dist[v]) {
                    dist[v] = dist[m] + graph.getWeight(m, v);
                }
            }
        }

    }
    private int mininaDistancia(int[] pesos, boolean[] visitado) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        int V = pesos.length;
        for (int v = 0; v < V; v++) {
            if (!visitado[v] && pesos[v] < min) {
                min = pesos[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
    */
}
