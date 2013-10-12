package ab2_test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ab1_adts.AverageVariance;
import ab2_adts.*;

public class Aufgabe8 {

	private final static int n = 60; // N x N is the size of the matrices
	private final static int t = 15; // t the number of randomly generated
									// matrices
	private final static List<Double> pValues = new ArrayList<>(Arrays.asList(
			0.01, 0.05, 0.1));

	private static long start = 0;

	private final static DecimalFormat df = new DecimalFormat("#.####");
	private final static DecimalFormat dfAccessCount = new DecimalFormat(
			"000000000.######");

	private static String getDuration() {
		return " - " + df.format((System.nanoTime() - start) / 1000000.0)
				+ " ms";
	}

	private static String niceFormat(double val) {
		return dfAccessCount.format(val);
	}

	@Test
	public void test() {
		start = System.nanoTime(); // count global time because it takes all
									// sooooo long :O

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
				int mListAddAccessCount = mList.accessCount(); // ###
				mList.resetAccessCount();

				// measuring multiplication on List
				mList.mul(factor);
				int mListMulAccessCount = mList.accessCount(); // ###
				mList.resetAccessCount();

				// measuring addition on ArrayList
				mArrayList.add(summand);
				int mArrayListAddAccessCount = mArrayList.accessCount(); // ###
				mArrayList.resetAccessCount();

				// measuring multiplication on ArrayList
				mArrayList.mul(factor);
				int mArrayListMulAccessCount = mArrayList.accessCount(); // ###
				mArrayList.resetAccessCount();

				statsListAdd.addValue(mListAddAccessCount);
				statsListMul.addValue(mListMulAccessCount);
				statsArrayListAdd.addValue(mArrayListAddAccessCount);
				statsArrayListMul.addValue(mArrayListMulAccessCount);
			}

			acc += "AccessCounts For pNonZero = " + pNonZero + ":\n";
			acc += "mList:\n";
			acc += "  Addition:\n";
			acc += "    Average: " + niceFormat(statsListAdd.getAverage())
					+ "\n";
			acc += "    Variance: " + niceFormat(statsListAdd.getVariance())
					+ "\n";
			acc += "  Multiplication:\n";
			acc += "    Average: " + niceFormat(statsListMul.getAverage())
					+ "\n";
			acc += "    Variance: " + niceFormat(statsListMul.getVariance())
					+ "\n";
			acc += "mArrayList:\n";
			acc += "  Addition:\n";
			acc += "    Average: " + niceFormat(statsArrayListAdd.getAverage())
					+ "\n";
			acc += "    Variance: "
					+ niceFormat(statsArrayListAdd.getVariance()) + "\n";
			acc += "  Multiplication:\n";
			acc += "    Average: " + niceFormat(statsArrayListMul.getAverage())
					+ "\n";
			acc += "    Variance: "
					+ niceFormat(statsArrayListMul.getVariance()) + "\n";
			acc += "\n\n";

		}
		// Platz_p(t):
		System.out.println("Finisched" + getDuration());
		System.out.println(acc);
	}
	// For the overhead - how much does a single element cost?
//	Started experiments - 2,7667 ms
//	p = 0.01 started  - 3,5416 ms
//	p = 0.05 started  - 29,2306 ms
//	p = 0.1 started  - 51,8186 ms
//	Finisched - 55,7082 ms
//	Output: 
//	n = 1
//	t = 500
//	AccessCounts For pNonZero = 0.01:
//	mList:
//	  Addition:
//	    Average: 000000000,008
//	    Variance: 000000000,007952
//	  Multiplication:
//	    Average: 000000000,008
//	    Variance: 000000000,007952
//	mArrayList:
//	  Addition:
//	    Average: 000000000,008
//	    Variance: 000000000,007952
//	  Multiplication:
//	    Average: 000000000,008
//	    Variance: 000000000,007952
//
//
//	AccessCounts For pNonZero = 0.05:
//	mList:
//	  Addition:
//	    Average: 000000000,048
//	    Variance: 000000000,045788
//	  Multiplication:
//	    Average: 000000000,048
//	    Variance: 000000000,045788
//	mArrayList:
//	  Addition:
//	    Average: 000000000,048
//	    Variance: 000000000,045788
//	  Multiplication:
//	    Average: 000000000,048
//	    Variance: 000000000,045788
//
//
//	AccessCounts For pNonZero = 0.1:
//	mList:
//	  Addition:
//	    Average: 000000000,108
//	    Variance: 000000000,096529
//	  Multiplication:
//	    Average: 000000000,108
//	    Variance: 000000000,096529
//	mArrayList:
//	  Addition:
//	    Average: 000000000,108
//	    Variance: 000000000,096529
//	  Multiplication:
//	    Average: 000000000,108
//	    Variance: 000000000,096529




	
	// Output:
//			Started experiments - 2,1462 ms
//			p = 0.01 started  - 2,638 ms
//			p = 0.05 started  - 1645,0155 ms
//			p = 0.1 started  - 7765,8316 ms
//			Finisched - 19866,8312 ms
//			Output: 
//			n = 60
//			t = 15
//			AccessCounts For pNonZero = 0.01:
//			mList:
//			  Addition:
//			    Average: 000142015,666667
//			    Variance: 474503863,666667
//			  Multiplication:
//			    Average: 008520940
//			    Variance: 1708213909200
//			mArrayList:
//			  Addition:
//			    Average: 000002365,933333
//			    Variance: 000131114,066667
//			  Multiplication:
//			    Average: 000141956
//			    Variance: 472010640
//		
//		
//			AccessCounts For pNonZero = 0.05:
//			mList:
//			  Addition:
//			    Average: 000622421
//			    Variance: 2912721758,142857
//			  Multiplication:
//			    Average: 037345260
//			    Variance: 10485798329314,285
//			mArrayList:
//			  Addition:
//			    Average: 000010383
//			    Variance: 000822370
//			  Multiplication:
//			    Average: 000622980
//			    Variance: 2960532000
//		
//		
//			AccessCounts For pNonZero = 0.1:
//			mList:
//			  Addition:
//			    Average: 001249198,733333
//			    Variance: 2165148774,066667
//			  Multiplication:
//			    Average: 074951924
//			    Variance: 7794535586640
//			mArrayList:
//			  Addition:
//			    Average: 000020835,866667
//			    Variance: 000593188,838095
//			  Multiplication:
//			    Average: 001250152
//			    Variance: 2135479817,142857

}
