package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    int[] openTimes;
    int experimentTimes;
    public PercolationStats(int N, int T, PercolationFactory pf)   // perform T independent experiments on an N-by-N grid
    {
        if(N<=0||T<=0)throw new IllegalArgumentException("N And T Should NOT be less or equal to 0!");
        experimentTimes=T;
        openTimes=new int[experimentTimes];
        for(int i=0;i<experimentTimes;i++)
        {
            Percolation percolationModel=pf.make(N);
            while(!percolationModel.percolates())
            {
                int openX= StdRandom.uniform(N);
                int openY= StdRandom.uniform(N);
                percolationModel.open(openX,openY);
            }
            openTimes[i]=percolationModel.numberOfOpenSites();
        }
    }
    public double mean()                                           // sample mean of percolation threshold
    {
        double sum=0;
        for(int i=0;i<openTimes.length;i++)
        {
            sum+=openTimes[i];
        }
        return sum/(double) experimentTimes;
    }
    public double stddev()                                         // sample standard deviation of percolation threshold
    {
        double Mean=mean();
        double son=0;
        for(int i=0;i<openTimes.length;i++)
        {
            son+=Math.pow((openTimes[i]-Mean),2);
        }
        double stddev_2=son/(double)(experimentTimes-1);
        return Math.sqrt(stddev_2);
    }
    public double confidenceLow()                                  // low endpoint of 95% confidence interval
    {
        double T_1_2=Math.sqrt(experimentTimes);
        return mean()-(1.96*stddev()/T_1_2);
    }
    public double confidenceHigh()                                 // high endpoint of 95% confidence interval
    {
        double T_1_2=Math.sqrt(experimentTimes);
        return mean()+(1.96*stddev()/T_1_2);
    }
}
