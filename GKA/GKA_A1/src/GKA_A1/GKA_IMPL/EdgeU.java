package GKA_A1.GKA_IMPL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EdgeU extends Edge {

	public EdgeU(long VID1, long VID2) {
		super(VID1, VID2);
	}

	@Override
	public boolean isDirected() {
		return true;
	}

}
