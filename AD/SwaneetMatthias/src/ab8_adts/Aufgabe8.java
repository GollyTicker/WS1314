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
	public static IList<Integer> randomSortedListOfLength(int n,int startLim, int maxStep) {
		IList<Integer> ls = new MLinkedList<>();
		int myRand = (int) (startLim*Math.random());
		for (int i = 0; i < n; i++) {
			ls.cons(myRand);
			myRand -= (int) (maxStep*Math.random());
		}
		return ls;
	}

}
