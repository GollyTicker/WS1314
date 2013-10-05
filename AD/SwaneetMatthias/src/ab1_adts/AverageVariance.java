package ab1_adts;

import java.text.DecimalFormat;
import java.util.List;

public final class AverageVariance {

	// Population Size
	private double n = 0;

	// accumulated sum of values
	private double accumAvg = 0;
	private double accumAvgSquare = 0;

	// DecimalFormat for precision
	private DecimalFormat df = new DecimalFormat("#.##");

	// Mutpiple Constructors for adding values
	public AverageVariance() {
	}

	public AverageVariance(double firstValue) {
		addValue(firstValue);
	}

	public AverageVariance(double... firstValue) {
		for (double d : firstValue)
			addValue(d);
	}

	public AverageVariance(List<Double> firstValues) {
		addValues(firstValues);
	}

	// Add one value incrementing accumSum by value each round
	public void addValue(double value) {
		accumAvg += value;
		n += 1;
		accumAvgSquare += Math.pow(value, 2);
	}

	// Bulk Operation calling addValue on each
	public void addValues(List<Double> vals) {
		for (double value : vals)
			addValue(value);
	}

	public void addValues(double... vals) {
		for (double value : vals)
			addValue(value);
	}

	public double getN() {
		return this.n;
	}

	// accumulated sum of values divided by population-size
	public double getAverage() {
		return this.accumAvg / this.n;
	}

	public double getVariance() {
		// There is no variance with one value
		if (this.n > 1) {
			return calculateVariance() * (1.0 / (this.n - 1.0));
		} else
			return 0.0;
	}

	// Nach dem Satz von Steiner (Verschiebungssatz)
	// 1/(n-1)* sum((X1..n - Xavg)^2) -> 1/(n-1) * sum((X1..n)^2) -
	// 1/n*sum((X1..n))^2
	private double calculateVariance() {
		return (accumAvgSquare - ((1.0 / this.n) * Math.pow(accumAvg, 2)));
	}

	@Override
	public String toString() {
		return "Average: " + df.format(getAverage()) + " Variance: "
				+ df.format(getVariance()) + "\twith a population-size n: "
				+ this.n;
	}

}
