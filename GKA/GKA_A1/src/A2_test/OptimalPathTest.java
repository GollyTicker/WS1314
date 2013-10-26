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
	private String graphname = "graph4.graph";

	private IAIGraph yolo;
	private IAIGraph swag = new AIGraph();

	public OptimalPathTest() {
		JavaParser jp = new JavaParser(path + graphname, "distance");
		yolo = jp.createGraph();
	}
	@Test
	public void test() {
		System.out.println(yolo.toMatrixU());
		System.out.println(yolo);
	}

}
