package ab2_adts;

import java.util.ArrayList;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class MatrixArrayList extends AbstractMatrix {

	private ArrayList<ArrayList<Double>> mArray = new ArrayList<ArrayList<Double>>();

	/**
	 * Creates a Matrix from a nested ArrayList.
	 * 
	 * @param m
	 *            m dimension of a Matrix
	 * @param n
	 *            n dimension of a Matrix
	 */
	public MatrixArrayList(int m, int n) {
		super(m, n);
		for (int i = 0; i < m; i++) {
			ArrayList<Double> tmpi = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				tmpi.add(0.0);
			}
			this.mArray.add(tmpi);
		}
	}

	/**
	 * Inserts a double value into the current ArrayList in row i at position j.
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
		this.mArray.get(i - 1).add(j - 1, value);
	}

	/**
	 * Gets the value out of the current ArrayList by row i at position j.
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
		return this.mArray.get(i - 1).get(j - 1);
	}

}
