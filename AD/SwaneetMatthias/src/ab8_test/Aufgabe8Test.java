package ab8_test;

import static org.junit.Assert.*;
import static ab8_adts.Aufgabe8.*;

import org.junit.Test;

import ab1_adts.ListImpl.IList;
import ab1_adts.ListImpl.MLinkedList;
import ab1_adts.ListImpl.MListIterator;

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
			assertTrue(randSortedList.isSorted());
		}
	}

	// Iterator Test
	@Test
	public void tesIterator() {
		for (int n = 0; n < 15; n++) {
			MLinkedList<Integer> ls = randomSortedListOfLength(n, STARTLIM, RANDOM_MAXSTEP);
			MListIterator<Integer> it = new MListIterator<Integer>(ls);
			int i = 0;
			while(it.hasNext()){
				assertEquals(ls.get(i), it.next());
				i+=1;
			}
		}
	}	
	/*// Aufgabe 8.4 und Aufgabe 8.5 Tests
	@Test
	public void testMerge() {
		for (int n = 0; n < 30; n++) {
			IList<Integer> rSortedLs1 = randomSortedListOfLength(n, STARTLIM, RANDOM_MAXSTEP);
			IList<Integer> rSortedLs2 = randomSortedListOfLength(n, STARTLIM, RANDOM_MAXSTEP);
			IList<Integer> mergedSorted = merge(rSortedLs1, rSortedLs2);
			assertTrue(mergedSorted.isSorted());
			
		}
	}*/

}
