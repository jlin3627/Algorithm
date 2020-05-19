public class Shell
{
    public static void sort(comparable[] a)
    {
        int N = a.length;
        int h = 1;
        while (h < N/3) 
        {
            // 1, 4 ,13, 40, 121, 364
            h = 3*h + 1;
        }

        while (h >= 1)
        {
            for(int i = h; i<N; i++)
            {
                for(int j = i; j>=h && less(a[j], a[j-h]); j-= h)
                {
                    exch(a, j, j-h);
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