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

	// TODO: iterate over a List of eID/vIDs with list indices instead of
	// the IDs themselves. The IDs themselves should not be used as indices!
	
	
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
		throwIfOutOfBound(this.graph, this.srcVId);
	}

	// BELLMAN FORD ALGORITHM
	public void start() {
		Set<Long> vertices = graph.getVertexes();
		Set<Long> edges = graph.getEdges();

		// Step 1
		// initialize the two hashmaps for storing the distance
		// and predecessor
		distance = new HashMap<>();
		predecessor = new HashMap<>();
		for (long vId : vertices) {
			if (vId == this.srcVId)
				distance.put(vId, 0.0);
			else
				distance.put(vId, INF);
			// we're using NULL_LONG(=-1) instead of the java null,
			// because it allows us to interpret the -1 as an invalid index.
			predecessor.put(vId, NULL_LONG);
		}

		// Step 2
		// we iterate over all the edges and ask whether
		// the path over a given vertice is shorter
		// than the direct path. If so, then
		// we save the vertice as predecessor and update the distance.
		for (int i = 0; i < vertices.size(); i++) {
			for (long eId : edges) {
				accessCount += 5;
				long source = graph.getSource(eId);
				long target = graph.getTarget(eId);
				double weight = graph.getValE(eId, cmpByAttribute);
				double weight_in_edge = distance.get(source) + weight;
				if (weight_in_edge < distance.get(target)) {
					distance.put(target, weight_in_edge);
					predecessor.put(target, source);
				}
			}
		}

		// Step 3:
		// We check whether we can still find shorter paths.
		// This is an indicator for a negative cycle.
		for (long eId : edges) {
			accessCount += 5;
			long source = graph.getSource(eId);
			long target = graph.getTarget(eId);
			
			// The following statements are equal to the shorter path detection in step 2.
			// But since all the shortest paths have been found in step 2,
			// further shorter paths only can be a cause of negative cycles.
			double weight = graph.getValE(eId, cmpByAttribute);
			double weight_in_edge = distance.get(source) + weight;
			if (weight_in_edge < distance.get(target)) {
				throwIfCycleDetected();
			}
		}

	}

	// MUTATORS
	public void setSrc(long srcVId) {
		this.srcVId = srcVId;
		throwIfOutOfBound(this.graph, this.srcVId);
	}

	public void setGraph(IAIGraph g) {
		this.graph = g;
		throwIfOutOfBound(this.graph, this.srcVId);
	}

	public void setCmpAttr(String s) {
		this.cmpByAttribute = s;
	}

	// SELECTORS
	public Map<Long, Double> getDist() {
		return this.distance;
	}

	public Map<Long, Long> getPred() {
		return this.predecessor;
	}

	public long getSrc() {
		return this.srcVId;
	}

	public String getPath(long dest) {
		throwIfOutOfBound(this.graph, dest);
		return getPathAcc(dest);
	}

	public List<Long> getPathList(long dest) {
		return getPathListAcc(dest, new ArrayList<Long>());
	}

	private String getPathAcc(long dest) {
		if (this.distance.get(dest) == INF)
			return NO_PATH;
		long predId = predecessor.get(dest);
		if (predId == NULL_LONG) {
			return "v" + srcVId;
		}
		return getPathAcc(predId) + " -> v" + dest;
	}

	private List<Long> getPathListAcc(long dest, List<Long> accu) {
		if (this.distance.get(dest) == INF)
			return new ArrayList<Long>();
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

}
