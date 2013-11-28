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
		// If one of the two lists is empty return the other one
		if (l1.isempty())
			return l2;
		if (l2.isempty())
			return l1;

		IList<Integer> merged = new MLinkedList<Integer>();

		// Get the first Node as node object from both lists
		INode<Integer> mint1 = l1.getFirst();
		INode<Integer> mint2 = l2.getFirst();

		// while the current node is not empty iterate over both lists and
		// append
		while (mint1 != null && mint2 != null) {
			if (mint1.getElem() < mint2.getElem()) {
				merged.append(mint1.getElem());
				mint1 = mint1.getNext();
			} else {
				merged.append(mint2.getElem());
				mint2 = mint2.getNext();
			}
		}

		// The problem is that the while loop before could terminate before all
		// elements of both lists are appended
		// example: [1,2,3] [1,2,3,4] -> the while before terminates at idx=2,
		// the second list still has a four at idx=3. For both cases we have to
		// call next on the rest of the list until both are empty. In One case
		// the while terminates without iteration and the other will append all
		// of the elements.
		while (mint1 != null) {
			merged.append(mint1.getElem());
			mint1 = mint1.getNext();
		}

		while (mint2 != null) {
			merged.append(mint2.getElem());
			mint2 = mint2.getNext();
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
		INode<Integer> currNode = ls.getFirst();

		while (currNode != null) {
			if (ll.length() < pivotindex) {
				ll.append(currNode.getElem());
			} else {
				lr.append(currNode.getElem());
			}
			currNode = currNode.getNext();
		}

		return merge(divide(ll), divide(lr));
	}

	public static IList<Integer> MergeSort(IList<Integer> ls) {
		return divide(ls);
	}
}
