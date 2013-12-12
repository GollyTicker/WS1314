package GKA_A4;

import java.util.ArrayList;
import java.util.List;

import GKA_A1.IAIGraph;

public class NearestInsertion {

	private final Long NULL_LONG = -1L;
	private final int INIT_MINIMUM = Integer.MAX_VALUE;
	private IAIGraph graph;
	private List<Long> w;
	private List<Long> vertices;
	private Long startvertice = NULL_LONG;
	private String cmp;

	public NearestInsertion(IAIGraph graph, String cmp) {
		this.graph = graph;
		this.cmp = cmp;
	}

	public List<Long> nearestInsertion() {

		reset();

		startvertice = pop();
		w.add(startvertice);
		w.add(startvertice);

		while (!vertices.isEmpty()) {

			Long currentNode = findNearestVertice();
			optimalInsert(currentNode);

		}

		return w;
	}

	private void optimalInsert(Long vId) {
		int minimum = INIT_MINIMUM;
		List<Long> bestCycle = new ArrayList<>();
		for (int idx = 1; idx < w.size(); idx++) {
			List<Long> possibleNextCycles = new ArrayList<>(w);
			possibleNextCycles.add(idx, vId);
			int sum = sumCircle(possibleNextCycles);
			if (sum < minimum) {
				bestCycle = possibleNextCycles;
				minimum = sum;
			}
		}
	}

	private int sumCircle(List<Long> nextCycle) {
		int acc = 0;
		for (int i = 0; i < nextCycle.size() - 1; i++) {
			Long eId = getEdgeBetween(nextCycle.get(i), nextCycle.get(i + 1));
			acc += graph.getValE(eId, cmp);
		}
		return acc;
	}

	private Long findNearestVertice() {
		// TODO: Better naming + maximum calculation
		Long minimumVertice = NULL_LONG;
		int minimumD = INIT_MINIMUM;
		for (Long vId : vertices) {
			int tmpD = d(vId);
			if (tmpD < minimumD) {
				minimumVertice = vId;
			}
		}
		vertices.remove(minimumVertice);
		return minimumVertice;
	}

	private int d(Long vId) {
		int minimum = INIT_MINIMUM;
		for (Long wId : w) {
			Long eId = getEdgeBetween(wId, vId);
			int dist = graph.getValE(eId, cmp);
			minimum = Math.min(dist, minimum);
		}
		return minimum;
	}

	private Long getEdgeBetween(Long wId, Long vId) {
		for (Long eId : graph.getIncident(vId)) {
			if (graph.edgeIsBetween(eId, vId, wId)) {
				return eId;
			}
		}
		return NULL_LONG;
	}

	private void reset() {
		w = new ArrayList<>();
		vertices = new ArrayList<>(graph.getVertexes());
		startvertice = NULL_LONG;
	}

	private Long pop() {
		Long head = vertices.get(0);
		vertices.remove(0);
		return head;
	}
}
