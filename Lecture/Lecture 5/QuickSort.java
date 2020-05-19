public class QuickSort 
{
    private static int partition(Comparable[]a, int lo, int hi)
    {
        int i = lo, j = hi+1;
        while (true)
        {
            // find item on left to swap
            while (less(a[++i], a[lo]))
                if (i == hi) break;
            
            // find item on right to swap    
            while (less(a[lo], a[--j]))
                if (j == lo) break;
            
            // check if pointers cross    
            if (i >= j) break; 
            // swap
            exch(a, i, j);    
        }
        
        // swap with partitioning item
        exch(a, lo, j);
        // return index of item now known to be in place
        return j;
    }

    public static void sort (Comparable[] a)
    {
        StdRandom.shuffle(a); // shuffle needed 
        sort(a, 0, a.length - 1);
    }
    public static void sort (Comparable[] a, int lo, int hi)
    {
        // improvement insertion sort small array
        if (hi <= lo + CUTOFF - 1)
        {
            Insertion.sort(a, lo, hi);
            return;
        }
        // if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }
}