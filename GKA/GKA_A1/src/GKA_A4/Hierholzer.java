package GKA_A4;

import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import GKA_A1.IAIGraph;
import GraphUtils.ITimeSpace;

public class Hierholzer implements ITimeSpace{

	private final Long NULL_IDX = -1L;
	private IAIGraph graph;
	private final boolean DEBUGMODE = true;
	
	private int accessCount = 0;

	private Set<Long> allEdges;

	public Hierholzer(IAIGraph graph) {
		this.graph = graph;
	}

	// Main algorithm
	public List<Long> hierholzeEs() {
		
		// reset and initialisze all global Variables
		resetVariables();
		
		// a graph can only have a eulertour
		// if its undirected and has only vertices with even degree
		checkEdgesHaveEvenDegree();

		// Step 1
		// chose a initial vertice to start the first cycle from.
		Long v = getInitialVertice();
		// K is a List of Edges
		// make the first cycle. start from the previously chosen vertice v.
		// all edges are allowed to be used.
		List<Long> k = makeCycleBeginningAtUsingEdges(v, allEdges);

		debugPrint("Initial Cycle: " + k);

		// Step 2
		while (!isEulerianCycle(graph, k)) {

			// Step 3
			// calculate the edges we've left out.
			Set<Long> edgesWithoutK = new HashSet<Long>(allEdges);
			edgesWithoutK.removeAll(k);

			// Step 4
			// get a vertice which can be used for further initial poitns for cycles
			// that is also in the current cycle k.
			Long newV = getVerticeInKWithPositiveDegreeUsingOnlyEdgesFrom(k,
					edgesWithoutK);

			if (newV == NULL_IDX)
				throw new IllegalArgumentException(
						"getEdgePos returned -1");
			
			// we can now make a cycle starting at the previously chosen point.
			// only unused edges may be used here.
			List<Long> newK = makeCycleBeginningAtUsingEdges(newV,
					edgesWithoutK);

			// Step 5 and 6
			// K is updated here
			// the Vertice newK starts form is also given over,
			// because it simplfies the function
			k = integrateLeftCycleIntoRightCycle(newK, k, newV);
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

	private Long getInitialVertice() {
		for (Long v : graph.getVertexes()) {
			countAccess(1);
			return v;
		}
		return NULL_IDX;
	}

	private List<Long> integrateLeftCycleIntoRightCycle(List<Long> newK,
			List<Long> k, Long newKStart) {

		// for vertices a and b the notation a:b means the edge (in newK or K)
		// inbetween the vertices a and b

		// K: [ 1:7, 7:3, 3:2, 2:1 ]
		// newK: [ 3:1, 1:8, 8:7, 7:4, 4:3 ]
		// integrated: [ 1:7, 7:3, 3:1, 1:8, ... 4:3, 3:2, 2:1]

		// We know, that the start Vertice of newK is contained in K.
		// Therefore, we need to find the first occurrence of this vertice in K
		// and insert newK at this position

		// the way wikipedia explains this is ambiguous.
		// therefore we're using this sligtly different approach

		List<Long> mergedK = new ArrayList<>(k);

		debugPrint("Going to Integrate");
		debugPrint("K: " + k + "; newK: " + newK + "; startV: " + newKStart);

		int size = k.size();
		for (int i = 0; i < k.size(); i++) {

			int currEidx = (i) % size;	//(this first module is not really needed)
			int nextEidx = (i + 1) % size;

			Long prevE = k.get(currEidx);
			Long currE = k.get(nextEidx);
			// the modulo makes the two picked vertices rotate
			// this allows me to also pick the initial vertice which
			// is also a valid insertion point

			// calculate the Vertice inbetween both of the edges
			Long verticeInbetween = verticeInBetween(prevE, currE);

			if (verticeInbetween.equals(newKStart)) {
				// the currE is an "outgoing" edge from the newKStart vertice

				// now we have to add the newK cycle inbetween these two edges
				// now i has the index of the second edge.

				// using this List-Operation, java will
				// shift this second edge and all subsequent edges
				// and insert the edges of newK in the expected order
				// into this position
				mergedK.addAll(nextEidx, newK);

				debugPrint("Merged: " + mergedK);

				// important to end now. else it would add it again
				// if the vertice appears multiple times
				return mergedK;
			}

		}
		
		// the merge should always succed.
		// the vertice has to be found in the list.
		throw new IllegalArgumentException(
				"whoooosh!..... this shouldnt happen! the insertion Vertice wasnt found!");
	}

	// (this looks so horrible in java.....)
	private Long verticeInBetween(Long prevE, Long currE) {
		Set<Long> common = commonVerticesOf(prevE, currE, graph);
		Set<Long> intersect = new HashSet<>(common);
		intersect.retainAll(graph.getSourceTarget(prevE));
		intersect.retainAll(graph.getSourceTarget(currE));
		countAccess(2);
		for (Long v : intersect) {
			return v;
		}
		return NULL_IDX;
	}

	private Long getVerticeInKWithPositiveDegreeUsingOnlyEdgesFrom(
			List<Long> k, Set<Long> usableEdges) {
		for (Long e : k) {
			for (Long v : graph.getSourceTarget(e)) {
				countAccess(1); 
				if (degreeWithinEdges(v, usableEdges) > 0) {
					return v;
				}
			}

		}
		return NULL_IDX;
	}

	private List<Long> makeCycleBeginningAtUsingEdges(Long startVertice,
			Set<Long> usableEdges) {

		List<Long> cycleEdges = new ArrayList<>();
		Long currHeadVertice = startVertice;

		debugPrint("Start from " + startVertice + " using edges " + usableEdges);
		
		// as long as we've not returned to our start vertice, we'll continue.
		// we have to add the size < 2 condition because we dont want to exit too early.
		while (cycleEdges.size() < 2
				|| !lastEdgeReachedVertice(cycleEdges, startVertice)) {
			debugPrint("CurrnetHeadVertice: " + currHeadVertice);
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
			
			// remove the used edges from the set of usable edges
			// add the edge to the cycle. (adds at the end)
			usableEdges.remove(usedEdge);
			cycleEdges.add(usedEdge);

			// begin form the next Vertice in the next iteration
			currHeadVertice = nextVertice;
		}

		return cycleEdges;
	}

	private List<Long> pickNextEdgeFrom_Using_(Long currHeadVertice,
			Set<Long> usableEdges) {

		Set<Long> incident = graph.getIncident(currHeadVertice);
		countAccess(1);

		// build the set of usable edges connected witht the head
		Set<Long> intersect = new HashSet<>(usableEdges);
		intersect.retainAll(incident);
		
		// this set has to have atleast one edge!
		if (intersect.isEmpty()) {
			throw new IllegalArgumentException(
					" <<< --- whoooooops!!! --- >>> \nArguments: "
							+ currHeadVertice + " and " + usableEdges);
		}

		// fetch the edge
		Long edge = NULL_IDX;
		for (Long e : intersect) { // java has no better way of getting a single
									// elem out of a set -_-
			edge = e;
			break;
		}
		
		// fetch the vertice
		Long vertice = NULL_IDX;
		for (Long v : graph.getSourceTarget(edge)) {
			countAccess(1);
			if (v != currHeadVertice) {
				vertice = v;
			}
		}

		if (vertice == NULL_IDX || edge == NULL_IDX) {
			throw new IllegalArgumentException(
					" <---- Nil! ----> \nArguments: " + currHeadVertice
							+ " and " + usableEdges);
		}
		
		// pack and return
		return new ArrayList<>(Arrays.asList(edge, vertice));
	}
	
	// check whether the alst edge is connected with the given vertice
	private boolean lastEdgeReachedVertice(List<Long> cycleEdges, Long v) {
		Long lastEdge = cycleEdges.get(cycleEdges.size() - 1);
		countAccess(1);
		return graph.getSourceTarget(lastEdge).contains(v);
	}
	
	// as static methode used in the algorithm for testing.
	//
	public static boolean isEulerianCycle(IAIGraph graph, List<Long> edges) {
		
		// check that each edge appears exactly once in the eulercycle
		if (new HashSet<Long>(edges).size() != graph.getEdges().size())
			return false;
		
		// now check, that all the edges are actually consecutive.
		int size = edges.size();
		for (int i = 0; i < size - 1; i++) {
			Long currE = edges.get(i) % size;
			Long nextE = edges.get(i + 1) % size;

			// check, that there is a vertice between two consecutive edges
			if (commonVerticesOf(currE, nextE, graph).size() > 3) {
				return false;
			}
		}
		return true;
	}

	// could be refactored into Graph ADT
	private static Set<Long> commonVerticesOf(Long e1, Long e2, IAIGraph graph) {
		Set<Long> commonVertices = graph.getSourceTarget(e1);
		commonVertices.addAll(graph.getSourceTarget(e2));
		return commonVertices;
	}

	private void checkEdgesHaveEvenDegree() {
		// check that all vertices have an even number of edges
		// and that the graph is directed
		if (graph.isDirected()) {
			countAccess(1);
			throw new IllegalArgumentException("Graph may not be undirected");
		}
		for (Long v : graph.getVertexes()) {
			countAccess(1);
			if (degree(v) % 2 != 0) {
				throw new IllegalArgumentException(
						"Graph has Vertices with odd degree!");
			}
		}
	}

	private int degree(Long v) {
		return degreeWithinEdges(v, allEdges);
	}
	
	// calculate the degree but limited to the given edges
	private int degreeWithinEdges(Long v, Set<Long> usableEdges) {
		countAccess(1);
		Set<Long> usableIncident = graph.getIncident(v);
		usableIncident.retainAll(usableEdges);
		return usableIncident.size();
	}

	private void resetVariables() {
		resetAccessCount();
		countAccess(1);
		this.allEdges = this.graph.getEdges();
	}

	private void debugPrint(String s) {
		if (DEBUGMODE)
			System.out.println(s);
	}

	@Override
	public int accessCount() {
		return accessCount;
	}

	@Override
	public void setAccessCount(int ac) {
		accessCount = ac;
	}
	
	private void countAccess(int add) {
		setAccessCount(accessCount() + add);
	}

	@Override
	public void resetAccessCount() {
		setAccessCount(0);
	}

	@Override
	public void printCount() {
		debugPrint("AccessCount:" + accessCount());
	}

}
