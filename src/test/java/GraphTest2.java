import junit.framework.TestCase;
import structure.Graph2.Graph;

public class GraphTest2 extends TestCase {
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

    public void testAddNode1() {
        setUpStage1();
        String result = graph.addNode(1, 1);
        assertEquals("Node added correctly.", result);
    }

    public void testAddNode2() {
        setUpStage2();
        String result = graph.addNode(2, 2);
        assertEquals("The node already exist in the graph", result);
    }

    public void testAddNode3() {
        setUpStage3();
        String result = graph.addNode(6, 4);
        assertEquals("Node added correctly.", result);
    }

    public void testAddEdge1() {
        setUpStage1();
        String result = graph.addEdge(1, 2, 5.0);
        assertEquals("That edge already exist in the graph", result);
    }

    public void testAddEdge2() {
        setUpStage2();
        String result = graph.addEdge(5, 3, 3.0);
        assertEquals("That edge already exist in the graph", result);
    }

    public void testAddEdge3() {
        setUpStage3();
        String result = graph.addEdge(2, 3, 4.0);
        assertEquals("That edge already exist in the graph", result);
    }

    public void testDeleteNode1() {
        setUpStage1();
        String result = graph.deleteNode(1);
        assertEquals("The node does not exist in the graph.", result);
    }

    public void testDeleteNode2() {
        setUpStage2();
        String result = graph.deleteNode(2);
        assertEquals("The node were deleted successfully.", result);
    }

    public void testDeleteNode3() {
        setUpStage3();
        String result = graph.deleteNode(1);
        assertEquals("The node were deleted successfully.", result);
    }

    public void testDeleteEdge1() {
        setUpStage1();
        String result = graph.deleteEdge(1, 2);
        assertEquals("The destination or source node does not exist or does not have a connecting edge", result);
    }

    public void testDeleteEdge2() {
        setUpStage2();
        String result = graph.deleteEdge(1, 2);
        assertEquals("The destination or source node does not exist or does not have a connecting edge", result);
    }

    public void testDeleteEdge3() {
        setUpStage3();
        String result = graph.deleteEdge(1, 2);
        assertEquals("The edge were deleted successfully", result);
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
        assertEquals("Edge not found", result);
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
}