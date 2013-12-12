/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GKA_A1;

import java.util.List;
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

	boolean deleteEdge(long v1Id, long v2Id);

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
	
	boolean isDirected();
	
	boolean edgeIsBetween(Long eId, Long v1Id, Long v2Id);
	
	public List<Long> getEdgesBetween(Long v1, Long v2);
	
	public Set<Long> getSourceTarget(Long e);

	// Mutators
	void setValE(long eId, String attr, int val);

	void setValV(long vId, String attr, int val);

	void setStrE(long eId, String attr, String val);

	void setStrV(long vId, String attr, String val);

}
