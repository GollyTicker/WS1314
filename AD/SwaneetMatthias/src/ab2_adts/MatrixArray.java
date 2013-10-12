package ab2_adts;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class MatrixArray extends AbstractMatrix {

	private double[][] mArray;

	/**
	 * Creates a Matrix from a new two dimensional array.
	 * 
	 * @param m
	 *            m dimension of a Matrix
	 * @param n
	 *            n dimension of a Matrix
	 */
	public MatrixArray(int m, int n) {
		super(m, n);
		mArray = new double[m][n];
	}

	/**
	 * Inserts a double value into the current array in row i at position j.
	 * 
	 * @param i
	 *            current column.
	 * @param j
	 *            current position.
	 * @param value
	 *            double to be inserted.
	 */
	@Override
	public void insert(int i, int j, double value) {
		outOfBound(i - 1, j - 1);
		mArray[i - 1][j - 1] = value;
	}

	/**
	 * Gets the value out of the current array by row i at position j.
	 * 
	 * @param i
	 *            current column.
	 * @param j
	 *            current position.
	 * @return double value to be at position i and j
	 */
	@Override
	public double get(int i, int j) {
		outOfBound(i - 1, j - 1);
		return mArray[i - 1][j - 1];
	}

	/**
	 * 
	 * Returns the number of saved values in the current implementation
	 * 
	 * @return noOfUsedMemoryBlocks
	 */
	@Override
	public int memoryUsage() {
		return m * n; // an 2-dim-array always uses all space for m*n values
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
	public Matrix mul(Matrix factor) {
		return super.mul_(factor,new MatrixArray(this.getM(), factor.getN()));
	}
	

	@Override
	public Matrix add(Matrix m) {
		return super.add_(m,new MatrixArray(this.getM(), this.getN()));
	}

	@Override
	public Matrix mul(double skalar) {
		return super.mul_(skalar,new MatrixArray(this.getM(), this.getN()));
	}

	@Override
	public Matrix pow(int exponent) {
		return super.pow_(exponent,new MatrixArray(this.getM(), this.getN()));
	}
}
