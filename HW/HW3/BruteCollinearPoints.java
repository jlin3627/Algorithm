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

public class BruteCollinearPoints {

    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        checkNull(points);
        ArrayList<LineSegment> ansList = new ArrayList<>();

        Point[] pts = Arrays.copyOf(points, points.length);
        Arrays.sort(pts);
        checkRepeat(pts);

        for (int p = 0; p < pts.length - 3; p++) {
            for (int q = p + 1; q < pts.length - 2; q++) {
                double slopePQ = pts[p].slopeTo(pts[q]);
                for (int r = q + 1; r < pts.length - 1; r++) {
                    double slopePR = pts[p].slopeTo(pts[r]);
                    if (slopePQ == slopePR) {
                        for (int s = r + 1; s < pts.length; s++) {
                            double slopePS = pts[p].slopeTo(pts[s]);
                            if (slopePQ == slopePS) {
                                LineSegment ans = new LineSegment(pts[p], pts[s]);
                                ansList.add(ans);
                            }
                        }

                    }
                }
            }
        }

        segments = ansList.toArray(new LineSegment[0]);

    }

    // the number of line segments
    public int numberOfSegments() {

        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
