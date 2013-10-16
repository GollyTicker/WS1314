package ab3_adts;

public class PotenceImpl {

	/**
	 * Takes an Integer x to the power of k
	 * 
	 * @param x
	 *            Base integer
	 * @param k
	 *            exponent
	 */
	public static int exp(int x, int k) {
		int r = 1;
		for (int i = 0; i < k; i++)
			r = r * x;
		return r;
	}

	/**
	 * Takes an Integer x to the power of k
	 * 
	 * @param x
	 *            Base integer
	 * @param k
	 *            exponent
	 */
	public static int expFast(int x, int k) {
		int r = 0;
		// x^0 = 1
		// x^2n = (x^n)^2
		// x^2n+1 = x*(x^n)^2
		return r;
	}

}
