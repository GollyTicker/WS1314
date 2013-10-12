package GKA_A1;


public class EdgeD extends Edge{
	
	public EdgeD(long srcVID, long destVID, long EID){
		super(srcVID, destVID, EID);
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
