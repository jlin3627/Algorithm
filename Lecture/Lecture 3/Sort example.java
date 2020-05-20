public class Experiment
{
    public static void main(String[] args)
    {
        int N = Interger.parseInt(args[0]);
        Double[] a = new Double[N];
        for (int i = 0; i<N; i++)
        {
            a[i] = stdRandom.uniform();
        }
        Insertion.sort(a);
        for (int i = 0; i< N; i++)
        {
            stdOut.println(a[i]);
        }
    }
}