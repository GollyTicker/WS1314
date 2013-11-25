package GKA_A3;

public interface IFlowAlgorithmsStackQueue {
	public boolean verticeIsMarked(long vID);

	public void markVertice(long vID, Tuple4 info);

	public Tuple4 getMarkedTuple(long vID);

	public void inspectVertice(long vID);

	public void resetMarks();
}
