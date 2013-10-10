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
		this.mArray.get(i).add(j, value);
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
		return this.mArray.get(i).get(j);
	}

	@Override
	public Matrix add(Matrix m) {
		Matrix m2 = new MatrixArrayList(this.m, this.n);
		for (int i = 0; i < this.m; i++) {
			for (int j = 0; j < this.n; j++) {
				if (Double.compare(this.get(i, j), 0.0) != 0)
					m2.insert(i, j, m.get(i, j) + this.get(i, j));
			}
		}
		return m2;
	}

	@Override
	public Matrix mul(double skalar) {
		Matrix m = new MatrixArrayList(this.m, this.n);
		for (int i = 0; i < this.m; i++) {
			for (int j = 0; j < this.n; j++) {
				if (Double.compare(this.get(i, j), 0.0) != 0)
					m.insert(i, j, skalar * this.get(i, j));
			}
		}
		return m;
	}

	@Override
	public Matrix mul(Matrix factor) {
		// TODO Auto-generated method stub
		return null;
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
}
