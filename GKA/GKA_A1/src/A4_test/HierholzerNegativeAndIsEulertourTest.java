package A4_test;

import static GKA_A4.Hierholzer.isEulerianPath;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import GKA_A1.IAIGraph;
import GKA_A4.Hierholzer;
import GraphUtils.JavaGraphParser;
import GraphUtils.WhichPath;

public class HierholzerNegativeAndIsEulertourTest {

	private String cmp = "km";
	private IAIGraph yolo;

	public HierholzerNegativeAndIsEulertourTest() {
	}

	// isEulerianPath
	@Test
	public void TestIsEulerianPath() {
		loadGraph("graph35.graph");

		List<Long> eulerpath = new ArrayList<>(Arrays.asList(0L, 1L, 2L, 3L));
		assertTrue(isEulerianPath(yolo, eulerpath));

		eulerpath = new ArrayList<>(Arrays.asList(0L, 1L, 2L, 1L, 3L));
		assertTrue(!isEulerianPath(yolo, eulerpath));

		eulerpath = new ArrayList<>(Arrays.asList(0L));
		assertTrue(!isEulerianPath(yolo, eulerpath));

		eulerpath = new ArrayList<>(Arrays.asList(2L, 0L, 1L, 3L));
		assertTrue(!isEulerianPath(yolo, eulerpath));
	}

	@Test(expected = IllegalArgumentException.class)
	public void TestHierholzerNegative1() {
		loadGraph("graph37.graph"); // invalid graph. has vertices with ood
									// degrees
		Hierholzer algo = new Hierholzer(yolo);
		algo.hierholzeEs();
	}

	@Test(expected = IllegalArgumentException.class)
	public void TestHierholzerNegative2() {
		loadGraph("graph38.graph"); // invalid graph. has vertices with ood
									// degrees
		Hierholzer algo = new Hierholzer(yolo);
		algo.hierholzeEs();

	}

	private void loadGraph(String graphname) {
		String path = WhichPath.getPath();
		JavaGraphParser jp = new JavaGraphParser(path + graphname, cmp);
		this.yolo = jp.createGraph();
		System.out.println("\n<======= New Test =======>\n Input Graph: "
				+ yolo);
	}
}
