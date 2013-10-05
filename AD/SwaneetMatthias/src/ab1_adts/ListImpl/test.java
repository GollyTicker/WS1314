package ab1_adts.ListImpl;

import ab1_adts.AverageVariance;

public class test {

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

        // Beim ersten Element gibt es 1 Zugriff(e).
        // Alle weiteren Aktionen brauchen zwei Zugriffe.
        // Daher haben wir bei 15 Elementen: 1 + 14*2 = 29 Dereferenzierungen


        // Aufgabe 4:

        mll = new MLinkedList<>();      // reset

        // Zeitmessung bei NoOfElements vielen Elementen
        // diesmal ohne das Ausgeben des Dereferenzierungen
        long start = System.nanoTime();
        for (int n = 0; n < NoOfElements; n++) {
            mll.cons(n);
        }
        long duration = System.nanoTime() - start;
        System.out.println(NoOfElements + " Elemente brauchen: " + duration + " ns.");
        mll.printCount();


        mll = new MLinkedList<>();      // reset

        // Wiederholung nun mit 150 Elementen
        NoOfElements = NoOfElements * 10;
        start = System.nanoTime();
        for (int n = 0; n < NoOfElements; n++) {
            mll.cons(n);
        }
        duration = System.nanoTime() - start;
        System.out.println(NoOfElements + " Elemente brauchen: " + duration + " ns.");
        mll.printCount();

        // Output:
        // 15 Elemente brauchen: 6414 ns.
        // AccessCount: 29
        // 150 Elemente brauchen: 62860 ns.
        // AccessCount: 299

        // 15 Elemente brauchen: 7270 ns.
        // AccessCount: 29
        // 150 Elemente brauchen: 65426 ns.
        // AccessCount: 299

        // 15 Elemente brauchen: 6842 ns.
        // AccessCount: 29
        // 150 Elemente brauchen: 59867 ns.
        // AccessCount: 299

        // Es macht Sinn, dass bei einem O(n)-Algorithmus bei
        // Verzehnfachung der Anzahl der Elemente sich die Dauer ungefähr verzehnfacht.
        // Dass die kleinere Dauer ncht ganz 1x-kleiner ist, liegt wahrscheinlich am
        // anfänglichen Overhead welcher bei kleineren Eingaben sichtbar(er) ist.
        // Außerdem verhält sich die Zeit proportional zur Anzahl der Dereferenzierungen



        // Aufgabe 5:

        mll = new MLinkedList<>();      // reset

        // Nun am Ende hinzufügen:
        // stattdessen wird an der n-1 ten Stelle hinzugefügt wegen der
        // implementation von insert(...) ....    :/
        NoOfElements = 15;
        mll.cons(-1);
        start = System.nanoTime();
        for (int n = 0; n < NoOfElements; n++) {
            mll.insert(n, n);
        }
        duration = System.nanoTime() - start;
        System.out.println(NoOfElements + " Elemente brauchen: " + duration + " ns.");
        mll.printCount();


        // Nun mit 10x vielen Elementen

        mll = new MLinkedList<>();      // reset

        NoOfElements = NoOfElements * 10;
        mll.cons(-1);
        start = System.nanoTime();
        for (int n = 0; n < NoOfElements; n++) {
            mll.insert(n, n);
        }
        duration = System.nanoTime() - start;
        System.out.println(NoOfElements + " Elemente brauchen: " + duration + " ns.");

        // 15 Elemente brauchen: 36775 ns.
        // AccessCount: 153
        // 150 Elemente brauchen: 546501 ns.
        // AccessCount: 11628  
        //
        // 15 Elemente brauchen: 33354 ns.
        // AccessCount: 153
        // 150 Elemente brauchen: 484923 ns.
        // AccessCount: 11628
        //
        // 15 Elemente brauchen: 23946 ns.
        // AccessCount: 153
        // 150 Elemente brauchen: 426339 ns.
        // AccessCount: 11628

        // Die Anzahl der Dereferenzierungen vermutet eine quadratische
        // Komplexität. Jedoch braucht die Bestätigung mehr Experimente mit höherer Eingabegröße



        // Aufgabe 6:

        AverageVariance zeitmess = new AverageVariance();
        for (int t = 5000; t > 0; t--) {      // t-oft w  iederholen
            NoOfElements = 52;
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
        System.out.println("Bei " + zeitmess.getN() + " Versuchen brauchen wir durchschnittlich");
        System.out.println(zeitmess.getAverage() + " Nanosekunden");
        System.out.println("mit einer Varianz von " + zeitmess.getVariance() +  " Nanosekunden*Nanosekunden");
        System.out.println("zum Hinzufuegen von " + NoOfElements + " Elementen an zufaelligen Positionen");

    }
}
