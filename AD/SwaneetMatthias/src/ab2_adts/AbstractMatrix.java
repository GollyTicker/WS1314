package ab2_adts;

public abstract class AbstractMatrix implements Matrix{

	protected int m; // i
	protected int n; // j
	
	public AbstractMatrix(int m, int n){
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
		
	}
	
	@Override
	public int getM() {
		return m;
	}

	@Override
	public int getN() {
		return n;
	}

	protected boolean isSameLength(Matrix m) {
		return this.m == m.getM() && this.n == m.getN();
	}

	protected boolean assertMulLength(Matrix m) {
		return this.n == m.getM();
	}

	protected void outOfBound(int i, int j) {
		if (!(0 <= i && i < m) || !(0 <= j && j < n))
			throw new IndexOutOfBoundsException();
	}


	protected void AssertExponentValid(int exp) {
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
