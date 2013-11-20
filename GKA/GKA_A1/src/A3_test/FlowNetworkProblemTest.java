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

	public FlowNetworkProblemTest() {
		String path = WhichPath.getPath();
		String graphname = "graph20.graph";
		quelleID = 0;
		senkeID = 4;
		JavaParser jp = new JavaParser(path + graphname, "cap");
		this.yolo = jp.createGraph();
		System.out.println("Input Graph: " + yolo);
	}
	
	@Test
	public void FordFulkersonTest() {
		FordFulkerson results = new FordFulkerson(yolo, quelleID, senkeID);
	}

	@Test
	public void EdmondsKarpTest() {
		yolo = edmondsKarp(yolo);
	}

}