private static void merge (Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
    {
        int i = lo, j = mid+l;

        // merge from a[] to aux[]
        for(int k = lo; k <= hi; k++)
        {
            // i already reach end, put rest of j in
            if(i > mid){
                aux[k] = a[j++];
            }
            // j already reach the end, put rest of i in
            else if (j > hi){
                aux[k] = a[i++];
            }         
            // if j is less, put j in
            else if (less(aux[j], aux[i])){
                aux[k] = a[j++];
            }
            // if i is smaller, put i in
            else{
                aux[k] = a[i++];
            }
        }
    }

   // Note: sort(a) initializes aux[] and sets aux[i] = a[i] for each i
private static void sort(Comparable[] a, Comparable[] aux, int, lo, int hi)
{
    if(hi <= lo) return;
    int mid = lo + (hi - lo)/2;
    sort(aux, a, lo, mid);
    sort(aux, a, mid+1, hi);
    merge(a, aux, lo, mid, hi); //Switch roles of aux[] and a[]
}