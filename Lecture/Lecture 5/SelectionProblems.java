import javax.lang.model.util.ElementScanner14;

public class SelectionProblems 
{
    // Quick Select
    // find the k largests
    public static Comparable select(Comparable[] a, int k)
    {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo)
        {
            int j = partition(a, lo, hi);
            if      (j < k) lo = j+1;
            else if (j > k) hi = j-1;
            else            return a[k];
        }
        return a[k];
    }
}