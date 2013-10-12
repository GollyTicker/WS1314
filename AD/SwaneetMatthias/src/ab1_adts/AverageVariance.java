package ab1_adts;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */

public final class AverageVariance {

	// Population Size
	private double n = 0;

	// accumulated sum of values
	private double accumAvg = 0;
	private double accumAvgSquare = 0;
	private List<Double> values = new ArrayList<>();

	// DecimalFormat for precision
	private DecimalFormat df = new DecimalFormat("#.##");

	// Multiple Constructors for adding values
	/**
	 * Standard-Constructor for calculating average and variance.
	 */
	public AverageVariance() {
	}

	/**
	 * Extended Constructor with initial value.
	 * 
	 * @param firstValue
	 *            the first value to be added
	 */
	public AverageVariance(double firstValue) {
		addValue(firstValue);
	}

	// Add one value incrementing accumSum by value each round
	/**
	 * Adds a measurement/value for later calculation
	 * 
	 * @param value
	 *            the value to be added
	 */
	public void addValue(double value) {
		values.add(value);
		accumAvg += value;
		n += 1;
		accumAvgSquare += Math.pow(value, 2);
	}

	// Bulk Operation calling addValue on each
	/**
	 * Adds multiple measurements/values given as a List<Double> at once
	 * 
	 * @param values
	 *            a List<Double> of the measurements/values
	 */
	public void addValues(List<Double> values) {
		for (double value : values)
			addValue(value);
	}

	/**
	 * Returns the number accumulated measurements/values.
	 * 
	 * @return n - the population size
	 */
	public double getN() {
		return this.n;
	}

	// accumulated sum of values divided by population-size
	/**
	 * Returns the average of the accumulated measurements/values.
	 * 
	 * @return average
	 * 
	 */
	public double getAverage() {
		return this.getAverageAccumulated();
	}
	
	public double getAverageAccumulated() {
		return this.accumAvg / this.n;
	}

	/**
	 * Returns the variance of the measurements/values.
	 * 
	 * @return variance
	 * 
	 */
	public double getVariance() {
		// There is no variance with one value
		if (this.n > 1) {
			return calculateVarianceAccumulated() * (1.0 / (this.n-1.0));
		} else
			return 0.0;
	}

	// According to the "Satz von Steiner" (Verschiebungssatz)
	// 1/(n-1)* sum((X1..n - Xavg)^2) -> 1/(n-1) * sum((X1..n)^2) -
	// 1/n*sum((X1..n))^2
	private double calculateVarianceAccumulated() {
		return (accumAvgSquare - ((1.0 / this.n) * Math.pow(accumAvg, 2)));
	}

	private double calculateVarianceExplicit() {
		double acc = 0;
		for (double d : values)
			acc += Math.pow(d - getAverage(), 2);
		return acc;
	}

	@Override
	public String toString() {
		return "Average: " + df.format(getAverage()) + " Variance: "
				+ df.format(getVariance()) + "\twith a population-size n: "
				+ this.n;
	}

}
