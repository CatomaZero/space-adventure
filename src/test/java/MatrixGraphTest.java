import junit.framework.TestCase;
import structure.Matrix.MatrixGraph;

import java.util.Arrays;

public class MatrixGraphTest extends TestCase {
    private MatrixGraph<Integer> matrixGraph;

    public void setUpStage1() {
        matrixGraph = new MatrixGraph<>();
    }

    public void setUpStage2() {
        matrixGraph = new MatrixGraph<>();
        matrixGraph.addNode(1);
        matrixGraph.addNode(2);
        matrixGraph.addNode(3);
    }

    public void setUpStage3() {
        matrixGraph = new MatrixGraph<>();
        matrixGraph.addNode(0);
        matrixGraph.addNode(1);
        matrixGraph.addNode(2);
        matrixGraph.addNode(3);
        matrixGraph.addNode(4);
        matrixGraph.addEdge(0, 1, 2.0);
        matrixGraph.addEdge(0, 3, 6.0);
        matrixGraph.addEdge(1, 2, 3.0);
        matrixGraph.addEdge(1, 3, 8.0);
        matrixGraph.addEdge(1, 4, 5.0);
        matrixGraph.addEdge(2, 4, 7.0);
        matrixGraph.addEdge(3, 4, 9.0);
    }


    public void setUpStage4() {
        matrixGraph = new MatrixGraph<>();
        matrixGraph.addNode(1);
        matrixGraph.addNode(2);
        matrixGraph.addNode(3);
        matrixGraph.addNode(4);
        matrixGraph.addNode(5);
        matrixGraph.addNode(6);
        matrixGraph.addNode(7);
        matrixGraph.addNode(8);
        matrixGraph.addEdge(1, 2, 5.0);
        matrixGraph.addEdge(1, 3, 2.0);
        matrixGraph.addEdge(2, 4, 5.0);
        matrixGraph.addEdge(2, 5, 2.0);
        matrixGraph.addEdge(3, 6, 5.0);
        matrixGraph.addEdge(3, 7, 5.0);
        matrixGraph.addEdge(3, 8, 5.0);
        matrixGraph.addEdge(5, 6, 5.0);
    }

    public void setupStage5(){
        matrixGraph = new MatrixGraph<>();
        matrixGraph.addNode(0);
        matrixGraph.addNode(1);
        matrixGraph.addNode(2);
        matrixGraph.addNode(3);
        matrixGraph.addNode(4);
        matrixGraph.addNode(5);
        matrixGraph.addNode(6);
        matrixGraph.addNode(7);
        matrixGraph.addNode(8);
        matrixGraph.addEdge(0, 1, 4.0);
        matrixGraph.addEdge(0, 7, 8.0);
        matrixGraph.addEdge(1, 2, 8.0);
        matrixGraph.addEdge(1, 7, 11.0);
        matrixGraph.addEdge(2, 3, 7.0);
        matrixGraph.addEdge(2, 8, 2.0);
        matrixGraph.addEdge(2, 5, 4.0);
        matrixGraph.addEdge(3, 4, 9.0);
        matrixGraph.addEdge(3, 5, 14.0);
        matrixGraph.addEdge(4, 5, 10.0);
        matrixGraph.addEdge(5, 6, 2.0);
        matrixGraph.addEdge(6, 7, 1.0);
        matrixGraph.addEdge(6, 8, 6.0);
        matrixGraph.addEdge(7, 8, 7.0);
    }

    public void setUpStage6() {
        matrixGraph = new MatrixGraph<>();
        matrixGraph.addNode(0);
        matrixGraph.addNode(1);
        matrixGraph.addNode(2);
        matrixGraph.addNode(3);
        matrixGraph.addNode(4);
        matrixGraph.addNode(5);
        matrixGraph.addNode(6);
        matrixGraph.addEdge(0, 1,2.0);
        matrixGraph.addEdge(0, 2, 6.0);
        matrixGraph.addEdge(1, 3, 5.0);
        matrixGraph.addEdge(2, 3, 8.0);
        matrixGraph.addEdge(3, 5, 15.0);
        matrixGraph.addEdge(2, 3, 8.0);
        matrixGraph.addEdge(3, 5, 15.0);
        matrixGraph.addEdge(3, 4, 10.0);
        matrixGraph.addEdge(4, 5, 6.0);
        matrixGraph.addEdge(4, 6, 2.0);
        matrixGraph.addEdge(5, 6, 6.0);
    }

