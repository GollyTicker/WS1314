/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphUtils;

import GKA_A1.AIGraph;

public class GraphMain {
	
	public static void main(String[] args) {
//		String gitSrc1 = "/Users/matthias/dev/WS1314/GKA/graphs/graph1.graph";
//		graphBySrc(gitSrc1);
//		String gitSrc3 = "/Users/matthias/dev/WS1314/GKA/graphs/graph3.graph";
//		graphBySrc(gitSrc3);
	}

	public static void graphBySrc(String src) {
		JavaParser jp = new JavaParser(src);
		AIGraph yolo = jp.createGraph();
		System.out.println(yolo.toString());
	}
}
