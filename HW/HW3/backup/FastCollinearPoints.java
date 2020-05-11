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
import java.util.LinkedList;

public class FastCollinearPoints {

    private LineSegment[] segments;

    /** @noinspection checkstyle:IllegalTokenText */
    public FastCollinearPoints(
            Point[] points)     // finds all line segments containing 4 or more points
    {


        checkNull(points);
        ArrayList<LineSegment> ansList = new ArrayList<>();

        Point[] ptsBySlope = Arrays.copyOf(points, points.length);
        Arrays.sort(ptsBySlope);
        checkRepeat(ptsBySlope);

        for (Point pt : points) {
            /*Arrays.sort(ptsBySlope, pt.slopeOrder());
            for (int q = 0; q < ptsBySlope.length - 1; q++) {
                double slope = pt.slopeTo(ptsBySlope[q]);
                int count = 0;
                int j = q + 1;
                while (j < ptsBySlope.length) {
                    double tmpSlope = pt.slopeTo(ptsBySlope[j]);
                    if (slope == tmpSlope) {
                        j++;
                        count++;
                        if (j == ptsBySlope.length && count >= 3 && (
                                pt.compareTo(ptsBySlope[q + 1]) < 0)) {
                            ansList.add(new LineSegment(pt, ptsBySlope[j - 1]));
                            if (count > 0) {
                                q = j - count - 1;
                            }
                            count = 0;
                        }
                    }
                    else {
                        if (count >= 3 && (pt.compareTo(ptsBySlope[q + 1]) < 0)) {
                            ansList.add(new LineSegment(pt, ptsBySlope[j - 1]));
                        }

                        if (count > 0) {
                            q = j - count - 1;
                        }
                        j = ptsBySlope.length;
                        count = 0;
                    }
                }
            }*/

            int x = 1;
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
            }
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
