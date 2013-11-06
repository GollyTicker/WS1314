package ab5_adts;

import ab5_adts.ListRec.IList;
import ab5_adts.ListRec.MLinkedList;

public class e {

	public static void main(String[] args) {
		IList<Integer> l = new MLinkedList<>();
		l.cons(1);
		l.cons(2);
		l.insertIter(3, 0);
		System.out.println(l);
		l.insertIter(4, 2);
		System.out.println(l);
	}
	
}
