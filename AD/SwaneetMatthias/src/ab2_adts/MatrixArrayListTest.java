package ab2_adts;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatrixArrayListTest {

	@Test
	public void Matrix_Array_List_Test() {
		Matrix m = new MatrixArrayList(2, 2);
		m.insert(0, 0, 1);
		m.insert(0, 1, 1);
		m.insert(1, 0, 1);
	}

}
