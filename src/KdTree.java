import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	
	private int size;
	private Node root;
	
	private static class Node {
		private Point2D p;
		private RectHV rect;
		private Node lb;
		private Node rt;
		private boolean isVertical;
		public Node(Point2D p, RectHV rect, boolean isVertical) {
			this.p = p;
			this.rect = rect;
			this.isVertical = isVertical;
		}
		public void draw() {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(.01);
			p.draw();
			StdDraw.setPenRadius();
			if (isVertical) {
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.line(p.x(), rect.ymin(), p.x(), rect.ymax());
			}
			else {
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(rect.xmin(), p.y(), rect.xmax(), p.y());
			}
		}
	}
	
	public KdTree() {
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void insert(Point2D p) {
		if (p == null) 
			throw new IllegalArgumentException();
		size++;
		RectHV init = new RectHV(0, 0, 1, 1);
		root = put(root, p, true, init);
	}
	
	private Node put(Node location, Point2D p, boolean isVertical, RectHV rect) {
		if (location == null) return new Node(p, rect, isVertical);
		double cmp;
		if (isVertical) {
			isVertical = false;
			cmp = p.x() - location.p.x();
			if (p.equals(location.p)) {
				size--;
			}
			else if (cmp < 0) {
				rect = new RectHV(rect.xmin(), rect.ymin(), location.p.x(), rect.ymax());
				location.lb = put(location.lb, p, isVertical, rect);
			}
			else {
				rect = new RectHV(location.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
				location.rt = put(location.rt, p, isVertical, rect);
			}
		}
		else {
			isVertical = true;
			cmp = p.y() - location.p.y();
			if (p.equals(location.p)) {
				size--;
			}
			else if (cmp < 0) {
				rect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), location.p.y());
				location.lb = put(location.lb, p, isVertical, rect);
			}
			else {
				rect = new RectHV(rect.xmin(), location.p.y(), rect.xmax(), rect.ymax());
				location.rt = put(location.rt, p, isVertical, rect);
			}
		}
		
		return location;
	}
	
	public boolean contains(Point2D p) {
		if (p == null) 
			throw new IllegalArgumentException();
		Node location = root;
		boolean isVertical = true;
		while (location != null) {
			double cmp;
			if (isVertical) {
				cmp = p.x() - location.p.x();
				isVertical = false;
			}
			else {
				cmp = p.y() - location.p.y();
				isVertical = true;
			}
			
			if (p.equals(location)) return true;
			else if (cmp < 0) location = location.lb;
			else location = location.rt;
		}
		return false;
	}
	
	public void draw() {
		draw(root);
	}
	
	private void draw(Node location) {
		if (location == null) return;
		draw(location.lb);
		draw(location.rt);
		location.draw();
	}
	
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) 
			throw new IllegalArgumentException();
		ArrayList<Point2D> list = new ArrayList<Point2D>();
		rangeSearch(rect, root, list);
		return list;
	}
	
	private void rangeSearch(RectHV rect, Node location, ArrayList<Point2D> list) {
		if (location == null || !location.rect.intersects(rect)) {
			return;
		}
		if (rect.contains(location.p)) {
			list.add(location.p);
		}
		rangeSearch(rect, location.lb, list);
		rangeSearch(rect, location.rt, list);
	}
	
	public Point2D nearest(Point2D p) {
		if (p == null) 
			throw new IllegalArgumentException();
		if (size == 0) return null;
		return nearestSearch(p, root, root.p);
	}
	
	private Point2D nearestSearch(Point2D p, Node location, Point2D closest) {
		if (location == null || location.rect.distanceSquaredTo(p) > closest.distanceSquaredTo(p)) {
			return closest;
		}
		
		if (location.p.distanceSquaredTo(p) < closest.distanceSquaredTo(p)) {
			closest = location.p;
		}
		if (location.lb == null) {
			closest = nearestSearch(p, location.rt, closest);
		}
		else if (location.rt == null) {
			closest = nearestSearch(p, location.lb, closest);
		}
		else if (location.lb.p.distanceSquaredTo(p) < location.rt.p.distanceSquaredTo(p)) {
			closest = nearestSearch(p, location.lb, closest);
			closest = nearestSearch(p, location.rt, closest);
		}
		else {
			closest = nearestSearch(p, location.rt, closest);
			closest = nearestSearch(p, location.lb, closest);
		}
		return closest;
	}

}
