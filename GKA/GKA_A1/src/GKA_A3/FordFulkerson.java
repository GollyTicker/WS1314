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

	private static Map<Long, Integer> flow = new HashMap<>();
	private static Map<Long, Tuple4> marked = new HashMap<>();

	public FordFulkerson(IAIGraph graph, long srcId, long destId) {
		this.graph = graph;
		this.srcId = srcId;
		this.destId = destId;
		start();
	}

	private void start() {
		algo();
	}

	private IAIGraph algo() {
		
		// Step 1 - initialize
		init();
		
		
		// the recurring GOTO Step 2 was refactored into a for-loop
		for(Long vi = getMarkedUninspected();vi != -1L;vi = getMarkedUninspected()){

			// Step 2
			List<Set<Long>> partition = makeInOutPartition(vi);
			Set<Long> incoming = partition.get(0);
			Set<Long> outgoing = partition.get(1);
			
			// Vorwaertskanten
			for(Long eID : outgoing){
				long vj = graph.getTarget(eID);
				if(!marked.containsKey(vj)
						&& f(eID) < c(eID)){
					
					Integer restCap_vi = marked.get(vi).getRestCap();
					int restCap = Math.min(c(eID) - f(eID), restCap_vi);
					marked.put(vj, new Tuple4("+", vi, restCap, false));
				}
			}
			
			// Rueckwaertskanten
			for(Long eID : incoming){
				long vj = graph.getSource(eID);
				if(!marked.containsKey(vj)
						&& f(eID) > 0){
					Integer restCap_vi = marked.get(vi).getRestCap();
					int restCap = Math.min(f(eID), restCap_vi);
					marked.put(vj, new Tuple4("-", vi, restCap, false));
				}
			}
			
			marked.get(vi).inspect();
			
			if (marked.containsKey(destId)){
				// step three. calculate the augmenting path and update the flow
				step3();
			}
		}
		
		// Schritt 4
		
		return graph;
	}
	
	private void step3() {
		List<Long> augmenting_path = getPathList(srcId, destId);
		int restCap = minimalRestCap(augmenting_path);
		for(Long eID : augmenting_path){
			Tuple4 tuple = marked.get(eID);
			update_flow(eID, tuple.getDirection(), restCap);
		}
	}

	private void update_flow(Long eID, String direction, int restCap) {
		int currentFlow = flow.get(eID);
		if(direction=="+"){
			flow.put(eID, currentFlow + restCap);
		}
		else{
			flow.put(eID, currentFlow - restCap);
		}
	}

	private int minimalRestCap(List<Long> path) {
		int min = marked.get((path.get(0))).getRestCap();
		for(Long eID : path){
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
			//accu.add(0, dest);
			accu.add(0, src);
			return accu;
		}
		accu.add(0, dest);
		return getPatListAcc(src, predId, accu);
	}

	private int c(Long eID) {	// returns the capacity of an edge
		return graph.getValE(eID, "cap");
	}

	private int f(Long eID) {	// returns the current flow intensity of an edge
		return flow.get(eID);
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
		for (Long eid : edges) {
			flow.put(eid, 0);
		}
	}
	
}
