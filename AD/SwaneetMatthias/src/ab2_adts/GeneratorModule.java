package ab2_adts;

public class GeneratorModule {

	public static void main(String[] args) {
		GeneratorModule gm = new GeneratorModule(20, 20, 0.9);
		gm.generateRandomMatrix();
		// System.out.println(gm.getMatrix());
		// System.out.println(gm.getRatio());
	}

	private double probabilityForZero;
	private Matrix matrix;
	private double MAX_VAL = 2000.0;
	private double MIN_VAL = -2000.0;
	private int m;
	private int n;

	public GeneratorModule(int m, int n, double probabilityForZero) {
		this.probabilityForZero = probabilityForZero;
		this.m = m;
		this.n = n;
		generateRandomMatrix();
	}

	public Matrix getMatrix() {
		return this.matrix;
	}

	public void generateRandomMatrix() {
		matrix = new MatrixArray(m, n);
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				matrix.insert(i, j, getRdmDouble());
			}
		}
	}

	public int countZero() {
		int count = 0;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (Double.compare(matrix.get(i, j), 0.0) == 0) {
					count += 1;
				}
			}
		}
		return count;
	}

	public double getRatio() {
		return ((double) countZero()) / ((double) (m * n));
	}

	private double getRdmDouble() {
		double acc = 0;
		if (Math.random() < this.probabilityForZero) {
			acc = 0.0;
		} else {
			if (Math.random() < 0.5)
				acc = Math.random() * MAX_VAL;
			else
				acc = Math.random() * MIN_VAL;
		}
		return acc;
	}

}
