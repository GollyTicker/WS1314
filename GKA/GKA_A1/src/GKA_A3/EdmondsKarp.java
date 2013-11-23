package GKA_A3;

import GKA_A1.IAIGraph;

public class EdmondsKarp extends FlowAlgorithms {
	
	// Der EdmondsKarp Algorithmus ist laut wikipedia(english)
	// fast gleich wie dem Ford-Fulkerson. Der Einzige Unterschied
	// ist, dass Ford-Fulkerson eine Praeferenz bei der Auswahl der
	// zu inspizierenden Knoten hat und der EdmondsKArp Algorithmus
	// eine BreitenSuche verwenden um den shortest augmenthing path zu finden.
	

	// Kommantare stehe daher nur im Breath First Algorithmus
	// da der Rest identisch zum Ford-Fulkerson ist.
	private int access = 0;
	
	public EdmondsKarp(IAIGraph graph, long srcId, long destId, String capAttr,
			String flowAttr) {
		this.graph = graph;
		this.srcId = srcId;
		this.destId = destId;
		this.capAttr = capAttr;
		this.flowAttr = flowAttr;
		algo();
	}

	private void algo() {

		// Step 1 -
	}

}
