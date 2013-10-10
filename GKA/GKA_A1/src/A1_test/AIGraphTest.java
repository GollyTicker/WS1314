package A1_test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;

import static org.junit.Assert.*;
import GKA_A1.GKA_IMPL.AIGraph;
import GraphUtils.JavaParser;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class AIGraphTest {
	
	private AIGraph yolo;
	
	public AIGraphTest() {
			JavaParser jp = new JavaParser(
					"C:/Users/Swaneet/github/WS1314/GKA/graphs/graph1.graph");
			yolo = jp.createGraph();
	}


	@Test
	public void test_source_target() {
		
		System.out.println(yolo);
		assertTrue(yolo.getVertexNames().contains("Augsburg"));
		assertTrue(!yolo.isEmpty());
		
		int augsburgID = 0; // Augsburg (src), eId1 = 0, 70km
		int munichID = 1; // Munich (target)
		
		// Even though the graph itself is undirected, the interface requires
		// .....
		
		assertEquals(augsburgID, yolo.getSource(0));
		assertNotSame(munichID, yolo.getSource(0));
		assertEquals(munichID, yolo.getTarget(0));
		assertNotSame(augsburgID, yolo.getTarget(0));
		
	}
	
	@Test
	public void test_attributes(){
		// Setter and Getter for EdgeValues
		yolo.setValE(0, "abcd", 21);
		yolo.setValE(0, "miles", 15);
		assertEquals(yolo.getValE(0, "miles"), 15);
		assertEquals(yolo.getValE(0, "abcd"), 21);
		yolo.setValE(0, "miles", 14);
		assertEquals(yolo.getValE(0, "miles"), 14);
		
		
		// Setter and Getter for EdgeStrings
		yolo.setStrE(0, "strAttri", "hallo");
		assertEquals(yolo.getStrE(0, "strAttri"), "hallo");
		yolo.setStrE(0, "strAttri", "hallo2");
		assertEquals(yolo.getStrE(0, "strAttri"), "hallo2");
		
		// Setter and Getter for VertexValues
		yolo.setValV(0, "balblaattribute", 24);
		assertEquals(yolo.getValV(0, "balblaattribute"), 24);
		yolo.setValV(0, "balblaattribute", 23);
		assertEquals(yolo.getValV(0, "balblaattribute"), 23);
		
		// Setter and Getter for VertexStrings
		yolo.setStrV(0, "balblaattribute", "hallo");
		assertEquals(yolo.getStrV(0, "balblaattribute"), "hallo");
		yolo.setStrV(0, "balblaattribute", "hallo2");
		assertEquals(yolo.getStrV(0, "balblaattribute"), "hallo2");
		
		// list of attributes for edges
		assertEquals(yolo.getAttrE(0).toString(),"[strAttri, miles, abcd, km]");
		assertEquals(yolo.getAttrE(1).toString(),"[km]");
		
		// list of attributes for vertices
		assertEquals(yolo.getAttrV(0).toString(),"[balblaattribute]");
		assertEquals(yolo.getAttrV(1).toString(),"[]");
	}
	
	@Test
	public void test_(){
		
		// TODO tests fuer erstere Selektoren und Konstruktoren
		
	}
}