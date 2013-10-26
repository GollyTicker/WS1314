package GKA_A2;

import GKA_A1.IAIGraph;
import GKA_A2.Matrix.Matrix;
import GKA_A2.Matrix.MatrixArray;

public class Floyd_Warshall {

	private IAIGraph graph;
	private String cmpByAttribute;
	private Matrix trans;
	private Matrix dist;
	private int size;

	private double inf = Double.POSITIVE_INFINITY;

	public Floyd_Warshall(IAIGraph graph, String cmpByAttribute) {
		this.graph = graph;
		this.cmpByAttribute = cmpByAttribute;
		this.size = graph.getVertexes().size();
		initMatrices();
	}

	public void start() {

		for (int j = 1; j <= this.size; j++)
			for (int i = 1; i <= this.size; i++)
				if (i != j)
					for (int k = 1; k <= this.size; k++)
						if (j != k) {
							// falls es cycles gibt:
							if (dist.get(i, i) < 0.0) {
								System.out.println("Cycle detected!");
								return;
							}
							double tmp = dist.get(i, j) + dist.get(j, k);
							if (dist.get(i, k) > tmp) {
								dist.insert(i, k, tmp);
								// j ist der MatrixIndex
								// des Vorgaengerknotens
								trans.insert(i, k, j);
							}
						}

	}

	public Matrix getDist() {
		return this.dist;
	}

	public Matrix getTrans() {
		return this.trans;
	}

	public String getPath(long src, long dest) {
		return getPath_((int) (src + 1), (int) (dest + 1));
	}

	// Shortest Path from -> to
	private String getPath_(int src, int dest) {
		int predId = (int) trans.get(src, dest);
		if (predId == 0.0) {
			return "v" + (src - 1) + " -> v" + (dest - 1);
		}
		return getPath_(src, predId) + " -> v" + (dest - 1);
	}

	private void initMatrices() {
		this.trans = new MatrixArray(this.size, this.size);
		dist = new MatrixArray(this.size, this.size);

		for (int i = 1; i <= this.size; i++)
			for (int j = 1; j <= this.size; j++)
				if (i == j) {
					dist.insert(i, j, 0);
				} else {
					dist.insert(i, j, inf);
				}

		for (Long eId : graph.getEdges()) {
			int srcID = (int) graph.getSource(eId);
			int destID = (int) graph.getTarget(eId);

			// minus 1 rechnen, weil die IDs nullbasiert sind,
			// und die Matrix 1 basiert ist

			dist.insert(srcID + 1, destID + 1,
					graph.getValE(eId, cmpByAttribute));
		}
	}

}
