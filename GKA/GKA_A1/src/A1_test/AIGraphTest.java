package A1_test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import GKA_A1.IAIGraph;
import GKA_A1.AIGraph;
import GraphUtils.JavaParser;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class AIGraphTest {

	private String swaneetpath = "C:/Users/Swaneet/github/WS1314/GKA/graphs/";
	private String matzepath = "/Users/matthias/dev/WS1314/GKA/graphs/";
	private String path = matzepath;
	private String graphname = "graph1.graph";

	private IAIGraph yolo;
	private IAIGraph swag = new AIGraph();

	public AIGraphTest() {
		JavaParser jp = new JavaParser(path + graphname, "distance");
		yolo = jp.createGraph();
	}

	@Test
	public void test_creation() {

		System.out.println(yolo);
		assertTrue(yolo.getVertexNames().contains("Augsburg"));
		assertTrue(!yolo.isEmpty());

		int augsburgID = 0; // Augsburg (src), eId1 = 0, 70km
		int munichID = 1; // Munich (target)

		assertEquals(augsburgID, yolo.getSource(0));
		assertNotSame(munichID, yolo.getSource(0));
		assertEquals(munichID, yolo.getTarget(0));
		assertNotSame(augsburgID, yolo.getTarget(0));

	}

	@Test
	public void test_selectors() {
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
		assertEquals(
				yolo.getAttrE(0),
				new HashSet<>(Arrays.asList("strAttri", "miles", "abcd",
						"distance")));
		assertEquals(yolo.getAttrE(1), new HashSet<>(Arrays.asList("distance")));

		// list of attributes for vertices
		assertEquals(yolo.getAttrV(0),
				new HashSet<>(Arrays.asList("balblaattribute")));
		assertEquals(yolo.getAttrV(1), new HashSet<>(Arrays.asList()));
	}

	@Test
	public void test_mutation() {

		assertTrue(swag.isEmpty());
		long v1 = swag.addVertex("Matthias");
		assertTrue(!swag.isEmpty());
		long e1 = swag.addEdgeD(v1, v1);
		assertEquals(v1, swag.getSource(e1));
		assertEquals(v1, swag.getTarget(e1));
		assertEquals(new HashSet<>(Arrays.asList(v1)), swag.getIncident(v1));

		long v2 = swag.addVertex("Swaneet");
		long e2 = swag.addEdgeD(v1, v2);
		assertEquals(new HashSet<>(Arrays.asList(v2)), swag.getAdjacent(v1));

		assertEquals(new HashSet<>(Arrays.asList(v1, v2)), swag.getVertexes());
		assertEquals(new HashSet<>(Arrays.asList(e1, e2)), swag.getEdges());
		assertEquals(new HashSet<>(Arrays.asList("Swaneet", "Matthias")),
				swag.getVertexNames());

		assertEquals(new HashSet<>(Arrays.asList(v1, v2)), swag.getVertexes());
		assertEquals(new HashSet<>(Arrays.asList(e1, e2)), swag.getEdges());
		assertEquals(v1, swag.getVertexByName("Matthias"));

		swag.deleteVertex(v2);
		assertEquals(new HashSet<>(Arrays.asList(v1)), swag.getVertexes());
		assertEquals(new HashSet<>(Arrays.asList(e1)), swag.getEdges());
		assertEquals(v1, swag.getVertexByName("Matthias"));
		assertEquals(-1, swag.getVertexByName("Swaneet"));

		swag.deleteEdge(e1);
		assertEquals(new HashSet<>(Arrays.asList(v1)), swag.getVertexes());
		assertEquals(new HashSet<>(Arrays.asList()), swag.getEdges());
		assertEquals(v1, swag.getVertexByName("Matthias"));

		// Equals
		JavaParser jp1 = new JavaParser(path + graphname, "distance");
		IAIGraph yolo1 = jp1.createGraph();
		JavaParser jp2 = new JavaParser(path + graphname, "distance");
		IAIGraph yolo2 = jp2.createGraph();
		assertEquals(yolo1, yolo2);
		yolo2.deleteVertex(0);
		assertNotSame(yolo1, yolo2);
	}

}