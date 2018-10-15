import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
	
	private List<LineSegment> segments;
	
	public FastCollinearPoints(Point[] points) {
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
		
		for (int i = 0; i < sorted.length; i++) {
			Point p = sorted[i];
			// arr is the array of all the points, excluding p, sorted by slope order
			Point[] arr = new Point[sorted.length - 1];
			int index = 0;
			for (int j = 0; j < sorted.length; j++) {
				if (j == i) continue;
				arr[index++] = sorted[j];
			}
			
			Arrays.sort(arr, p.slopeOrder());
			
			// code for testing purposes
			/*System.out.println("p=" + p);
			for (Point q : arr) {
				System.out.print(p.slopeTo(q) + " ");
			}
			System.out.println();*/
			
			for (int j = 0; j < arr.length - 2; j++) {
				boolean isFirstPoint = true;
				if (p.compareTo(arr[j]) >= 0)
					isFirstPoint = false;
				int count = 1;
				while (j < arr.length - 1 && p.slopeTo(arr[j]) == p.slopeTo(arr[j + 1])) {
					count++;
					j++;
				}
				if (isFirstPoint && count >= 3)
					segments.add(new LineSegment(p, arr[j]));
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
