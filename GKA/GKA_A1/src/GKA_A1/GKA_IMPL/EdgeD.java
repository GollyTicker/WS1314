package GKA_A1.GKA_IMPL;


public class EdgeD extends Edge{
	
	public EdgeD(long srcVID, long destVID){
		super(srcVID, destVID);
	}

	@Override
	public boolean isDirected() {
		return false;
	}
}
