package GKA_A2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import GKA_A1.IAIGraph;
import GraphUtils.ITimeSpace;
import static GKA_A2.Constants.*;

public class BellmanFord implements ITimeSpace {

	// INITIALIZATION
	private IAIGraph graph;
	private String cmpByAttribute;
	private long srcVId;

	private Map<Long, Double> distance;
	private Map<Long, Long> predecessor;

	private int accessCount = 0;

	// CREATION
	public BellmanFord(IAIGraph graph, String cmpByAttribute, long srcVId) {
		this.graph = graph;
		this.cmpByAttribute = cmpByAttribute;
		this.srcVId = srcVId;
		throwIfOutOfBound(this.srcVId);
	}

	// BELLMAN FORD ALGORITHM
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
			predecessor.put(vId, NULL_LONG);
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

	// SELECTORS
	public Map<Long, Double> getDist() {
		return this.distance;
	}

	public Map<Long, Long> getPred() {
		return this.predecessor;
	}

	public void setSrc(long srcVId) {
		this.srcVId = srcVId;
	}

	public long getSrc() {
		return this.srcVId;
	}

	public String getPath(long dest) {
		throwIfOutOfBound(dest);
		return getPathAcc(dest);
	}

	public List<Long> getPathList(long dest) {
		return getPathListAcc(dest, new ArrayList<Long>());
	}

	private String getPathAcc(long dest) {
		long predId = predecessor.get(dest);
		if (predId == NULL_LONG) {
			return "v" + srcVId;
		}
		return getPathAcc(predId) + " -> v" + dest;
	}

	private List<Long> getPathListAcc(long dest, List<Long> accu) {
		long predId = predecessor.get(dest);
		if (predId == NULL_LONG) {
			accu.add(0, srcVId);
			return accu;
		}
		accu.add(0, dest);
		return getPathListAcc(predId, accu);
	}

	// ACCESSCOUNT
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

	// EXCEPTION
	private void throwIfOutOfBound(long vertice) {
		if (!this.graph.getVertexes().contains(vertice))
			throw new IndexOutOfBoundsException(
					"srcVId has to be in the current Graph!");
	}

}
