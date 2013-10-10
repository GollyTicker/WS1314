package ab2_adts;

public class MatrixArray extends AbstractMatrix {

	private double[][] mArray;

	public MatrixArray(int m, int n) {
		super(m,n);
		mArray = new double[m][n];
	}

	@Override
	public void insert(int i, int j, double value) {
		outOfBound(i-1, j-1);
		mArray[i-1][j-1] = value;
	}

	@Override
	public double get(int i, int j) {
		outOfBound(i-1, j-1);
		return mArray[i-1][j-1];
	}
}
