package GKA_A1;

public class EdgeU extends Edge {

	public EdgeU(long vId1, long vId2, long eId) {
		super(vId1, vId2, eId);
	}

	@Override
	public boolean isDirected() {
		return false;
	}

	@Override
	public String toString() {
		return "edge: " + this.ID + "\nbetween: " + this.getSrcVId() + " <=> "
				+ this.getDestVId();
	}

}
