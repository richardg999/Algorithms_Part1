import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private double[] data;
	private double mean;
	private double stddev;
	
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
		
		data = new double[trials];
		
		for (int i = 0; i < trials; i++) {
			Percolation perco = new Percolation(n);
			int count = 0;
			
			while (!perco.percolates()) {
				int randRow = 0;
				int randCol = 0;
				do {
					//randRow = (int) (Math.random() * n) + 1;
					//randCol = (int) (Math.random() * n) + 1;
					randRow = StdRandom.uniform(1, n + 1);
					randCol = StdRandom.uniform(1, n + 1);
				} while (perco.isOpen(randRow, randCol));
				perco.open(randRow, randCol);
				count++;
			}
			
			data[i] = (double) count / (n * n);
		}
	}
	
	public double mean() {
		/*double total = 0;
		for (double num : data) {
			total += num;
		}
		double mean = total / data.length;
		return mean;*/
		mean = StdStats.mean(data);
		return mean;
	}
	
	public double stddev() {
		if (data.length == 1) return Double.NaN;
		
		/*double mean = mean();
		double total = 0;
		for (double num : data) {
			total += Math.pow(num - mean, 2);
		}
		double stddev = Math.sqrt(total / (data.length - 1));
		return stddev;*/
		stddev = StdStats.stddev(data);
		return stddev;
	}
	
	public double confidenceLo() {
		if (mean == 0) mean();
		if (stddev == 0) stddev();
		return mean - 1.96 * stddev / Math.sqrt(data.length);
	}
	
	public double confidenceHi() {
		if (mean == 0) mean();
		if (stddev == 0) stddev();
		return mean + 1.96 * stddev / Math.sqrt(data.length);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = StdIn.readInt();
		int T = StdIn.readInt();
		
		PercolationStats percoStats = new PercolationStats(n, T);
		
		StdOut.printf("%-23s = %f\n", "mean", percoStats.mean());
		StdOut.printf("%-23s = %f\n", "stddev", percoStats.stddev());
		StdOut.printf("%-23s = [%f, %f]\n", "95% confidence interval", percoStats.confidenceLo(), percoStats.confidenceHi());
	}

}
