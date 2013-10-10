package ab2_adts;

import java.util.ArrayList;
import java.util.List;

public class MatrixList implements Matrix {

	List<Element> elems = new ArrayList<>();

	private int m; // 1 <= i <= m
	private int n; // 1 <= j <= n

	public MatrixList(int m, int n) {
		if (n <= 0 || m <= 0) {
			try {
				throw new Exception(
						"The matrix has to have a size of at least 1 x 1.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.m = m;
		this.n = n;
		// all elements are initialized with 0
		// therefore the elements don't need to be added into the list
	}

	private void AssertIndicesInBounds(int i, int j) {
		if (!(1 <= i && i <= m) || !(1 <= j && j <= n)) {
			throw new IndexOutOfBoundsException("Indices are invalid!");
		}
	}

	@Override
	public void insert(int i, int j, double value) {
		for (Element e : elems) {
			if (e.getI() == i && e.getJ() == j) {
				elems.remove(e);
			}
		}
		elems.add(new Element(i, j, value));
	}

	@Override
	public int getM() {
		return m;
	}

	@Override
	public int getN() {
		return n;
	}

	@Override
	public double get(int i, int j) {
		AssertIndicesInBounds(i, j);
		for (Element e : elems) {
			if (e.getI() == i && e.getJ() == j)
				return e.getValue();
		}
		return 0.0;
	}

	private void AssertMatrixAddable(Matrix summand) {
		if (!(this.getM() == summand.getM() && this.getN() == summand.getN())) {
			try {
				throw new Exception(
						"Matrices cannot be added together. Incompatible size.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Matrix add(Matrix summand) {
		AssertMatrixAddable(summand);
		Matrix sum = new MatrixList(this.getM(), this.getN());
		for (int i = 1; i <= this.getM(); i++) {
			for (int j = 1; j <= this.getN(); j++) {
				double value = this.get(i, j) + summand.get(i, j); // <-
																	// important
																	// line
				sum.insert(i, j, value);
			}
		}
		return sum;
	}

	@Override
	public Matrix mul(double skalar) {
		Matrix multedMatrix = new MatrixList(this.getM(), this.getN());
		for (int i = 1; i <= this.getM(); i++) {
			for (int j = 1; j <= this.getN(); j++) {
				double value = this.get(i, j) * skalar; // <- important line
				multedMatrix.insert(i, j, value);
			}
		}
		return multedMatrix;
	}

	private void AssertMatrixMultable(Matrix factor) {
		if (!(this.getN() == factor.getM())) {
			try {
				throw new Exception(
						"Matrices cannot be multiplied together. Incompatible size.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Matrix mul(Matrix factor) {
		AssertMatrixMultable(factor);
		Matrix product = new MatrixList(this.getM(), factor.getN());
		for (int i = 1; i <= product.getM(); i++) {
			for (int j = 1; j <= product.getN(); j++) {

				// formula:
				// c(i,j) = sum(k=1 to k=n){ a(i,k) * b(k,j) }
				double value = 0;
				for (int k = 1; k <= n; k++) {
					value += this.get(i, k) * factor.get(k, j);
				}
				product.insert(i, j, value);
			}
		}
		return product;
	}

	private void AssertExponentValid(int exp) {
		if (exp < 1) {
			try {
				throw new Exception("Matrix to the power of " + exp
						+ "won't be computed.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Matrix pow(int exponent) { // the exponent goes from 1 to infinity
		AssertExponentValid(exponent);
		Matrix pot = new MatrixList(this.getM(), this.getN());
		// bei matrix hoch 1 wird die schleife nicht erst ausgefuehrt
		if (exponent == 1)
			return this;

		for (int i = 2; i <= exponent; i++) {
			pot = this.mul(this);
		}

		return pot;
	}

	@Override
	public String toString() {
		String acc = "[\n";
		for (int i = 1; i <= m; i++) {
			acc += "[";
			for (int j = 1; j <= n; j++) {
				acc += this.get(i, j) + ((j <= (n - 1)) ? "," : "");
			}
			acc += "]" + ((i <= (m - 1)) ? ",\n" : "");
		}
		return acc + "\n]";
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
