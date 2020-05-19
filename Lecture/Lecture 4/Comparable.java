public class Comparable 
{
    /**
     * public interface Comparable<key>
     *      int compare(Key v, Key w)
     *  */ 

    // Example:
    public class Date implements Comparable<Date>
    private final int month, day, year;

    public Date (int m, int d, int y)
    {
        month   = m;
        day     = d;
        year    = y;
    }

    public int compareTo(Date that)
    {
        if(this.year  < that.year)  return -1;
        if(this.year  > that.year)  return +1;
        if(this.month < that.month) return -1;
        if(this.month > that.month) return +1;
        if(this.day   < that.day  ) return -1;
        if(this.day   > that.day  ) return +1;
        return 0;
    }

    // Example 2:
    public class Student
    {
        public static final Comparator<Student> BY_NAME = new ByName();
        public static final Comparator<Student> BY_Section = new BySection();
        private final String name;
        private final int section;

        private static class ByName implements Comparator<Student>
        {
            public int compare(Student v, Student w)
            {
                return v.name.compareTo(w.name);
            }
        }

        private static class BySection implements Comparator<Student>
        {
            public int compare(student v, Stuident w)
            {
                return v.section - w.section;
            }
        }
    }

    // example 3:
    public class Point2D
    {
        public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();
        private final double x, y;

        private static int ccw(Point2D a, Point2D b, Point2D c)
        {/**/}

        private class PolarOrder implements Comparator<Point2D>
        {
            public int compare(Point2D q1, Point2D q2)
            {
                double dy1 = q1.y - y;
                double dy2 = q2.y - y;

                if  (dy1 == 0 && dy2 == 0) 
                { 
                    //... p,q1,q2 horizontal
                } 
                else if (dy1 >= 0 && dy2 < 0 ) return -1; //q1 above p; q2 below p
                else if (dy2 >= 0 && dy1 < 0 ) return +1; //q1 below p; q2 above p
                else return -ccw(Point2d.this, q1, q2);
            }
        }
    }

}

// Insertion sort using a comparator
public static void sort(Object[] a, Comparator comparator)
{
    int N = a.length;
    for (int i = 0; i < N; i++)
        for (int j = i; j>0 && less(comparator, a[j], a[j-1]); j--)
        {
            exch(a, j ,j-1);
        }
}

private static boolean less(Comparator c, object v, Object w)
{
    return c.compare(v, w)<0;
}

private static void exch(Object[] a, int i, int j)
{
    Object swap = a[i]; 
    a[i] = a[j]; 
    a[j] = swap;
}