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

public class FordFulkerson extends FlowAlgorithms {


	// accessCount
	// Als AccessCounts zaehlen die Zeilen Code,
	// indenen auf ein Attribut des Graphen zugegriffen/gesetzt wird
	// oder ein Edge/Vertice des Graphen angefragt wird.
	// Der Umgang mit dem "marked" zaehlt auch mit.


	public FordFulkerson(IAIGraph graph, long srcId, long destId,
			String capAttr, String flowAttr) {
		super(graph, srcId, destId,	capAttr, flowAttr);
		algo();
	}

	private void algo() {

		// Step 1 - 
		// initialize the zero flow (if none is given)
		// mark Quelle
		init();

		// Step 2
		// save the current to be inspected Vertice into vi. 
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
			
			// Step 3
			// if the senke/destination was reached(marek) then augment the flow
			increaseAccess(); // Zugriff auf die markierten Vertices
			if (marked.containsKey(destId)) {
				// step three. calculate the augmenting path and update the flow
				updateByAugmentingPath();
			}
		}

		// Step 4
		// we're finished now!

	}

	

}
