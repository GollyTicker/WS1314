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
	private final String capacityAttrName = "c";
	private final String flowAttrName = "f";

	public FlowNetworkProblemTest() {
		// dont forget to change the quelleID and senkeID as well!
		String graphname = "graph20.graph";
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
		new FordFulkerson(yolo, quelleID, senkeID, capacityAttrName, flowAttrName);
		System.out.println("Graph with maximal Flow: " + yolo);
	}

	@Test
	public void FordFulkersonBackwardTest() {
		new FordFulkerson(yolo, quelleID, senkeID, capacityAttrName, flowAttrName);
		System.out.println("Graph with maximal Flow: " + yolo);
	}

	/*@Test
	public void EdmondsKarpTest() {
		yolo = edmondsKarp(yolo);
	}*/

}