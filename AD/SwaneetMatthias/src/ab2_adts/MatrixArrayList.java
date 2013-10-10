package ab2_adts;

import java.util.ArrayList;

public class MatrixArrayList implements Matrix {

	private ArrayList<ArrayList<Double>> mArray = new ArrayList<ArrayList<Double>>();

	private int m;
	private int n;

	public MatrixArrayList(int m, int n) {
		for (int i = 0; i < m; i++) {
			ArrayList<Double> tmpi = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				tmpi.add(0.0);
			}
			this.mArray.add(tmpi);
		}
		this.m = m;
		this.n = n;
	}

	@Override
	public void insert(int i, int j, double value) {
		outOfBound(i-1,j-1);
		this.mArray.get(i-1).add(j-1, value);
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
		outOfBound(i-1,j-1);
		return this.mArray.get(i-1).get(j-1);
	}

	private void outOfBound(int i, int j) {
		if (!(this.m >= i && this.n >= j))
			throw new IndexOutOfBoundsException();
	}

	@Override
	public Matrix add(Matrix m) {
		Matrix m2 = new MatrixArrayList(this.m, this.n);
		for (int i = 1; i <= this.m; i++) {
			for (int j = 1; j <= this.n; j++) {
				if (Double.compare(this.get(i, j), 0.0) != 0)
					m2.insert(i, j, m.get(i, j) + this.get(i, j));
			}
		}
		return m2;
	}

	@Override
	public Matrix mul(double skalar) {
		Matrix m = new MatrixArrayList(this.m, this.n);
		for (int i = 1; i <= this.m; i++) {
			for (int j = 1; j <= this.n; j++) {
				if (Double.compare(this.get(i, j), 0.0) != 0)
					m.insert(i, j, skalar * this.get(i, j));
			}
		}
		return m;
	}
	
	private boolean assertMulLength(Matrix m) {
		return this.n == m.getM();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		String acc = "";
		for (int i = 0; i < this.m; i++) {
			ArrayList<Double> tmpi = this.mArray.get(i);
			for (int j = 0; j < this.n; j++) {
				acc += tmpi.get(j) + (j != this.n - 1 ? ", " : "");
			}
			acc += (i != this.m - 1 ? "\n" : "");
		}
		return acc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + m;
		result = prime * result + n;
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
