import edu.princeton.cs.algs4.*;


public class PercolationStats{
	
	private double[] results;
	private int T;

	public PercolationStats(int n, int trials){  // perform trials independent experiments on an n-by-n grid
		if(n<=0||trials<=0)
			throw new IllegalArgumentException("Can't Initalize Stats");		
		T = trials;
		results = new double[T];
 		int[] f = new int[n];
		for(int i=0; i<n; i++){
			f[i] = 1;
		}
		for(int i=0; i<T; i++){
			Percolation per = new Percolation(n);
			int openSites = 0;
			while(!per.percolates()){
				// randomly open a site
				int row = StdRandom.discrete(f)+1;
				int column = StdRandom.discrete(f)+1;
				//System.out.println("row = "+row+";column = "+column);
				while(per.isOpen(row,column)){
					row = StdRandom.discrete(f)+1;
					row = StdRandom.discrete(f)+1;		
				}
				per.open(row,column);
				openSites++;			
			}
			//System.out.println("Open sites: "+openSites);
			results[i] = openSites/(n*n+0.0);	
		} 	    
	}  
	

	public double mean(){     // sample mean of percolation threshold
		return StdStats.mean(results);
	}
			
	public double stddev(){  // sample standard deviation of percolation threshold
		return StdStats.stddev(results);
	}

	public double confidenceLo(){ // low  endpoint of 95% confidence interval
		return mean()-1.96*stddev()/Math.sqrt(T);
	}

	public double confidenceHi(){  // high endpoint of 95% confidence interval
		return mean()+1.96*stddev()/Math.sqrt(T);
	}
	

	public static void main(String[] args){
		PercolationStats stats = new PercolationStats(StdIn.readInt(),StdIn.readInt());
		System.out.print("mean                     = ");
		System.out.println(stats.mean());
		System.out.println("stddev                   = "+stats.stddev());
		System.out.print("95% confidencce interval =");
		System.out.println(stats.confidenceLo()+", "+stats.confidenceHi());
	}
}
