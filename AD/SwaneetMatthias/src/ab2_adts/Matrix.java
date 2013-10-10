package ab2_adts;

public interface Matrix {
	
	void insert(int i, int j, double value);
	
	int getI();
	
	int getJ();
	
	double get(int i, int j);
	
	Matrix add(Matrix m);
	
	Matrix mul(double skalar);
	
	Matrix mul(Matrix factor);
	
	Matrix pow(int exponent);
}
