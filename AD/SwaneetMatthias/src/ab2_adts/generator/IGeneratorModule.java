package ab2_adts.generator;

import ab2_adts.matrix.Matrix;

public interface IGeneratorModule {

	// get the current Randomized Matrix
	Matrix getMatrix();

	// create a new Randomized Matrix and overrwrite the old one
	void generateRandomMatrix();

	// count all zeros in the current matrix (absolute)
	int countZero();

	// ratio between zeros and values in decimal <= 1.0
	double getRatio();

}
