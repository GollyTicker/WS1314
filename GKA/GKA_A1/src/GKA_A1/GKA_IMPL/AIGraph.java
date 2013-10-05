/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GKA_A1.GKA_IMPL;

/**
 *
 * @author Swaneet Sahoo, Matthias Nitsche
 */
import java.util.List;

public interface AIGraph {
    
    // Constructors
    long addVertex(String newItem);
    
    boolean deleteVertex(long vID);
    
    long addEdgeU(long v1ID, long v2ID);
     
    long addEdgeD(long v1ID, long v2ID);
    
    boolean deleteEdge(long eID);
    
    // Selectors
    boolean isEmpty();
    
    long getSource(long eID);
    
    long getTarget(long eID);
    
    List<Long> getIncident(long vID);
    
    List<Long> getAdjacent(long vID);
    
    List<Long> getVertexes();
    
    List<Long> getEdges();
    
    List<String> getVertexNames();
    
    // Selectors
    int getValE(long eID, String attr);
    
    int getValV(long vID, String attr);
    
    String getStrE(long eID, String attr);
    
    String getStrV(long vID, String attr);
    
    List<String> getAttrV(long vID);
    
    List<String> getAttrE(long eID);
    
    // Mutators
    void setValE(long eID, String attr, int val);
    
    void setValV(long vID, String attr, int val);
    
    void setStrE(long eID, String attr, String val);
    
    void setStrV(long vID, String attr, String val);
    
}
