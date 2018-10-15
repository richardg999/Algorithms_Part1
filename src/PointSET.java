import java.util.ArrayList;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
	
	private TreeSet<Point2D> set;
	
	public PointSET() {
		set = new TreeSet<Point2D>();
	}
	
	public boolean isEmpty() {
		return set.isEmpty();
	}
	
	public int size() {
		return set.size();
	}
	
	public void insert(Point2D p) {
		if (p == null) 
			throw new IllegalArgumentException();
		set.add(p);
	}
	
	public boolean contains(Point2D p) {
		if (p == null) 
			throw new IllegalArgumentException();
		return set.contains(p);
	}
	
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		for (Point2D p : set) {
			p.draw();
		}
	}
	
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) 
			throw new IllegalArgumentException();
		ArrayList<Point2D> list = new ArrayList<Point2D>();
		for (Point2D p : set) {
			if (rect.contains(p)) {
				list.add(p);
			}
		}
		return list;
	}
	
	public Point2D nearest(Point2D p) {
		if (p == null) 
			throw new IllegalArgumentException();
		double minDistance = Double.POSITIVE_INFINITY;
		Point2D minPoint = null;
		for (Point2D point : set) {
			double distance = p.distanceSquaredTo(point);
			if (distance < minDistance) {
				minDistance = distance;
				minPoint = point;
			}
		}
		return minPoint;
	}

}
