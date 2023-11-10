import junit.framework.TestCase;
import structure.Graph1.Graph;

public class GraphTest1 extends TestCase {
    private Graph<Integer, Integer> graph;

    public void setUpStage1() {
        graph = new Graph<>();
    }

    public void setUpStage2() {
        graph = new Graph<>();
        graph.addNode(1, 1);
        graph.addNode(2, 2);
        graph.addNode(3, 3);
    }

    public void setUpStage3() {
        graph = new Graph<>();
        graph.addNode(1, 1);
        graph.addNode(2, 2);
        graph.addNode(3, 3);
        graph.addNode(4, 4);
        graph.addNode(5, 5);
        graph.addEdge(1, 2, 5.0);
        graph.addEdge(2, 3, 2.0);
        graph.addEdge(3, 4, 5.0);
        graph.addEdge(3, 5, 2.0);
        graph.addEdge(5, 1, 5.0);
    }

    public void setUpStage4() {
        graph = new Graph<>();
        graph.addNode(1, 1);
        graph.addNode(2, 2);
        graph.addNode(3, 3);
        graph.addNode(4, 4);
        graph.addNode(5, 5);
        graph.addNode(6, 5);
        graph.addNode(7, 5);
        graph.addNode(8, 5);
        graph.addEdge(1, 2, 5.0);
        graph.addEdge(1, 3, 2.0);
        graph.addEdge(2, 4, 5.0);
        graph.addEdge(2, 5, 2.0);
        graph.addEdge(3, 6, 5.0);
        graph.addEdge(3, 7, 5.0);
        graph.addEdge(3, 8, 5.0);
        graph.addEdge(5, 6, 5.0);
    }

    public void testAddNode1() {
        setUpStage1();
        String result = graph.addNode(1, 1);
        assertEquals("Node added successfully.", result);
    }

    public void testAddNode2() {
        setUpStage2();
        String result = graph.addNode(2, 2);
        assertEquals("The addition of this node is not possible as there is one with the same key.", result);
    }

    public void testAddNode3() {
        setUpStage3();
        String result = graph.addNode(6, 4);
        assertEquals("Node added successfully.", result);
    }

    public void testAddEdge1() {
        setUpStage1();
        String result = graph.addEdge(1, 2, 5.0);
        assertEquals("Empty graph", result);
    }

    public void testAddEdge2() {
        setUpStage2();
        String result = graph.addEdge(5, 3, 3.0);
        assertEquals("Initial node not found", result);
    }

    public void testAddEdge3() {
        setUpStage3();
        String result = graph.addEdge(2, 3, 4.0);
        assertEquals("Edge added successfully.", result);
    }

    public void testDeleteNode1() {
        setUpStage1();
        String result = graph.deleteNode(1);
        assertEquals("Node not found", result);
    }

    public void testDeleteNode2() {
        setUpStage2();
        String result = graph.deleteNode(2);
        assertEquals("Node deleted successfully.", result);
    }

    public void testDeleteNode3() {
        setUpStage3();
        String result = graph.deleteNode(1);
        assertEquals("Node deleted successfully.", result);
    }

    public void testDeleteEdge1() {
        setUpStage1();
        String result = graph.deleteEdge(1, 2);
        assertEquals("Node not found", result);
    }

    public void testDeleteEdge2() {
        setUpStage2();
        String result = graph.deleteEdge(1, 2);
        assertEquals("Edge not found", result);
    }

    public void testDeleteEdge3() {
        setUpStage3();
        String result = graph.deleteEdge(1, 2);
        assertEquals("Edge deleted successfully.", result);
    }

    public void testConsult1() {
        setUpStage1();
        String result = graph.consult();
        assertEquals("", result);
    }

    public void testConsult2() {
        setUpStage2();
        String result = graph.consult();
        String expected = "Key: 1\nData: 1\nEdges: \nKey: 2\nData: 2\nEdges: \nKey: 3\nData: 3\nEdges:";
        assertEquals(expected, result);
    }

    public void testConsult3() {
        setUpStage3();
        String result = graph.consult();
        String expected = "Key: 1\nData: 1\nEdges: \n     (1, 2)   Weight: 5.0\n     (5, 1)   Weight: 5.0\n" +
                "Key: 2\nData: 2\nEdges: \n     (1, 2)   Weight: 5.0\n     (2, 3)   Weight: 2.0\n" +
                "Key: 3\nData: 3\nEdges: \n     (2, 3)   Weight: 2.0\n     (3, 4)   Weight: 5.0\n     (3, 5)   Weight: 2.0\n" +
                "Key: 4\nData: 4\nEdges: \n     (3, 4)   Weight: 5.0\n" +
                "Key: 5\nData: 5\nEdges: \n     (3, 5)   Weight: 2.0\n     (5, 1)   Weight: 5.0";
        assertEquals(expected, result);
    }

    public void testConsultNode1() {
        setUpStage1();
        String result = graph.consultNode(1);
        assertEquals("Node not found", result);
    }

    public void testConsultNode2() {
        setUpStage2();
        String result = graph.consultNode(2);
        assertEquals("Key: 2\nData: 2\nEdges: ", result);
    }

    public void testConsultNode3() {
        setUpStage3();
        String result = graph.consultNode(4);
        assertEquals("Key: 4\nData: 4\nEdges: \n     (3, 4)   Weight: 5.0", result);
    }

    public void testConsultEdge1() {
        setUpStage1();
        String result = graph.consultEdge(1, 2);
        assertEquals("Node not found", result);
    }

    public void testConsultEdge2() {
        setUpStage2();
        String result = graph.consultEdge(1, 3);
        assertEquals("Edge not found", result);
    }

    public void testConsultEdge3() {
        setUpStage3();
        String result = graph.consultEdge(1, 2);
        assertEquals("(1, 2)   Weight: 5.0", result);
    }

    public void testDFS1(){
        setUpStage4();
        assertEquals("1 2 4 5 6 3 7 8", graph.DFS(1));
    }
}