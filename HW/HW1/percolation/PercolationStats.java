/* *****************************************************************************
 *  Name: Jing Yee Lin
 *  Date: 4/19/2020
 *  Description: Calculate the percolation stats for HW1
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats
{
	private double ans_mean;
	private double stdDev_ans;
	private int totalT;

	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials)
	{
		Percolation perc;
		double ans[];

		if (n <= 0 || trials <= 0)
		{
			throw new IllegalArgumentException("Out of bound");
		}

		int newRow;
		int newCol;
		int total;
		double single_ans = 0;
		ans = new double[trials];
		totalT = trials;
		stdDev_ans = 0;
		for (int i = 0; i < trials; i++)
		{
			perc = new Percolation(n);
			newRow = 0;
			newCol = 0;
			total = 0;
			while (!perc.percolates())
			{

				newRow = StdRandom.uniform(n) + 1;
				newCol = StdRandom.uniform(n) + 1;
				perc.open(newRow, newCol);
			}
			total = perc.numberOfOpenSites();
			single_ans = total * 1.0 / (n * n);
			//ans_mean += single_ans;
			ans[i] = single_ans;
		}
		//ans_mean = ans_mean / totalT;
		ans_mean = StdStats.mean(ans);
		stdDev_ans = StdStats.stddev(ans);
		/*for (int i = 0; i < ans.length; i++)
		{
			stdDev_ans += (ans[i] - ans_mean) * (ans[i] - ans_mean);
		}
		stdDev_ans = stdDev_ans / (totalT - 1);
		stdDev_ans = Math.sqrt(stdDev_ans);*/

	}

	// sample mean of percolation threshold
	public double mean()
	{

		return ans_mean;
	}

	// sample standard deviation of percolation threshold
	public double stddev()
	{
		return stdDev_ans;
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo()
	{
		double tempT = 1.0 * totalT;
		double conLo = ans_mean - ((1.96 * stdDev_ans) / Math.sqrt(tempT));
		return conLo;
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi()
	{
		double tempT = 1.0 * totalT;
		double conHi = ans_mean + ((1.96 * stdDev_ans) / Math.sqrt(tempT));
		return conHi;
	}

	// test client (see below)
	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		int total = Integer.parseInt(args[1]);
		PercolationStats newPerc = new PercolationStats(n, total);
		System.out.println("mean:                     " + newPerc.mean());
		System.out.println("stddev:                   " + newPerc.stddev());
		System.out.println("95% confidence itnerval = [" + newPerc.confidenceLo() + ", " + newPerc
				.confidenceHi() + "]");
	}
}
