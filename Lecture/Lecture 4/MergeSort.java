public class Merge
{
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
    {
        if( hi <= lo)
        {
            return;
        }
        int mid = lo + (hi - lo)/2;
        sort (a, aux, lo, mid);
        sort( a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a)
    {
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
    }

    private static void merge (Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
    {
        assert isSorted(a, lo, mid);    // precondition: a[lo...mid] sorted
        assert isSorted(a, mid+1, hi);  // precondition: a[mid+1..hi] sorted
        
        for (int k = lo; k<= hi; k++)
        {
            aux[k] = a[k];
        }

        int i = lo, j = mid+l;
        for(int k = lo; k <= hi; k++)
        {
            // i already reach end, put rest of j in
            if(i > mid){
                a[k] = aux[j++];
            }
            // j already reach the end, put rest of i in
            else if (j > hi){
                a[k] = aux[i++];
            }         
            // if j is less, put j in
            else if (less(aux[j], aux[i])){
                a[k] = aux[j++];
            }
            // if i is smaller, put i in
            else{
                a[k] = aux[i++];
            }
        }
    }

    public interface Comparable<Item>
    {
        public int compareTo(Item that);
    }

    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }
}




