package GKA_A2;

import java.util.Set;

import GKA_A1.IAIGraph;
import GKA_A2.Matrix.Matrix;
import GKA_A2.Matrix.MatrixArray;

public class Floyd_Warshall {

	private IAIGraph graph;
	private boolean isDirected;
	private String cmpByAttribute;
	Matrix trans;
	Matrix dist;

	private double inf = Double.POSITIVE_INFINITY;

	public Floyd_Warshall(IAIGraph graph, String cmpByAttribute) {
		this.graph = graph;
		this.isDirected = graph.isDirected();
		this.cmpByAttribute = cmpByAttribute;
	}

	public void initialize() {
		int size = graph.getVertexes().size();

		dist = new MatrixArray(size, size);

		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {

				if (i == j) {
					dist.insert(i, j, 0);
				} else {
					dist.insert(i, j, inf);
				}
			}
		}

		Set<Long> edges = graph.getEdges();
		for (Long eId : edges) {
			int srcID = (int) graph.getSource(eId);
			int destID = (int) graph.getTarget(eId);

			// minus 1 rechnen, weil die IDs nullbasiert sind,
			// und die Matrix 1 basiert ist

			dist.insert(srcID + 1, destID + 1,
					graph.getValE(eId, cmpByAttribute));

		}

		trans = new MatrixArray(size, size);

		// haupt algorithmus

		for (int j = 1; j <= size; j++) {
			for (int i = 1; i <= size; i++) {
				if (i != j) {
					for (int k = 1; k <= size; k++) {
						if (j != k) {
							
							// falls es cycles gibt:
							if(dist.get(i, i) < 0.0){
								System.out.println("Cycle detected!");
								return;
							}
							
							double tmp = dist.get(i, j) + dist.get(j, k);
							if( dist.get(i, k) > tmp){
								dist.insert(i, k, tmp);
								trans.insert(i, k, j);// j ist der MatrixIndex des Vorgaengerknotens
							}
							
						}
					}
				}
			}
		}

		System.out.println(dist);
		System.out.println(trans);
		
	}
	
	public String getPath(long src, long dest) {
		return getPath_((int)(src + 1), (int)(dest + 1));
	}
	// Shortest Path from -> to
	private String getPath_(int src, int dest) {
		
		int vorgaengerM_id = (int)trans.get(src, dest);
		
		
		if(vorgaengerM_id == 0.0){
			return "v"+(src - 1) + " -> v" + (dest-1);
		}
		
		return getPath_(src, vorgaengerM_id) + " -> v" + (dest - 1); 
	}

}
