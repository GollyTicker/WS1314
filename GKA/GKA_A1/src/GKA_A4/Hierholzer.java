package GKA_A4;

import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import GKA_A1.IAIGraph;

public class Hierholzer {

	private final Long NULL_LONG = -1L;
	private IAIGraph graph;

	private Set<Long> allEdges;

	public Hierholzer(IAIGraph graph) {
		this.graph = graph;
	}

	// Main algorithm
	public List<Long> hierholzeEs() {
		resetVariables();

		checkEdgesHaveEvenDegree();

		// Step 1
		Long v = -1L;
		// K is a List of Edges
		List<Long> k = makeCycleBeginningAtUsingEdges(v, allEdges);

		// Step 2
		while (!isEulerianPath(graph, k)) {

			// Step 3
			// vernachlaessige Kanten von K
			Set<Long> edgesWithoutK = new HashSet<Long>(k);
			edgesWithoutK.removeAll(allEdges);

			// Step 4
			Long newV = getEdgeInKWithPositiveDegree(k);
			List<Long> newK = makeCycleBeginningAtUsingEdges(newV,
					edgesWithoutK);

			// Step 5 and 6
			// K is updated here
			integrateLeftCycleIntoRightCycle(newK, k);
		}
		return k;
	}

	// Voraussetzung: Sei G=(V,E) ein zusammenhaengender Graph, der nur Knoten
	// mit geradem Grad aufweist.
	// 1. Waehle einen beliebigen Knoten v_0 des Graphen und konstruiere von v_0
	// ausgehend einen Unterkreis K in G, der keine Kante in G zweimal
	// durchlaeuft.
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

	// Aus Wikipedia: https://de.wikipedia.org/wiki/Algorithmus_von_Hierholzer
	// (12.12.13)

	private void integrateLeftCycleIntoRightCycle(List<Long> newK, List<Long> k) {
		// TODO: this function
	}

	private Long getEdgeInKWithPositiveDegree(List<Long> k) {
		for (Long e : k) {
			for (Long v : graph.getSourceTarget(e)) {
				if (degree(v) > 0) {
					return v;
				}
			}

		}
		return NULL_LONG;
	}

	private List<Long> makeCycleBeginningAtUsingEdges(Long startVertice,
			Set<Long> usableEdges) {

		List<Long> cycleEdges = new ArrayList<>();
		Long currHeadVertice = startVertice;

		do {

			// LOOOOL <<<------ first time I really had a good
			// usage for the do while syntax, lel xD
			// I have to go through this loop atleast once, because after adding
			// the first
			// incident edge, v will ofcource be a source/Target of this edge.
			// this edge is going to be the last edge in the list at that time
			// and
			// the loop-condition would evaluate to true and it'd exit with only
			// one edge.
			// (with the loop unevaluated)
			// thats why need to use do while instead of a normal while.

			// this method returns a list with two elements.
			// the first element is the eID of the chosen edge and the
			// second element id the vId of the corresponging parter vertice
			// of the given currHeadVertice
			// the method name is to be read as:
			// pick next edge from "currHEadVertice" using "usableEdges"
			List<Long> container = pickNextEdgeFrom_Using_(currHeadVertice,
					usableEdges);

			Long usedEdge = container.get(0);
			Long nextVertice = container.get(1);

			usableEdges.remove(usedEdge);
			cycleEdges.add(usedEdge);

			// begin form the next Vertice in the next iteration
			currHeadVertice = nextVertice;

		} while (!lastEdgeReachedVertice(cycleEdges, startVertice));

		return cycleEdges;
	}

	private List<Long> pickNextEdgeFrom_Using_(Long currHeadVertice,
			Set<Long> usableEdges) {

		Set<Long> incident = graph.getIncident(currHeadVertice);

		// build set od edge incident with the head vertice and
		// which are also allowed to be used.
		Set<Long> intersect = new HashSet<>(usableEdges);
		intersect.retainAll(incident);

		// this set has to have atleast one edge!
		if (intersect.isEmpty()) {
			System.err.println(" <<< --- whoooooops!!! --- >>> ");
			System.err.println("Arguments: " + currHeadVertice + " and "
					+ usableEdges);
		}

		// fetch the edge and vertice
		Long edge = NULL_LONG;
		for (Long e : intersect) { // java has no better way of getting a single
									// elem out of a set -_-
			edge = e;
			break;
		}

		Long vertice = NULL_LONG;
		for (Long v : graph.getSourceTarget(edge)) {
			if (v != currHeadVertice) {
				vertice = v;
			}
		}

		if (vertice == NULL_LONG || edge == NULL_LONG) {
			System.err.println(" <---- Nil! ----> ");
			System.err.println("Arguments: " + currHeadVertice + " and "
					+ usableEdges);
		}
		return new ArrayList<>(Arrays.asList(edge, vertice));
	}

	private boolean lastEdgeReachedVertice(List<Long> cycleEdges, Long v) {
		Long lastEdge = cycleEdges.get(cycleEdges.size() - 1);
		return graph.getSourceTarget(lastEdge).contains(v);
	}

	public static boolean isEulerianPath(IAIGraph graph, List<Long> edges) {
		if (new HashSet<Long>(edges).size() != edges.size())
			return false;

		for (int i = 0; i < edges.size() - 1; i++) {
			Long currE = edges.get(i);
			Long nextE = edges.get(i + 1);

			// check, that there is a vertice between two consecutive edges
			Set<Long> shouldBeThreeOrLess = graph.getSourceTarget(currE);
			shouldBeThreeOrLess.addAll(graph.getSourceTarget(nextE));
			if (shouldBeThreeOrLess.size() > 3) {
				return false;
			}
		}
		return true;
	}

	private void checkEdgesHaveEvenDegree() {
		// check that all vertices have an even number of edges
		// and that the graph is directed
		if (graph.isDirected()) {
			throw new IllegalArgumentException("Graph may not be undirected");
		}
		for (Long v : graph.getVertexes()) {
			if (degree(v) % 2 != 0) {
				throw new IllegalArgumentException(
						"Graph has Edges with odd degree!");
			}
		}
	}

	private int degree(Long v) {
		return graph.getIncident(v).size();
	}

	private void resetVariables() {
		this.allEdges = this.graph.getEdges();
	}

}
