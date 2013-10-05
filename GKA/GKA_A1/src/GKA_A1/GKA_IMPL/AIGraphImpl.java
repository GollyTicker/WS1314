/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GKA_A1.GKA_IMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class AIGraphImpl implements AIGraph {

	// Vertices and Edges are saved to two Hashmaps that identify the ID to the Object
	// VerticeID -> Vertice (Object)
    Map<Long, Vertice> vertices = new HashMap<>();
    Map<Long, Edge> edges = new HashMap<>();

    public AIGraphImpl() {
    }

    @Override
    public long addVertex(String name) {
        Vertice v = new Vertice(name);
        vertices.put(v.ID, v);
        return v.ID;
    }

    @Override
    public boolean deleteVertex(long vID) {
        return vertices.remove(vID) != null;
        /*if (vertices.containsKey(vID)) {
            vertices.remove(vID);
            return true;
        }
        return false;*/
    }

    @Override
    public long addEdgeU(long v1ID, long v2ID) {
        Edge e = new EdgeU(v1ID, v2ID);
        edges.put(e.ID, e);
        return e.ID;
    }

    @Override
    public long addEdgeD(long v1ID, long v2ID) {
        Edge e = new EdgeD(v1ID, v2ID);
        edges.put(e.ID, e);
        return e.ID;
    }

    @Override       // TODO:
    public boolean deleteEdge(long eID) {
        return true;
        /*boolean success=false;
        for (Long eID : edges.keySet()) {
            Kante e = edges.get(eID);
            if (e.SourceVertex() == v1 && e.DestVertex() == v2
                    || !e.isDirected() && e.SourceVertex() == v2 && e.DestVertex() == v1) {
                // l��scht die Kanta von v1 nach v2 und auch die umgekehrte, falls die Kante ungerichtet ist.
                success = true;
                edges.remove(eID);
            }
        }
        return success;*/
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

    // es wird angenommen, dass inzidente Kanten alle Kanten
    // die auf irgendwelche Weise mit der Ecke verbunden sind
    // The assumption is
    @Override
    public List<Long> getIncident(long v1) {
        List<Long> eIDs = new ArrayList<>();
        for (Edge e : edges.values()) {
            if (e.getSrcVId() == v1 || e.getDestVId() == v1) {
                eIDs.add(e.ID);
            }
        }
        return eIDs;
    }

    @Override
    public List<Long> getAdjacent(long v1) {
        // gehe durch alle inzidenten Kanten durch und f��ge alle Ecken
        // au��er der eigenen Ecke einmalig hinzu.
        List<Long> vIDs = new ArrayList<>();
        List<Long> incident = getIncident(v1);
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
    public List<Long> getVertexes() {
        return new ArrayList<>(vertices.keySet());
    }

    @Override
    public List<Long> getEdges() {
        return new ArrayList<>(edges.keySet());
    }

    // SELEKTOREN
    // diese Attributsselektoren werden einfach weiterdeligiert
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
    public List<String> getAttrV(long v1) {
        return vertices.get(v1).getAttrV();
    }

    @Override
    public List<String> getAttrE(long e1) {
        return edges.get(e1).getAttrE();
    }

    // MUTATOREN
    // diese Attributsmutatoren werden einfach weiterdeligiert
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
    
    @Override public String toString(){
        String stracc = "";
        
        for(Long eID:edges.keySet()){
            stracc += edges.get(eID).toString()+"\n";
        }
        
        return stracc;
    }

    @Override 
    public List<String> getVertexNames() {
        List<String> l = new ArrayList<>();
        for(Vertice k : vertices.values())
            l.add(k.getName());
        return l;
    }
}
