package structure.Graph1;

public class Edge<K, D> {
    private Double weight;
    private Node<K, D> initial;
    private Node<K, D> terminal;

    public Edge(Double weight, Node<K, D> initial, Node<K, D> terminal){
        this.weight = weight;
        this.initial = initial;
        this.terminal = terminal;
    }

    public double getWeight() {
        return weight;
    }

    public Node<K, D> getTerminal() {
        return terminal;
    }

    public Node<K, D> getInitial() {
        return initial;
    }

    public String toString() {
        return "(" + initial.getKey() + ", " + terminal.getKey() + ")   Weight: " + weight;
    }
}
