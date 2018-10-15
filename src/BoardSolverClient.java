import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BoardSolverClient {

	public static void main(String[] args) {

	    // create initial board from file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);
	    /*System.out.println(initial.toString());
	    System.out.println(initial.hamming());
	    System.out.println(initial.manhattan());
	    System.out.println(initial.isGoal());
	    //System.out.println(initial.twin());
	    for (Board board : initial.neighbors()) {
	    	System.out.println(board);
	    }*/
	    
	    
	    
	    // solve the puzzle
	    Solver solver = new Solver(initial);
	    
	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	}

}
