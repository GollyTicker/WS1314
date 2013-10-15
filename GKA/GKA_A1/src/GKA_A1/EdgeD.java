package GKA_A1;


public class EdgeD extends Edge{
	
	public EdgeD(long srcVId, long destVId, long eId){
		super(srcVId, destVId, eId);
	}

	@Override
	public boolean isDirected() {
		return true;
	}
	
	@Override
	public String toString() {
		return "edge: " + this.ID + "\nbetween: " + this.getSrcVId() + " => " + this.getDestVId();
	}

}
