package GKA_A4;

import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import GKA_A1.IAIGraph;

public class Hierholzer {

	private IAIGraph graph;
	private String cmp;

	private Set<Long> allEdges;

	public Hierholzer(IAIGraph graph, String cmp) {
		this.graph = graph;
		this.cmp = cmp;
	}

	public List<Long> hierholzeEs() {
		resetVariables();

		checkEdgesHaveEvenDegree();

		// Step 1
		Long v = -1L;
		// K ist eine Liste der Kanten
		List<Long> k = makeCycleBeginningAtUsingEdges(v, allEdges);

		// Step 2
		while (!isEulerianPath(k)) {

			// Step 3
			// vernachlaessige Kanten von K
			Set<Long> edgesWithoutK = new HashSet<Long>(k);
			edgesWithoutK.removeAll(allEdges);

			// Step 4
			Long newV = getEdgeInKWithPositiveDegree();
			List<Long> newK = makeCycleBeginningAtUsingEdges(newV,
					edgesWithoutK);

			// Step 5 and 6
			integrateLeftCycleIntoRightCycle(newK, k);
			// K is being updated here
		}
		return k;
	}

	// Voraussetzung: Sei G=(V,E) ein zusammenhaengender Graph, der nur Knoten
	// mit geradem Grad aufweist.
	// 1. Waehle einen beliebigen Knoten v_0 des Graphen und konstruiere von v_0
	// ausgehend einen
	// Unterkreis K in G, der keine Kante in G zweimal durchlaeuft.
	// 2. Wenn K ein Eulerkreis ist, breche ab. Andernfalls:
	// 3. Vernachlaessige nun alle Kanten des Unterkreises K.
	// 4. Am ersten Eckpunkt von K, dessen Grad groesser 0 ist, laesst man nun
	// einen weiteren Unterkreis K'
	// entstehen, der keine Kante in K durchlaeuft und keine Kante in G zweimal
	// enthaelt.
	// 5. Fuege in K den zweiten Kreis K' ein, indem der Startpunkt von K' durch
	// alle Punkte von K'
	// in der richtigen Reihenfolge ersetzt wird.
	// 6. Nenne jetzt den so erhaltenen Kreis K und fahre bei Schritt 2 fort.

	private void integrateLeftCycleIntoRightCycle(List<Long> newK, List<Long> k) {
		// TODO Auto-generated method stub

	}

	private Long getEdgeInKWithPositiveDegree() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Long> makeCycleBeginningAtUsingEdges(Long v, Set<Long> edges) {
		// TODO Auto-generated method stub
		List<Long> cycleEdges = new ArrayList<>();
		// while(currEdge)
		return cycleEdges;
	}

	private boolean isEulerianPath(List<Long> k) {
		// TODO: with von matthias erstellt
		return false;
	}

	private void checkEdgesHaveEvenDegree() {
		// check that all vertices have an even number of edges
		// and that the graph is directed
		if (graph.isDirected()) {
			throw new IllegalArgumentException("Graph may not be undirected");
		}
		for (Long v : graph.getVertexes()) {
			if (graph.getIncident(v).size() % 2 != 0) {
				throw new IllegalArgumentException(
						"Graph has Edges with odd degree!");
			}
		}
	}

	private void resetVariables() {
		this.allEdges = this.graph.getEdges();
	}

}
