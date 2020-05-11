//stacks example
//last in first out

public class LinkedList
{
    private Node first = null;

    private class Node{
        String item;
        Node Next;
    }

    public boolean isempty()
    { return first == null; }

    public void push(String item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public String pop()
    {
        String item = first.item;
        first = first.next;
        return item;
    }
}