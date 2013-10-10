package ab2_adts;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatrixListTests {
	
	
	@Test
	public void test_initialization_and_dimensions() {
		int m = 3;
		int n = 5;
		Matrix m1 = new MatrixList(m,n);
		
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
		Matrix m1 = new MatrixList(m,n);
		
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
		Matrix m1 = new MatrixList(m,n);
		Matrix m2 = new MatrixList(m,n);

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
		Matrix m1 = new MatrixList(m,n);
		Matrix m2 = new MatrixList(m,n);
		Matrix m3 = new MatrixList(m,n);
		
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
//		[3.0,4.0,5.0],
//		[5.0,6.0,7.0]
//		]
		
		// m2:
//		[
//		[1.0,4.0,9.0],
//		[4.0,16.0,36.0]
//		]
		
		// Erwartetes Ergebnis:
//		[
//		[4.0, 8.0, 14.0],
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
		Matrix m1 = new MatrixList(a,b);
		Matrix m2 = new MatrixList(b,c);
		Matrix m3 = new MatrixList(a,c);
		
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
//		[3.0,4.0,5.0],
//		[5.0,6.0,7.0]
//		]
		
		// m2:
//		[
//		[1.0,4.0,9.0],
//		[4.0,16.0,36.0]
//		]
		
		// Erwartetes Ergebnis:
//		[
//		[4.0, 8.0, 14.0],
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

}
