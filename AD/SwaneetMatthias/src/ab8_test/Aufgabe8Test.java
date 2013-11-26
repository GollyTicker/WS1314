package ab8_test;

import static org.junit.Assert.*;
import static ab8_adts.Aufgabe8.*;

import org.junit.Test;

import ab1_adts.ListImpl.IList;

public class Aufgabe8Test {

	@Test
	public void testRandomListOfLengthN() {
		for(int n = 0; n < 15; n++) {
			IList<Integer> randList = randomListOfLength(n,-100,200);
			assertEquals(randList.length(),n);
			System.out.println(randList);
		}
	}

}
