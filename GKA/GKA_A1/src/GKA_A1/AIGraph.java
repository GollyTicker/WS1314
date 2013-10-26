package GKA_A1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import GKA_A2.Matrix.Matrix;
import GKA_A2.Matrix.MatrixArray;
import GraphUtils.ITimeSpace;

public class AIGraph implements IAIGraph, ITimeSpace {

	// accesscount variable for counting
	int accessCount = 0;

	// Vertices and Edges are saved to two Hashmaps that identify the ID to the
	// Object
	// VerticeID -> Vertice (Object)
	private Map<Long, Vertice> vertices = new HashMap<>();
	private Map<Long, Edge> edges = new HashMap<>();

	// Counter for Vertice and Edge incrementing by one each time an edge is
	// added
	private long vIdCounter = 0;
	private long eIdCounter = 0;

	public AIGraph() {
	}

	public AIGraph(String name) {
		addVertex(name);
	}

	// Adds a Vertex to the Graph with a given Name. The name has to be Unique.
	@Override
	public long addVertex(String name) {
		// Check if name is in the Hashmap
		for (Vertice v : this.vertices.values())
			if (name.equals(v.getName()))
				return v.ID;
		// if not create a new Vertice
		Vertice v = new Vertice(name, vIdCounter);
		vIdCounter += 1;
		vertices.put(v.ID, v);
		return v.ID;
	}

	// if a vertex is deleted check all incident edges to this vertex and delete
	// them.
	@Override
	public boolean deleteVertex(long vId) {
		for (long eId : this.getIncident(vId))
			edges.remove(eId);
		return vertices.remove(vId) != null;
	}

	// if verticeIds are not present in the Graph throw an error.
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

	// check if ids are present else throw Error
	private void checkContainsVertices(long v1Id, long v2Id) {
		if (!vertices.containsKey(v1Id) || !vertices.containsKey(v2Id)) {
			throw new IllegalArgumentException(
					"At least one of the given vertice IDs is currently not in the Graph!");
		}
	}

	// delete the edge from hashmap return true if element was removed
	@Override
	public boolean deleteEdge(long eId) {
		return edges.remove(eId) != null;
	}

	// delete an edge between two vertices
	@Override
	public boolean deleteEdge(long v1Id, long v2Id) {
		for (Edge e : edges.values()) {
			// if it is directed then IDs have to match src and dest
			boolean directed = e.getSrcVId() == v1Id && e.getDestVId() == v2Id;
			// if it is undirected the edge has to be undirected and it is
			// possible that src and dest are switched
			boolean undirected = !e.isDirected() && e.getSrcVId() == v2Id
					&& e.getDestVId() == v1Id;
			// so either it is directed or undirected if it is none, don't
			// delete
			if (undirected || directed)
				return edges.remove(e.ID) != null;
		}
		return false;
	}

	// Selectors

	// if Graph has no vertices it is empty
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
		// get all incident Edges to the given Vertice. All adjacent are subset
		// of the incidents
		Set<Long> incident = getIncident(vId);
		for (Long eId : incident) {
			// test all srcIDs if they are not equal the given ID and is not
			// already in the adjacent Set.
			long couldBeAdjc = edges.get(eId).getSrcVId();
			if (!vIds.contains(couldBeAdjc) && couldBeAdjc != vId) {
				vIds.add(couldBeAdjc);
			}
			// Same goes for Destination IDs
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
	// through delegation
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
		String stracc = "Graph: \n";

		// Edge: edgeId - VerticeName1(vId1) <=>//=> VerticeName2(vId2)
		for (Edge e : edges.values()) {
			Vertice source = vertices.get(e.getSrcVId());
			Vertice target = vertices.get(e.getDestVId());

			String edge = "Edge: " + e.ID + " - ";
			String sourceS = source.getName() + "(" + source.ID + ")";
			String destS = target.getName() + "(" + target.ID + ")";
			String direction = (e.isDirected() ? " => " : " <=> ");

			stracc += edge + sourceS + direction + destS + "\n";
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
		// go through the list auf values and return the first element that
		// matches given name
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
		// assumption: both Hashmaps must be equal
		return otherG.getEdgeMap().equals(this.getEdgeMap())
				&& otherG.getVerticeMap().equals(this.getVerticeMap());
	}

	public Map<Long, Vertice> getVerticeMap() {
		return this.vertices;
	}

	public Map<Long, Edge> getEdgeMap() {
		return this.edges;
	}

	@Override
	public Matrix toMatrixU() {
		int vSize = vertices.size();
		Matrix graphMatrix = new MatrixArray(vSize, vSize);
		for (int i = 0; i < vSize; i++) {
			for (int j = 0; j < vSize; j++) {
				int edgesFromItoJ = 0;
				for (long eid : getIncident(i))
					if (edges.get(eid).getSrcVId() == i
							&& edges.get(eid).getDestVId() == j)
						edgesFromItoJ += 1;
				graphMatrix.insert(i + 1, j + 1, edgesFromItoJ);
			}
		}
		return graphMatrix;
	}

	@Override
	public Matrix toMatrixD() {
		int vSize = vertices.size();
		Matrix graphMatrix = new MatrixArray(vSize, vSize);
		for (int i = 0; i < vSize; i++) {
			for (int j = 0; j < vSize; j++) {
				int edgesFromItoJ = 0;
				for (long eid : getIncident(i))
					if (edges.get(eid).getSrcVId() == i
							&& edges.get(eid).getDestVId() == j)
						edgesFromItoJ += 1;
				graphMatrix.insert(i + 1, j + 1, edgesFromItoJ);
			}
		}
		return graphMatrix;
	}

	@Override
	public int accessCount() {
		return accessCount;
	}

	@Override
	public void setAccessCount(int ac) {
		accessCount = ac;

	}

	@Override
	public void resetAccessCount() {
		accessCount = 0;

	}

	@Override
	public int memoryUsage() {
		// wir interpretieren einfachheitshalber die VerticesZahl und Edgeszahl
		// als Speicherverbrauch - Diese Methode gehört eigentlich zum
		// Interface, ist aber kein Teil der Aufgabe.
		return edges.size() + vertices.size();
	}

	@Override
	public void printCount() {
		System.out.println("AccessCount: " + accessCount);
	}
}
