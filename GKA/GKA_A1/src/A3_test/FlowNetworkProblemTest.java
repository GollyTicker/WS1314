package A3_test;

import static org.junit.Assert.*;
import static GKA_A3.EdmondsKarp.*;
import static GKA_A3.FordFulkerson.*;

import org.junit.Test;

import GKA_A1.IAIGraph;
import GKA_A3.EdmondsKarp;
import GKA_A3.FordFulkerson;
import GraphUtils.JavaParser;
import GraphUtils.WhichPath;

public class FlowNetworkProblemTest {

	private IAIGraph yolo;
	private long quelleID;
	private long senkeID;
	private String graphname;
	private final String capacityAttrName = "c";
	private final String flowAttrName = "f";

	public FlowNetworkProblemTest() {
		// dont forget to change the quelleID and senkeID as well!
		graphname = "graph20.graph";
		quelleID = 0;
		senkeID = 4;
		// dont forget to change the quelleID and senkeID as well!

		String path = WhichPath.getPath();
		JavaParser jp = new JavaParser(path + graphname, capacityAttrName);
		this.yolo = jp.createGraph();
		System.out.println("Input Graph: " + yolo);
	}

	@Test
	public void FordFulkersonTest() {
		new FordFulkerson(yolo, quelleID, senkeID, capacityAttrName,
				flowAttrName);
		System.out.println("Graph with maximal Flow: " + yolo);
	}

	@Test
	public void FordFulkersonBackwardTest() {
		if (graphname == "graph20.graph") {
			// ONLY WORKS FOR --- graph20.graph --- !!!1
			// add a bad Flow before giving it over to FordFulkerson
			f_set(0L, 4);
			f_set(1L, 5);
			f_set(2L, 3);
			f_set(3L, 8);
			f_set(4L, 5);
			f_set(5L, 6);
			f_set(6L, 4);
			f_set(7L, 3);
			
			System.out.println("Bad Flow Graph: " + yolo);
			new FordFulkerson(yolo, quelleID, senkeID, capacityAttrName,
					flowAttrName);
			System.out.println("Graph with maximal Flow: " + yolo);
		}
	}

	private void f_set(Long eID, int flowValue) { // sets the current flow on
		// the edge
		yolo.setValE(eID, flowAttrName, flowValue);
	}

	/*
	 * @Test public void EdmondsKarpTest() { yolo = edmondsKarp(yolo); }
	 */

}