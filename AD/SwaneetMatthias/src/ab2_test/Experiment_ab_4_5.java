package ab2_test;

import static org.junit.Assert.*;

import org.junit.Test;

import ab2_adts.*;

// TODO: Matthias: update the javadoc because of the addition of copyFrom.
// TODO: delete the tests duplicates in ab2_adts
// TODO: blabla bluff

public class Experiment_ab_4_5 {
	
	
	// despite this file's name: this file contains Aufgabe 6
	
	private static int n = 60;		// don't insert numbers higher than ~100 if you don't have paitence
	
	// n = 60: waiting about a couple of seconds

	@Test
	public void testAufgabe6() {
		GeneratorModule gm = new GeneratorModule(n,n, 0.5);
		
		// the matrices A and B
		gm.generateRandomMatrix();
		Matrix a = gm.getMatrix();
		gm.generateRandomMatrix();
		Matrix b = gm.getMatrix();
		
		// MatrixList implementation
		Matrix mListA = new MatrixList(n, n);
		Matrix mListB = new MatrixList(n, n);
		mListA.copyFrom(a);
		mListB.copyFrom(b);
		
		
		// MatrixArray implementation
		Matrix mArrayA = new MatrixArray(n, n);
		Matrix mArrayB = new MatrixArray(n, n);
		mArrayA.copyFrom(a);
		mArrayB.copyFrom(b);
		

		// MatrixArrayList implementation
		Matrix mArrayListA = new MatrixArrayList(n, n);
		Matrix mArrayListB = new MatrixArrayList(n, n);
		mArrayListA.copyFrom(a);
		mArrayListB.copyFrom(b);
		
		
		// multiply all matrices
		
		
		Matrix mListProduct = mListA.mul(mListB);
		Matrix mArrayProduct = mArrayA.mul(mArrayB);
		Matrix mArrayListProduct = mArrayListA.mul(mArrayListB);
		
		
		assertEquals(mListProduct,mArrayProduct);
		assertEquals(mArrayProduct,mArrayListProduct);
		assertEquals(mArrayListProduct,mListProduct);
		
	}

}
