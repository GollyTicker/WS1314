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
	private static final String NULL_DIRECTION = "X";

	private IAIGraph graph;
	private long srcId, destId;
	private String capAttr, flowAttr;

	private Map<Long, Tuple4> marked = new HashMap<>();
	
	
	public FordFulkerson(IAIGraph graph, long srcId, long destId,
			String capAttr, String flowAttr) {
		this.graph = graph;
		this.srcId = srcId;
		this.destId = destId;
		this.capAttr = capAttr;
		this.flowAttr = flowAttr;
		algo();
	}
	
	private void algo() {

		// Step 1 - initialize
		init();

		// Step 2
		// the recurring GOTO Step 2 was refactored into a for-loop
		
		// save the current to be inspected Vertice into as vi. end loop, if
		// there are no more.
		for (Long vi = getMarkedUninspected(); vi != -1L; vi = getMarkedUninspected()) {

			// filter all edges by O(vi) and I(vi).
			List<Set<Long>> partition = makeInOutPartitionOf(vi);
			Set<Long> incoming = partition.get(0);
			Set<Long> outgoing = partition.get(1);

			// Forward-edges
			for (Long eID : outgoing) {
				long vj = graph.getTarget(eID);

				// make the forward-mark of for every edge that goes from vi to
				// an yet unmarked vj
				if (!marked.containsKey(vj) && f(eID) < c(eID)) {
					step2Forward(eID, vj, vi);
				}
			}

			// Backward-edges
			for (Long eID : incoming) {
				long vj = graph.getSource(eID);

				// make the backward-mark of for every edge that goes from vi to
				// an yet unmarked vj
				if (!marked.containsKey(vj) && f(eID) > 0) {
					step2Backward(eID, vj, vi);
				}
			}
			
			// mark vi as inspected
			marked.get(vi).inspect();
			
			// if the senke/destination was reached(marek) then augment the flow
			if (marked.containsKey(destId)) {
				// step three. calculate the augmenting path and update the flow
				step3();
			}
		}

		// Step 4
		// we're finished now!

	}
	

	// update the information on this possible augmenting edge
	private void step2Backward(Long eID, long vj, long vi) {
		Integer restCap_vi = marked.get(vi).getRestCap();
		int restCap = Math.min(f(eID), restCap_vi);
		marked.put(vj, new Tuple4("-", vi, restCap, false));
	}

	// update the information on this possible augmenting edge
	private void step2Forward(Long eID, long vj, long vi) {
		Integer restCap_vi = marked.get(vi).getRestCap();
		int restCap = Math.min(c(eID) - f(eID), restCap_vi);
		marked.put(vj, new Tuple4("+", vi, restCap, false));
	}

	private void step3() {
		List<Long> augPathVertices = getAugmentingPathV();
		List<Long> augPathEdges = getPathListAsEdges(augPathVertices);
		int flowUpdateRestCap = minimalRestCap(augPathVertices);

		// using indices instead of for-each because
		// it'd be hard to get the vIDs and corresponding eIDs
		// in a proper manner
		for (int i = 0; i < augPathEdges.size(); i++) {
			long eid = augPathEdges.get(i);
			// destVid points to the vertice of
			// eid which is closer to the destination(destID)
			// destVid does NOT refer to the Target Vertice of eid
			long destVid = augPathVertices.get(i + 1);
			String direction = marked.get(destVid).getDirection();
			
			// update the flow on each edge of the augmenting path
			update_flow(eid, direction, flowUpdateRestCap);
		}

		resetMarked();

	}

	public List<Long> getPathListAsEdges(List<Long> pathV) {
		List<Long> pathE = new ArrayList<>(pathV.size());
		for (Long eid : graph.getEdges()) {
			for (int i = 0; i < pathV.size() - 1; i++) {
				if (graph.getSource(eid) == pathV.get(i)
						&& graph.getTarget(eid) == pathV.get(i + 1)) {
					pathE.add(eid);
					break;
				}
			}
		}
		return pathE;
	}
	
	// increase/decrease flow on the given edge by restCap
	private void update_flow(Long eID, String direction, int restCap) {
		int currentFlow = f(eID);
		if (direction == "+") {
			f_set(eID, currentFlow + restCap);
		} else if (direction == "-") {
			f_set(eID, currentFlow - restCap);
		} else {
			System.err.println("ALERT! NULL_DIRECTION in AugPath");
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
	public List<Long> getAugmentingPathV() {
		return getPatListAcc(srcId, destId, new ArrayList<Long>());
	}

	private List<Long> getPatListAcc(long src, long dest, List<Long> accu) {
		long predId = marked.get(dest).getPredID();
		if (predId == NO_PRED) {
			accu.add(0, src);
			return accu;
		}
		accu.add(0, dest);
		return getPatListAcc(src, predId, accu);
	}

	private int c(Long eID) { // returns the capacity of an edge
		return graph.getValE(eID, capAttr);
	}

	private int f(Long eID) { // returns the flow intensity of an edge
		return graph.getValE(eID, flowAttr);
	}

	private void f_set(Long eID, int flowValue) { // sets the current flow on
													// the edge
		graph.setValE(eID, flowAttr, flowValue);
	}

	private List<Set<Long>> makeInOutPartitionOf(Long vi) {
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
		// initialize the zero flow
		// dont do anything, if a flow is already given
		initFirstFlow();
		initQMark();
	}

	private void initQMark() {
		// infinity is set to over the maximum capacity,
		// because Integer doesn't have any built-in INFINITY
		int infinity = getMaxCapPlus1();
		marked.put(srcId, new Tuple4(NULL_DIRECTION, NO_PRED, infinity, false));
	}

	private void resetMarked() {
		marked = new HashMap<>();
		initQMark();
	}

	private int getMaxCapPlus1() {
		int maxCap = graph.getValE(0, capAttr);
		for (Long eid : graph.getEdges()) {
			maxCap = Math.max(maxCap, graph.getValE(eid, capAttr));
		}
		return maxCap + 1;
	}

	// initialize the zero flow
	// don't do anything, if a flow is already given
	private void initFirstFlow() {
		Set<Long> edges = graph.getEdges();
		// in the AIGraph implementation. the integer value
		// for unassigned attributes is -1
		int null_representation = -1;
		for (Long eID : edges) {
			// only if the edge has no flow, then set it to zero
			// else let it be it's given value
			if (f(eID) == null_representation) {
				f_set(eID, 0);
			}
		}
	}

}
