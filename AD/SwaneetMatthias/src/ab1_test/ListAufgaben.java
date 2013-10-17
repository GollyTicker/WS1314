package ab1_test;

import ab1_adts.ListImpl.MLinkedList;
import ab1_adts.statmodule.AverageVariance;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */

public class ListAufgaben {

	private static int NoOfElements = 15;
	private static MLinkedList<Integer> mll;

	public static void main(String[] args) {

//		aufgabe5();
//
//		aufgabe6();
//
//		aufgabe7();

		aufgabe8();

	}

	private static void aufgabe5() { // Aufgabe 5:

		mll = new MLinkedList<>();
		System.out.println("Before inserting elements:");
		mll.printCount();
		for (int n = 0; n < NoOfElements; n++) {
			mll.cons(n);
			System.out.println("After inserting " + n + "th element:");
			mll.printCount();
		}
		System.out.println("After all elements:");
		mll.printCount();
	}

	private static void aufgabe6() { // Aufgabe 6:

		mll = new MLinkedList<>(); // reseting the list

		// measuring the time for NoOfElements many elements
		long start = System.nanoTime();
		for (int n = 0; n < NoOfElements; n++) {
			mll.cons(n);
		}
		long duration = System.nanoTime() - start;
		System.out.println(NoOfElements + " elements need: " + duration
				+ " ns.");
		mll.printCount();

		mll = new MLinkedList<>(); // reset

		// repeating with 100 elements now
		NoOfElements = NoOfElements * 10;
		start = System.nanoTime();
		for (int n = 0; n < NoOfElements; n++) {
			mll.cons(n);
		}
		duration = System.nanoTime() - start;
		System.out.println(NoOfElements + " elements need: " + duration
				+ " ns.");
		mll.printCount();
	}

	private static void aufgabe7() { // Aufgabe 7:
		mll = new MLinkedList<>(); // reset

		// because of the implementation/interpretation of insert(elem,n)
		// insert needs an (n+1) element for the new element to put inbetween n
		// and (n+1).
		// therefore we begin with an initial element, and then always insert
		// the new element
		// at the forelast position
		NoOfElements = 10;
		mll.cons(-1);
		for (int n = 0; n < NoOfElements; n++) {
			mll.insert(n, n);
		}
		mll.printCount();

		// now with 10x many elements

		mll = new MLinkedList<>(); // reset

		NoOfElements = NoOfElements * 10;
		mll.cons(-1);
		for (int n = 0; n < NoOfElements; n++) {
			mll.insert(n, n);
		}
		mll.printCount();
	}

	private static void aufgabe8() {// Aufgabe 8:
		AverageVariance zeitmess = new AverageVariance();
		for (int t = 50; t > 0; t--) { // repeat this t-times
			NoOfElements = 104; // repeat with 52 elements
			mll = new MLinkedList<>();
			mll.cons(0);
			for (int i = 0; i < NoOfElements; i++) {
				int pos = (int) Math.floor(Math.random() * i);
				mll.insert(i, pos);
			}
			zeitmess.addValue(mll.getAccessCount());
		}
		System.out.println(zeitmess.getAverage());
		System.out.println(zeitmess.getVariance());

	}
}
