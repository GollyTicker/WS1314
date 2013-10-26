/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import GKA_A1.IAIGraph;
import GKA_A1.AIGraph;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class JavaParser {

	private final static String UNDIRECTED = "ungerichtet";
	private final static String DIRECTED = "gerichtet";

	private String src = "";
	private String direction = "";
	private String attribute = "";
	private IAIGraph graph;

	public JavaParser(String src, String attribute) {
		this.src = src;
		this.attribute = attribute;
	}

	public IAIGraph createGraph() {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(this.src));

			// Files are not encoded so you have to do it manually
			String line1 = UTF8EncodedString(br.readLine());
			this.direction = getDirection(line1);
			System.out.println(direction);
			initGraph();

			String line = UTF8EncodedString(br.readLine());
			while (line != null && line != "") {
				String[] srcDestEdge = line.split(",");
				// if there is no correct split: src, dest, edge break out of
				// while
				if (srcDestEdge.length <= 2) {
					break;
				}
				// Method to add src dest and edge into Graph
				addToGraph(srcDestEdge);
				// read in next line
				line = UTF8EncodedString(br.readLine());
			}

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (java.io.IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (java.io.IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return graph;
	}

	public void initGraph() {
		this.graph = new AIGraph(this.direction.equals(UNDIRECTED) ? false
				: true);
	}

	// Split current direction form top of the line (0)
	public String getDirection(String line) {
		String direct = (line.split("#")[1]).trim();
		directionOrThrow(direct);
		return direct;
	}

	private void directionOrThrow(String direct) {
		boolean dire = direct.equals(UNDIRECTED) || direct.equals(DIRECTED);
		if (!dire)
			try {
				throw new IOException(
						"No Direction Found at first line of given File!");
			} catch (IOException e) {
			}
	}

	// Encode a String to UTF-8
	private String UTF8EncodedString(String line) {
		if (line != null) {
			return new String(line.getBytes(), Charset.forName("UTF-8"));
		}
		return "None";
	}

	// Refactored Method from the while loop for clarity
	private void addToGraph(String[] s) {
		long vID1 = this.graph.addVertex(s[0]);
		long vID2 = this.graph.addVertex(s[1]);
		addEdgeGraph(vID1, vID2, s[2]);

	}

	private void addEdgeGraph(long v1ID, long v2ID, String edge) {
		long eId;
		if (direction.equals(UNDIRECTED)) {
			eId = this.graph.addEdgeU(v1ID, v2ID);
		} else if (direction.equals(DIRECTED)) {
			eId = this.graph.addEdgeD(v1ID, v2ID);
		} else {
			throw new NullPointerException("Not yet implemented!");
		}
		addIntegerEdge(eId, edge);
	}

	private void addIntegerEdge(long eId, String edge) {
		Integer newEdge = Integer.parseInt(edge);
		this.graph.setValE(eId, this.attribute, newEdge);
	}

}
