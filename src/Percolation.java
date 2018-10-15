import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private static final int[] D_ROW = {-1, 0, 1, 0};
	private static final int[] D_COL = {0, 1, 0, -1};
	
	private boolean[][] arr;
	// uf tests "pseudo-connection" for percolation(), ufReal tests real connection for isFull()
	private WeightedQuickUnionUF uf;
	private WeightedQuickUnionUF ufReal;
	private int numOpenSites;
	
	public Percolation(int n) {
		if (n <= 0) throw new IllegalArgumentException();
		arr = new boolean[n][n];
		uf = new WeightedQuickUnionUF(n * n);
		ufReal = new WeightedQuickUnionUF(n * n);
		numOpenSites = 0;
		// union top row to 0 and union bottom row to index n*n-1
		for (int i = 0; i < n; i++) {
			uf.union(0, i);
			uf.union(n * n - 1, conv(n - 1, i));
			ufReal.union(0, i);
		}
	}
	
	public boolean isOpen(int row, int col) {
		row--; col--;
		if (row < 0 || row >= arr.length || col < 0 || col >= arr.length) throw new IllegalArgumentException();
		
		return arr[row][col];
	}
	
	public boolean isFull(int row, int col) {
		row--; col--;
		if (row < 0 || row >= arr.length || col < 0 || col >= arr.length) throw new IllegalArgumentException();
		
		if (!isOpen(row + 1, col + 1)) return false;
		return ufReal.connected(0, conv(row, col));
	}
	
	public int numberOfOpenSites() {
		return numOpenSites;
	}
	
	public void open(int row, int col) {
		row--; col--;
		if (row < 0 || row >= arr.length || col < 0 || col >= arr.length) throw new IllegalArgumentException();
		
		if (arr[row][col]) return;
		
		arr[row][col] = true;
		numOpenSites++;
		
		for (int i = 0; i < 4; i++) {
			int newRow = row + D_ROW[i];
			int newCol = col + D_COL[i];
			if (newRow >= 0 && newRow < arr.length && newCol >= 0 && newCol < arr.length) {
				if (arr[newRow][newCol]) {
					int conv1 = conv(row, col);
					int conv2 = conv(newRow, newCol);
					uf.union(conv1, conv2);
					ufReal.union(conv1, conv2);
				}
			}
		}
	}
	
	public boolean percolates() {
		if (arr.length == 1 && !isOpen(1, 1)) return false;
		return uf.connected(0, arr.length * arr.length - 1);
	}
	
	private int conv(int row, int col) {
		return row * arr.length + col;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
