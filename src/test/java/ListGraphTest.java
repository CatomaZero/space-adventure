import junit.framework.TestCase;
import structure.List.ListGraph;

import java.util.Arrays;

public class ListGraphTest extends TestCase {
    private ListGraph<Integer> listGraph;

    public void setUpStage1() {
        listGraph = new ListGraph<>();
    }

    public void setUpStage2() {
        listGraph = new ListGraph<>();
        listGraph.addNode(1);
        listGraph.addNode(2);
        listGraph.addNode(3);
    }

    public void setUpStage3() {
        listGraph = new ListGraph<>();
        listGraph.addNode(1);
        listGraph.addNode(2);
        listGraph.addNode(3);
        listGraph.addNode(4);
        listGraph.addNode(5);
        listGraph.addEdge(1, 2, 5.0);
        listGraph.addEdge(2, 3, 2.0);
        listGraph.addEdge(3, 4, 5.0);
        listGraph.addEdge(3, 5, 2.0);
        listGraph.addEdge(5, 1, 5.0);
    }

    public void setUpStage4() {
        listGraph = new ListGraph<>();
        listGraph.addNode(1);
        listGraph.addNode(2);
        listGraph.addNode(3);
        listGraph.addNode(4);
        listGraph.addNode(5);
        listGraph.addNode(6);
        listGraph.addNode(7);
        listGraph.addNode(8);
        listGraph.addEdge(1, 2, 5.0);
        listGraph.addEdge(1, 3, 2.0);
        listGraph.addEdge(2, 4, 5.0);
        listGraph.addEdge(2, 5, 2.0);
        listGraph.addEdge(3, 6, 5.0);
        listGraph.addEdge(3, 7, 5.0);
        listGraph.addEdge(3, 8, 5.0);
        listGraph.addEdge(5, 6, 5.0);
    }

    public void setupStage5(){
        listGraph = new ListGraph<>();
        listGraph.addNode(0);
        listGraph.addNode(1);
        listGraph.addNode(2);
        listGraph.addNode(3);
        listGraph.addNode(4);
        listGraph.addNode(5);
        listGraph.addNode(6);
        listGraph.addNode(7);
        listGraph.addNode(8);
        listGraph.addEdge(0, 1, 4.0);
        listGraph.addEdge(0, 7, 8.0);
        listGraph.addEdge(1, 2, 8.0);
        listGraph.addEdge(1, 7, 11.0);
        listGraph.addEdge(2, 3, 7.0);
        listGraph.addEdge(2, 8, 2.0);
        listGraph.addEdge(2, 5, 4.0);
        listGraph.addEdge(3, 4, 9.0);
        listGraph.addEdge(3, 5, 14.0);
        listGraph.addEdge(4, 5, 10.0);
        listGraph.addEdge(5, 6, 2.0);
        listGraph.addEdge(6, 7, 1.0);
        listGraph.addEdge(6, 8, 6.0);
        listGraph.addEdge(7, 8, 7.0);
    }

    public void setUpStage6(){
        listGraph = new ListGraph<>();
        listGraph.addNode(0);
        listGraph.addNode(1);
        listGraph.addNode(2);
        listGraph.addNode(3);
        listGraph.addNode(4);
        listGraph.addNode(5);
        listGraph.addEdge(0, 1, 4.0);
        listGraph.addEdge(0, 2, 4.0);
        listGraph.addEdge(1, 2, 2.0);
        listGraph.addEdge(1, 0, 4.0);
        listGraph.addEdge(2, 0, 4.0);
        listGraph.addEdge(2, 1, 2.0);
        listGraph.addEdge(2, 3, 3.0);
        listGraph.addEdge(2, 5, 2.0);
        listGraph.addEdge(2, 4, 4.0);
        listGraph.addEdge(3, 2, 3.0);
        listGraph.addEdge(3, 4, 3.0);
        listGraph.addEdge(4, 2, 4.0);
        listGraph.addEdge(4, 3, 3.0);
        listGraph.addEdge(5, 2, 2.0);
        listGraph.addEdge(5, 4, 3.0);
    }

