package GKA_A2;

import GKA_A1.IAIGraph;
import GKA_A2.Matrix.Matrix;

public class Floyd_Warshall {

	private IAIGraph graph;
	private boolean isDirected;
	private String cmpByAttribute;

	private double inf = Double.POSITIVE_INFINITY;

	public Floyd_Warshall(IAIGraph graph, String cmpByAttribute) {
		this.graph = graph;
		this.isDirected = graph.isDirected();
		this.cmpByAttribute = cmpByAttribute;
	}

	public void initialize() {
		int size = graph.getVertexes().size();

	}

	// Shortest Path from -> to
	public String getPath(long v1, long v2) {
		String accu = "";
		return "";
	}

}
