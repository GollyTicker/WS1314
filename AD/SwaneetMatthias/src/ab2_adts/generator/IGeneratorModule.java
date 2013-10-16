package ab2_adts.generator;

import ab2_adts.matrix.Matrix;

public interface IGeneratorModule {

	Matrix getMatrix();

	void generateRandomMatrix();

	int countZero();

	double getRatio();

}
