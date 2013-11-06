package ab5_adts;

import static org.junit.Assert.*;

import org.junit.Test;

import ab5_adts.ListRec.IList;
import ab5_adts.ListRec.MLinkedList;

public class ListRecIter {
	
	IList<Integer> itr = new MLinkedList<>();
	IList<Integer> rec = new MLinkedList<>();

	@Test
	public void test_both() {
		
		// TODO: make correct accessCount in insert(rec)
		
		shouldBeSame();
		
		cons(1);
		shouldBeSame();
		

		cons(2);
		shouldBeSame();
		
		out();
		tail();
		out();
		shouldBeSame();
		
		out();
		insert(3, 0);
		out();
		shouldBeSame();
		


		
		insert(4, 2);

		out();

		shouldBeSame();
		

//		l.insertIter(4, 2);
//		System.out.println(l);
//
//		l.cons(5);
//		System.out.println(l);
//
//		l.tail();
//		System.out.println(l);
//
//		l.cons(1);
//		l.cons(2);
//
//		l.insert(3, 0);
//		System.out.println(l);
//
//		l.insert(4, 2);
//		System.out.println(l);
//
//		l.cons(5);
//		System.out.println(l);
//
//		l.tail();
//		System.out.println(l);
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
		System.out.println("Iter: "+itr);
		System.out.println("Rec: "+rec);
	}

}
