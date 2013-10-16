package ab2_adts.matrix;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public interface Matrix {

	void insert(int i, int j, double value);

	int getM();

	int getN();

	double get(int i, int j);

	void copyFrom(Matrix source);
	
	int memoryUsage();
	
	int accessCount();
	
	void resetAccessCount();

	Matrix add(Matrix m);

	Matrix mul(double skalar);

	Matrix mul(Matrix factor);

	Matrix pow(int exponent);

}
