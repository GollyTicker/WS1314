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
		// don't forget to change the quelleID and senkeID as well!
		
		// there two graphs are built in such a way
		// that the maximal flow is can be recognized
		// easily by checking whether the source/dest has
		// it's edges fully filled
		
		quelleID = 0;
		senkeID = 4;
		graphname = "graph20.graph";
		
//		quelleID = 0;
//		senkeID = 3;
//		graphname = "graph21.graph";
		
//		quelleID = 0;
//		senkeID = 9;
//		graphname = "graph9.graph";
		
		// don't forget to change the quelleID and senkeID as well!

		String path = WhichPath.getPath();
		JavaParser jp = new JavaParser(path + graphname, capacityAttrName);
		this.yolo = jp.createGraph();
		System.out.println("<======= New Test =======>\n Input Graph: " + yolo);
	}

	@Test
	public void FordFulkersonTest() {
		FordFulkerson swag = new FordFulkerson(yolo, quelleID, senkeID, capacityAttrName,
				flowAttrName);
		System.out.println("Graph with maximal Flow: " + yolo);
		swag.printCount();
	}

	@Test
	public void FordFulkersonBackwardTest() {
		
		// test only for graph21 because,
		// the test adds graph-specific flow
		if (graphname == "graph21.graph") {
			
			// add a bad Flow before giving it over to FordFulkerson
			f_set(0L, 1);
			f_set(1L, 9);
			f_set(2L, 8);
			f_set(3L, 1);
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