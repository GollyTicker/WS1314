/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.Charset;

import GKA_A1.GKA_IMPL.AIGraph;
import GKA_A1.GKA_IMPL.AIGraphImpl;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class JavaParser {

	private String src = "";
	private boolean isDirected;

	public JavaParser(String src, boolean isDirected) {
		this.src = src;
		this.isDirected = isDirected;
	}

	public AIGraph createGraph() {
		if (isDirected)
			System.out.println("isDirected!");
		else
			System.out.println("isUndirected!");
		AIGraph graph = new AIGraphImpl();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(this.src));
			String line = null;
			line = UTF8EncodedString(br.readLine());
			while (line != null && line != "") {
				if (!line.startsWith("#")) {
					String[] s = line.split(",");
					if (s.length <= 2) {
						break;
					}
					long v1ID = graph.addVertex(s[0]);
					long v2ID = graph.addVertex(s[1]);
					long eID = graph.addEdgeU(v1ID, v2ID);
					Integer edge = Integer.parseInt(s[2]);
					graph.setValE(eID, "km", edge);
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
