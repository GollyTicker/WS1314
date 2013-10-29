package ab3_adts;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import ab1_adts.statmodule.AverageVariance;
import ab1_adts.statmodule.IAverageVariance;
import ab2_adts.generator.GeneratorModule;
import ab2_adts.matrix.*;

public class Aufgabe4_Matrizenpotenzen {

	static int n = 45;
	static int tMax = 5;
	static List<Integer> expValues = new ArrayList<>();

	@Test
	public void test() {
		
		for(int k = 1; k < n; k++){
			expValues.add(Integer.valueOf(k));
		}

		Map<Integer, List<Double>> messwerteNormal = new HashMap<>();
		Map<Integer, List<Double>> messwerteFast = new HashMap<>();

		GeneratorModule gm = new GeneratorModule(n, n, 0.01); // p = 0.01
		gm.generateRandomMatrix();

		Matrix m = new MatrixArrayList(n, n);
		m.copyFrom(gm.getMatrix());

		Matrix mFast = new MatrixArrayList(n, n);
		mFast.copyFrom(m);

		for (Integer i : expValues) {
			messwerteNormal.put(i, new ArrayList<Double>());
			messwerteFast.put(i, new ArrayList<Double>());
		}

		for (int t = 0; t < tMax; t++) {
			for (Integer i : expValues) {

				m.resetAccessCount();
				m.pow(i.intValue());
				Double value = Double.valueOf(m.accessCount());
				messwerteNormal.get(i).add(value);

				mFast.resetAccessCount();
				mFast.powFast(i.intValue());
				Double value2 = Double.valueOf(mFast.accessCount());
				messwerteFast.get(i).add(value2);
				
				System.out.println(i.toString() + "executed" + System.nanoTime());

			}
		}
		
		// turn the values into Statistics Objects
		Map<Integer, IAverageVariance> statsNormal = refact(messwerteNormal);
		Map<Integer, IAverageVariance> statsFast = refact(messwerteFast);
		
		System.out.println("\n\n\n\n======================Output==================\n\n");
		// for to copy into Excel

		System.out.println("k:");
		for(Integer k : expValues){
			System.out.println(k);
		}
		System.out.println("NormalPotAverage:");
		for(Integer k : expValues){
			System.out.println(statsNormal.get(k).getAverage());
		}
		System.out.println("FastPotAverage:");
		for(Integer k : expValues){
			System.out.println(statsFast.get(k).getAverage());
		}
		
		
		//Diskussion:
		// gleiches ergebnis wie bei zahlenpotenzierung nur halt auf matrizen statt zahlen

	}

	public static Map<Integer, IAverageVariance> refact(
			Map<Integer, List<Double>> messwerte) {
		Map<Integer, IAverageVariance> stats = new HashMap<>();
		for (Map.Entry<Integer, List<Double>> entryNormal : messwerte
				.entrySet()) {
			IAverageVariance av = new AverageVariance();
			av.addValues(entryNormal.getValue());
			stats.put(entryNormal.getKey(), av);
		}
		return stats;
	}

}
