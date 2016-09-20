import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
	         
	private int N;
	private boolean grid[][];
        private WeightedQuickUnionUF quHelper;
	public Percolation (int n){    // create n-by-n grid, with all sites blocked
		
                if(n<=0) throw new IllegalArgumentException("Illegal size of the board");		
		N = n;
		grid = new boolean[N][N];
		quHelper = new WeightedQuickUnionUF(N*N+2);
	
	}

	public void open(int i, int j){      // open site (row i, column j) if it is not open already
		if(i<1||i>N||j<1||j>N)
			throw new IndexOutOfBoundsException("open()");
		if(!grid[i-1][j-1]){
			grid[i-1][j-1] = true;
			if(isValid(i-1,j)&&isOpen(i-1,j))           // Union upper neighbor if it's valid open
				quHelper.union(N*(i-2)+j,N*(i-1)+j); 
			if(isValid(i+1,j)&&isOpen(i+1,j))	     // Union lower neighbor if it's open	
				quHelper.union(N*(i)+j,N*(i-1)+j);
			if(isValid(i,j-1)&&isOpen(i,j-1))           // Union left neighbor if it's open
				quHelper.union(N*(i-1)+j-1,N*(i-1)+j);
			if(isValid(i,j+1)&&isOpen(i,j+1))           // Union right neighbor if it's open
				quHelper.union(N*(i-1)+j+1,N*(i-1)+j);
		
			if(i==1)
				quHelper.union(0,j);    // connect to the source(virtual top) if the site is in the first row
			if(i==N)
				quHelper.union(N*N+1,N*(N-1)+j); //connect to the sink(virtual bottom) if the site is in the last row 
		}
	}

        public boolean isOpen(int i, int j){   // is site (row i, column j) open?
		if(i<1||i>N||j<1||j>N) 
			throw new IndexOutOfBoundsException("isOpen()");
		return grid[i-1][j-1] == true;
	}

	private boolean isValid(int i, int j){
		return !(i<1||i>N||j<1||j>N);
			
	}

	public boolean isFull(int i, int j){  // is site (row i, column j) full?
		return quHelper.connected(0,N*(i-1)+j);
	}

	public boolean percolates(){    // does the system percolate?
		return quHelper.connected(0,N*N+1);
	}
   	
	public static void main(String[] args) {
		Percolation per = new Percolation(3);
		System.out.print("System percolates: ");
		System.out.println(per.percolates());
		per.open(1,2);
		System.out.println("(1,2) is now open");
		System.out.print("System percolates: ");
		System.out.println(per.percolates());
		per.open(2,3);
		System.out.println("(2,3) is now open");
		System.out.print("System percolates: ");
		System.out.println(per.percolates());
		per.open(1,3);
		System.out.println("(3,3) is now open");
		System.out.print("System percolates: ");
		System.out.println(per.percolates());
		per.open(2,2);
		System.out.println("(2,2) is now open");
		System.out.print("System percolates: ");
		System.out.println(per.percolates());

		System.out.println("Empty");
		
	}	   
}
