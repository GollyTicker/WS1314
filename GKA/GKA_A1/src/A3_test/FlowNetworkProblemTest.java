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
		String graphname = "graph2.graph";
		JavaParser jp = new JavaParser(path + graphname, "km");
		this.yolo = jp.createGraph();
	}

	@Test
	public void EdmondsKarpTest() {
		yolo = edmondsKarp(yolo);
	}

}
