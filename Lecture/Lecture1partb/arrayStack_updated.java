// Stack with all item
// last in first out

public class FixedCapacityStack<Item> imp;ements Iterable<Item>
{
    private Item[] s;
    private int N = 0;

    public FixedCapacityStack(int capacity)
    {
        s = (Item[]) new Object[capacity]; 
    }

    public boolean isEmpety()
    {
        return N==0; 
    }

    public void push(Item item)
    {
        s[N++] = item;
    }

    public Item pop()
    {
        return s[--N];
    }

    public Iterator<Item> iterator()
    {return new ReversArrayIterator();}

    private class ReverseArrayIterator implements Iterator<Item>
    {
        private int i = N;
        public boolean hasNext(){return i > 0;}
        public void remove() {/* Not supported */}
        public Item next() {return s[--i];} 
    }
}