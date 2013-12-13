package A4_test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import GKA_A1.IAIGraph;
import GKA_A4.Hierholzer;
import static GKA_A4.Hierholzer.*;
import GKA_A4.NearestInsertion;
import GraphUtils.JavaGraphParser;
import GraphUtils.WhichPath;

public class TrailProblemTest {

	private String cmp = "km";
	private IAIGraph yolo;

	public TrailProblemTest() {
	}

	// NearestInsertion
	// @Test
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

	// @Test
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

	// Hierholzer
	@Test
	public void TestIsEulerianPath() {
		loadGraph("graph35.graph");

		List<Long> eulerpath = new ArrayList<>(Arrays.asList(0L, 1L, 2L, 3L));
		assertTrue(isEulerianPath(yolo, eulerpath));

		eulerpath = new ArrayList<>(Arrays.asList(0L, 1L, 2L, 1L, 3L));
		assertTrue(!isEulerianPath(yolo, eulerpath));

		eulerpath = new ArrayList<>(Arrays.asList(2L, 0L, 2L, 3L));
		assertTrue(!isEulerianPath(yolo, eulerpath));
	}

	// @Test
	public void TestHierholzerPositive1() {
		loadGraph("graph35.graph");
		Hierholzer algo = new Hierholzer(yolo);
		List<Long> result = algo.hierholzeEs();
		assertTrue(isEulerianPath(yolo, result));
	}

	// @Test
	public void TestHierholzerNegative1() {
		loadGraph("graph36.graph");
		Hierholzer algo = new Hierholzer(yolo);
		List<Long> result = algo.hierholzeEs();
		assertTrue(!isEulerianPath(yolo, result));
	}

	// @Test
	public void TestHierholzerPositive2() {
		loadGraph("graph37.graph");
		Hierholzer algo = new Hierholzer(yolo);
		List<Long> result = algo.hierholzeEs();
		assertTrue(isEulerianPath(yolo, result));
	}

	// @Test
	public void TestHierholzerNegative2() {
		loadGraph("graph38.graph");
		Hierholzer algo = new Hierholzer(yolo);
		List<Long> result = algo.hierholzeEs();
		assertTrue(!isEulerianPath(yolo, result));
		
		// TODO: activate all Tests
	}

	private void loadGraph(String graphname) {
		String path = WhichPath.getPath();
		JavaGraphParser jp = new JavaGraphParser(path + graphname, cmp);
		this.yolo = jp.createGraph();
		System.out.println("\n<======= New Test =======>\n Input Graph: "
				+ yolo);
	}
}
