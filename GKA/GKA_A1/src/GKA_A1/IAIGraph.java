/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GKA_A1;

/**
 *
 * @author Swaneet Sahoo, Matthias Nitsche
 */
import java.util.Set;

public interface IAIGraph {
    
    // Constructors
    long addVertex(String newItem);
    
    boolean deleteVertex(long vId);
    
    long addEdgeU(long v1Id, long v2Id);
     
    long addEdgeD(long v1Id, long v2Id);
    
    boolean deleteEdge(long eId);
    
    // Selectors
    boolean isEmpty();
    
    long getSource(long eId);
    
    long getTarget(long eId);
    
    Set<Long> getIncident(long vId);
    
    Set<Long> getAdjacent(long vId);
    
    Set<Long> getVertexes();
    
    Set<Long> getEdges();
    
    Set<String> getVertexNames();
    
    long getVertexByName(String name);
    
    // Selectors
    int getValE(long eId, String attr);
    
    int getValV(long vId, String attr);
    
    String getStrE(long eId, String attr);
    
    String getStrV(long vId, String attr);
    
    Set<String> getAttrV(long vId);
    
    Set<String> getAttrE(long eId);
    
    // Mutators
    void setValE(long eId, String attr, int val);
    
    void setValV(long vId, String attr, int val);
    
    void setStrE(long eId, String attr, String val);
    
    void setStrV(long vId, String attr, String val);
    
}
