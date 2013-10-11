package ab2_adts;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class MatrixList extends AbstractMatrix {

	List<Element> elems = new ArrayList<>();

	/**
	 * Creates a Matrix from a List.
	 * 
	 * @param m
	 *            m dimension of a Matrix
	 * @param n
	 *            n dimension of a Matrix
	 */
	public MatrixList(int m, int n) {
		super(m, n);
		// all elements are initialized with 0
		// therefore the elements don't need to be added into the list
	}

	/**
	 * Inserts a double value into the current List in row i at position j.
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
		for (Element e : elems) {
			if (e.getI() == i && e.getJ() == j) {
				elems.remove(e);
			}
		}
		if (Double.compare(value, 0.0) != 0) { // only non zero values need to
												// be added
			elems.add(new Element(i, j, value));
		}
	}

	/**
	 * Gets the value out of the current List by row i at position j.
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
		for (Element e : elems) {
			if (e.getI() == i && e.getJ() == j)
				return e.getValue();
		}
		return 0.0;
	}

}
