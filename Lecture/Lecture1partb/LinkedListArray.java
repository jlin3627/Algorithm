public class LinkedListArray
{
    private String[] s;
    private int N = 0;

    public FixedCapacityStackofStrings(int capacity)
    {
        s = new String[capacity];
    }

    public FixedCapacityStackofStrings()
    {
        s = new String[1];
    }

    public FixedCapacityStackofStrings(int capacity)
    {
        s = new String[capacity];
    }*

    public boolean isEmpty()
    {
        return N == 0;
    }

    public void push (String item)
    {
        //use to index into array; then increment N
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    //  Loitering - holding a reference to an object when it is no longer needed
    /*public string pop()
    {
        //decrement N; then use to index into array
        return s[--N];
    }*/
    
    // Avoid loitering: 
    // garbage collector can reclaim memory
    // only if no outstanding references
    public String pop()
    {
        String item = s[--N];
        s[N] = null;
        if (N>0 && N == s.length/4) 
        {
            resize(s.length/2);
        }
        return item;
    }
    

    private void resize(int capacity)
    {
        String[] copy = new String[capacity];
        for (int i = 0; i<N; i++)
        {
            copy[i] = s[i];
        }
        s= copy;
    }
}