package ab1_adts.statmodule;

import java.util.List;

public interface IAverageVariance {
	
	void addValue(double value);
	
	void addValues(List<Double> values);
	
	double getN();
	
	double getAverage();
	
	double getVariance();

}
