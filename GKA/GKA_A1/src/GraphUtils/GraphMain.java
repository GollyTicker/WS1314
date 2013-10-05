/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphUtils;

import GKA_A1.GKA_IMPL.AIGraph;

public class GraphMain {
	
	public static void main(String[] args) {
		String gitSrc = "";
		graphBySrc(gitSrc, true);
	}

	public static void graphBySrc(String src, boolean isDirected) {
		JavaParser jp = new JavaParser(src, isDirected);
		AIGraph yolo = jp.createGraph();
		System.out.println(yolo.toString());
	}
}
