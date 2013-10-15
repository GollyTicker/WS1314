package GKA_A1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public abstract class Edge implements IEdge {

	private Map<String, String> attrs = new HashMap<>();
	public final long ID;
	private long vId1;
	private long vId2;

	public Edge(long vId1, long vId2, long EID) {
		ID = EID;
		this.vId1 = vId1;
		this.vId2 = vId2;
	}

	// Selectors
	@Override
	public int getValE(String attr) {
		if (attrs.containsKey(attr)) {
			return Integer.parseInt(attrs.get(attr));
		}
		return Integer.MAX_VALUE;
	}

	@Override
	public String getStrE(String attr) {
		if (attrs.containsKey(attr)) {
			return attrs.get(attr);
		}
		return "";
	}

	@Override
	public Set<String> getAttrE() {
		return new HashSet<>(attrs.keySet());
	}

	// Mutators
	@Override
	public void setValE(String attr, int val) {
		attrs.put(attr, Integer.valueOf(val).toString());
	}

	@Override
	public void setStrE(String attr, String val) {
		attrs.put(attr, val);
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 17 * hash + (int) (this.ID ^ (this.ID >>> 32));
		return hash;
	}

	@Override
	public String toString() {
		return "edge: " + this.ID + "\nbetween: " + this.vId1 + " " + this.vId2;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Edge))
			return false;
		Edge k = (Edge) o;
		return this.ID == k.ID;
	}

	@Override
	public long getSrcVId() {
		return this.vId1;
	}

	@Override
	public long getDestVId() {
		return this.vId2;
	}

	@Override
	public boolean hasVertice(long vId) {
		return (vId == this.vId1 || vId == this.vId2);
	}

	@Override
	public Set<Long> getSourceTarget() {
		return new HashSet<Long>(Arrays.asList(this.vId1, this.vId2));
	}

}
