package GKA_A2.Matrix;


/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public interface IException {
	
	boolean isSameLength(Matrix m);

	boolean assertMulLength(Matrix m);

	void outOfBound(int i, int j);

	void AssertExponentValid(int exp);
}
