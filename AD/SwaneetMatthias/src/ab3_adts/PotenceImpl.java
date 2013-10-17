package ab3_adts;

import java.util.List;
import java.util.ArrayList;

import ab1_adts.statmodule.AverageVariance;

public class PotenceImpl {

	public static void main(String[] args) {
		long max = 60;	// the limit of the java long type is 2^60
		List<Long> expL = new ArrayList<>();
		List<Long> expFastL = new ArrayList<>();
		for (long i = 0; i < max; i++) {
			long x = 2;
			long k = i;
			accessCountNormal = 0;
			accessCountFast = 0;
			long exp1 = exp(x, k);
			long exp2 = expFast(x, k);
			expL.add(accessCountNormal);
			expFastL.add(accessCountFast);
		}
		System.out.println(expL);
		System.out.println(expFastL);
	}

	public static long accessCountNormal = 0;

	/**
	 * Takes an Integer x to the power of k
	 * 
	 * @param x
	 *            Base Integer
	 * @param k
	 *            exponent
	 */
	public static long exp(long x, long k) {
		long r = 1;
		for (long i = 0; i < k; i++) {
			accessCountNormal++;
			r = r * x;
		}
		return r;
	}

	public static long accessCountFast = 0;

	/**
	 * Takes an Integer x to the power of k
	 * 
	 * @param x
	 *            Base Integer
	 * @param k
	 *            exponent
	 */
	public static long expFast(long x, long k) {
		accessCountFast++;
		if (k == 0)
			return 1;
		if (k % 2 == 0) {
			long tmp = expFast(x, k / 2);
			return tmp * tmp;
		}
		long tmp = expFast(x, (k - 1) / 2);
		return tmp * tmp * x;
	}
}
