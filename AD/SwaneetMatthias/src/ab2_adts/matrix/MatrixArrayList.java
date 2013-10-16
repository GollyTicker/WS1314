package ab2_adts.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class MatrixArrayList extends AbstractMatrix {

	// private ArrayList<ArrayList<ArrayListElement>> mArray = new
	// ArrayList<ArrayList<ArrayListElement>>();
	private List<ArrayListElement>[] mArray;

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
		mArray = new List[m];
		for (int i = 0; i < m; i++) {
			ArrayList<ArrayListElement> tmpi = new ArrayList<>();
			// no elements need to be added since all are already initialized
			// with zero
			this.mArray[i] = tmpi;
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
		// only non zero elements are saved
		if (Double.compare(value, 0.0) != 0) {
			for (ArrayListElement elem : mArray[i - 1]) {
				accessCount += 1; // durchsuchen des elements
				if (elem.getJ() == (j - 1)) {
					mArray[i - 1].remove(elem);
					break;
				}
			}
			this.mArray[i - 1].add(new ArrayListElement(j - 1, value));
			accessCount += 1; // hinzufuegen eines NonZero elements
		}
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
		for (ArrayListElement elem : this.mArray[i - 1]) {
			accessCount += 1; // access auf das Element
			if (elem.getJ() == (j - 1)) {
				return elem.getValue();
			}
		}
		return 0.0;
	}

	/**
	 * 
	 * Returns the number of saved values in the current implementation
	 * 
	 * @return noOfUsedMemoryBlocks
	 */
	@Override
	public int memoryUsage() {
		int noOfElems = 0;
		for (int i = 0; i < mArray.length; i++)
			noOfElems += mArray[i].size();
		return noOfElems;
	}

	@Override
	public Matrix mul(Matrix factor) {
		return super.mul_(factor,
				new MatrixArrayList(this.getM(), factor.getN()));
	}

	@Override
	public Matrix add(Matrix m) {
		return super.add_(m, new MatrixArrayList(this.getM(), this.getN()));
	}

	@Override
	public Matrix mul(double skalar) {
		return super
				.mul_(skalar, new MatrixArrayList(this.getM(), this.getN()));
	}

	@Override
	public Matrix pow(int exponent) {
		return super.pow_(exponent,
				new MatrixArrayList(this.getM(), this.getN()));
	}
}
