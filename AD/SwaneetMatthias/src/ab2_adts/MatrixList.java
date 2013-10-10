package ab2_adts;

import java.util.ArrayList;
import java.util.List;

public class MatrixList extends AbstractMatrix {

	List<Element> elems = new ArrayList<>();

	public MatrixList(int m, int n) {
		super(m,n);
		// all elements are initialized with 0
		// therefore the elements don't need to be added into the list
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
	public double get(int i, int j) {
		outOfBound(i-1, j-1);
		for (Element e : elems) {
			if (e.getI() == i && e.getJ() == j)
				return e.getValue();
		}
		return 0.0;
	}

}