    public void setUpStage7() {
        matrixGraph = new MatrixGraph<>();
        matrixGraph.addNode(0);
        matrixGraph.addNode(1);
        matrixGraph.addNode(2);
        matrixGraph.addNode(3);
        matrixGraph.addNode(4);
        matrixGraph.addNode(5);
        matrixGraph.addNode(6);
        matrixGraph.addEdge(0, 1,2.0);
        matrixGraph.addEdge(0, 2, 6.0);
        matrixGraph.addEdge(1, 3, 5.0);
        matrixGraph.addEdge(2, 3, 8.0);
        matrixGraph.addEdge(3, 5, 15.0);
        matrixGraph.addEdge(2, 3, 8.0);
        matrixGraph.addEdge(3, 5, 15.0);
        matrixGraph.addEdge(3, 4, 10.0);
        matrixGraph.addEdge(4, 5, 6.0);
        matrixGraph.addEdge(4, 6, 2.0);
        matrixGraph.addEdge(5, 6, 6.0);
    }

    public void setUpStage8(){
        matrixGraph = new MatrixGraph<>();
        matrixGraph.addNode(0);
        matrixGraph.addNode(1);
        matrixGraph.addNode(2);
        matrixGraph.addNode(3);
        matrixGraph.addNode(4);
        matrixGraph.addNode(5);
        matrixGraph.addEdge(0, 1, 700.0);
        matrixGraph.addEdge(0, 2, 200.0);
        matrixGraph.addEdge(1, 2, 300.0);
        matrixGraph.addEdge(1, 3, 200.0);
        matrixGraph.addEdge(2, 3, 700.0);
        matrixGraph.addEdge(2, 4, 600.0);
        matrixGraph.addEdge(3, 4, 300.0);
        matrixGraph.addEdge(3, 5, 100.0);
        matrixGraph.addEdge(4, 5, 500.0);
    }

    public void testAddNode1() {
        setUpStage1();
        String result = matrixGraph.addNode(1);
        assertEquals("Node added successfully.", result);
    }

    public void testAddNode2() {
        setUpStage2();
        String result = matrixGraph.addNode(2);
        assertEquals("The addition of this node is not possible as there is one with the same key.", result);
    }

    public void testAddNode3() {
        setUpStage3();
        String result = matrixGraph.addNode(6);
        assertEquals("Node added successfully.", result);
    }

    public void testAddEdge1() {
        setUpStage1();
        String result = matrixGraph.addEdge(1, 2, 5.0);
        assertEquals("Empty graph", result);
    }

    public void testAddEdge2() {
        setUpStage2();
        String result = matrixGraph.addEdge(5, 3, 3.0);
        assertEquals("One or both nodes not found.", result);
    }

    public void testAddEdge3() {
        setUpStage3();
        String result = matrixGraph.addEdge(2, 3, 4.0);
        assertEquals("Edge added successfully.", result);
    }

    public void testConsult1(){
        setUpStage1();
        String actual = matrixGraph.consult();
        assertEquals("", actual);
    }

    public void testConsult2(){
        setUpStage2();
        String expected = "1\n" +
                "2\n" +
                "3";
        String actual = matrixGraph.consult();
        assertEquals(expected, actual);
    }

    public void testConsult3(){
        setUpStage3();
        String expected = "0\n" +
                "(0, 1)   Weight: 2.0\n" +
                "(0, 3)   Weight: 6.0\n" +
                "1\n" +
                "(0, 1)   Weight: 2.0\n" +
                "(1, 2)   Weight: 3.0\n" +
                "(1, 3)   Weight: 8.0\n" +
                "(1, 4)   Weight: 5.0\n" +
                "2\n" +
                "(1, 2)   Weight: 3.0\n" +
                "(2, 4)   Weight: 7.0\n" +
                "3\n" +
                "(0, 3)   Weight: 6.0\n" +
                "(1, 3)   Weight: 8.0\n" +
                "(3, 4)   Weight: 9.0\n" +
                "4\n" +
                "(1, 4)   Weight: 5.0\n" +
                "(2, 4)   Weight: 7.0\n" +
                "(3, 4)   Weight: 9.0";
        String actual = matrixGraph.consult();
        assertEquals(expected, actual);
    }

