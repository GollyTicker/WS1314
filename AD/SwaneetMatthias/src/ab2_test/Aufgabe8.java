package ab2_test;

import static org.junit.Assert.*;

import org.junit.Test;

import ab2_adts.*;

public class Aufgabe8 {

	private static int n = 15;
	
	@Test
	public void test() {
		
		GeneratorModule gm = new GeneratorModule(n, n, 0.05);
		gm.generateRandomMatrix();
		Matrix mList = new MatrixList(n, n);
		mList.copyFrom(gm.getMatrix());
		System.out.println(mList);
		System.out.println("AccessCount: " + mList.accessCount());
		
	}

}
