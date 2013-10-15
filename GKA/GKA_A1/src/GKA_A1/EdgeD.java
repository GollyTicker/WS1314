package GKA_A1;


public class EdgeD extends Edge{
	
	public EdgeD(long srcVId, long destVId, long eId){
		super(srcVId, destVId, eId);
	}

	
	// Hook-method: An EdgeD is directed
	@Override
	public boolean isDirected() {
		return true;
	}
	
	@Override
	public String toString() {
		return "edge: " + this.ID + "\nbetween: " + this.getSrcVId() + " => " + this.getDestVId();
	}

}
