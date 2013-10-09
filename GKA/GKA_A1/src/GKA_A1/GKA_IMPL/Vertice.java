/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GKA_A1.GKA_IMPL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class Vertice implements IVertice {

	// Names - Values
	private Map<String, String> attrs = new HashMap<>();

	// source and target Vertices for easier use
	private Set<Long> vertices = new HashSet<>();

	private String name;
	public final long ID;

	public Vertice(String name, long VID) {
		ID = VID;
		this.name = name;
	}

	// Selectors
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getValV(String attr) {
		if (attrs.containsKey(attr)) {
			return Integer.parseInt(attrs.get(attr));
		}
		return Integer.MAX_VALUE;
	}

	@Override
	public String getStrV(String attr) {
		if (attrs.containsKey(attr)) {
			return attrs.get(attr);
		}
		return "";
	}

	@Override
	public List<String> getAttrV() {
		return new ArrayList<>(attrs.keySet());
	}

	// Mutators
	@Override
	public void setValV(String attr, int val) {
		attrs.put(attr, Integer.valueOf(val).toString());
	}

	@Override
	public void setStrV(String attr, String val) {
		attrs.put(attr, val);
	}

	@Override
	public String toString() {
		return this.getName() + ": " + this.ID;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 73 * hash + (int) (this.ID ^ (this.ID >>> 32));
		return hash;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Vertice))
			return false;
		Vertice k = (Vertice) o;
		return this.ID == k.ID;
	}

	@Override
	public boolean hasVertice(long vID) {
		return vertices.contains(vID);
	}

	@Override
	public boolean addVertice(long vID) {
		return vertices.add(vID);
	}

}
