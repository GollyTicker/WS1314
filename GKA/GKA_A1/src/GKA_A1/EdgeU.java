package GKA_A1;

public class EdgeU extends Edge {

	public EdgeU(long VID1, long VID2, long EID) {
		super(VID1, VID2, EID);
	}

	@Override
	public boolean isDirected() {
		return false;
	}

	@Override
	public String toString() {
		return "edge: " + this.ID + "\nbetween: " + this.getSrcVId() + " <=> " + this.getDestVId();
	}

}
