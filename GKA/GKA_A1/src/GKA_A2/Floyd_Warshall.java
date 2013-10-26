package GKA_A2;

import java.util.Set;

import GKA_A1.IAIGraph;
import GKA_A1.IVertice;
import GKA_A2.Matrix.Matrix;
import GKA_A2.Matrix.MatrixArray;

public class Floyd_Warshall {

	private IAIGraph graph;
	private boolean isDirected;
	private String cmpByAttribute;

	private Matrix trans;
	private Matrix dist;
	private double inf = Double.POSITIVE_INFINITY;

	public Floyd_Warshall(IAIGraph graph, boolean isDirected,
			String cmpByAttribute) {
		this.graph = graph;
		this.isDirected = isDirected;
		this.cmpByAttribute = cmpByAttribute;
		setWarshallFields();
	}

	public void initialize() {

	}

	// Shortest Path from -> to
	public String getPath(long v1, long v2) {
		String accu = "";
		return "";
	}

	private void setWarshallFields() {
		

	}

}
