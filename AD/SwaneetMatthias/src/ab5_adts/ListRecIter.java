package ab5_adts;

import static org.junit.Assert.*;

import org.junit.Test;

import ab5_adts.ListRec.IList;
import ab5_adts.ListRec.MLinkedList;

public class ListRecIter {

	IList<Integer> itr;
	IList<Integer> rec;

	@Test
	public void test_both() {
		itr = new MLinkedList<>();
		rec = new MLinkedList<>();

		// both lists are empty
		shouldBeSame();

		// cons 1. are both still same?
		cons(1);
		shouldBeSame();

		// repeat again
		cons(2);
		shouldBeSame();

		// does the tail work?
		tail();
		shouldBeSame();

		// insert 3 at index 0 and check
		insert(3, 0);
		assertEquals((Integer) 3, itr.get(0));
		assertEquals((Integer) 3, rec.get(0));
		shouldBeSame();

		// repeat with 4
		insert(4, 0);
		assertEquals((Integer) 4, itr.get(0));
		assertEquals((Integer) 4, rec.get(0));
		shouldBeSame();

		// insert in-between (btw, inserting at the end is not allowed)
		insert(5, 2);
		shouldBeSame();

		// insert in-between (btw, inserting at the end is not allowed)
		insert(6, 2);
		shouldBeSame();

		out();

	}

	// test tail negative
	@Test(expected = java.lang.IndexOutOfBoundsException.class)
	public void test_no_tail_rec() {
		itr = new MLinkedList<>();
		rec = new MLinkedList<>();
		tail();
	}

	// test tail negative
	@Test(expected = java.lang.IndexOutOfBoundsException.class)
	public void test_no_tail_itr() {
		itr = new MLinkedList<>();
		rec = new MLinkedList<>();
		tail();
	}

	private void shouldBeSame() {
		assertEquals(itr.length(), rec.length());
		assertEquals(itr, rec);
	}

	private void tail() {
		itr.tail();
		rec.tail();
	}

	private void cons(int i) {
		itr.cons(i);
		rec.cons(i);
	}

	private void insert(int i, int index) {
		itr.insertIter(i, index);
		rec.insert(i, index);
	}

	private void out() {
		System.out.println("Iter: " + itr);
		System.out.println("Rec: " + rec);
	}

}
