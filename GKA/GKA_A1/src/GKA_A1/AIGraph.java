package GKA_A1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AIGraph implements IAIGraph {

	// Vertices and Edges are saved to two Hashmaps that identify the ID to the
	// Object
	// VerticeID -> Vertice (Object)
	Map<Long, Vertice> vertices = new HashMap<>();
	Map<Long, Edge> edges = new HashMap<>();

	private long VIDcounter = 0;
	private long EIDcounter = 0;

	public AIGraph() {
	}

	@Override
	public long addVertex(String name) {
		Vertice v = new Vertice(name, VIDcounter);
		VIDcounter += 1;
		vertices.put(v.ID, v);
		return v.ID;
	}

	@Override
	public boolean deleteVertex(long vID) {
		for(long eId : this.getIncident(vID))
			edges.remove(eId);
		return vertices.remove(vID) != null;
	}

	@Override
	public long addEdgeU(long v1ID, long v2ID) {
		if (!vertices.containsKey(v1ID) || !vertices.containsKey(v2ID)) {
			throw new IllegalArgumentException(
					"At least one of the given vertice IDs is currently not in the Graph!");
		}
		Edge e = new EdgeU(v1ID, v2ID, EIDcounter);
		EIDcounter += 1;
		edges.put(e.ID, e);
		return e.ID;
	}

	@Override
	public long addEdgeD(long v1ID, long v2ID) {
		if (!vertices.containsKey(v1ID) || !vertices.containsKey(v2ID)) {
			throw new IllegalArgumentException(
					"At least one of the given vertice IDs is currently not in the Graph!");
		}
		Edge e = new EdgeD(v1ID, v2ID, EIDcounter);
		EIDcounter += 1;
		edges.put(e.ID, e);
		return e.ID;
	}

	@Override
	public boolean deleteEdge(long eId) {
		return edges.remove(eId) != null;
	}

	// Selectors
	@Override
	public boolean isEmpty() {
		return vertices.isEmpty();
	}

	@Override
	public long getSource(long e1) {
		return edges.get(e1).getSrcVId();
	}

	@Override
	public long getTarget(long e1) {
		return edges.get(e1).getDestVId();
	}

	// The assumption is that incident edges are connected to another vertices
	// in any way
	@Override
	public Set<Long> getIncident(long v1) {
		Set<Long> eIDs = new HashSet<>();
		for (Edge e : edges.values()) {
			if (e.getSrcVId() == v1 || e.getDestVId() == v1) {
				eIDs.add(e.ID);
			}
		}
		return eIDs;
	}

	@Override
	public Set<Long> getAdjacent(long v1) {
		// traverse through all incident edges and make unique add all vertices
		// except the own one
		Set<Long> vIDs = new HashSet<>();
		Set<Long> incident = getIncident(v1);
		for (Long eID : incident) {
			long couldBeAdjc = edges.get(eID).getSrcVId();
			if (!vIDs.contains(couldBeAdjc) && couldBeAdjc != v1) {
				vIDs.add(couldBeAdjc);
			}
			couldBeAdjc = edges.get(eID).getDestVId();
			if (!vIDs.contains(couldBeAdjc) && couldBeAdjc != v1) {
				vIDs.add(couldBeAdjc);
			}
		}
		return vIDs;
	}

	@Override
	public Set<Long> getVertexes() {
		return new HashSet<>(vertices.keySet());
	}

	@Override
	public Set<Long> getEdges() {
		return new HashSet<>(edges.keySet());
	}

	// Selectors
	// through delegation
	@Override
	public int getValE(long e1, String attr) {
		return edges.get(e1).getValE(attr);
	}

	@Override
	public int getValV(long v1, String attr) {
		return vertices.get(v1).getValV(attr);
	}

	@Override
	public String getStrE(long e1, String attr) {
		return edges.get(e1).getStrE(attr);
	}

	@Override
	public String getStrV(long v1, String attr) {
		return vertices.get(v1).getStrV(attr);
	}

	@Override
	public Set<String> getAttrV(long v1) {
		return vertices.get(v1).getAttrV();
	}

	@Override
	public Set<String> getAttrE(long e1) {
		return edges.get(e1).getAttrE();
	}

	// Mutators
	// thorugh delegtion
	@Override
	public void setValE(long e1, String attr, int val) {
		edges.get(e1).setValE(attr, val);
	}

	@Override
	public void setValV(long v1, String attr, int val) {
		vertices.get(v1).setValV(attr, val);
	}

	@Override
	public void setStrE(long e1, String attr, String val) {
		edges.get(e1).setStrE(attr, val);
	}

	@Override
	public void setStrV(long v1, String attr, String val) {
		vertices.get(v1).setStrV(attr, val);
	}

	@Override
	public String toString() {
		String stracc = "Graph\n";
		for (Long eID : edges.keySet()) {
			stracc += edges.get(eID).toString() + "\n";
		}

		return stracc;
	}

	@Override
	public Set<String> getVertexNames() {
		Set<String> s = new HashSet<>();
		for (Vertice k : vertices.values())
			s.add(k.getName());
		return s;
	}

	@Override
	public long getVertexByName(String name) {
		for (Vertice elem : vertices.values()) {
			if (elem.getName() == name)
				return elem.ID;
		}
		return -1;
	}
}
