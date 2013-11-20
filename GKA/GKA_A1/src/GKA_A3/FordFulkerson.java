package GKA_A3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import GKA_A1.IAIGraph;

public class FordFulkerson {

	private static final long NO_PRED = -1;
	private static final int UNDEF = -1;

	private IAIGraph graph;
	private long srcId, destId;
	private String capAttr, flowAttr;

	// moves flow information from Map<Long, Integer> to Attributes in the graph. it'll be written into the graph
	// the user has easier access with this solution
	//private static Map<Long, Integer> flow = new HashMap<>();
	private static Map<Long, Tuple4> marked = new HashMap<>();

	public FordFulkerson(IAIGraph graph, long srcId, long destId, String capAttr, String flowAttr) {
		this.graph = graph;
		this.srcId = srcId;
		this.destId = destId;
		this.capAttr = capAttr;
		this.flowAttr = flowAttr;
		start();
	}

	private void start() {
		algo();
	}

	private void algo() {

		// Step 1 - initialize
		init();

		// Step 2

		// the recurring GOTO Step 2 was refactored into a for-loop
		for (Long vi = getMarkedUninspected(); vi != -1L; vi = getMarkedUninspected()) {

			List<Set<Long>> partition = makeInOutPartition(vi);
			Set<Long> incoming = partition.get(0);
			Set<Long> outgoing = partition.get(1);

			// Forward-edges
			for (Long eID : outgoing) {
				long vj = graph.getTarget(eID);
				if (!marked.containsKey(vj) && f(eID) < c(eID)) {
					// update the information on this possible augmenting edge
					step2Forward(eID, vj, vi);
				}
			}

			// Backward-edges
			for (Long eID : incoming) {
				long vj = graph.getSource(eID);
				if (!marked.containsKey(vj) && f(eID) > 0) {
					// update the information on this possible augmenting edge
					step2Backward(eID, vj, vi);
				}
			}

			marked.get(vi).inspect();

			if (marked.containsKey(destId)) {
				// step three. calculate the augmenting path and update the flow
				step3();
			}
		}

		// Step 4
		// we're finished now!

	}

	private void step2Backward(Long eID, long vj, Long vi) {
		Integer restCap_vi = marked.get(vi).getRestCap();
		int restCap = Math.min(f(eID), restCap_vi);
		marked.put(vj, new Tuple4("-", vi, restCap, false));
	}

	// update the information on this possible augmenting edge
	private void step2Forward(Long eID, long vj, Long vi) {
		Integer restCap_vi = marked.get(vi).getRestCap();
		int restCap = Math.min(c(eID) - f(eID), restCap_vi);
		marked.put(vj, new Tuple4("+", vi, restCap, false));
	}

	private void step3() {
		List<Long> augmenting_path = getPathList(srcId, destId);
		int restCap = minimalRestCap(augmenting_path);
		for (Long eID : augmenting_path) {
			Tuple4 tuple = marked.get(eID);
			update_flow(eID, tuple.getDirection(), restCap);
		}
	}

	private void update_flow(Long eID, String direction, int restCap) {
		int currentFlow = f(eID);
		if (direction == "+") {
			f_set(eID, currentFlow + restCap);
		} else {
			f_set(eID, currentFlow - restCap);
		}
	}

	private int minimalRestCap(List<Long> path) {
		int min = marked.get((path.get(0))).getRestCap();
		for (Long eID : path) {
			min = Math.min(marked.get(eID).getRestCap(), min);
		}
		return min;
	}

	// Shortest Path from -> to
	public List<Long> getPathList(long src, long dest) {
		return getPatListAcc(src, dest, new ArrayList<Long>());
	}

	private List<Long> getPatListAcc(long src, long dest, List<Long> accu) {
		long predId = marked.get(dest).getPredID();
		if (predId == NO_PRED) {
			// accu.add(0, dest);
			accu.add(0, src);
			return accu;
		}
		accu.add(0, dest);
		return getPatListAcc(src, predId, accu);
	}

	private int c(Long eID) { // returns the capacity of an edge
		return graph.getValE(eID, capAttr);
	}

	private int f(Long eID) { // returns the current flow intensity of an edge
		return graph.getValE(eID, flowAttr);
	}
	
	private void f_set(Long eID, int flowValue) {	// sets the current flow on the edge
		graph.setValE(eID, flowAttr, flowValue);
	}

	private List<Set<Long>> makeInOutPartition(Long vi) {
		Set<Long> inci = graph.getIncident(vi);
		Set<Long> incoming = new HashSet<>();
		Set<Long> outgoing = new HashSet<>();
		for (Long eID : inci)
			if (graph.getSource(eID) == vi)
				outgoing.add(eID);
			else
				incoming.add(eID);
		return new ArrayList<Set<Long>>(Arrays.asList(incoming, outgoing));
	}

	private Long getMarkedUninspected() {
		for (Long vID : marked.keySet())
			if (!marked.get(vID).wasInspected())
				return vID;
		return -1L;
	}

	private void init() {
		initFirstFlow(graph);
		marked.put(srcId, new Tuple4("", NO_PRED, UNDEF, false));
	}

	private void initFirstFlow(IAIGraph graph) {
		Set<Long> edges = graph.getEdges();
		for (Long eID : edges) {
			f_set(eID, 0);
		}
	}

}
