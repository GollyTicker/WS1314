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

	public AIGraphTest() {
	}

	@Test
	public void test1() {
		JavaParser jp = new JavaParser(
				"C:\\Users\\Swaneet\\Desktop\\HAW\\graph_01.graph", true);
		AIGraph yolo = jp.createGraph();

		assertTrue(yolo.getVertexNames().contains("Augsburg"));
		assertTrue(!yolo.isEmpty());
		int augsburgID = 0; // Augsburg (src), eId1 = 0, 70km
		int munichID = 1; // Munich (target)
		assertEquals(augsburgID, yolo.getSource(0));
		assertNotSame(munichID, yolo.getSource(0));
		assertEquals(munichID, yolo.getTarget(0));
		assertNotSame(augsburgID, yolo.getTarget(0));

	}
}