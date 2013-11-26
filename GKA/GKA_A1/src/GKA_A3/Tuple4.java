package GKA_A3;

public class Tuple4 {

	private String direction;
	private Long predID;
	private Integer restCap;
	private boolean inspected;

	public Tuple4(String direction, Long predID, Integer restCap,
			boolean inspected) {
		this.direction = direction;
		this.predID = predID;
		this.restCap = restCap;
		this.inspected = inspected;
	}

	public String getDirection() {
		return this.direction;
	}

	public Long getPredID() {
		return this.predID;
	}

	public Integer getRestCap() {
		return this.restCap;
	}

	public boolean wasInspected() {
		return this.inspected;
	}

	public void inspect() {
		this.inspected = true;
	}

	@Override
	public String toString() {
		return "(" + this.direction + ", " + this.predID + ", " + this.restCap
				+ ", " + this.inspected + ")";
	}
}
