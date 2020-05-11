// insertion sort
// comapre 1/4 n^2
// exchange 1/4 n^2
// best case, ascending order, N-1 compares 0 exchanges
// worst case, descending order, 1/2 N^2 compares, 1/2 N ^2 exchanges
// partially sort arrays run linear time 

public class Insertion
{
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i< N; i++)
        {
            for (int j = i; j>0; j--)
            {
                if (less(a[j], a[j-1]))
                {
                    exch(a,j,j-1);
                }
                else
                {
                    break;
                }
            }
        }
    }
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}