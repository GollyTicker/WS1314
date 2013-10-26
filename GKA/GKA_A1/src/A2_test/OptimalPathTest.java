package A2_test;

import static org.junit.Assert.*;

import org.junit.Test;

import GKA_A1.AIGraph;
import GKA_A1.IAIGraph;
import GraphUtils.JavaParser;

public class OptimalPathTest {

	private String swaneetpath = "C:/Users/Swaneet/github/WS1314/GKA/graphs/";
	private String matzepath = "/Users/matthias/dev/WS1314/GKA/graphs/";
	private String path = matzepath;
	
	private IAIGraph yolo;
	private IAIGraph swag;

	public OptimalPathTest() {
		JavaParser jp1 = new JavaParser(path + "graph4.graph", "distance");
		yolo = jp1.createGraph();
		JavaParser jp2 = new JavaParser(path + "graph3.graph", "distance");
		swag = jp2.createGraph();
	}
	@Test
	public void test() {
		// System.out.println(yolo.toMatrixU());
		// System.out.println(yolo);
		// System.out.println(swag.toMatrixD());
		// System.out.println(swag);
	}

}