    public void testConsultNode1(){
        setUpStage1();
        assertEquals("Node not found.", matrixGraph.consultNode(1));
    }

    public void testConsultNode2(){
        setUpStage2();
        assertEquals("3", matrixGraph.consultNode(3));
    }

    public void testConsultNode3(){
        setUpStage3();
        String result = "4\n" +
                "(1, 4)   Weight: 5.0\n" +
                "(2, 4)   Weight: 7.0\n" +
                "(3, 4)   Weight: 9.0";
        assertEquals(result, matrixGraph.consultNode(4));
    }

    public void testConsultEdge1(){
        setUpStage3();
        assertEquals("Edge not found.", matrixGraph.consultEdge(0, 4));
    }

    public void testConsultEdge2(){
        setUpStage4();
        assertEquals("(2, 4)   Weight: 5.0", matrixGraph.consultEdge(2, 4));
    }

    public void testConsultEdge3(){
        setupStage5();
        assertEquals("(2, 8)   Weight: 2.0", matrixGraph.consultEdge(2, 8));
    }


    public void testDeleteNode1() {
        setUpStage1();
        String result = matrixGraph.deleteNode(1);
        assertEquals("Node not found", result);
    }

    public void testDeleteNode2() {
        setUpStage2();
        String result = matrixGraph.deleteNode(2);
        assertEquals("Node deleted successfully.", result);
    }

    public void testDeleteNode3() {
        setUpStage3();
        String result = matrixGraph.deleteNode(1);
        assertEquals("Node deleted successfully.", result);
    }

    public void testDeleteEdge1() {
        setUpStage1();
        String result = matrixGraph.deleteEdge(1, 2);
        assertEquals("One or both nodes not found.", result);
    }

    public void testDeleteEdge2() {
        setUpStage2();
        String result = matrixGraph.deleteEdge(1, 2);
        assertEquals("Edge not found", result);
    }

    public void testDeleteEdge3() {
        setUpStage3();
        String result = matrixGraph.deleteEdge(1, 2);
        assertEquals("Edge deleted successfully.", result);
    }

    public void testBFS1() {
        setUpStage4();
        assertEquals("1 2 3 4 5 6 7 8", matrixGraph.BFS(1));
    }

    public void testBFS2() {
        setUpStage3();
        assertEquals("2 1 4 0 3", matrixGraph.BFS(2));
    }

    public void testBFS3() {
        setUpStage6();
        assertEquals("Node not found.", matrixGraph.BFS(10));
    }

    public void testDFS1() {
        setUpStage4();
        assertEquals("1 2 4 5 3 6 7 8", matrixGraph.DFS(8,1,3));
    }

    public void testDFS2() {
        setupStage5();
        assertEquals("0 1 2 5 7", matrixGraph.DFS(5,0,3));
    }
    public void testDFS3() {
        setUpStage6();
        assertEquals("Node not found", matrixGraph.DFS(20,0,3));
    }

    public void testDijkstra1() {
        setupStage5();
        assertEquals("[0.0, 4.0, 12.0, 19.0, 21.0, 11.0, 9.0, 8.0, 14.0]", Arrays.toString(matrixGraph.dijkstra(0)));
    }

    public void testDijkstra2() {
        setUpStage7();
        assertEquals("[0.0, 2.0, 6.0, 7.0, 17.0, 22.0, 19.0]", Arrays.toString(matrixGraph.dijkstra(0)));
    }

    public void testDijkstra3() {
        setUpStage7();
        assertEquals("[]", Arrays.toString(matrixGraph.dijkstra(10)));
    }

    public void testFloydWarshall1(){
        setUpStage8();
        String result = "0.0  500.0  200.0  700.0  800.0  800.0  \n" +
                "500.0  0.0  300.0  200.0  500.0  300.0  \n" +
                "200.0  300.0  0.0  500.0  600.0  600.0  \n" +
                "700.0  200.0  500.0  0.0  300.0  100.0  \n" +
                "800.0  500.0  600.0  300.0  0.0  400.0  \n" +
                "800.0  300.0  600.0  100.0  400.0  0.0";
        assertEquals(result, matrixGraph.getFloydWarshallResultString());
    }

