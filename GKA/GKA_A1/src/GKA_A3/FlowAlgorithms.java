package GKA_A3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import GKA_A1.IAIGraph;
import GraphUtils.ITimeSpace;

public abstract class FlowAlgorithms implements ITimeSpace,
		IFlowAlgorithmsStackQueue {

	protected static final long NO_PRED = -1;
	protected static final String NULL_DIRECTION = "X";
	protected static final boolean DEBUGMODE = true;
	protected int INF;
	protected IAIGraph graph;
	protected long srcId, destId;
	protected String capAttr, flowAttr;
	protected int access = 0;

	protected Map<Long, Tuple4> marked = new HashMap<>();

	protected FlowAlgorithms(IAIGraph graph, long srcId, long destId,
			String capAttr, String flowAttr) {
		this.graph = graph;
		this.srcId = srcId;
		this.destId = destId;
		this.capAttr = capAttr;
		this.flowAttr = flowAttr;
	}

	protected void updateByAugmentingPath() {
		debug("----------------------------------");
		debug("Before flow update: " + graph);
		debug("Marks: " + marked);

		List<Long> augPathVertices = getAugmentingPathV();
		List<Long> augPathEdges = getPathListAsEdges(augPathVertices);
		int flowUpdateRestCap = minimalRestCap(augPathVertices);
		debug("AugPathV: " + augPathVertices);
		debug("AugPathE: " + augPathEdges);
		debug("FlowAddition: " + flowUpdateRestCap);
		// using indices instead of for-each because
		// it'd be hard to get the vIDs and corresponding eIDs
		// in a proper manner
		for (int i = 0; i < augPathEdges.size(); i++) {
			long eid = augPathEdges.get(i);
			// destVid points to the vertice of
			// eid which is closer to the destination(destID)
			// destVid does NOT refer to the Target Vertice of eid
			long destVid = augPathVertices.get(i + 1);
			String direction = getMarkedTuple(destVid).getDirection();

			// update the flow on each edge of the augmenting path
			update_flow(eid, direction, flowUpdateRestCap);
		}

		resetMarks();

	}

	// update the information on this possible augmenting edge
	protected void saveForwardEdge(Long eID, long vj, long vi) {
		Integer restCap_vi = getMarkedTuple(vi).getRestCap();
		int restCap = Math.min(c(eID) - f(eID), restCap_vi);
		markVertice(vj, new Tuple4("+", vi, restCap, false));
	}

	// update the information on this possible augmenting edge
	protected void saveBackwardEdge(Long eID, long vj, long vi) {
		Integer restCap_vi = getMarkedTuple(vi).getRestCap();
		int restCap = Math.min(f(eID), restCap_vi);
		markVertice(vj, new Tuple4("-", vi, restCap, false));
	}

	public List<Long> getPathListAsEdges(List<Long> pathV) {
		List<Long> pathE = new ArrayList<>(pathV.size());
		for (int i = 0; i < pathV.size() - 1; i++) {
			for (Long eid : graph.getEdges()) {

				long currVertice = pathV.get(i);
				long nextVertice = pathV.get(i + 1);

				// case forward
				// in this if/elseif we ask the next vertice in the augmenting
				// path
				// how we can reach it. depending on the direction (+ or -)

				if (getMarkedTuple(nextVertice).getDirection() == "+") {

					// if the next vertice goes on a normal way, then
					// pick the edge which goes from the current vertice
					// to the next vertice
					increaseAccess(2); // Zugriff auf zwei Edges
					if (graph.getSource(eid) == currVertice
							&& graph.getTarget(eid) == nextVertice) {

						debug("forward: " + currVertice + " "
								+ getMarkedTuple(nextVertice).getDirection()
								+ " " + nextVertice + " Edge: " + eid);

						pathE.add(eid);
						break;
					}
				} // case backward
				else if (getMarkedTuple(nextVertice).getDirection() == "-") {

					// if the vertice points over a backward edge to the next
					// vertice
					// pick the edge which goes form the nextVertice to the
					// currentVertice
					increaseAccess(2); // Zugriff auf zwei Edges
					if (graph.getSource(eid) == nextVertice
							&& graph.getTarget(eid) == currVertice) {

						debug("backward: " + currVertice + " "
								+ getMarkedTuple(nextVertice).getDirection()
								+ " " + nextVertice + " Edge: " + eid);

						pathE.add(eid);
						break;
					}

				} else {
					System.err
							.println("Shouldn't have any undirectional veritces!"
									+ nextVertice);
				}

			}
		}
		return pathE;
	}

	// increase/decrease flow on the given edge by restCap
	protected void update_flow(Long eID, String direction, int restCap) {
		int currentFlow = f(eID);
		if (direction == "+") {
			f_set(eID, currentFlow + restCap);
		} else if (direction == "-") {
			f_set(eID, currentFlow - restCap);
		} else {
			System.err.println("ALERT! NULL_DIRECTION in AugPath");
		}
	}

	protected int minimalRestCap(List<Long> path) {
		int min = getMarkedTuple((path.get(0))).getRestCap();
		for (Long eID : path) {
			min = Math.min(getMarkedTuple(eID).getRestCap(), min);
		}
		return min;
	}

	// Shortest Path from -> to
	public List<Long> getAugmentingPathV() {
		return getPatListAcc(srcId, destId, new ArrayList<Long>());
	}

	private List<Long> getPatListAcc(long src, long dest, List<Long> accu) {
		long predId = getMarkedTuple(dest).getPredID();
		if (predId == NO_PRED) {
			accu.add(0, src);
			return accu;
		}
		accu.add(0, dest);
		return getPatListAcc(src, predId, accu);
	}

	protected void f_set(Long eID, int flowValue) { // sets the current flow on
													// the edge
		increaseAccess(); // Zugriff auf den Graphen
		graph.setValE(eID, flowAttr, flowValue);
	}

	protected List<Set<Long>> makeInOutPartitionOf(Long vi) {
		Set<Long> inci = graph.getIncident(vi);
		Set<Long> incoming = new HashSet<>();
		Set<Long> outgoing = new HashSet<>();
		for (Long eID : inci) {
			increaseAccess(); // Zugriff auf einem Edge
			if (graph.getSource(eID) == vi)
				outgoing.add(eID);
			else
				incoming.add(eID);
		}
		return new ArrayList<Set<Long>>(Arrays.asList(incoming, outgoing));
	}

	protected int c(Long eID) { // returns the capacity of an edge
		increaseAccess();
		return graph.getValE(eID, capAttr);
	}

	protected int f(Long eID) { // returns the flow intensity of an edge
		increaseAccess();
		return graph.getValE(eID, flowAttr);
	}

	protected int calcMaxCapPlus1() {
		increaseAccess(); // Zugriff auf Attribut
		int maxCap = graph.getValE(0, capAttr);
		for (Long eid : graph.getEdges()) {
			increaseAccess(); // Zugriff auf Attribut
			maxCap = Math.max(maxCap, graph.getValE(eid, capAttr));
		}
		return maxCap + 1;
	}

	// INITIALIZATION
	protected void init() {
		// initialize the zero flow
		// dont do anything, if a flow is already given
		initFirstFlow();
		initQMark();
	}

	protected void initQMark() {
		// infinity is set to over the maximum capacity,
		// because Integer doesn't have any built-in INFINITY
		INF = calcMaxCapPlus1();
		markVertice(srcId, new Tuple4(NULL_DIRECTION, NO_PRED, INF, false));
	}

	// initialize the zero flow
	// don't do anything, if a flow is already given
	protected void initFirstFlow() {
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

	// ACCESS COUNT
	protected void increaseAccess() {
		setAccessCount(accessCount() + 1);
	}

	protected void increaseAccess(int amount) {
		setAccessCount(accessCount() + amount);
	}

	@Override
	public int accessCount() {
		return access;
	}

	@Override
	public void setAccessCount(int ac) {
		access = ac;
	}

	@Override
	public void resetAccessCount() {
		setAccessCount(0);
	}

	@Override
	public void printCount() {
		System.out.println("AccessCount: " + access);
	}

	// EXCEPTION HANDLING + DEBUGGING
	protected void debug(String string) {
		if (DEBUGMODE)
			System.out.println(string);
	}

	@Override
	public boolean verticeIsMarked(long vID) {
		throw new UnsupportedOperationException(".");
	}

	@Override
	public void markVertice(long vID, Tuple4 info) {
		throw new UnsupportedOperationException(".");
	}

	@Override
	public Tuple4 getMarkedTuple(long vID) {
		throw new UnsupportedOperationException(".");
	}

	@Override
	public void inspectVertice(long vID) {
		throw new UnsupportedOperationException(".");
	}

	@Override
	public void resetMarks() {
		throw new UnsupportedOperationException(".");
	}

}
