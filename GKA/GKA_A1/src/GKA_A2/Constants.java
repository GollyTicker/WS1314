package GKA_A2;

import java.awt.IllegalComponentStateException;

import GKA_A1.IAIGraph;

public class Constants {

	public static final double INF = Double.POSITIVE_INFINITY;
	public static final Long NULL_LONG = -1L;
	public static final Double NULL_DOUBLE = -1.0;
	public static final String NO_PATH = "No Path!";

	public static final void throwIfCycleDetected() {
		throw new IllegalComponentStateException(
				"Negative weighted cycle detected!");
	}
	
	public static final void throwIfOutOfBound(IAIGraph graph, long vertice) {
		if (!graph.getVertexes().contains(vertice))
			throw new IndexOutOfBoundsException(
					"srcVId has to be in the current Graph!");
	}
}
