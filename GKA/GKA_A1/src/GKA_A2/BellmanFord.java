package GKA_A2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import GKA_A1.IAIGraph;
import GraphUtils.ITimeSpace;

public class BellmanFord implements ITimeSpace {

	private static final double INF = Double.POSITIVE_INFINITY;

	private IAIGraph graph;
	private String cmpByAttribute;
	private long srcVId;
	private static Long nullLong = -1L;
	private int accessCount = 0;

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
			predecessor.put(vId, nullLong);
		}

		// Step 2
		for (int i = 0; i < vertices.size(); i++) {
			for (long eId : edges) {
				accessCount += 5;
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
			accessCount += 5;
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

	// Shortest Path from -> to
	public String getPath(long dest) {
		long predId = predecessor.get(dest);
		if (predId == nullLong) {
			return "v"+srcVId;
		}
		return getPath(predId) + " -> v" + dest;
	}

	// Shortest Path from -> to
	public List<Long> getPathList(long dest) {
		return getPathListAcc(dest, new ArrayList<Long>());
	}

	// Shortest Path from -> to
	private List<Long> getPathListAcc(long dest, List<Long> accu) {
		long predId = predecessor.get(dest);
		if (predId == nullLong) {
			accu.add(0, srcVId);
			return accu;
		}
		accu.add(0, dest);
		return getPathListAcc(predId, accu);
	}

	public Map<Long, Double> getDist() {
		return this.distance;
	}

	public Map<Long, Long> getPred() {
		return this.predecessor;
	}

	@Override
	public int accessCount() {
		return this.accessCount;
	}

	@Override
	public void setAccessCount(int ac) {
		this.accessCount = ac;

	}

	@Override
	public void resetAccessCount() {
		this.accessCount = 0;

	}

	@Override
	public void printCount() {
		System.out.println("accessCount: " + accessCount);
	}

}
