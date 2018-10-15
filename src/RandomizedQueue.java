import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>{

	private Item[] arr;
	private int N;
	
	public RandomizedQueue() {
		arr = (Item[]) new Object[2];
		N = 0;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void enqueue(Item item) {
		if (item == null) throw new IllegalArgumentException();
		else if (N == arr.length) {
			Item[] temp = (Item[]) new Object[arr.length * 2];
			for (int i = 0; i < arr.length; i++) {
				temp[i] = arr[i];
			}
			arr = temp;
		}
		arr[N++] = item;
	}
	
	public Item dequeue() {
		if (N == 0) throw new NoSuchElementException();
		else if (N < arr.length / 4) {
			Item[] temp = (Item[]) new Object[arr.length / 2];
			for (int i = 0; i < N; i++) {
				temp[i] = arr[i];
			}
			arr = temp;
		}
		int rand = StdRandom.uniform(N);
		Item item = arr[rand];
		arr[rand] = arr[N - 1];
		arr[N - 1] = null;
		N--;
		return item;
	}
	
	public Item sample() {
		if (N == 0) throw new NoSuchElementException();
		int rand = StdRandom.uniform(N);
		return arr[rand];
	}
	
	public Iterator<Item> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<Item> {
		int index = 0;
		Item[] itArr;
		public QueueIterator() {
			itArr = (Item[]) new Object[N];
			for (int i = 0; i < N; i++) {
				itArr[i] = arr[i];
			}
			StdRandom.shuffle(itArr);
			
		}
		public boolean hasNext() {
			return index < N;
		}
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			return itArr[index++];
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		rq.enqueue(4);
		rq.enqueue(2);
		rq.enqueue(9);
		for (int num : rq) {
			System.out.println(num);
		}
		System.out.println(rq.dequeue());
		System.out.println(rq.dequeue());
		System.out.println(rq.dequeue());
	}

}
