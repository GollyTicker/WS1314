package ab2_adts;

import java.util.Arrays;

public class MatrixArray implements Matrix {

	private double[][] mArray;
	private int m; // i
	private int n; // j

	public MatrixArray(int m, int n) {
		mArray = new double[m][n];
		this.m = m;
		this.n = n;
	}

	public MatrixArray(double[][] mArray) {
		this.mArray = mArray;
		this.m = mArray.length;
		this.n = mArray[0].length;
	}

	@Override
	public Matrix add(Matrix m) {
		if (!isSameLength(m))
			throw new IndexOutOfBoundsException("mxn do not equal this matrix");
		Matrix m2 = new MatrixArray(this.m, this.n);
		for (int i = 1; i <= this.m; i++) {
			for (int j = 1; j <= this.n; j++) {
				m2.insert(i, j, this.get(i, j) + m.get(i, j));
			}
		}
		return m2;
	}

	@Override
	public Matrix mul(double skalar) {
		Matrix m = new MatrixArray(this.m, this.n);
		for (int i = 1; i <= this.m; i++) {
			for (int j = 1; j <= this.n; j++) {
				m.insert(i, j, skalar * this.get(i, j));
			}
		}
		return m;
	}

	@Override
	public Matrix mul(Matrix factor) {
		if (!assertMulLength(factor))
			throw new IndexOutOfBoundsException(
					"M of this Matrix does not equal N of given Matrix");
		Matrix m2 = new MatrixArray(this.getM(), factor.getN());
		for (int i = 1; i <= m2.getM(); i++) {
			for (int j = 1; j <= m2.getN(); j++) {
				double acc = 0;
				for (int k = 1; k <= this.n; k++) {
					acc += (this.get(i, k) * factor.get(k, j));
				}
				m2.insert(i, j, acc);
			}
		}
		return m2;
	}

	@Override
	public Matrix pow(int exponent) {
		// TODO: Negative Exponenten und Null
		for (int round = 0; round < exponent; round++) {
			this.mul(this);
		}
		return this;
	}

	@Override
	public void insert(int i, int j, double value) {
		outOfBound(i-1, j-1);
		this.mArray[i-1][j-1] = value;
	}

	@Override
	public int getM() {
		return this.m;
	}

	@Override
	public int getN() {
		return this.n;
	}

	@Override
	public double get(int i, int j) {
		outOfBound(i-1, j-1);
		return this.mArray[i-1][j-1];
	}

	private boolean isSameLength(Matrix m) {
		return this.m == m.getM() && this.n == m.getN();
	}

	private boolean assertMulLength(Matrix m) {
		return this.n == m.getM();
	}

	private void outOfBound(int i, int j) {
		if (!(this.m >= i && this.n >= j))
			throw new IndexOutOfBoundsException();
	}

	@Override
	public String toString() {
		String acc = "";
		for (int i = 0; i < this.m; i++) {
			for (int j = 0; j < this.n; j++) {
				acc += this.mArray[i][j] + (j != this.n - 1 ? ", " : "");
			}
			acc += (i != this.m - 1 ? "\n" : "");
		}
		return acc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(mArray);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (!(o instanceof Matrix))
			return false;

		Matrix m = (Matrix) o;
		for (int i = 1; i <= this.getM(); i++) {
			for (int j = 1; j <= this.getN(); j++) {
				if (this.get(i, j) != m.get(i, j))
					return false;
			}
		}
		return true;
	}
}