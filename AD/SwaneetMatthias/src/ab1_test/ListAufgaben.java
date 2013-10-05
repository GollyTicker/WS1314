package ab1_test;

import ab1_adts.AverageVariance;
import ab1_adts.ListImpl.MLinkedList;

public class ListAufgaben {

    private static int NoOfElements = 15;

    public static void main(String[] args) {
        /*MLinkedList<Integer> mll = new MLinkedList<>(2);
         mll.cons(1);
         mll.cons(5);
         mll.cons(1);
         mll.cons(1);
         System.out.println(mll);
         mll.head();
         mll.head();
         System.out.println(mll);
         System.out.println(mll.get(mll.length()-1));*/

        // Aufgabe 3:

        MLinkedList<Integer> mll = new MLinkedList<>();
        System.out.println("Before inserting elements:");
        mll.printCount();
        for (int n = 0; n < NoOfElements; n++) {
            mll.cons(n);
            System.out.println("After inserting " + n + "th element:");
            mll.printCount();
        }
        System.out.println("After all elements:");
        mll.printCount();

        
        // the first elements need 1 Access
        // All following elements needs 2 Accesses.
        // Therefore with NoOfElements=15 we have: 1 + (15 - 1)*2 = 29 accesses


        // Aufgabe 4:

        mll = new MLinkedList<>();      // reseting the list

        // measuring the time for NoOfElements many elements
        long start = System.nanoTime();
        for (int n = 0; n < NoOfElements; n++) {
            mll.cons(n);
        }
        long duration = System.nanoTime() - start;
        System.out.println(NoOfElements + " elements need: " + duration + " ns.");
        mll.printCount();


        mll = new MLinkedList<>();      // reset

        // repeating with 150 elements now
        NoOfElements = NoOfElements * 10;
        start = System.nanoTime();
        for (int n = 0; n < NoOfElements; n++) {
            mll.cons(n);
        }
        duration = System.nanoTime() - start;
        System.out.println(NoOfElements + " elements need: " + duration + " ns.");
        mll.printCount();

        // Output:
        // 15 elements need: 6414 ns.
        // AccessCount: 29
        // 150 elements need: 62860 ns.
        // AccessCount: 299

        // 15 elements need: 7270 ns.
        // AccessCount: 29
        // 150 elements need: 65426 ns.
        // AccessCount: 299

        // 15 elements need: 6842 ns.
        // AccessCount: 29
        // 150 elements need: 59867 ns.
        // AccessCount: 299
        
        // These observations fit to a O(n)-Algorithm. Indeed, the time needed for
        // inserting elements at the front grows proportionaly to the number of elements.
        // 10x the elements needs approx. 10x the time.
        // The initial overhead can also be recognized.



        // Aufgabe 5:

        mll = new MLinkedList<>();      // reset

        // because of the implementation/interpretation of insert(elem,n)
        // insert needs an (n+1) element for the new element to put inbetween n and (n+1).
        // therefore we begin with an initial element, and then always insert the new element
        // at the forelast position
        NoOfElements = 15;
        mll.cons(-1);
        start = System.nanoTime();
        for (int n = 0; n < NoOfElements; n++) {
            mll.insert(n, n);
        }
        duration = System.nanoTime() - start;
        System.out.println(NoOfElements + " elements need:  " + duration + " ns.");
        mll.printCount();


        // now with 10x many elements

        mll = new MLinkedList<>();      // reset

        NoOfElements = NoOfElements * 10;
        mll.cons(-1);
        start = System.nanoTime();
        for (int n = 0; n < NoOfElements; n++) {
            mll.insert(n, n);
        }
        duration = System.nanoTime() - start;
        System.out.println(NoOfElements + " elements need:  " + duration + " ns.");

        // 15 elements need:  36775 ns.
        // AccessCount: 153
        // 150 elements need:  546501 ns.
        // AccessCount: 11628  
        //
        // 15 elements need:  33354 ns.
        // AccessCount: 153
        // 150 elements need:  484923 ns.
        // AccessCount: 11628
        //
        // 15 elements need:  23946 ns.
        // AccessCount: 153
        // 150 elements need:  426339 ns.
        // AccessCount: 11628

        // The number of Accesses suspects a quadratic complexity.
        // It can be shown, that inserting at the end in a
        // linked list is indeed of quadratic complexity.


        // Aufgabe 6:

        AverageVariance zeitmess = new AverageVariance();
        for (int t = 5000; t > 0; t--) {      // repeat this t-times
            NoOfElements = 52;					// repeat with 52 elements
            mll = new MLinkedList<>();
            mll.cons(0);
            start = System.nanoTime();
            for (int i = 0; i < NoOfElements; i++) {
                int pos = (int) Math.floor(Math.random() * i);
                mll.insert(i, pos);
            }
            duration = System.nanoTime() - start;
            zeitmess.addValue(duration);
        }
        System.out.println("For " + zeitmess.getN() + " repetitions we need");
        System.out.println(zeitmess.getAverage() + " nanoseconds on average");
        System.out.println("with a variance of " + zeitmess.getVariance() +  " nanoseconds*nanoseconds");
        System.out.println("for inserting " + NoOfElements + " elements at random positions.");

    }
}
