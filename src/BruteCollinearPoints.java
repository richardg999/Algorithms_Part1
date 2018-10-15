import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
	
	private List<LineSegment> segments;
	
	public BruteCollinearPoints(Point[] points) {
		if (points == null) throw new IllegalArgumentException();
		Point[] sorted = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) throw new IllegalArgumentException();
			sorted[i] = points[i];
		}
		Arrays.sort(sorted);
		for (int i = 0; i < sorted.length - 1; i++) {
			if (sorted[i].compareTo(sorted[i + 1]) == 0) throw new IllegalArgumentException();
		}
		
		segments = new ArrayList<LineSegment>();
		for (int i = 0; i < sorted.length - 3; i++) {
			Point p = sorted[i];
			for (int j = i + 1; j < sorted.length - 2; j++) {
				for (int k = j + 1; k < sorted.length - 1; k++) {
					for (int l = k + 1; l < sorted.length; l++) {
						double slope = p.slopeTo(sorted[j]);
						if (p.slopeTo(sorted[k]) == slope && p.slopeTo(sorted[l]) == slope) {
							segments.add(new LineSegment(p, sorted[l]));
						}
						
					}
				}
			}
		}
	}
	
	public int numberOfSegments() {
		return segments.size();
	}
	
	public LineSegment[] segments() {
		return segments.toArray(new LineSegment[segments.size()]);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
