package A4_test;

import org.junit.Test;

import GKA_A1.IAIGraph;
import GraphUtils.JavaParser;
import GraphUtils.WhichPath;

public class TrailProblemTest {
	
	private String distAttr = "km";
	private IAIGraph yolo;
	
	public TrailProblemTest() {
	}
	
	@Test
	public void NearestInsertionTest1(){
		loadGraph("graph30.graph");
		// this graph is the graph the algorithm is presented in the book with.
		// write here call of nearest inserteion algorithm and its test case
		
		// List<> // liste der Kreisknoten
		
	}

	@Test
	public void NearestInsertionTest2(){
		loadGraph("graph31.graph");
		// this graph is the graph used in the exercises for the nearest insertion algorithm
		// write here call of nearest inserteion algorithm and its test case
	}
	
	private void loadGraph(String graphname) {
		String path = WhichPath.getPath();
		JavaParser jp = new JavaParser(path + graphname, distAttr);
		this.yolo = jp.createGraph();
		System.out.println("\n<======= New Test =======>\n Input Graph: "
				+ yolo);
	}
}
