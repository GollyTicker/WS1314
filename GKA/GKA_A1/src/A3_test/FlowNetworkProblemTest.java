package A3_test;

import static org.junit.Assert.*;
import static GKA_A3.EdmondsKarp.*;
import org.junit.Test;

import GKA_A1.IAIGraph;
import GKA_A3.EdmondsKarp;
import GraphUtils.JavaParser;
import GraphUtils.WhichPath;

public class FlowNetworkProblemTest {

	private IAIGraph yolo;

	public FlowNetworkProblemTest() {
		String path = WhichPath.getPath();
		String graphname = "graph20.graph";
		JavaParser jp = new JavaParser(path + graphname, "cap");
		this.yolo = jp.createGraph();
	}

	@Test
	public void EdmondsKarpTest() {
		System.out.println(yolo);
		yolo = edmondsKarp(yolo);
	}

}
