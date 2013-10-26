package A2_test;

import org.junit.Test;
import static org.junit.Assert.*;

import GKA_A1.AIGraph;
import GKA_A1.IAIGraph;
import GKA_A2.Floyd_Warshall;

public class OptimalPathTest {

	private IAIGraph yolo;

	public OptimalPathTest() {

	}

	@Test
	public void test() {

		yolo = new AIGraph(true);

		long v1 = yolo.addVertex("v1");
		long v2 = yolo.addVertex("v2");
		long v3 = yolo.addVertex("v3");
		long v4 = yolo.addVertex("v4");

		long v1v2 = yolo.addEdgeD(v1, v2);
		yolo.setValE(v1v2, "km", 1);

		long v1v4 = yolo.addEdgeD(v1, v4);
		yolo.setValE(v1v4, "km", 3);

		long v2v3 = yolo.addEdgeD(v2, v3);
		yolo.setValE(v2v3, "km", 2);

		long v2v4 = yolo.addEdgeD(v2, v4);
		yolo.setValE(v2v4, "km", 1);

		long v3v2 = yolo.addEdgeD(v3, v2);
		yolo.setValE(v3v2, "km", 2);

		long v4v3 = yolo.addEdgeD(v4, v3);
		yolo.setValE(v4v3, "km", 2);

		long v4v1 = yolo.addEdgeD(v4, v1);
		yolo.setValE(v4v1, "km", 2);

		System.out.println(yolo);

		Floyd_Warshall algo = new Floyd_Warshall(yolo, "km");

		algo.start();

		System.out.println("v1: " + v1 + "; v2: " + v2);
		System.out.println(algo.getPath(v1, v2));

		System.out.println("v3: " + v3 + "; v1: " + v1);
		System.out.println(algo.getPath(v3, v1));

		assertEquals("v2 -> v1 -> v3 -> v0", algo.getPath(v3, v1));
		System.out.println(algo.getDist());
		System.out.println(algo.getTrans());
		// System.out.println(yolo.toMatrixU());
		// System.out.println(yolo);
		// System.out.println(swag.toMatrixD());
		// System.out.println(swag);
	}

}
