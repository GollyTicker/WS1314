package ab2_test;

import static org.junit.Assert.*;

import org.junit.Test;

import ab2_adts.*;

public class CopyFromTests {

	@Test
	public void test_copyFrom() {
		int m = 3;
		int n = 5;
		Matrix m1 = new MatrixList(m, n);

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++)
				m1.insert(i, j, (double) (i * i - j * 3));
		}

		// go through all implementations

		Matrix mList = new MatrixList(m, n);
		mList.copyFrom(m1);

		Matrix mArrayList = new MatrixArrayList(m, n);
		mArrayList.copyFrom(mList);

		Matrix mArray = new MatrixArray(m, n);
		mArray.copyFrom(mArrayList);
		
		Matrix mList2 = new MatrixList(m, n);
		mList2.copyFrom(mArray);

		// check the equalities
		assertEquals(m1, mList);
		assertEquals(m1, mArray);
		assertEquals(m1, mArrayList);
		assertEquals(m1, mList2);

		assertEquals(mList, mArray);
		assertEquals(mList, mArrayList);
		assertEquals(mList, mList2);

		assertEquals(mArray, mArrayList);
		assertEquals(mArray, mList2);

	}
}
