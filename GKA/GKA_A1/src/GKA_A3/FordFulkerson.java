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

public class FordFulkerson implements ITimeSpace {

	private static final long NO_PRED = -1;
	private static final String NULL_DIRECTION = "X";
	private static final boolean DEBUGMODE = true;
	private int INF;
	private IAIGraph graph;
	private long srcId, destId;
	private String capAttr, flowAttr;

	// accessCount
	// Als AccessCounts zaehlen die Zeilen Code,
	// indenen auf ein Attribut des Graphen zugegriffen/gesetzt wird
	// oder ein Edge/Vertice des Graphen angefragt wird.
	// Der Umgang mit dem "marked" zaehlt auch mit.
	private int access = 0;

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
		// save the current to be inspected Vertice into as vi. 
		// end loop, if there are no more.
		for (Long vi = getMarkedUninspected(); vi != -1L; vi = getMarkedUninspected()) {

			// filter all edges by O(vi) and I(vi).
			List<Set<Long>> partition = makeInOutPartitionOf(vi);
			Set<Long> incoming = partition.get(0);
			Set<Long> outgoing = partition.get(1);

			// Forward-edges
			for (Long eID : outgoing) {
				increaseAccess(); // Zugriff auf ein Edge
				long vj = graph.getTarget(eID);

				// make the forward-mark of for every edge that goes from vi to
				// an yet unmarked vj
				increaseAccess(); // Zugriff auf die markierten Vertices
				if (!marked.containsKey(vj) && f(eID) < c(eID)) {
					step2Forward(eID, vj, vi);
				}
			}

			// Backward-edges
			for (Long eID : incoming) {
				increaseAccess(); // Zugriff auf ein Edge
				long vj = graph.getSource(eID);

				// make the backward-mark of for every edge that goes from vi to
				// an yet unmarked vj
				increaseAccess(); // Zugriff auf die markierten Vertices
				if (!marked.containsKey(vj) && f(eID) > 0) {
					step2Backward(eID, vj, vi);
				}
			}

			// mark vi as inspected
			increaseAccess(); // Zugriff auf den zu markierenden Vertice
			marked.get(vi).inspect();

			// if the senke/destination was reached(marek) then augment the flow
			increaseAccess(); // Zugriff auf die markierten Vertices
			if (marked.containsKey(destId)) {
				// step three. calculate the augmenting path and update the flow
				step3();
			}
		}

		// Step 4
		// we're finished now!

	}

	private void debug(String string) {
		if (DEBUGMODE)
			System.out.println(string);
	}

	// update the information on this possible augmenting edge
	private void step2Backward(Long eID, long vj, long vi) {
		increaseAccess(); // Zugriff auf die markierten Vertices
		Integer restCap_vi = marked.get(vi).getRestCap();
		int restCap = Math.min(f(eID), restCap_vi);
		marked.put(vj, new Tuple4("-", vi, restCap, false));
	}

	// update the information on this possible augmenting edge
	private void step2Forward(Long eID, long vj, long vi) {
		increaseAccess(); // Zugriff auf die markierten Vertices
		Integer restCap_vi = marked.get(vi).getRestCap();
		int restCap = Math.min(c(eID) - f(eID), restCap_vi);
		marked.put(vj, new Tuple4("+", vi, restCap, false));
	}

	private void step3() {
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
			increaseAccess(); // Zugriff auf die markierten Vertices
			String direction = marked.get(destVid).getDirection();

			// update the flow on each edge of the augmenting path
			update_flow(eid, direction, flowUpdateRestCap);
		}

		resetMarked();

	}

	public List<Long> getPathListAsEdges(List<Long> pathV) {
		List<Long> pathE = new ArrayList<>(pathV.size());
		for (int i = 0; i < pathV.size() - 1; i++) {

			// we skip the first vertice,
			// because it is the source vertice
			for (Long eid : graph.getEdges()) {

				long currVertice = pathV.get(i);
				long nextVertice = pathV.get(i + 1);

				// case forward
				increaseAccess(); // Zugriff auf ein marked Vertice
				
				if (marked.get(nextVertice).getDirection() == "+") {

					// if the next vertice goes on a normal way, then
					// pick the edge which goes from the current vertice
					// to the next vertice
					increaseAccess(2); // Zugriff auf zwei Edges
					if (graph.getSource(eid) == currVertice
							&& graph.getTarget(eid) == nextVertice) {
						
						debug("forward: " + currVertice + " "
								+ marked.get(nextVertice).getDirection() + " "
								+ nextVertice + " Edge: " + eid);
						
						pathE.add(eid);
						break;
					}
				} // case backward
				else if (marked.get(nextVertice).getDirection() == "-") {

					// if the vertice points over a backward edge to the next
					// vertice
					// pick the edge which goes form the nextVertice to the
					// currentVertice
					increaseAccess(2); // Zugriff auf zwei Edges
					if (graph.getSource(eid) == nextVertice
							&& graph.getTarget(eid) == currVertice) {
						
						debug("backward: " + currVertice + " "
								+ marked.get(nextVertice).getDirection() + " "
								+ nextVertice + " Edge: " + eid);
						
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
		increaseAccess(); // Zugriff auf die markierten Vertices
		int min = marked.get((path.get(0))).getRestCap();
		for (Long eID : path) {
			increaseAccess(); // Zugriff auf die markierten Vertices
			min = Math.min(marked.get(eID).getRestCap(), min);
		}
		return min;
	}

	// Shortest Path from -> to
	public List<Long> getAugmentingPathV() {
		return getPatListAcc(srcId, destId, new ArrayList<Long>());
	}

	private List<Long> getPatListAcc(long src, long dest, List<Long> accu) {
		increaseAccess(); // Zugriff auf die markierten Vertices
		long predId = marked.get(dest).getPredID();
		if (predId == NO_PRED) {
			accu.add(0, src);
			return accu;
		}
		accu.add(0, dest);
		return getPatListAcc(src, predId, accu);
	}

	private int c(Long eID) { // returns the capacity of an edge
		increaseAccess();
		return graph.getValE(eID, capAttr);
	}

	private int f(Long eID) { // returns the flow intensity of an edge
		increaseAccess();
		return graph.getValE(eID, flowAttr);
	}

	private void f_set(Long eID, int flowValue) { // sets the current flow on
													// the edge
		increaseAccess(); // Zugriff auf den Graphen
		graph.setValE(eID, flowAttr, flowValue);
	}

	private List<Set<Long>> makeInOutPartitionOf(Long vi) {
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

	private Long getMarkedUninspected() {
		for (Long vID : marked.keySet()) {
			increaseAccess(); // Zugriff auf marked
			if (!marked.get(vID).wasInspected())
				return vID;
		}
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
		INF = calcMaxCapPlus1();
		increaseAccess(); // Setzten in "marked"
		marked.put(srcId, new Tuple4(NULL_DIRECTION, NO_PRED, INF, false));
	}

	private void resetMarked() {
		marked = new HashMap<>();
		initQMark();
	}

	private int calcMaxCapPlus1() {
		increaseAccess(); // Zugriff auf Attribut
		int maxCap = graph.getValE(0, capAttr);
		for (Long eid : graph.getEdges()) {
			increaseAccess(); // Zugriff auf Attribut
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

	private void increaseAccess() {
		setAccessCount(accessCount() + 1);
	}

	private void increaseAccess(int amount) {
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

}
