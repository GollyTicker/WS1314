package GKA_A2;

import GKA_A1.IAIGraph;

public class Floyd_Warshall {

	private IAIGraph graph;
	private boolean isDirected;

	public Floyd_Warshall(IAIGraph graph, boolean isDirected) {
		this.graph = graph;
		this.isDirected = isDirected;
	}

	public void initialize() {

	}

	// Shortest Path from -> to
	public String getPath(long v1, long v2) {
		String accu = "";
		return "";
	}

}
