package ab1_test;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import ab1_adts.AverageVariance;


public class AverageVarianceTest {
	
	/**
	 * test for javadoc
	 */
	
	@Test
	public void test() {
	}
	
	@Test
	public void test_average(){
		AverageVariance av = new AverageVariance();
		av.addValues(Arrays.asList(6.0, 3.0, 8.0, 5.0, 3.0));
		assertEquals(5, av.getAverage(), 0.01);
	}
	
	@Test
	public void test_variance(){
		AverageVariance av1 = new AverageVariance();
		av1.addValues(Arrays.asList(6.0, 3.0, 8.0, 5.0, 3.1));
		assertTrue(Double.compare(4.5, av1.getVariance()) != 0);
		AverageVariance av2 = new AverageVariance();
		av2.addValues(Arrays.asList(6.0, 3.0, 8.0, 5.0, 3.0));
		assertEquals(4.5, av2.getVariance(), 0.01);
	}

}
