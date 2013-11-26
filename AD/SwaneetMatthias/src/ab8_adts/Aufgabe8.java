package ab8_adts;

import ab1_adts.ListImpl.*;

public class Aufgabe8 {

	// Aufgabe 8.2
	public static IList<Integer> randomListOfLength(int n, int min, int max) {
		IList<Integer> ls = new MLinkedList<>();
		for (int i = 0; i < n; i++) {
			int myRand = (int) Math.round((min + (max - min) * Math.random()));
			ls.cons(myRand);
		}
		return ls;
	}

	// Aufgabe 8.3

	public static MLinkedList<Integer> randomSortedListOfLength(int n,
			int startLim, int maxStep) {
		MLinkedList<Integer> ls = new MLinkedList<>();
		int myRand = (int) (startLim * Math.random());
		for (int i = 0; i < n; i++) {
			ls.cons(myRand);
			myRand -= (int) (maxStep * Math.random());
		}
		return ls;
	}

	// Aufgabe 8.4
	// Aufgabe 8.4
	public static MLinkedList<Integer> merge(MLinkedList<Integer> l1,
			MLinkedList<Integer> l2) {
		if (l1.isempty())
			return l2;
		if (l2.isempty())
			return l1;

		MLinkedList<Integer> merged = new MLinkedList<Integer>();

		MListIterator<Integer> miter1 = new MListIterator<Integer>(l1);
		MListIterator<Integer> miter2 = new MListIterator<Integer>(l2);

		merged.cons(-1);

		Integer mint1 = miter1.next();
		Integer mint2 = miter2.next();

		while (mint1 != null && mint2 != null) {
			if (mint1 < mint2) {
				merged.insert(mint1, merged.length() - 1);
				mint1 = miter1.next();
			} else {
				merged.insert(mint2, merged.length() - 1);
				mint2 = miter2.next();

			}
		}
		while (mint1 != null) {
			merged.insert(mint1, merged.length() - 1);
			mint1 = miter1.next();
		}

		while (mint2 != null) {
			merged.insert(mint2, merged.length() - 1);
			mint2 = miter2.next();
		}

		merged.head();

		return merged;
	}

}