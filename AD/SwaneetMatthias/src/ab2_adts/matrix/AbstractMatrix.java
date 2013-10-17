package ab2_adts.matrix;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public abstract class AbstractMatrix implements Matrix, IException {

	protected int m; // i
	protected int n; // j

	// counting accesses for Aufgabe 8
	protected int accessCount = 0;

	@Override
	public int accessCount() {
		return this.accessCount;
	}

	@Override
	public void resetAccessCount() {
		this.accessCount = 0;
	}

	// Creation
	/**
	 * Abstract Implementation for all Matrix subclasses to refactor and shorten
	 * Code Implementation. The Index starts by 1 not 0. All indexes are then
	 * subtracted by 1.
	 * 
	 * @param m
	 *            m dimension of a Matrix
	 * @param n
	 *            n dimension of a Matrix
	 */
	public AbstractMatrix(int m, int n) {
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

	// Selectors
	@Override
	public int getM() {
		return m;
	}

	@Override
	public int getN() {
		return n;
	}

	// Mutators
	/**
	 * Multiply a m x n Matrix by a given Scalar.
	 * 
	 * @param skalar
	 *            the given multiplier for the Matrix.
	 * @return New Matrix.
	 */
	@Override
	public abstract Matrix mul(double skalar);

	protected Matrix mul_(double skalar, Matrix destination) {
		for (int i = 1; i <= destination.getM(); i++) {
			for (int j = 1; j <= destination.getN(); j++) {
				double value = this.get(i, j) * skalar; // <- important line
				destination.insert(i, j, value);
			}
		}
		return destination;
	}

	/**
	 * Multiply a m x n Matrix with a given m x n Matrix. The M of this has to
	 * be equal to the N of the given Matrix.
	 * 
	 * @param factor
	 *            the given Matrix for a m x n Matrix.
	 * @return New Matrix with m of this matrix and n of given matrix.
	 */

	@Override
	public abstract Matrix mul(Matrix factor);

	protected Matrix mul_(Matrix factor, Matrix destination) {
		if (!assertMulLength(factor))
			throw new IndexOutOfBoundsException(
					"M of this Matrix does not equal N of given Matrix");
		for (int i = 1; i <= destination.getM(); i++) {
			for (int j = 1; j <= destination.getN(); j++) {
				double acc = 0;
				for (int k = 1; k <= this.n; k++) {
					acc += (this.get(i, k) * factor.get(k, j));
				}
				destination.insert(i, j, acc);
			}
		}
		return destination;
	}

	/**
	 * Add a m x n Matrix with a given m x n Matrix. This Matrix has to be equal
	 * to the given Matrix.
	 * 
	 * @param m
	 *            the given Matrix for a m x n Matrix.
	 * @return New Matrix.
	 */

	@Override
	public abstract Matrix add(Matrix m);

	protected Matrix add_(Matrix m, Matrix destination) {
		if (!isSameLength(m))
			throw new IndexOutOfBoundsException("mxn do not equal this matrix");
		for (int i = 1; i <= this.m; i++) {
			for (int j = 1; j <= this.n; j++) {
				destination.insert(i, j, this.get(i, j) + m.get(i, j));
			}
		}
		return destination;
	}

	/**
	 * Multiply this Matrix to the power of n. It is only possible to Multiply a
	 * Matrix if M and N are equal.
	 * 
	 * @param exponent
	 *            multiply the Matrix to the power of the exponent.
	 * @return New Matrix.
	 */

	@Override
	public abstract Matrix pow(int exponent);

	protected Matrix pow_(int exponent, Matrix destination) { // the exponent
																// goes from 1
														// to infinity
		AssertExponentValid(exponent);
		destination.copyFrom(this);
		// bei matrix hoch 1 wird die schleife nicht erst ausgefuehrt
		if (exponent == 1)
			return this;

		for (int i = 2; i <= exponent; i++) {
			destination = destination.mul(this);
		}

		return destination;
	}
	
	/**
	 * Multiply this Matrix to the power of n. It is only possible to Multiply a
	 * Matrix if M and N are equal.
	 * 
	 * @param exponent
	 *            multiply the Matrix to the power of the exponent.
	 * @return New Matrix.
	 */

	@Override
	public abstract Matrix powFast(int exponent);

	protected Matrix powFast_(int exponent, Matrix destination) { // the exponent
																// goes from 1
																// to infinity
		AssertExponentValid(exponent);
		// bei matrix hoch 1 wird die schleife nicht erst ausgefuehrt
		
		if (exponent == 1)
			return destination;
		if (exponent % 2 == 0) {
			destination = powFast_(exponent / 2, destination);
			destination = destination.mul(destination);
			return destination;
		}
		destination = powFast_((exponent-1) / 2, destination);
		destination = destination.mul(destination).mul(this);
		return destination;
	}

	/**
	 * Copies all the contents of the given Matrix into the current matrix. This
	 * is useful for changing the uses Matrix implementation. the contents are
	 * then availible in the implementation of "this" matrix.
	 * 
	 * @param source
	 *            the matrix of which the contents are to be taken
	 */
	@Override
	public void copyFrom(Matrix source) {
		if (!isSameLength(source))
			throw new IndexOutOfBoundsException("mxn do not equal this matrix");
		for (int i = 1; i <= this.getM(); i++) {
			for (int j = 1; j <= this.getN(); j++) {
				this.insert(i, j, source.get(i, j));
			}
		}
	}

	// Identities
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
			for (int j = 1; j <= this.getN(); j++) { // compare Doubles with
														// delta = 0.0000001
				if (Double.compare(Math.abs(this.get(i, j) - m.get(i, j)),
						0.0000001) > 0)
					return false;
			}
		}
		return true;
	}

	// Exceptions

	/**
	 * Check if a Matrix is of same length than another.
	 * 
	 * @param m
	 *            Matrix that is compared to another
	 * @return Boolean Value.
	 */
	@Override
	public boolean isSameLength(Matrix m) {
		return this.m == m.getM() && this.n == m.getN();
	}

	/**
	 * Check if a m of a Matrix is of same length than the n of another.
	 * 
	 * @param m
	 *            Matrix that is compared to another
	 * @return Boolean Value.
	 */
	@Override
	public boolean assertMulLength(Matrix m) {
		return this.n == m.getM();
	}

	/**
	 * Check if two indices are within the bound of a m x n Matrix.
	 * 
	 * @param i
	 *            Integer for the m dimension.
	 * @param j
	 *            Integer for the n dimension.
	 */
	@Override
	public void outOfBound(int i, int j) {
		if (!(0 <= i && i < m) || !(0 <= j && j < n))
			throw new IndexOutOfBoundsException();
	}

	/**
	 * Check if an exponent is valid, that means zero is not a valid integer.
	 * 
	 * @param exp
	 *            Integer has to be greater than zero.
	 */
	@Override
	public void AssertExponentValid(int exp) {
		if (exp < 1) {
			try {
				throw new Exception("Matrix to the power of " + exp
						+ "won't be computed.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
