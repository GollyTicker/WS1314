package GKA_A3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import GKA_A1.IAIGraph;

public interface IFlowAlgorithmsStackQueue {
	public boolean verticeIsMarked(long vID);

	public void markVertice(long vID, Tuple4 info);

	public Tuple4 getMarkedTuple(long vID);

	public void inspectVertice(long vID);

	public void resetMarks();
}
