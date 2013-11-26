package ab1_test;

import static org.junit.Assert.*;

import org.junit.Test;

import ab1_adts.ListImpl.IList;
import ab1_adts.ListImpl.MLinkedList;

public class LIstMergeSortTest {

	@Test
	public void test() {
		IList<Integer> mll = new MLinkedList<>();
		assertEquals(true, mll.isSorted());
		for (int n = 15; n > 0; n--) {
			mll.cons(n);
		}
		System.out.println(mll);
		assertEquals(true, mll.isSorted());
		System.out.println(mll);
		mll.cons(5);
		assertEquals(false, mll.isSorted());
		IList<Integer> mll2 = new MLinkedList<>();
		mll2.cons(1);
		assertEquals(true, mll2.isSorted());
		mll2.cons(0);
		assertEquals(true, mll2.isSorted());
		mll2.cons(5);
		assertEquals(false, mll2.isSorted());
	}

}
