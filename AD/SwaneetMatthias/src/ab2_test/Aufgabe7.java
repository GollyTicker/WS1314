package ab2_test;

import java.text.DecimalFormat;
import java.util.Arrays;

import org.junit.Test;

import ab1_adts.statmodule.AverageVariance;
import ab2_adts.*;
import ab2_adts.generator.GeneratorModule;
import ab2_adts.matrix.Matrix;
import ab2_adts.matrix.MatrixArray;
import ab2_adts.matrix.MatrixArrayList;
import ab2_adts.matrix.MatrixList;

import java.util.*;

public class Aufgabe7 {

	// CAREFUL!! On overly overfilled and slightly old laptops like
	// mine(Swaneet's)
	// already the n=450, t=5 experiment takes 415 seconds - 7mins!

	private final static int n = 250; // N x N is the size of the matrices
	private final static int t = 5; // t the number of randomly generated
									// matrices
	private final static List<Double> pValues = new ArrayList<>(Arrays.asList(
			0.01, 0.05, 0.1));

	private static long start = 0;

	private final static DecimalFormat df = new DecimalFormat("#.####");

	private static String getDuration() {
		return " - " + df.format((System.nanoTime() - start) / 1000000.0)
				+ " ms";
	}

	@Test
	public void test() {

		start = System.nanoTime();

		String acc = "Output: \n";
		acc += "n = " + n + "\n";
		acc += "t = " + t + "\n";

		System.out.println("Started experiments" + getDuration());
		// repeat for each propabilit of a non-zero element
		for (Double pNonZero : pValues) {

			System.out.println("p = " + pNonZero + " started " + getDuration());

			AverageVariance statsList = new AverageVariance();
			AverageVariance statsArrayList = new AverageVariance();
			AverageVariance statsArray = new AverageVariance();

			GeneratorModule gm = new GeneratorModule(n, n, pNonZero);

			// repeat t-times
			for (int tCounter = 1; tCounter <= t; tCounter++) {

				// generate random matrix
				gm.generateRandomMatrix();
				Matrix m = gm.getMatrix();

				Matrix mList = new MatrixList(n, n);
				Matrix mArrayList = new MatrixArrayList(n, n);
				Matrix mArray = new MatrixArray(n, n);

				// fill matrices in the different implementations
				mList.copyFrom(m);
				mArrayList.copyFrom(m);
				mArray.copyFrom(m);

				// save the memUsage for each impl.
				statsList.addValue(mList.memoryUsage());
				statsArrayList.addValue(mArrayList.memoryUsage());
				statsArray.addValue(mArray.memoryUsage());
			}

			acc += "Average NoOfElements for p = " + pNonZero + ":\n";
			acc += "mList - " + statsList.getAverage() + "\n";
			acc += "mArrayList - " + statsArrayList.getAverage() + "\n";
			acc += "mArray - " + statsArray.getAverage() + "\n";
			acc += "\n\n";

		}
		// Platz_p(t):
		System.out.println("Finisched" + getDuration());
		System.out.println(acc);

		// For the overhead - how much does a single element cost?
//			Output: 
//			n = 1
//			t = 500
//			Average NoOfElements for p = 0.01:
//			mList - 0.012
//			mArrayList - 0.012
//
//
//			Average NoOfElements for p = 0.05:
//			mList - 0.056
//			mArrayList - 0.056
//
//
//			Average NoOfElements for p = 0.1:
//			mList - 0.118
//			mArrayList - 0.118
		
		// Output 1:
		// n = 150
		// t = 5
		// Average NoOfElements for p = 0.01:
		// mList - 235.0
		// mArrayList - 235.0
		//
		//
		// Average NoOfElements for p = 0.05:
		// mList - 1139.8
		// mArrayList - 1139.8
		//
		//
		// Average NoOfElements for p = 0.1:
		// mList - 2239.6
		// mArrayList - 2239.6

		// Output - 2:
		// Started experiments - 0,0902 ms
		// p = 0.01 started - 0,8292 ms
		// p = 0.05 started - 2163,8838 ms
		// p = 0.1 started - 12288,6113 ms
		// Finisched - 32391,8897 ms
		// Output:
		// n = 250
		// t = 5
		// Average NoOfElements for p = 0.01:
		// mList - 623.4
		// mArrayList - 623.4
		//
		//
		// Average NoOfElements for p = 0.05:
		// mList - 3084.0
		// mArrayList - 3084.0
		//
		//
		// Average NoOfElements for p = 0.1:
		// mList - 6294.4
		// mArrayList - 6294.4

		// Output - 3:
		// Started experiments - 0,0988 ms
		// p = 0.01 started - 0,9331 ms
		// p = 0.05 started - 17599,0173 ms
		// p = 0.1 started - 121828,4797 ms
		// Finisched - 415058,773 ms
		// Output:
		// n = 450
		// t = 5
		// Average NoOfElements for p = 0.01:
		// mList - 2016.2
		// mArrayList - 2016.2
		//
		//
		// Average NoOfElements for p = 0.05:
		// mList - 10150.6
		// mArrayList - 10150.6
		//
		//
		// Average NoOfElements for p = 0.1:
		// mList - 20218.4
		// mArrayList - 20218.4
		
		// Output - 4 auf Matthias laptop:
//		Started experiments - 0,117 ms
//		p = 0.01 started - 1,354 ms
//		p = 0.05 started - 71990,496 ms
//		p = 0.1 started - 408894,977 ms
//		Finisched - 1787230,669 ms
//		Output:
//		n = 1000
//		t = 5
//		Average NoOfElements for p = 0.01:
//		mList - 9946.0
//		mArrayList - 9946.0
//
//
//		Average NoOfElements for p = 0.05:
//		mList - 50088.2
//		mArrayList - 50088.2
//
//
//		Average NoOfElements for p = 0.1:
//		mList - 99693.4
//		mArrayList - 99693.4
	}

}
