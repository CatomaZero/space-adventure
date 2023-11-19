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
        listGraph.addEdge(0,1, 5.0);
        listGraph.addEdge(0,3, 10.0);
        listGraph.addEdge(1,2,3.0);
        listGraph.addEdge(2, 3, 1.0);
    }

    public void setUpStage7(){
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

    public void setUpStage8(){
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
        assertEquals("Initial node not found", result);
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

    public void testDFS1(){
        setUpStage4();
        assertEquals("1 2 4 5 6 3 7 8", listGraph.DFS(1));
    }

    public void testDijkstra1(){
        setupStage5();
        assertEquals("[0.0, 4.0, 12.0, 19.0, 21.0, 11.0, 9.0, 8.0, 14.0]", Arrays.toString(listGraph.dijkstra(0)));
    }

    public void testFloydWarshall(){
        setUpStage6();
        String result =
                "0.0\t5.0\t8.0\t9.0\t\n" +
                "5.0\t0.0\t3.0\t4.0\t\n" +
                "8.0\t3.0\t0.0\t1.0\t\n" +
                "9.0\t4.0\t1.0\t0.0";
        assertEquals(result, listGraph.getFloydWarshallResultString());
    }

    public void testPrim(){
        setUpStage7();
        assertEquals("xd", listGraph.primMST());
    }

    public void testKruskal(){
        setUpStage8();
        assertEquals("xd", listGraph.kruskalMST());
    }
}
