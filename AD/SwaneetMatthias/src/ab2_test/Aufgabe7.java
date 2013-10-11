package ab2_test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import ab1_adts.AverageVariance;
import ab2_adts.*;
import java.util.*;

public class Aufgabe7 {

	private static int n = 100; // N x N is the size of the matrices
	private static int t = 10; // t the number of randomly generated matrices
	// private static double pNonZero = 0.1;
	private static List<Double> pValues = new ArrayList<>(Arrays.asList(0.01,
			0.05, 0.1));

	@Test
	public void test() {

		for (Double pNonZero : pValues) {
			AverageVariance statsList = new AverageVariance();
			AverageVariance statsArray = new AverageVariance();
			AverageVariance statsArrayList = new AverageVariance();

			GeneratorModule gm = new GeneratorModule(n, n, pNonZero);

			// repeat t-times
			for (int tCounter = 1; tCounter <= t; tCounter++) {

				// generate random matrix
				gm.generateRandomMatrix();
				Matrix m = gm.getMatrix();

				Matrix mList = new MatrixList(n, n);
				Matrix mArray = new MatrixArray(n, n);
				Matrix mArrayList = new MatrixArrayList(n, n);

				// fill matrices in the different implementations
				mList.copyFrom(m);
				mArray.copyFrom(m);
				mArrayList.copyFrom(m);

				// save the memUsage for each impl.
				statsList.addValue(mList.memoryUsage());
				statsArray.addValue(mArray.memoryUsage());
				statsArrayList.addValue(mArrayList.memoryUsage());
			}

			System.out.println("mList avg memUsage: " + statsList.getAverage()
					+ " elements");
			System.out.println("mArray avg memUsage: "
					+ statsArray.getAverage() + " elements");
			System.out.println("mArrayList avg memUsage: "
					+ statsArrayList.getAverage() + " elements");

		}
		// Platz_p(t):
	}

}
