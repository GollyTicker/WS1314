package GKA_A1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AIGraph implements IAIGraph {

	// Vertices and Edges are saved to two Hashmaps that identify the ID to the
	// Object
	// VerticeID -> Vertice (Object)
	private Map<Long, Vertice> vertices = new HashMap<>();
	private Map<Long, Edge> edges = new HashMap<>();

	private long vIdCounter = 0;
	private long eIdCounter = 0;

	public AIGraph() {
	}

	public AIGraph(String name) {
		addVertex(name);
	}

	@Override
	public long addVertex(String name) {
		// Strings are Unique
		for (Vertice v : this.vertices.values())
			if (name.equals(v.getName()))
				return v.ID;
		Vertice v = new Vertice(name, vIdCounter);
		vIdCounter += 1;
		vertices.put(v.ID, v);
		return v.ID;
	}

	@Override
	public boolean deleteVertex(long vId) {
		for (long eId : this.getIncident(vId))
			edges.remove(eId);
		return vertices.remove(vId) != null;
	}

	@Override
	public long addEdgeU(long v1Id, long v2Id) {
		checkContainsVertices(v1Id, v2Id);
		Edge e = new EdgeU(v1Id, v2Id, eIdCounter);
		eIdCounter += 1;
		edges.put(e.ID, e);
		return e.ID;
	}

	@Override
	public long addEdgeD(long v1Id, long v2Id) {
		checkContainsVertices(v1Id, v2Id);
		Edge e = new EdgeD(v1Id, v2Id, eIdCounter);
		eIdCounter += 1;
		edges.put(e.ID, e);
		return e.ID;
	}

	private void checkContainsVertices(long v1Id, long v2Id) {
		if (!vertices.containsKey(v1Id) || !vertices.containsKey(v2Id)) {
			throw new IllegalArgumentException(
					"At least one of the given vertice IDs is currently not in the Graph!");
		}
	}

	@Override
	public boolean deleteEdge(long eId) {
		return edges.remove(eId) != null;
	}

	@Override
	public boolean deleteEdge(long v1Id, long v2Id) {
		for (Edge e : edges.values()) {
			boolean directed = e.getSrcVId() == v1Id
					&& e.getDestVId() == v2Id;
			boolean undirected = !e.isDirected() && e.getSrcVId() == v2Id
					&& e.getDestVId() == v1Id;
			if (undirected || directed)
				return edges.remove(e.ID) != null;
		}
		return false;
	}

	// Selectors
	@Override
	public boolean isEmpty() {
		return vertices.isEmpty();
	}

	@Override
	public long getSource(long e1Id) {
		return edges.get(e1Id).getSrcVId();
	}

	@Override
	public long getTarget(long eId) {
		return edges.get(eId).getDestVId();
	}

	// The assumption is that incident edges are connected to another vertices
	// in any way
	@Override
	public Set<Long> getIncident(long vId) {
		Set<Long> eIds = new HashSet<>();
		for (Edge e : edges.values()) {
			if (e.getSrcVId() == vId || e.getDestVId() == vId) {
				eIds.add(e.ID);
			}
		}
		return eIds;
	}

	@Override
	public Set<Long> getAdjacent(long vId) {
		// traverse through all incident edges and make unique add all vertices
		// except the own one
		Set<Long> vIds = new HashSet<>();
		Set<Long> incident = getIncident(vId);
		for (Long eId : incident) {
			long couldBeAdjc = edges.get(eId).getSrcVId();
			if (!vIds.contains(couldBeAdjc) && couldBeAdjc != vId) {
				vIds.add(couldBeAdjc);
			}
			couldBeAdjc = edges.get(eId).getDestVId();
			if (!vIds.contains(couldBeAdjc) && couldBeAdjc != vId) {
				vIds.add(couldBeAdjc);
			}
		}
		return vIds;
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
	public int getValE(long eId, String attr) {
		return edges.get(eId).getValE(attr);
	}

	@Override
	public int getValV(long vId, String attr) {
		return vertices.get(vId).getValV(attr);
	}

	@Override
	public String getStrE(long eId, String attr) {
		return edges.get(eId).getStrE(attr);
	}

	@Override
	public String getStrV(long vId, String attr) {
		return vertices.get(vId).getStrV(attr);
	}

	@Override
	public Set<String> getAttrV(long vId) {
		return vertices.get(vId).getAttrV();
	}

	@Override
	public Set<String> getAttrE(long eId) {
		return edges.get(eId).getAttrE();
	}

	// Mutators
	// through delegtion
	@Override
	public void setValE(long eId, String attr, int val) {
		edges.get(eId).setValE(attr, val);
	}

	@Override
	public void setValV(long vId, String attr, int val) {
		vertices.get(vId).setValV(attr, val);
	}

	@Override
	public void setStrE(long eId, String attr, String val) {
		edges.get(eId).setStrE(attr, val);
	}

	@Override
	public void setStrV(long vId, String attr, String val) {
		vertices.get(vId).setStrV(attr, val);
	}

	@Override
	public String toString() {
		String stracc = "Graph\n";

		for (Edge e : edges.values()) {
			Vertice source = vertices.get(e.getSrcVId());
			Vertice target = vertices.get(e.getDestVId());
			stracc += "Edge: " + e.ID + " - " + source.getName() + "("
					+ source.ID + ")" + (e.isDirected() ? " => " : " <=> ")
					+ target.getName() + "(" + target.ID + ")\n";
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AIGraph))
			return false;
		AIGraph otherG = (AIGraph) obj;
		return otherG.getEdgeMap().equals(this.getEdgeMap())
				&& otherG.getVerticeMap().equals(this.getVerticeMap());
	}

	public Map<Long, Vertice> getVerticeMap() {
		return this.vertices;
	}

	public Map<Long, Edge> getEdgeMap() {
		return this.edges;
	}
}
