package ab2_adts;

import java.util.ArrayList;

public class MatrixArrayList extends AbstractMatrix {

	private ArrayList<ArrayList<Double>> mArray = new ArrayList<ArrayList<Double>>();

	public MatrixArrayList(int m, int n) {
		super(m,n);
		for (int i = 0; i < m; i++) {
			ArrayList<Double> tmpi = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				tmpi.add(0.0);
			}
			this.mArray.add(tmpi);
		}
	}

	@Override
	public void insert(int i, int j, double value) {
		outOfBound(i-1,j-1);
		this.mArray.get(i-1).add(j-1, value);
	}

	@Override
	public double get(int i, int j) {
		outOfBound(i-1,j-1);
		return this.mArray.get(i-1).get(j-1);
	}
	
}
