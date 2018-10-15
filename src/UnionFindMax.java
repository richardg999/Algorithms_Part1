
public class UnionFindMax {

	private int[] uf;
	private int[] size;
	private int[] maxValue;
	
	public UnionFindMax(int num) {
		uf = new int[num];
		size = new int[num];
		maxValue = new int[num];
		for (int i = 0; i < num; i++) {
			uf[i] = i;
			size[i] = 1;
			maxValue[i] = i;
		}
	}
	
	public boolean connected(int a, int b) {
		return root(a) == root(b);
	}
	
	public int maxValue(int num) {
		return maxValue[root(num)];
	}
	
	public int root(int num) {
		while (uf[num] != num) {
			uf[num] = uf[uf[num]];
			num = uf[num];
		}
		return num;
	}
	
	public void union(int a, int b) {
		int root1 = root(a);
		int root2 = root(b);
		if (root1 == root2) return;
		
		// add to a if a is larger than b
		if (size[root1] > size[root2]) {
			uf[root1] = uf[root2];
			size[root1] += size[root2];
			maxValue[root1] = Math.max(maxValue[root1], maxValue[root2]);
		}
		else {
			uf[root1] = uf[root2];
			size[root1] += size[root2];
			maxValue[root2] = Math.max(maxValue[root1], maxValue[root2]);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
