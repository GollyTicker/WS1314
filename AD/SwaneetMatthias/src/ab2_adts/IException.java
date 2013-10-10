package ab2_adts;

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
