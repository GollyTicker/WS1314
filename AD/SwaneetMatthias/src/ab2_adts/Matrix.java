package ab2_adts;

public interface Matrix {

	public Matrix add(Matrix m);
	
	public Matrix mul(double skalar);
	
	public Matrix mul(Matrix factor);
	
	public Matrix pow(int exponent);
	//Die Grundoperationen einer Matrix sind die Initialisierung (wie), die Addition, die ska-
	// lare Multiplikation, die (Matrix-)Multiplikation und die Potenzierun
}
