package structure.Matrix;

public class Edge<K> {
    private Double weight;
    private Node<K> initial;
    private Node<K> terminal;

    public Edge(Double weight, Node<K> initial, Node<K> terminal){
        this.weight = weight;
        this.initial = initial;
        this.terminal = terminal;
    }

    public double getWeight() {
        return weight;
    }

    public Node<K> getTerminal() {
        return terminal;
    }

    public Node<K> getInitial() {
        return initial;
    }

    public String toString() {
        return "(" + initial.getKey() + ", " + terminal.getKey() + ")   Weight: " + weight;
    }
}
