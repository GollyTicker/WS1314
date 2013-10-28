package A2_test;

import static org.junit.Assert.assertEquals;

import java.awt.IllegalComponentStateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import GKA_A1.AIGraph;
import GKA_A1.IAIGraph;
import GKA_A2.BellmanFord;
import GKA_A2.FloydWarshall;
import GraphUtils.JavaParser;
import static GKA_A2.Constants.*;

public class OptimalPathTest {

	private IAIGraph testGraph;
	private long v1, v2, v3, v4;

	private IAIGraph cyclicGraph;
	private long v10, v11, v12;

	public OptimalPathTest() {
		// Create Graph from sample of the Book we are using in GKA
		setUpGraph();
		setUpCycleGraph();
	}

	@Test
	public void testFloydWarshall() {
		System.out.println("----- testFloydWarshall -----");
		FloydWarshall algo = new FloydWarshall(this.testGraph, "km");

		algo.printCount();
		algo.start();
		algo.printCount();

		// If there is a Path!
		assertEquals("v2 -> v1 -> v3 -> v0", algo.getPath(v3, v1));
		assertEquals("v0 -> v0", algo.getPath(v1, v1));
		List<Long> expected = new ArrayList<>(Arrays.asList(2L, 1L, 3L, 0L));
		assertEquals(expected, algo.getPathList(v3, v1));
		expected = new ArrayList<>(Arrays.asList(0L, 0L));
		assertEquals(expected, algo.getPathList(v1, v1));

		// If there is no Path!
		long v5 = this.testGraph.addVertex("v5");
		algo = new FloydWarshall(this.testGraph, "km");
		algo.start();
		assertEquals(new ArrayList<Long>(), algo.getPathList(v1, v5));
		assertEquals(NO_PATH, algo.getPath(v1, v5));

		// printFloyd(algo);
	}

	@Test(expected = IllegalComponentStateException.class)
	public void testFloydCycle() {
		FloydWarshall algo = new FloydWarshall(this.cyclicGraph, "km");
		algo.start();
	}

	@Test
	public void testBellmanFord() {
		System.out.println("----- testBellmanFord -----");
		BellmanFord algo = new BellmanFord(this.testGraph, "km", v3);

		algo.printCount();
		algo.start();
		algo.printCount();

		// If there is a Path!
		assertEquals("v2 -> v1 -> v3 -> v0", algo.getPath(v1));
		List<Long> expected = new ArrayList<>(Arrays.asList(2L, 1L, 3L, 0L));
		assertEquals(expected, algo.getPathList(v1));

		algo.setSrc(v1);
		algo.start();
		assertEquals(Arrays.asList(0L), algo.getPathList(v1));

		// If there is no Path!
		long v5 = this.testGraph.addVertex("v5");
		algo = new BellmanFord(this.testGraph, "km", v5);
		algo.start();
		assertEquals(new ArrayList<Long>(), algo.getPathList(v1));
		assertEquals(NO_PATH, algo.getPath(v1));

		// printBell(algo);
	}

	@Test(expected = IllegalComponentStateException.class)
	public void testBellmanCycle() {
		BellmanFord algo = new BellmanFord(this.cyclicGraph, "km", this.v10);
		algo.start();
	}

	@Test
	public void testCmpBoth() {
		String swaneetpath = "C:/Users/Swaneet/github/WS1314/GKA/graphs/";
		String matzepath = "/Users/matthias/dev/WS1314/GKA/graphs/";
		String path = matzepath;
		String graphname = "graph2.graph";

		System.out.println("----- testBoth -----");
		JavaParser jp = new JavaParser(path + graphname, "km");

		System.out.println("----- countFloydWarshall -----");
		IAIGraph yolo = jp.createGraph();
		FloydWarshall algoF = new FloydWarshall(yolo, "km");
		algoF.printCount();
		algoF.start();
		algoF.printCount();

		System.out.println("----- countBellmanFord -----");
		IAIGraph swag = jp.createGraph();
		BellmanFord algoB = new BellmanFord(swag, "km", 0);
		algoB.printCount();
		algoB.start();
		algoB.printCount();
	}

	// INITIALIZATION
	private void setUpGraph() {
		this.testGraph = new AIGraph(true);
		this.v1 = testGraph.addVertex("v1");
		this.v2 = testGraph.addVertex("v2");
		this.v3 = testGraph.addVertex("v3");
		this.v4 = testGraph.addVertex("v4");
		long v1v2 = testGraph.addEdgeD(v1, v2);
		testGraph.setValE(v1v2, "km", 1);
		long v1v4 = testGraph.addEdgeD(v1, v4);
		testGraph.setValE(v1v4, "km", 3);
		long v2v3 = testGraph.addEdgeD(v2, v3);
		testGraph.setValE(v2v3, "km", 2);
		long v2v4 = testGraph.addEdgeD(v2, v4);
		testGraph.setValE(v2v4, "km", 1);
		long v3v2 = testGraph.addEdgeD(v3, v2);
		testGraph.setValE(v3v2, "km", 2);
		long v4v3 = testGraph.addEdgeD(v4, v3);
		testGraph.setValE(v4v3, "km", 2);
		long v4v1 = testGraph.addEdgeD(v4, v1);
		testGraph.setValE(v4v1, "km", 2);
	}

	private void setUpCycleGraph() {
		this.cyclicGraph = new AIGraph(true);
		this.v10 = this.cyclicGraph.addVertex("v10");
		this.v11 = this.cyclicGraph.addVertex("v11");
		this.v12 = this.cyclicGraph.addVertex("v12");
		long v10v11 = this.cyclicGraph.addEdgeD(v10, v11);
		cyclicGraph.setValE(v10v11, "km", 1);
		long v10v12 = this.cyclicGraph.addEdgeD(v10, v12);
		cyclicGraph.setValE(v10v12, "km", -2);
		long v11v10 = this.cyclicGraph.addEdgeD(v11, v10);
		cyclicGraph.setValE(v11v10, "km", 1);
		long v11v12 = this.cyclicGraph.addEdgeD(v11, v12);
		cyclicGraph.setValE(v11v12, "km", 1);
		long v12v10 = this.cyclicGraph.addEdgeD(v12, v10);
		cyclicGraph.setValE(v12v10, "km", 1);
		long v12v11 = this.cyclicGraph.addEdgeD(v12, v11);
		cyclicGraph.setValE(v12v11, "km", 1);
	}

	// HELPERS
	private void printFloyd(FloydWarshall algo) {
		System.out.println("v1: " + v1 + "; v1: " + v1);
		System.out.println(algo.getPath(v1, v1));
		System.out.println("v1: " + v1 + "; v2: " + v2);
		System.out.println(algo.getPath(v1, v2));
		System.out.println("v3: " + v3 + "; v1: " + v1);
		System.out.println(algo.getPath(v3, v1));
		System.out.println(algo.getDist());
		System.out.println(algo.getTrans());
	}

	private void printBell(BellmanFord algo) {
		System.out.println(algo.getDist());
		System.out.println(algo.getPred());
		System.out.println(algo.getPath(v3)); // path zu sich selber
		System.out.println(algo.getPath(v1)); // path nach v1
	}

}
