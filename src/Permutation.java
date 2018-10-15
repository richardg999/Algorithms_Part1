import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<>();
		
		while (!StdIn.isEmpty()) {
			rq.enqueue(StdIn.readString());
		}
		
		/*String[] inputs = StdIn.readLine().trim().split(" ");
		for (String str : inputs) {
			rq.enqueue(str);
		}*/
		
		for (int i = 0; i < k; i++) {
			StdOut.println(rq.dequeue());
		}
	}

}
