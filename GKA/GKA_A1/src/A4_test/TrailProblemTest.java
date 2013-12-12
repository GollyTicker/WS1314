package A4_test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import GKA_A1.IAIGraph;
import GKA_A4.NearestInsertion;
import GraphUtils.JavaParser;
import GraphUtils.WhichPath;

public class TrailProblemTest {

	private String cmp = "km";
	private IAIGraph yolo;

	public TrailProblemTest() {
	}

	@Test
	public void NearestInsertionTest1() {
		loadGraph("graph30.graph");
		// this graph is the graph the algorithm is presented in the book with.
		// write here call of nearest insertion algorithm and its test case

		NearestInsertion algo = new NearestInsertion(yolo, cmp);
		List<Long> bestCycle = algo.nearestInsertion();

		System.out.println("BestCycle: " + bestCycle);
		System.out.println("Minimum: " + algo.getMinimum());

		assertTrue(algo.getMinimum() <= 120);
	}

	@Test
	public void NearestInsertionTest2() {
		loadGraph("graph31.graph");
		// this graph is the graph used in the exercises for the nearest
		// insertion algorithm
		// write here call of nearest inserteion algorithm and its test case

		NearestInsertion algo = new NearestInsertion(yolo, cmp);
		List<Long> bestCycle = algo.nearestInsertion();

		System.out.println("BestCycle: " + bestCycle);
		System.out.println("Minimum: " + algo.getMinimum());

		assertTrue(algo.getMinimum() <= 33);
	}

	private void loadGraph(String graphname) {
		String path = WhichPath.getPath();
		JavaParser jp = new JavaParser(path + graphname, cmp);
		this.yolo = jp.createGraph();
		System.out.println("\n<======= New Test =======>\n Input Graph: "
				+ yolo);
	}
}
