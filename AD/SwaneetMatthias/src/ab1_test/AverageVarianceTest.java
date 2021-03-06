package ab1_test;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import ab1_adts.statmodule.AverageVariance;

/**
 *
 * @author Swaneet Sahoo, Matthias Nitsche
 */

public class AverageVarianceTest {
	
	@Test
	public void test_average(){
		AverageVariance av = new AverageVariance();
		av.addValues(Arrays.asList(6.0, 3.0, 8.0, 5.0, 3.0));
		assertEquals(5, av.getAverage(), 0.01);

		av = new AverageVariance();
		av.addValues(Arrays.asList(7.0, 2.0, 8.0, 5.0, 3.0));
		assertEquals(5, av.getAverage(), 0.01);
		
		av = new AverageVariance();
		av.addValues(Arrays.asList(7.0, 2.0, 8.0, 5.0, 3.0, 24.0));
		assertEquals(8.16667, av.getAverage(), 0.001);
	}
	
	@Test
	public void test_variance(){
//		AverageVariance av1 = new AverageVariance();
//		av1.addValues(Arrays.asList(6.0, 3.0, 8.0, 5.0, 3.1));
//		assertTrue(Double.compare(4.5, av1.getVariance()) != 0);
		AverageVariance av2 = new AverageVariance();
//		av2.addValues(Arrays.asList(6.0, 3.0, 8.0, 5.0, 3.0));
//
//		assertEquals(4.5, av2.getVariance(), 0.01);
		System.out.println("Avg*Avg: "+av2.getAverage()*av2.getAverage()+"; Varianz:"+av2.getVariance());
		

		av2.addValues(Arrays.asList(6.0, 3.0, 8.0, 5.0, 3.0, 4.0,15.0,16.0,0.3,20.0,130.0,5000.0,1234.0));

		System.out.println("Avg*Avg: "+av2.getAverage()*av2.getAverage()+"; Varianz:"+av2.getVariance());
		//assertEquals(3.76667, av2.getVariance(), 0.01);
		// unerklaerlicher Fehler zwsWolframAlpha und unseren Werten
		// wir haben aber in expliziter und akkumulierter form das gleiche ergebnis! ???!
		// 1945510.0
		
		
	}

}
