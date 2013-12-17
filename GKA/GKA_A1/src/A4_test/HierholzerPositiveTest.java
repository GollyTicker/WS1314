package A4_test;

import static GKA_A4.Hierholzer.isEulerianCycle;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import GKA_A1.IAIGraph;
import GKA_A4.Hierholzer;
import GraphUtils.JavaGraphParser;
import GraphUtils.WhichPath;

public class HierholzerPositiveTest {

	private String cmp = "km";
	private IAIGraph yolo;

	public HierholzerPositiveTest() {
	}

	// Hierholzer Algorithm
	@Test
	public void TestHierholzerPositive1() {
		loadGraph("graph35.graph");
		Hierholzer algo = new Hierholzer(yolo);
		List<Long> result = algo.hierholzeEs();
		System.out.println("Eulertour(edges): " + result);
		algo.printCount();
		assertTrue(isEulerianCycle(yolo, result));
	}

	@Test
	public void TestHierholzerPositive2() {
		loadGraph("graph36.graph");
		Hierholzer algo = new Hierholzer(yolo);
		List<Long> result = algo.hierholzeEs();
		System.out.println("Eulertour(edges): " + result);
		algo.printCount();
		assertTrue(isEulerianCycle(yolo, result));
	}

	@Test
	public void TestHierholzerPositive3() {
		loadGraph("graph39.graph");
		// more difficult graph which names the vertices in such a way
		// so that a merging of two cycles is nessesary
		Hierholzer algo = new Hierholzer(yolo);
		List<Long> result = algo.hierholzeEs();
		System.out.println("Eulertour(edges): " + result);
		algo.printCount();
		assertTrue(isEulerianCycle(yolo, result));
	}

	@Test
	public void TestHierholzerPositive4() {
		loadGraph("graph42.graph"); // this is the one from the book page 121.
									// its the one with 8 vertices
		Hierholzer algo = new Hierholzer(yolo);
		List<Long> result = algo.hierholzeEs();
		System.out.println("Eulertour(edges): " + result);
		algo.printCount();
		assertTrue(isEulerianCycle(yolo, result));
	}

	@Test
	public void TestHierholzerPositive5() {
		loadGraph("graph43.graph");
		// form the german wikipedia article:
		// https://de.wikipedia.org/wiki/Algorithmus_von_Hierholzer
		Hierholzer algo = new Hierholzer(yolo);
		List<Long> result = algo.hierholzeEs();
		System.out.println("Eulertour(edges): " + result);
		algo.printCount();
		assertTrue(isEulerianCycle(yolo, result));
	}

	private void loadGraph(String graphname) {
		String path = WhichPath.getPath();
		JavaGraphParser jp = new JavaGraphParser(path + graphname, cmp);
		this.yolo = jp.createGraph();
		System.out.println("\n<======= New Test =======>\n Input Graph: "
				+ yolo);
	}
}
