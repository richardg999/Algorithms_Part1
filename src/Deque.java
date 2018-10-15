import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private Node first;
	private Node last;
	private int size;
	
	private class Node {
		Item item;
		Node next;
		Node previous;
	}
	
	public Deque() {
		first = null;
		last = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void addFirst(Item item) {
		if (item == null) throw new IllegalArgumentException();
		else if (isEmpty()) {
			size++;
			first = last = new Node();
			first.item = item;
		} 
		else {
			size++;
			Node oldFirst = first;
			first = new Node();
			first.item = item;
			first.next = oldFirst;
			oldFirst.previous = first;
		}
	}
	
	public void addLast(Item item) {
		if (item == null) throw new IllegalArgumentException();
		else if (isEmpty()) {
			size++;
			first = last = new Node();
			last.item = item;
		} 
		else {
			size++;
			Node oldLast = last;
			last = new Node();
			last.item = item;
			last.previous = oldLast;
			oldLast.next = last;
		}
	}
	
	public Item removeFirst() {
		if (isEmpty()) throw new NoSuchElementException();
		else if (size == 1) {
			size--;
			Item item = first.item;
			first = last = null;
			return item;
		}
		else {
			size--;
			Node oldFirst = first;
			first = oldFirst.next;
			Item item = oldFirst.item;
			first.previous = null;
			return item;
		}
	}
	
	public Item removeLast() {
		if (isEmpty()) throw new NoSuchElementException();
		else if (size == 1) {
			size--;
			Item item = last.item;
			first = last = null;
			return item;
		}
		else {
			size--;
			Node oldLast = last;
			last = oldLast.previous;
			Item item = oldLast.item;
			last.next = null;
			return item;
		}
	}
	
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item> {
		Node node = first;
		public boolean hasNext() {
			return node != null;
		}
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = node.item;
			node = node.next;
			return item;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args) {
		// quick test
		Deque<Integer> deck = new Deque<>();
		deck.addFirst(1);
		deck.addFirst(2);
		for (int num : deck) {
			System.out.println(num);
		}
		System.out.println(deck.removeLast());
		System.out.println(deck.removeLast());
	}
}
