package ab3_adts;

import java.util.ArrayList;
import java.util.List;

import ab2_adts.generator.GeneratorModule;
import ab2_adts.matrix.Matrix;
import ab2_adts.matrix.MatrixList;

public class MatrixPotenceFastVsSlow {

	static final int n = 129;
	static final int maxExponent = n;
	static final double pNonZero = 0.1;

	public static void moin(String[] args) {
		
		int accessCountNormal;
		int accessCountFast;

		List<Long> expL = new ArrayList<>();
		List<Long> expFastL = new ArrayList<>();

		GeneratorModule gm = new GeneratorModule(n, n, pNonZero);
		gm.generateRandomMatrix();

		Matrix mFast = new MatrixList(n, n);
		mFast.copyFrom(gm.getMatrix());

		Matrix mSlow = new MatrixList(n, n);
		mFast.copyFrom(gm.getMatrix());

		for (int t = 0; t < 3; t++) {		// wiederhole t-oft
			for (int k = 1; k < maxExponent; k=k*2) {
											// es werden nicht alle Exponenten bis zum Maximum ausprobiert
											// Stattdessen werden die Exponenten in
											// verdoppelten Abständen durchgegangen (-> log. Skala im Diagramm)
				int m = 2;
				accessCountNormal = 0;
				accessCountFast = 0;
				int exp1 = exp(x, k);
				int exp2 = expFast(x, k);
				expL.add(accessCountNormal);
				expFastL.add(accessCountFast);

				System.out.println("matrix ^ " + k + " finished.");
			}
		}
		
		
		
		
		

		System.out.println(expL);
		System.out.println(expFastL);
	}
}
