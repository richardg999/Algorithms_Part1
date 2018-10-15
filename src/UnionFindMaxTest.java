
public class UnionFindMaxTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UnionFindMax uf = new UnionFindMax(8);
		uf.union(3, 4);
		uf.union(2, 3);
		System.out.println(uf.connected(1, 2));
		System.out.println(uf.connected(2, 3));
		System.out.println(uf.connected(4, 2));
		uf.union(2, 6);
		System.out.println(uf.maxValue(3));

	}

}
