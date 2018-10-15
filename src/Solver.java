import java.util.ArrayList;
import java.util.Comparator;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
	
	private boolean isSolvable;
	private int numMoves;
	private ArrayList<Board> solutionList;
	
	private class Node {
		Board board;
		Node predecessor;
		int moves;
		int manhattan;
		public Node(Board board, Node predecessor, int moves) {
			this.board = board;
			this.predecessor = predecessor;
			this.moves = moves;
			manhattan = board.manhattan();
		}
	}
	
	private static class ManhattanComparator implements Comparator<Node> {
		public int compare(Node a, Node b) {
			return (a.manhattan + a.moves) - (b.manhattan + b.moves);
		}
	}
	
	public Solver(Board initial) {
		if (initial == null) throw new IllegalArgumentException();
		
		MinPQ<Node> pq = new MinPQ<>(new ManhattanComparator());
		MinPQ<Node> pqTwin = new MinPQ<>(new ManhattanComparator());
		pq.insert(new Node(initial, null, 0));
		pqTwin.insert(new Node(initial.twin(), null, 0));
		
		while (true) {
			Node node = pq.delMin();
			Board board = node.board;
			Node predecessor = node.predecessor;
			int moves = node.moves;
			if (board.isGoal()) {
				isSolvable = true;
				numMoves = moves;
				solutionList = new ArrayList<>();
				solutionList.add(board);
				while (predecessor != null) {
					solutionList.add(0, predecessor.board);
					predecessor = predecessor.predecessor;
				}
				break;
			}
			for (Board neighbor : board.neighbors()) {
				if (predecessor != null && neighbor.equals(predecessor.board)) 
					continue;
				pq.insert(new Node(neighbor, node, moves + 1));
			}
			
			// for pqTwin
			Node nodeTwin = pqTwin.delMin();
			Board boardTwin = nodeTwin.board;
			Node predecessorTwin = nodeTwin.predecessor;
			int movesTwin = nodeTwin.moves;
			if (boardTwin.isGoal()) {
				isSolvable = false;
				numMoves = -1;
				break;
			}
			for (Board neighbor : boardTwin.neighbors()) {
				if (predecessorTwin != null && neighbor.equals(predecessorTwin.board))
					continue;
				pqTwin.insert(new Node(neighbor, nodeTwin, movesTwin + 1));
			}
		}
		
	}
	
	public boolean isSolvable() {
		return isSolvable;
	}
	
	public int moves() {
		return numMoves;
	}
	
	public Iterable<Board> solution() {
		return solutionList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
