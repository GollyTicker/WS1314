package GKA_A2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import GKA_A1.IAIGraph;

public class BellmanFord {

	private static final double INF = Double.POSITIVE_INFINITY;

	private IAIGraph graph;
	private String cmpByAttribute;
	private long srcVId;

	private Map<Long, Double> distance;
	private Map<Long, Long> predecessor;

	public BellmanFord(IAIGraph graph, String cmpByAttribute, long srcVId) {
		this.graph = graph;
		this.cmpByAttribute = cmpByAttribute;
		this.srcVId = srcVId;
	}

	public void start() {
		Set<Long> vertices = graph.getVertexes();
		Set<Long> edges = graph.getEdges();

		// Step 1
		distance = new HashMap<>();
		predecessor = new HashMap<>();
		for (long vId : vertices) {
			if (vId == this.srcVId)
				distance.put(vId, 0.0);
			else
				distance.put(vId, INF);
			predecessor.put(vId, null);
		}

		// Step 2
		for (int i = 0; i < vertices.size(); i++) {
			for (long eId : edges) {
				long source = graph.getSource(eId);
				long target = graph.getTarget(eId);
				double weight = graph.getValE(eId, cmpByAttribute);
				double temp = distance.get(source) + weight;
				if (temp < distance.get(target)) {
					distance.put(target, temp);
					predecessor.put(target, source);
				}
			}
		}

		// Step 3:
		for (long eId : edges) {
			long source = graph.getSource(eId);
			long target = graph.getTarget(eId);
			double weight = graph.getValE(eId, cmpByAttribute);
			double temp = distance.get(source) + weight;
			if (temp < distance.get(target)) {
				System.out.println("Negative weighted Cycle Detected!");
				break;
			}
		}
	}
	
	
	
}
