package ab2_test;

import static org.junit.Assert.*;

import org.junit.Test;

import ab2_adts.Matrix;
import ab2_adts.MatrixArray;
import ab2_adts.MatrixList;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class MatrixArrayTest {
	
	
	@Test
	public void test_initialization_and_dimensions() {
		int m = 3;
		int n = 5;
		Matrix m1 = new MatrixArray(m,n);
		
		assertEquals(m1.getM(),m);
		assertEquals(m1.getN(),n);
		
		// intial values are zero for all elements
		
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++)
				assertEquals(m1.get(i, j), 0.0, 0.01);
		}
	}
	
	@Test
	public void test_insertions() {
		int m = 3;
		int n = 5;
		Matrix m1 = new MatrixArray(m,n);
		
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++)
				m1.insert(i, j, (double)(i*j));
		}
		
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++)
				assertEquals(m1.get(i,j),(double)(i*j), 0.01);
		}
	}
	
	@Test
	public void test_equals(){
		int m = 3;
		int n = 5;
		Matrix m1 = new MatrixArray(m,n);
		Matrix m2 = new MatrixArray(m,n);

		assertEquals(m1,m1);
		assertEquals(m1,m2);
		assertEquals(m2,m1);
		assertEquals(m2,m2);

		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++){
				m1.insert(i, j, (double)(i*i+j));
				m2.insert(i, j, (double)(i*i+j));
			}
		}

		assertEquals(m1,m1);
		assertEquals(m1,m2);
		assertEquals(m2,m1);
		assertEquals(m2,m2);
	}
	
	@Test
	public void test_addition() {
		int m = 2;
		int n = 3;
		Matrix m1 = new MatrixArray(m,n);
		Matrix m2 = new MatrixArray(m,n);
		Matrix m3 = new MatrixArray(m,n);
		
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++)
				m1.insert(i, j, (double)(2*i+j));
		}
		
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++)
				m2.insert(i, j, (double)(i*i*j*j));
		}
		
		// m1:
//		[
//		[3.0, 4.0, 5.0],
//		[5.0, 6.0, 7.0]
//		]
		
		// m2:
//		[
//		[1.0, 4.0,  9.0],
//		[4.0, 16.0, 36.0]
//		]
		
		// Erwartetes Ergebnis:
//		[
//		[4.0, 8.0,  14.0],
//		[9.0, 22.0, 43.0]
//		]
		
		m3.insert(1, 1, 4.0);
		m3.insert(1, 2, 8.0);
		m3.insert(1, 3, 14.0);
		m3.insert(2, 1, 9.0);
		m3.insert(2, 2, 22.0);
		m3.insert(2, 3, 43.0);
		
		assertEquals(m1.add(m2),m3);
	}
	

	@Test
	public void test_mult_matrix() {
		int a = 2;
		int b = 3;
		int c = 4;
		Matrix m1 = new MatrixArray(a,b);
		Matrix m2 = new MatrixArray(b,c);
		Matrix m3 = new MatrixArray(a,c);
		
		for(int i = 1; i <= a; i++){
			for(int j = 1; j <= b; j++)
				m1.insert(i, j, (double)(2*i-j));
		}
		
		for(int i = 1; i <= b; i++){
			for(int j = 1; j <= c; j++)
				m2.insert(i, j, (double)(i*i*j*j));
		}
		
		// m1:
//		[
//		[1.0, 0.0, -1.0],
//		[3.0, 2.0, 1.0]
//		]
		
		// m2:
//		[
//		[1.0, 4.0,  9.0,  16.0],
//		[4.0, 16.0, 36.0, 64.0],
//		[9.0, 36.0, 81.0, 144.0]
//		]
		
		// Erwartetes Ergebnis:
//	    [
//	    [-8.0, -32.0, -72.0, -128.0],
//	    [20.0, 80.0,  180.0, 320.0]
//	    ]
		
		
		m3.insert(1, 1, -8.0);
		m3.insert(1, 2, -32.0);
		m3.insert(1, 3, -72.0);
		m3.insert(1, 4, -128.0);
		m3.insert(2, 1, 20.0);
		m3.insert(2, 2, 80.0);
		m3.insert(2, 3, 180.0);
		m3.insert(2, 4, 320.0);

		
		assertEquals(m3,m1.mul(m2));
	}
	

	@Test
	public void test_mult_skalar() {
		int a = 2;
		int b = 3;
		int skalar = -4;
		Matrix m1 = new MatrixArray(a,b);
		Matrix m2 = new MatrixArray(a,b);
		
		for(int i = 1; i <= a; i++){
			for(int j = 1; j <= b; j++)
				m1.insert(i, j, (double)(2*i-j));
		}
		
		for(int i = 1; i <= a; i++){
			for(int j = 1; j <= b; j++)
				m2.insert(i, j, (double)((2*i-j) * skalar));
		}
		
		
		//System.out.println(m2);
		assertEquals(m2,m1.mul(skalar));
	}
	
	@Test
	public void test_pow() {
		int dim = 3;
		Matrix m1 = new MatrixList(dim,dim);
		Matrix m2 = new MatrixList(dim,dim);

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
		
		
		assertEquals(m1,m1.pow(1));
		
		assertEquals(m2,m1.pow(2));
	}
}
