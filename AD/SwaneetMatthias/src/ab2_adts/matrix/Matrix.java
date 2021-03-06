package ab2_adts.matrix;

import ad_utils.ITimeSpace;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public interface Matrix extends ITimeSpace{

	// insert an element in m x n Matrix with value
	void insert(int i, int j, double value);

	// get dimension m
	int getM();

	// get dimension n
	int getN();

	// get elem at given indizes
	double get(int i, int j);

	// make a deep copy for every matrix and take the type of the source
	void copyFrom(Matrix source);

	// All implementations create a new Object -> Immutable
	Matrix add(Matrix m);

	Matrix mul(double skalar);

	Matrix mul(Matrix factor);

	// O(n) implementation of pow
	Matrix pow(int exponent);

	// O(log(n)) Implementation of pow
	Matrix powFast(int exponent);

}
