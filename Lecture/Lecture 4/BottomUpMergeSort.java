public class BottomUpMergeSort 
{
    private static Comparable[] aux;
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

    public static void sort(Comparable[] a)
    {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz)
            for (int lo = 0; lo < N-sz; lo += sz+sz)
                merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
    }
}