package ab2_adts;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatrixTesting {

	@Test
	public void MatrixArray_test() {

		double[][] d = { { 1, 2, 3, 4 }, { 1, 2, 3, 4 } };
		Matrix m1 = new MatrixArray(d);

		// Multiplication
		Matrix mulFactor = new MatrixArray(new double[][] { { 2, 4, 6, 8 },
				{ 2, 4, 6, 8 } });
		Matrix m2 = m1.mul(2);
		assertEquals(mulFactor, m2);
		assertNotSame(m1, m2);

		// Add Matrix
		Matrix addMatrix = new MatrixArray(new double[][] { { 2, 4, 6, 8 },
				{ 2, 4, 6, 8 } });
		Matrix m3 = m1.add(m1);
		assertEquals(addMatrix, m3);
		assertNotSame(m1, m3);

		// Multiplication Matrix
		double[][] d3 = { { 0, 1 }, { 1, 0 } };
		Matrix m6 = new MatrixArray(d3);
		Matrix m7 = m6.mul(m6);
		System.out.println(m7);
		assertEquals(m6, m7);

		double[][] d2 = { { 1, 2, 3, 4 }, { 1, 2, 3, 4 }, { 1, 2, 3, 4 },
				{ 1, 2, 3, 4 } };
		Matrix m4 = new MatrixArray(d2);
		Matrix m5 = m4.mul(m4);
		Matrix mulMatrix = new MatrixArray(new double[][] { { 1, 4, 9, 16 },
				{ 1, 4, 9, 16 }, { 1, 4, 9, 16 }, { 1, 4, 9, 16 } });
		assertEquals(mulMatrix, m5);
		assertNotSame(m4, m5);

	}

}
