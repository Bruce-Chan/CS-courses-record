package hw2;

import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    List<Integer> thresholdLst = new ArrayList<>();

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T)   {
        if(N>=0||T<=0){
            throw new IllegalArgumentException("N: "+N+" T: "+T);
        }
        List<Integer> openedSites = new ArrayList<>();
        for(int i = 0; i < T; i++){
            Percolation perc = new Percolation(N);
            int row = StdRandom.uniform(N);
            int col = StdRandom.uniform(N);
            while(perc.percolates()==false){
                while(perc.isOpen(row,col)){ // check if this site is opened
                    row = StdRandom.uniform(N);
                    col = StdRandom.uniform(N);
                }
                perc.open(row,col);
            }
            int threshold = perc.openSitesSize;
            thresholdLst.add(threshold);
        }
    }
    // sample mean of percolation threshold
    public double mean(){
        int sum = 0;
        for(int thres : thresholdLst){
            sum+=thres;
        }
        return sum/thresholdLst.size();
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        double mean = mean();
        double squareSum = 0.0;
        for(int thres : thresholdLst){
            squareSum += Math.pow(thres-mean,2);
        }
        return Math.sqrt(squareSum/(thresholdLst.size()-1));
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLow(){
        double mean = mean();
        double dev = stddev();
        int T = thresholdLst.size();
        return mean-(1.96*dev)/Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        double mean = mean();
        double dev = stddev();
        int T = thresholdLst.size();
        return mean+(1.96*dev)/Math.sqrt(T);
    }
}                       
