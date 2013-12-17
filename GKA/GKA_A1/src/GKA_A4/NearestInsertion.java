package GKA_A4;

import java.util.ArrayList;
import java.util.List;

import GKA_A1.IAIGraph;
import GraphUtils.ITimeSpace;

public class NearestInsertion implements ITimeSpace {

	private final Long NULL_INDEX = -1L;
	private final int INFINITY = Integer.MAX_VALUE;

	private int accessCount = 0;
	private final boolean DEBUGMODE = true;

	private IAIGraph graph;
	private String cmp;
	private List<Long> w;
	private List<Long> vertices;
	private Long initialvertice = NULL_INDEX;
	private int minimum = INFINITY;

	public NearestInsertion(IAIGraph graph, String cmp) {
		this.graph = graph;
		this.cmp = cmp;
	}

	public List<Long> nearestInsertion() {
		if (graph.isEmpty()) {
			throw new IllegalArgumentException("Graph may not be empty!");
		}

		reset();

		initialvertice = pop();
		w.add(initialvertice);
		w.add(initialvertice);

		debugPrint("Initial W: " + w);

		while (!vertices.isEmpty()) {
			Long currentNode = findNearestVertice();
			debugPrint("Nearest Vertice: " + currentNode);
			optimalInsert(currentNode);
			debugPrint("Chosen => " + w);
		}

		return w;
	}

	private void optimalInsert(Long vId) {
		int minimum = INFINITY;
		List<Long> bestCycle = new ArrayList<>();
		for (int idx = 1; idx < w.size(); idx++) {
			accessCount += (1 + w.size());
			List<Long> possibleNextCycles = new ArrayList<>(w);
			possibleNextCycles.add(idx, vId);
			int sum = sumCircle(possibleNextCycles);
			debugPrint(" :: Possibility { " + sum + " , " + possibleNextCycles
					+ " }");
			if (sum < minimum) {
				bestCycle = possibleNextCycles;
				minimum = sum;
				this.minimum = minimum;
			}
		}
		w = bestCycle;
	}

	private int sumCircle(List<Long> nextCycle) {
		int acc = 0;
		for (int i = 0; i < nextCycle.size() - 1; i++) {
			accessCount += 2;
			Long source = nextCycle.get(i);
			Long target = nextCycle.get(i + 1);
			// Exception case for W= [A,A]
			if (source != target) {
				accessCount += 1;
				Long eId = getEdgeBetween(source, target);
				acc += graph.getValE(eId, cmp);
			}
		}
		return acc;
	}

	private Long findNearestVertice() {
		// d() has to be called on every remaining element in vertices
		Long minimum_vertice = NULL_INDEX;
		int minimum_d = INFINITY;
		for (Long vId : vertices) {
			accessCount += 1;
			int tmpD = d(vId);
			if (tmpD < minimum_d) {
				minimum_vertice = vId;
			}
		}
		return pop(minimum_vertice);
	}

	private int d(Long vId) {
		// finds the minimum weight from a vertice in w to the current given
		// vertice vId.
		int minimum = INFINITY;
		for (Long wId : w) {
			accessCount += 1;
			Long eId = getEdgeBetween(wId, vId);
			int distance = graph.getValE(eId, cmp);
			minimum = Math.min(distance, minimum);
		}
		return minimum;
	}

	public Long getEdgeBetween(Long wId, Long vId) {
		for (Long eId : graph.getIncident(vId)) {
			accessCount += 1;
			if (graph.edgeIsBetween(eId, vId, wId)) {
				return eId;
			}
		}
		return NULL_INDEX;
	}

	private void reset() {
		w = new ArrayList<>();
		vertices = new ArrayList<>(graph.getVertexes());
		initialvertice = NULL_INDEX;
	}

	private Long pop() {
		int idx = 0;
		Long head = vertices.get(idx);
		vertices.remove(idx);
		accessCount += 2;
		return head;
	}

	private Long pop(Long vId) {
		int idx = vertices.indexOf(vId);
		Long head = vertices.get(idx);
		vertices.remove(idx);
		accessCount += 3;
		return head;
	}

	public int getMinimum() {
		return minimum;
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

	@Override
	public void resetAccessCount() {
		accessCount = 0;

	}

	@Override
	public void printCount() {
		System.out.println(accessCount);
	}
}
