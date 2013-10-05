package A1_test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import GKA_A1.JavaParser;
import org.junit.Test;
import static org.junit.Assert.*;
import GKA_A1.AIGraph;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author Swaneet
 */
public class AIGraphTest {

	public AIGraphTest() {
	}

	@Test
	public void test1() throws FileNotFoundException, IOException {
		// new AIGraph();
		assertTrue(true);

		JavaParser jp = new JavaParser(
				"C:\\Users\\Swaneet\\Desktop\\HAW\\graph_01.graph");
		AIGraph yolo = jp.createGraph();

		// System.out.println(yolo.toString());

		assertTrue(yolo.getVertexNames().contains("Augsburg"));
		assertTrue(!yolo.isEmpty());
		int augsburgID = 0; // Augsburg (src), eId1 = 0, 70km
		int munichID = 1; // M��nchen (target)
		assertEquals(augsburgID, yolo.getSource(0));
		assertNotSame(munichID, yolo.getSource(0));
		assertEquals(munichID, yolo.getTarget(0));
		assertNotSame(augsburgID, yolo.getTarget(0));

	}
}