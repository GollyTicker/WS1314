package GKA_A3;

import java.util.List;
import java.util.Set;

import GKA_A1.IAIGraph;

public class EdmondsKarp extends FlowAlgorithms {

	// Der EdmondsKarp Algorithmus ist laut wikipedia(english)
	// fast gleich wie dem Ford-Fulkerson. Der Einzige Unterschied
	// ist, dass Ford-Fulkerson eine Praeferenz bei der Auswahl der
	// zu inspizierenden Knoten hat und der EdmondsKArp Algorithmus
	// eine BreitenSuche verwenden um den shortest augmenthing path zu finden.

	// Kommantare stehe daher nur im Breath First Algorithmus
	// da der Rest identisch zum Ford-Fulkerson ist.

	public EdmondsKarp(IAIGraph graph, long srcId, long destId, String capAttr,
			String flowAttr) {
		super(graph, srcId, destId, capAttr, flowAttr);
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
		for (Long vi = popFromStack(); vi != -1L; vi = popFromStack()) {

			// filter all edges by O(vi) and I(vi).
			List<Set<Long>> partition = makeInOutPartitionOf(vi);
			Set<Long> incoming = partition.get(0);
			Set<Long> outgoing = partition.get(1);

			// Forward-edges
			for (Long eID : outgoing) {
				increaseAccess(); // Zugriff auf ein Edge
				long vj = graph.getTarget(eID);

				// make the forward-mark of for every edge that goes from vi to
				// an yet unmarked 
				if (!verticeIsMarked(vj) && f(eID) < c(eID)) {
					step2Forward(eID, vj, vi);
				}
			}

			// Backward-edges
			for (Long eID : incoming) {
				increaseAccess(); // Zugriff auf ein Edge
				long vj = graph.getSource(eID);

				// make the backward-mark of for every edge that goes from vi to
				// an yet unmarked vj
				if (!marked.containsKey(vj) && f(eID) > 0) {
					step2Backward(eID, vj, vi);
				}
			}

			// mark vi as inspected
			getMarkedTuple(vi).inspect();

			// Step 3
			// if the senke/destination was reached(marek) then augment the flow
			if (verticeIsMarked(destId)) {
				// step three. calculate the augmenting path and update the flow
				updateByAugmentingPath();
			}
		}

		// Step 4
		// we're finished now!

	}

	private Long popFromStack() {
		for (Long vID : marked.keySet()) {
			if (!getMarkedTuple(vID).wasInspected())
				return vID;
		}
		return -1L;
	}

}
