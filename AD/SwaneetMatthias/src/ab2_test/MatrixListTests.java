package ab2_test;

import static org.junit.Assert.*;

import org.junit.Test;

import ab2_adts.*;
import ab2_adts.matrix.Matrix;
import ab2_adts.matrix.MatrixArrayList;
import ab2_adts.matrix.MatrixList;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class MatrixListTests {

	@Test
	public void test_initialization_and_dimensions() {
		int m = 3;
		int n = 5;
		Matrix m1 = new MatrixList(m, n);

		assertEquals(m1.getM(), m);
		assertEquals(m1.getN(), n);

		// intial values are zero for all elements

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++)
				assertEquals(m1.get(i, j), 0.0, 0.01);
		}
	}

	@Test
	public void test_insertions() {
		int m = 3;
		int n = 5;
		Matrix m1 = new MatrixList(m, n);

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++)
				m1.insert(i, j, (double) (i * j));
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++)
				assertEquals(m1.get(i, j), (double) (i * j), 0.01);
		}
	}

	@Test
	public void test_equals() {
		int m = 3;
		int n = 5;
		Matrix m1 = new MatrixList(m, n);
		Matrix m2 = new MatrixList(m, n);

		assertEquals(m1, m1);
		assertEquals(m1, m2);
		assertEquals(m2, m1);
		assertEquals(m2, m2);

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				m1.insert(i, j, (double) (i * i + j));
				m2.insert(i, j, (double) (i * i + j));
			}
		}

		assertEquals(m1, m1);
		assertEquals(m1, m2);
		assertEquals(m2, m1);
		assertEquals(m2, m2);
	}

	@Test
	public void test_addition() {
		int m = 2;
		int n = 3;
		Matrix m1 = new MatrixList(m, n);
		Matrix m2 = new MatrixList(m, n);
		Matrix m3 = new MatrixList(m, n);

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++)
				m1.insert(i, j, (double) (2 * i + j));
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++)
				m2.insert(i, j, (double) (i * i * j * j));
		}

		// m1:
		// [
		// [3.0, 4.0, 5.0],
		// [5.0, 6.0, 7.0]
		// ]

		// m2:
		// [
		// [1.0, 4.0, 9.0],
		// [4.0, 16.0, 36.0]
		// ]

		// Erwartetes Ergebnis:
		// [
		// [4.0, 8.0, 14.0],
		// [9.0, 22.0, 43.0]
		// ]

		m3.insert(1, 1, 4.0);
		m3.insert(1, 2, 8.0);
		m3.insert(1, 3, 14.0);
		m3.insert(2, 1, 9.0);
		m3.insert(2, 2, 22.0);
		m3.insert(2, 3, 43.0);

		assertEquals(m1.add(m2), m3);
	}

	@Test
	public void test_mult_matrix() {
		int a = 2;
		int b = 3;
		int c = 4;
		Matrix m1 = new MatrixList(a, b);
		Matrix m2 = new MatrixList(b, c);
		Matrix m3 = new MatrixList(a, c);

		for (int i = 1; i <= a; i++) {
			for (int j = 1; j <= b; j++)
				m1.insert(i, j, (double) (2 * i - j));
		}

		for (int i = 1; i <= b; i++) {
			for (int j = 1; j <= c; j++)
				m2.insert(i, j, (double) (i * i * j * j));
		}

		// m1:
		// [
		// [1.0, 0.0, -1.0],
		// [3.0, 2.0, 1.0]
		// ]

		// m2:
		// [
		// [1.0, 4.0, 9.0, 16.0],
		// [4.0, 16.0, 36.0, 64.0],
		// [9.0, 36.0, 81.0, 144.0]
		// ]

		// Erwartetes Ergebnis:
		// [
		// [-8.0, -32.0, -72.0, -128.0],
		// [20.0, 80.0, 180.0, 320.0]
		// ]

		m3.insert(1, 1, -8.0);
		m3.insert(1, 2, -32.0);
		m3.insert(1, 3, -72.0);
		m3.insert(1, 4, -128.0);
		m3.insert(2, 1, 20.0);
		m3.insert(2, 2, 80.0);
		m3.insert(2, 3, 180.0);
		m3.insert(2, 4, 320.0);

		assertEquals(m1.mul(m2), m3);
	}

	@Test
	public void test_mult_matrix_associative() {
		int dim = 2;

		Matrix a = new MatrixList(dim, dim);
		Matrix b = new MatrixList(dim, dim);
		Matrix c = new MatrixList(dim, dim);

		a.insert(1, 1, -2.0);
		a.insert(1, 2, -3.0);
		a.insert(2, 1, 4.0);
		a.insert(2, 2, 5.0);

		b.insert(1, 1, -1.0);
		b.insert(1, 2, 4.0);
		b.insert(2, 1, 3.5);
		b.insert(2, 2, 0.0);

		c.insert(1, 1, 6.0);
		c.insert(1, 2, 1.5);
		c.insert(2, 1, -5.0);
		c.insert(2, 2, -1.2);

		// (A*B)*C = A*(B*C)
		assertEquals((a.mul(b)).mul(c), a.mul(b.mul(c)));

		// (B*A)*C = B*(A*C)
		assertEquals((b.mul(a)).mul(c), b.mul(a.mul(c)));
	}

	@Test
	public void test_mult_matrix_identity() {
		int dim = 3;

		Matrix a = new MatrixList(dim, dim);
		Matrix identity_matrix = new MatrixList(dim, dim);

		for (int i = 1; i <= dim; i++) {
			for (int j = 1; j <= dim; j++)
				a.insert(i, j, (double) (2 * i - j));
		}

		for (int i = 1; i <= dim; i++) {
			for (int j = 1; j <= dim; j++)
				identity_matrix.insert(i, j, (double) ((i == j) ? 1.0 : 0.0));
		}

		assertEquals(a.mul(identity_matrix), a);
		assertEquals(identity_matrix.mul(a), a);
	}

	@Test
	public void test_mult_skalar_identity() {
		int dim = 3;

		Matrix a = new MatrixList(dim, dim);
		double skalar = 1.0;

		for (int i = 1; i <= dim; i++) {
			for (int j = 1; j <= dim; j++)
				a.insert(i, j, (double) (2 * i - j));
		}

		assertEquals(a.mul(skalar), a);
	}

	@Test
	public void test_mult_skalar() {
		int a = 2;
		int b = 3;
		int skalar = -4;
		Matrix m1 = new MatrixList(a, b);
		Matrix m2 = new MatrixList(a, b);

		for (int i = 1; i <= a; i++) {
			for (int j = 1; j <= b; j++)
				m1.insert(i, j, (double) (2 * i - j));
		}

		for (int i = 1; i <= a; i++) {
			for (int j = 1; j <= b; j++)
				m2.insert(i, j, (double) ((2 * i - j) * skalar));
		}

		assertEquals(m1.mul(skalar), m2);
	}

	@Test
	public void test_pow() {
		int dim = 3;
		Matrix m1 = new MatrixList(dim, dim);
		Matrix m2 = new MatrixList(dim, dim);
		Matrix m3 = new MatrixList(dim, dim);

		m1.insert(1, 1, 1.0);
		m1.insert(1, 2, 2.0);
		m1.insert(1, 3, 3.0);

		m1.insert(2, 1, 4.0);
		m1.insert(2, 2, 5.0);
		m1.insert(2, 3, 6.0);

		m1.insert(3, 1, 7.0);
		m1.insert(3, 2, 8.0);
		m1.insert(3, 3, 9.0);

		m2.insert(1, 1, 30.0);
		m2.insert(1, 2, 36.0);
		m2.insert(1, 3, 42.0);

		m2.insert(2, 1, 66.0);
		m2.insert(2, 2, 81.0);
		m2.insert(2, 3, 96.0);

		m2.insert(3, 1, 102.0);
		m2.insert(3, 2, 126.0);
		m2.insert(3, 3, 150.0);

		m3.insert(1, 1, 194251120.0);
		m3.insert(1, 2, 238678984.0);
		m3.insert(1, 3, 283106848.0);

		m3.insert(2, 1, 439902154.0);
		m3.insert(2, 2, 540513739.0);
		m3.insert(2, 3, 641125324.0);

		m3.insert(3, 1, 685553188.0);
		m3.insert(3, 2, 842348494.0);
		m3.insert(3, 3, 999143800.0);

		m3.copyFrom(m3.mul(177147));

		assertEquals(m1, m1.powFast(1));
		assertEquals(m2, m1.powFast(2));
		assertEquals(m3, m1.powFast(12));

		assertEquals(m1, m1.pow(1));
		assertEquals(m2, m1.pow(2));
		assertEquals(m3, m1.pow(12));
	}

}
