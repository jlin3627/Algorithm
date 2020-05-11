/* *****************************************************************************
 *  Name: Jing Yee Lin
 *  Date:
 *  Description: HW3
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] segments;

    /** @noinspection checkstyle:IllegalTokenText */
    public FastCollinearPoints(
            Point[] points)     // finds all line segments containing 4 or more points
    {


        checkNull(points);
        ArrayList<LineSegment> ansList = new ArrayList<>();

        Point[] pts = Arrays.copyOf(points, points.length);
        Arrays.sort(pts);
        checkRepeat(pts);

        for (Point pt : points) {
            Point[] ptsBySlope = pts.clone();
            Arrays.sort(ptsBySlope, pt.slopeOrder());
            for (int q = 1; q < ptsBySlope.length; q++) {
                double slope = pt.slopeTo(ptsBySlope[q]);
                int count = 0;
                int q2 = q;
                while (q2 < ptsBySlope.length && pt.slopeTo(ptsBySlope[q2]) == slope) {
                    count++;
                    q2++;
                }
                if (count >= 3 && pt.compareTo(ptsBySlope[q]) < 0) {
                    ansList.add(new LineSegment(pt, ptsBySlope[q2 - 1]));
                }
                q = q2 - 1;
            }

            /*int x = 1;
            while (x < points.length) {

                LinkedList<Point> candidates = new LinkedList<>();
                final double SLOPE_REF = pt.slopeTo(ptsBySlope[x]);
                do {
                    candidates.add(ptsBySlope[x++]);
                } while (x < points.length && pt.slopeTo(ptsBySlope[x]) == SLOPE_REF);
                if (candidates.size() >= 3
                        && pt.compareTo(candidates.peek()) < 0) {
                    Point min = pt;
                    Point max = candidates.removeLast();
                    ansList.add(new LineSegment(min, max));
                }
            }*/
        }

        segments = ansList.toArray(new LineSegment[0]);
    }

    public int numberOfSegments()        // the number of line segments
    {
        return segments.length;
    }

    public LineSegment[] segments()                // the line segments
    {

        return segments.clone();
    }

    private void checkNull(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("points are null");
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Null point found");
            }
        }
    }

    private void checkRepeat(Point[] points) {
        for (int i = 1; i < points.length; i++) {
            if (points[i].equals(points[i - 1])) {
                throw new IllegalArgumentException("Repeted points");
            }
        }
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
