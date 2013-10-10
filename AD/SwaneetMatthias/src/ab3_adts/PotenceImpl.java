package ab3_adts;

public class PotenceImpl {

	public static int exp(int x, int k) {
		int r = 1;
		for (int i = 0; i < k; i++)
			r = r * x;
		return r;
	}

	// x^0 = 1
	// x^2n = (x^n)^2
	// x^2n+1 = x*(x^n)^2
	// public static int exp2(int x, int k) {
	// int r = 1;
	// for (int i = 0; i < k; i++)
	// r = r * x;
	// return r;
	// }

}
