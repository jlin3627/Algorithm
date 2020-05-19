import MergeSort.java;
import InsertionSort.java;

public class MergeSortWithCutOff 
{
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
    {
        if( hi <= lo + CUTOFF - 1)
        {
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo)/2;
        sort (a, aux, lo, mid);
        sort( a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }
    /*rest is same as MergeSort*/
}