/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.Charset;

import GKA_A1.AIGraph;
import GKA_A1.AIGraphImpl;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class JavaParser {

	private String src = "";
	private String direction = "";

	private final static String UNDIRECTED = "ungerichtet";
	private final static String DIRECTED = "gerichtet";

	public JavaParser(String src) {
		this.src = src;
	}

	public AIGraph createGraph() {
		AIGraph graph = new AIGraphImpl();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(this.src));
			String line = null;
			line = UTF8EncodedString(br.readLine());
			direction = getDirection(line);
			System.out.println(direction);
			while (line != null && line != "") {
				if (!line.startsWith("#")) {
					String[] s = line.split(",");
					if (s.length <= 2) {
						break;
					}
					long v1ID = graph.addVertex(s[0]);
					long v2ID = graph.addVertex(s[1]);
					long eId;

					if (direction.equals(UNDIRECTED)) {
						eId = graph.addEdgeU(v1ID, v2ID);
					} else if (direction.equals(DIRECTED)) {
						eId = graph.addEdgeD(v1ID, v2ID);
					} else {
						throw new NullPointerException("Not yet implemented!");
					}

					Integer edge = Integer.parseInt(s[2]);
					graph.setValE(eId, "km", edge);		// specific to this graph with kilometers
				}
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

	public String getDirection(String line) {
		try {
			return (line.split("#")[1]).trim();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		return "";
	}

	private String UTF8EncodedString(String line) {
		if (line != null) {
			return new String(line.getBytes(), Charset.forName("UTF-8"));
		}
		return "";
	}

	void puts(String[] s) {
		String stracc = "";
		for (String s_ : s) {
			stracc += s_ + ",";
		}
		System.out.println("Splited: " + stracc);
	}
}
