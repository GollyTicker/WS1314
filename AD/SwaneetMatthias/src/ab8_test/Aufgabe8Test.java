package ab8_test;

import static org.junit.Assert.*;
import static ab8_adts.Aufgabe8.*;

import org.junit.Test;

import ab1_adts.ListImpl.IList;
import ab1_adts.ListImpl.MLinkedList;

public class Aufgabe8Test {

	private final int RANDOM_MIN = -100;
	private final int RANDOM_MAX = 200;
	private final int RANDOM_MAXSTEP = 56;
	private final int STARTLIM = 1337;
	
	// Aufgabe 8.1 Tests
	@Test
	public void testSorted1() {
		IList<Integer> mll = new MLinkedList<>();
		assertEquals(true, mll.isSorted());
		for (int n = 15; n > 0; n--) {
			mll.cons(n);
		}
		assertEquals(true, mll.isSorted());
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

	// Aufgabe 8.2 Tests
	@Test
	public void testRandomListOfLengthN() {
		for (int n = 0; n < 30; n++) {
			IList<Integer> randList = randomListOfLength(n, RANDOM_MIN, RANDOM_MAX);
			assertEquals(randList.length(), n);
		}
	}
	
	// Aufgabe 8.3 Tests
	@Test
	public void testRandomSortedListOfLengthN() {
		for (int n = 0; n < 30; n++) {
			IList<Integer> randSortedList = randomSortedListOfLength(n, STARTLIM, RANDOM_MAXSTEP);
			System.out.println(randSortedList);
			assertTrue(randSortedList.isSorted());
		}
	}

}