    public void testFloydWarshall2() {
        setUpStage4();
        String result = "0.0  5.0  2.0  10.0  7.0  7.0  7.0  7.0  \n" +
                "5.0  0.0  7.0  5.0  2.0  7.0  12.0  12.0  \n" +
                "2.0  7.0  0.0  12.0  9.0  5.0  5.0  5.0  \n" +
                "10.0  5.0  12.0  0.0  7.0  12.0  17.0  17.0  \n" +
                "7.0  2.0  9.0  7.0  0.0  5.0  14.0  14.0  \n" +
                "7.0  7.0  5.0  12.0  5.0  0.0  10.0  10.0  \n" +
                "7.0  12.0  5.0  17.0  14.0  10.0  0.0  10.0  \n" +
                "7.0  12.0  5.0  17.0  14.0  10.0  10.0  0.0";
        assertEquals(result, matrixGraph.getFloydWarshallResultString());
    }

    public void testFloydWarshall3(){
        setUpStage6();
        String result = "0.0  2.0  6.0  7.0  17.0  22.0  19.0  \n" +
                "2.0  0.0  8.0  5.0  15.0  20.0  17.0  \n" +
                "6.0  8.0  0.0  8.0  18.0  23.0  20.0  \n" +
                "7.0  5.0  8.0  0.0  10.0  15.0  12.0  \n" +
                "17.0  15.0  18.0  10.0  0.0  6.0  2.0  \n" +
                "22.0  20.0  23.0  15.0  6.0  0.0  6.0  \n" +
                "19.0  17.0  20.0  12.0  2.0  6.0  0.0";
        assertEquals(result, matrixGraph.getFloydWarshallResultString());
    }

    public void testPrim1(){
        setUpStage3();
        String result = "(0, 1)   Weight: 2.0\n" +
                "(1, 2)   Weight: 3.0\n" +
                "(1, 4)   Weight: 5.0\n" +
                "(0, 3)   Weight: 6.0";
        assertEquals(result, matrixGraph.primMST(0, 50));
    }

    public void testPrim2(){
        setupStage5();
        String result = "(1, 0)   Weight: 4.0\n" +
                "(0, 7)   Weight: 8.0\n" +
                "(7, 6)   Weight: 1.0\n" +
                "(6, 5)   Weight: 2.0\n" +
                "(5, 2)   Weight: 4.0\n" +
                "(2, 8)   Weight: 2.0\n" +
                "(2, 3)   Weight: 7.0\n" +
                "(3, 4)   Weight: 9.0";
        assertEquals(result, matrixGraph.primMST(1, 200));
    }

    public void testPrim3(){
        setUpStage6();
        String result = "(0, 1)   Weight: 2.0\n" +
                "(1, 3)   Weight: 5.0\n" +
                "(0, 2)   Weight: 6.0\n" +
                "(3, 4)   Weight: 10.0\n" +
                "(4, 6)   Weight: 2.0\n" +
                "(4, 5)   Weight: 6.0";
        assertEquals(result, matrixGraph.primMST(1,1000));
    }

    public void testKruskal1(){
        setUpStage3();
        String result = "(0, 1)   Weight: 2.0\n" +
                "(1, 2)   Weight: 3.0\n" +
                "(1, 4)   Weight: 5.0\n" +
                "(0, 3)   Weight: 6.0";
        assertEquals(result, matrixGraph.kruskalMST());
    }

    public void testKruskal2(){
        setupStage5();
        String result = "(6, 7)   Weight: 1.0\n" +
                "(2, 8)   Weight: 2.0\n" +
                "(5, 6)   Weight: 2.0\n" +
                "(0, 1)   Weight: 4.0\n" +
                "(2, 5)   Weight: 4.0\n" +
                "(2, 3)   Weight: 7.0\n" +
                "(0, 7)   Weight: 8.0\n" +
                "(3, 4)   Weight: 9.0";
        assertEquals(result, matrixGraph.kruskalMST());
    }

    public void testKruskal3(){
        setUpStage6();
        String result = "(0, 1)   Weight: 2.0\n" +
                "(4, 6)   Weight: 2.0\n" +
                "(1, 3)   Weight: 5.0\n" +
                "(0, 2)   Weight: 6.0\n" +
                "(4, 5)   Weight: 6.0\n" +
                "(3, 4)   Weight: 10.0";
        assertEquals(result, matrixGraph.kruskalMST());
    }
}
