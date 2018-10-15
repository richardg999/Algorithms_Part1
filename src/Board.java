import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	
	private static final int[] DROW = {0, -1, 0, 1};
	private static final int[] DCOL = {-1, 0, 1, 0};
	
	private final int[][] blocks;
	
	public Board(int[][] blocks) {
		if (blocks == null)
			throw new IllegalArgumentException();
		int[][] arr = new int[blocks.length][blocks.length];
		for (int row = 0; row < blocks.length; row++) {
			for (int col = 0; col < blocks.length; col++) {
				arr[row][col] = blocks[row][col];
			}
		}
		this.blocks = arr;
	}
	
	public int dimension() {
		return blocks.length;
	}
	
	public int hamming() {
		int count = 0;
		int index = 1;
		for (int[] row : blocks) {
			for (int block : row) {
				if (block != index)
					count++;
				index++;
				// break before last index
				if (index == blocks.length * blocks.length)
					break;
			}
		}
		return count;
	}
	
	public int manhattan() {
		int sum = 0;
		for (int row = 0; row < blocks.length; row++) {
			for (int col = 0; col < blocks.length; col++) {
				int block = blocks[row][col];
				if (block == 0) continue;
				// rowOrig and colOrig are zero-indexed
				int rowOrig = (block - 1) / blocks.length;
				int colOrig = (block - 1) % blocks.length;
				
				sum += Math.abs(row - rowOrig) + Math.abs(col - colOrig);
			}
		}
		return sum;
	}
	
	public boolean isGoal() {
		if (hamming() == 0)
			return true;
		else
			return false;
	}
	
	public Board twin() {
		int[][] twinBlocks = copyBlocks();
		// swap either first two blocks of first row or first two blocks of second row
		if (twinBlocks[0][0] == 0 || twinBlocks[0][1] == 0) {
			int temp = twinBlocks[1][0];
			twinBlocks[1][0] = twinBlocks[1][1];
			twinBlocks[1][1] = temp;
		}
		else {
			int temp = twinBlocks[0][0];
			twinBlocks[0][0] = twinBlocks[0][1];
			twinBlocks[0][1] = temp;
		}
		Board twin = new Board(twinBlocks);
		return twin;
	}
	
	public boolean equals(Object y) {
		if (y == this) return true;
		if (y == null) return false;
		if (y.getClass() != this.getClass()) return false;
		Board other = (Board) y;
		return Arrays.deepEquals(this.blocks, other.blocks);
	}
	
	public Iterable<Board> neighbors() {
		ArrayList<Board> neighbors = new ArrayList<>();
		// find blank square
		int blankRow = 0;
		int blankCol = 0;
		for (int row = 0; row < blocks.length; row++) {
			for (int col = 0; col < blocks.length; col++) {
				if (blocks[row][col] == 0) {
					blankRow = row;
					blankCol = col;
					break;
				}
			}
		}
		// generate neighbors
		for (int i = 0; i < 4; i++) {
			int newRow = blankRow + DROW[i];
			int newCol = blankCol + DCOL[i];
			if (newRow < 0 || newCol < 0 || newRow >= blocks.length || newCol >= blocks.length)
				continue;
			int[][] newBlocks = copyBlocks();
			newBlocks[blankRow][blankCol] = newBlocks[newRow][newCol];
			newBlocks[newRow][newCol] = 0;
			neighbors.add(new Board(newBlocks));
		}
		return neighbors;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder(blocks.length + "\n");
		for (int row = 0; row < blocks.length; row++) {
			for (int col = 0; col < blocks.length; col++) {
				result.append(String.format("%2d ", blocks[row][col]));
			}
			result.append("\n");
		}
		return result.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	private int[][] copyBlocks() {
		int[][] copyBlocks = new int[blocks.length][blocks.length];
		for (int row = 0; row < blocks.length; row++) {
			for (int col = 0; col < blocks.length; col++) {
				copyBlocks[row][col] = blocks[row][col];
			}
		}
		return copyBlocks;
	}

}
