package ab1_test;

import static org.junit.Assert.*;
import org.junit.Test;

import ab1_adts.ListImpl.MLinkedList;

public class ListImplTest {

	@Test
	public void test() {
	}

	@Test
	public void test_experiment_one() {
		MLinkedList<Integer> mll = new MLinkedList<>();
		for (int n = 0; n < 10; n++) {
			mll.cons(n);
		}
		assertTrue(mll.get(mll.length() - 1) == 0);
		assertTrue(mll.get(0) == 9);
	}

	@Test
	public void test_general() {
		MLinkedList<Integer> mll = new MLinkedList<>();
		for (int n = 0; n < 120; n++) {
			mll.cons(n);
			assertTrue(mll.first().intValue() == n);
			assertTrue(mll.length() == n + 1);
		}
		mll.insert(10, 50);
		assertTrue(mll.get(51).intValue() == 10);
		assertTrue(mll.length() == 121);
	}
        
        
        // alle Tests stehen im tests unterordner, weil
        // ich in Netbeans die Tests hier nicht starten kann.
        // dort stehen alle BasisTests
        
        // die Experimente die in den
        // Listenaufgaben auf dem Praktikumsblatt stehen,
        // sind in der Datei neben den Implementationsklassen
        
        

}
