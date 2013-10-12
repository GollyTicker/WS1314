package ab2_test;

import static org.junit.Assert.*;

import org.junit.Test;

import ab2_adts.*;

public class Aufgabe8 {

	private static int n = 15;
	
	@Test
	public void test() {
		
		// als Dereferenzierung zaehlen nur Zugriffe mit get(...) da das insert() ja sich den gespeicherten
		// Wert nicht anguckt. daher zaehlen wir sie nicht als Zugriffe
		
		GeneratorModule gm = new GeneratorModule(n, n, 0.05);
		gm.generateRandomMatrix();
		Matrix mList = new MatrixList(n, n);
		mList.resetAccessCount();
		mList.copyFrom(gm.getMatrix());
		System.out.println("AccessCount: " + mList.accessCount());
		System.out.println(mList);
		System.out.println("AccessCount nach dem toString(): " + mList.accessCount());
		
	}

}
