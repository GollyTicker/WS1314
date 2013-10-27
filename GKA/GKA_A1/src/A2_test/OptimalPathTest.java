package A2_test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import GKA_A1.AIGraph;
import GKA_A1.IAIGraph;
import GKA_A2.BellmanFord;
import GKA_A2.FloydWarshall;
import GraphUtils.JavaParser;

public class OptimalPathTest {

	private IAIGraph testGraph;
	private long v1, v2, v3, v4;

	public OptimalPathTest() {
		// Create Graph from sample of the Book we are using in GKA
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

	@Test
	public void testFloydWarshall() {
		System.out.println("----- testFloydWarshall -----");
		FloydWarshall algo = new FloydWarshall(this.testGraph, "km");

		algo.printCount();
		algo.start();
		algo.printCount();

		assertEquals("v2 -> v1 -> v3 -> v0", algo.getPath(v3, v1));
		assertEquals("v0 -> v0", algo.getPath(v1, v1));
		List<Long> expected = new ArrayList<>(Arrays.asList(2L, 1L, 3L, 0L));
		assertEquals(expected, algo.getPathList(v3, v1));
		expected = new ArrayList<>(Arrays.asList(0L, 0L));
		assertEquals(expected, algo.getPathList(v1, v1));
		// printFloyd(algo);
	}

	@Test
	public void testBellmanFord() {
		System.out.println("----- testBellmanFord -----");
		BellmanFord algo = new BellmanFord(this.testGraph, "km", v3);

		algo.printCount();
		algo.start();
		algo.printCount();

		assertEquals("v2 -> v1 -> v3 -> v0", algo.getPath(v1));
		List<Long> expected = new ArrayList<>(Arrays.asList(2L, 1L, 3L, 0L));
		assertEquals(expected, algo.getPathList(v1));

		algo.setSrc(v1);
		algo.start();
		assertEquals(Arrays.asList(0L), algo.getPathList(v1));

		// printBell(algo);
	}

	private String swaneetpath = "C:/Users/Swaneet/github/WS1314/GKA/graphs/";
	private String matzepath = "/Users/matthias/dev/WS1314/GKA/graphs/";
	private String path = matzepath;
	private String graphname = "graph2.graph";

	@Test
	public void testCmpBoth() {
		System.out.println("----- testBoth -----");
		JavaParser jp = new JavaParser(path + graphname, "km");

		IAIGraph yolo = jp.createGraph();
		FloydWarshall algoF = new FloydWarshall(yolo, "km");
		algoF.printCount();
		algoF.start();
		algoF.printCount();

		IAIGraph swag = jp.createGraph();
		BellmanFord algoB = new BellmanFord(swag, "km", 0);
		algoB.printCount();
		algoB.start();
		algoB.printCount();
	}

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
