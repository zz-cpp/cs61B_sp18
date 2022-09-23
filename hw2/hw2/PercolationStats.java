package hw2;


import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

import java.util.Date;
import java.util.Random;

import static java.lang.Math.sqrt;

public class PercolationStats {

    // experiments number
    int experimentNum;

    int[] samples;

    int rowLength;

    double mean;

    double stddev;

    PercolationFactory pf;

    public PercolationStats(int N, int T, PercolationFactory pf) {

        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("PercolationStats: Constructor");
        }
        rowLength = N;
        samples = new int[T];
        this.pf = pf;
        experimentNum = T;
    }

    private void simulation() {

        for (int i = 0; i < experimentNum; i++) {
            StdRandom.setSeed(new Date().getTime());
            Percolation p = this.pf.make(rowLength);
            while (true) {
                int x = StdRandom.uniform(rowLength);
                int y = StdRandom.uniform(rowLength);
                p.open(x, y);
                if (p.percolates()) {
                    samples[i] = p.numberOfOpenSites();
                    break;
                }
            }
        }
    }

    /**
     * sample mean of percolation threshold
     * */
    public double mean() {
        if (samples[0] == 0) {
            simulation();
        }
        return  this.mean = StdStats.mean(samples);
    }

    /**
     * sample standard deviation of percolation threshold
     * */
    public double stddev() {
        if (samples[0] == 0) {
            simulation();
        }
        return  this.stddev = StdStats.stddev(samples);
    }

    /**
     * low endpoint of 95% confidence interval
     * */
    public double confidenceLow() {
        return mean - 1.96 * sqrt(stddev) / sqrt(experimentNum);

    }

    /**
     * high endpoint of 95% confidence interval
     * */
    public double confidenceHigh() {
        return mean - 1.96 * sqrt(stddev) / sqrt(experimentNum);
    }



}
