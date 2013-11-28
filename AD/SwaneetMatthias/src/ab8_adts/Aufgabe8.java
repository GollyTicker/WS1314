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
			ls.append(myRand);
			myRand += (int) (maxStep * Math.random());
		}
		return ls;
	}

	// Aufgabe 8.4
	public static IList<Integer> merge(IList<Integer> l1, IList<Integer> l2) {
		if (l1.isempty())
			return l2;
		if (l2.isempty())
			return l1;

		IList<Integer> merged = new MLinkedList<Integer>();

		MListIterator<Integer> miter1 = new MListIterator<Integer>(l1);
		MListIterator<Integer> miter2 = new MListIterator<Integer>(l2);

		INode<Integer> mint1 = miter1.next();
		INode<Integer> mint2 = miter2.next();

		while (mint1 != null && mint2 != null) {
			if (mint1.getElem() < mint2.getElem()) {
				merged.append(mint1.getElem());
				mint1 = miter1.next();
			} else {
				merged.append(mint2.getElem());
				mint2 = miter2.next();

			}
		}
		// Todo comment
		while (mint1 != null) {
			merged.append(mint1.getElem());
			mint1 = miter1.next();
		}

		while (mint2 != null) {
			merged.append(mint2.getElem());
			mint2 = miter2.next();
		}

		return merged;
	}

	public static IList<Integer> divide(IList<Integer> ls) {
		
		if (ls.length() <= 1) {
			return ls;
		}

		IList<Integer> ll = new MLinkedList<>();
		IList<Integer> lr = new MLinkedList<>();

		int len = ls.length();
		int pivotindex = len / 2;
		MListIterator<Integer> iter = new MListIterator<>(ls);
		while (iter.hasNext()) {
			if (ll.length() < pivotindex) {
				ll.append(iter.next().getElem());
			} else {
				lr.append(iter.next().getElem());
			}
		}
		
		return merge(divide(ll), divide(lr));
	}

	public static IList<Integer> MergeSort(IList<Integer> ls) {
		return divide(ls);
	}
}