    public void setUpStage7() {
        listGraph = new ListGraph<>();
        listGraph.addNode(0);
        listGraph.addNode(1);
        listGraph.addNode(2);
        listGraph.addNode(3);
        listGraph.addNode(4);
        listGraph.addNode(5);
        listGraph.addNode(6);
        listGraph.addEdge(0, 1,2.0);
        listGraph.addEdge(0, 2, 6.0);
        listGraph.addEdge(1, 3, 5.0);
        listGraph.addEdge(2, 3, 8.0);
        listGraph.addEdge(3, 5, 15.0);
        listGraph.addEdge(2, 3, 8.0);
        listGraph.addEdge(3, 5, 15.0);
        listGraph.addEdge(3, 4, 10.0);
        listGraph.addEdge(4, 5, 6.0);
        listGraph.addEdge(4, 6, 2.0);
        listGraph.addEdge(5, 6, 6.0);
    }

    public void testAddNode1() {
        setUpStage1();
        String result = listGraph.addNode(1);
        assertEquals("Node added successfully.", result);
    }

    public void testAddNode2() {
        setUpStage2();
        String result = listGraph.addNode(2);
        assertEquals("The addition of this node is not possible as there is one with the same key.", result);
    }

    public void testAddNode3() {
        setUpStage3();
        String result = listGraph.addNode(6);
        assertEquals("Node added successfully.", result);
    }

    public void testAddEdge1() {
        setUpStage1();
        String result = listGraph.addEdge(1, 2, 5.0);
        assertEquals("Empty graph", result);
    }

    public void testAddEdge2() {
        setUpStage2();
        String result = listGraph.addEdge(5, 3, 3.0);
        assertEquals("One or both nodes not found.", result);
    }

    public void testAddEdge3() {
        setUpStage3();
        String result = listGraph.addEdge(2, 3, 4.0);
        assertEquals("Edge added successfully.", result);
    }

    public void testDeleteNode1() {
        setUpStage1();
        String result = listGraph.deleteNode(1);
        assertEquals("Node not found", result);
    }

    public void testDeleteNode2() {
        setUpStage2();
        String result = listGraph.deleteNode(2);
        assertEquals("Node deleted successfully.", result);
    }

    public void testDeleteNode3() {
        setUpStage3();
        String result = listGraph.deleteNode(1);
        assertEquals("Node deleted successfully.", result);
    }

    public void testDeleteEdge1() {
        setUpStage1();
        String result = listGraph.deleteEdge(1, 2);
        assertEquals("One or both nodes not found.", result);
    }

    public void testDeleteEdge2() {
        setUpStage2();
        String result = listGraph.deleteEdge(1, 2);
        assertEquals("Edge not found", result);
    }

    public void testDeleteEdge3() {
        setUpStage3();
        String result = listGraph.deleteEdge(1, 2);
        assertEquals("Edge deleted successfully.", result);
    }

    public void testBFS1() {
        setUpStage4();
        assertEquals("1 2 3 4 5 6 7 8", listGraph.BFS(1));
    }

    public void testBFS2() {
        setUpStage3();
        assertEquals("2 1 3 5 4", listGraph.BFS(2));
    }

    public void testBFS3() {
        setUpStage6();
        assertEquals("Node not found", listGraph.BFS(10));
    }

    public void testDFS1() {
        setUpStage4();
        assertEquals("1 2 4 5 6 3 7 8", listGraph.DFS(1));
    }

    public void testDFS2() {
        setupStage5();
        assertEquals("0 1 2 3 4 5 6 7 8", listGraph.DFS(0));
    }

    public void testDFS3() {
        setUpStage6();
        assertEquals("Node not found", listGraph.DFS(20));
    }

    public void testDijkstra1() {
        setupStage5();
        assertEquals("[0.0, 4.0, 12.0, 19.0, 21.0, 11.0, 9.0, 8.0, 14.0]", Arrays.toString(listGraph.dijkstra(0)));
    }

    public void testDijkstra2() {
        setUpStage7();
        assertEquals("[0.0, 2.0, 6.0, 7.0, 17.0, 22.0, 19.0]", Arrays.toString(listGraph.dijkstra(0)));
    }

    public void testDijkstra3() {
        setUpStage7();
        assertEquals("null", Arrays.toString(listGraph.dijkstra(10)));
    }
}
