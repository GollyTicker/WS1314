package GKA_A3;

import static GKA_A2.Constants.INF;
import static GKA_A2.Constants.NULL_DOUBLE;
import static GKA_A2.Constants.throwIfOutOfBound;

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

		init(graph, srcId, destId);
		
		Long vi = getMarkedUninspected();
		while(vi != -1L){
			// schritt 2
			List<Set<Long>> partition = makePartition(vi);
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
				//schritt 3
				List<Long> augmenting_path = getPathList(srcId, destId);
				// restCap
				for(Long eID : augmenting_path){
					
				}
				
			}
			vi = getMarkedUninspected();
		}
		
		// Schritt 4
		
		return graph;
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

	private int c(Long eID) {
		return graph.getValE(eID, "cap");
	}

	private int f(Long eID) {
		return flow.get(eID);
	}

	private List<Set<Long>> makePartition(Long vi) {
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

	private void init(IAIGraph graph, long srcId, long destId) {
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
