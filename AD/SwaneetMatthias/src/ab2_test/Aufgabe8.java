package ab2_test;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ab1_adts.AverageVariance;
import ab2_adts.*;

public class Aufgabe8 {

	private final static int n = 100; // N x N is the size of the matrices
	private final static int t = 5; // t the number of randomly generated
									// matrices
	private final static List<Double> pValues = new ArrayList<>(Arrays.asList(
			0.001, 0.005, 0.01, 0.05, 0.1, 0.5));

	private static long start = 0;

	private final static DecimalFormat df = new DecimalFormat("#.####");

	private static String getDuration() {
		return " - " + df.format((System.nanoTime() - start) / 1000000.0)
				+ " ms";
	}

	@Test
	public void test() {
		start = System.nanoTime();			// count global time because it takes all sooooo long :O

		String acc = "Output: \n";
		acc += "n = " + n + "\n";
		acc += "t = " + t + "\n";
		
		Matrix summand = new MatrixList(n, n);
		Matrix factor = new MatrixList(n, n);

		System.out.println("Started experiments" + getDuration());
		// repeat for each propability of a non-zero element
		for (Double pNonZero : pValues) {

			System.out.println("p = " + pNonZero + " started " + getDuration());

			AverageVariance statsListAdd = new AverageVariance();
			AverageVariance statsListMul = new AverageVariance();
			AverageVariance statsArrayListAdd = new AverageVariance();
			AverageVariance statsArrayListMul = new AverageVariance();

			GeneratorModule gm = new GeneratorModule(n, n, pNonZero);

			// repeat t-times
			for (int tCounter = 1; tCounter <= t; tCounter++) {

				// generate random matrix
				gm.generateRandomMatrix();
				Matrix m = gm.getMatrix();

				Matrix mList = new MatrixList(n, n);
				Matrix mArrayList = new MatrixArrayList(n, n);

				// fill matrices in the different implementations
				mList.copyFrom(m);
				mArrayList.copyFrom(m);
				
				// reset and initialize for measurements
				mList.resetAccessCount();
				mArrayList.resetAccessCount();
				
				
				
				// measuring addition on List
				mList.add(summand);
				int mListAddAccessCount = mList.accessCount();							// ###
				mList.resetAccessCount();
				

				// measuring multiplication on List
				mList.mul(factor);
				int mListMulAccessCount = mList.accessCount();							// ###
				mList.resetAccessCount();
				
				
				// measuring addition on ArrayList
				mArrayList.add(summand);
				int mArrayListAddAccessCount = mList.accessCount();						// ###
				mArrayList.resetAccessCount();
				

				// measuring multiplication on ArrayList
				mArrayList.mul(factor);
				int mArrayListMulAccessCount = mList.accessCount();						// ###
				mArrayList.resetAccessCount();
				
				
				statsListAdd.addValue(mListAddAccessCount);
				statsListMul.addValue(mListMulAccessCount);
				statsArrayListAdd.addValue(mArrayListAddAccessCount);
				statsArrayListMul.addValue(mArrayListMulAccessCount);
			}

//			acc += "Average NoOfElements for p = " + pNonZero + ":\n";
//			acc += "mList - " + statsList.getAverage() + "\n";
//			acc += "mArrayList - " + statsArrayList.getAverage() + "\n";
//			acc += "\n\n";

		}
		// Platz_p(t):
		System.out.println("Finisched" + getDuration());
		System.out.println(acc);
	}

}
