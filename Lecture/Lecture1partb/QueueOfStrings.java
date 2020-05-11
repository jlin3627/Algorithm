// Queue structure
// First in first out
public class QueueOfStrings
{
    private Node first, last;

    private class Node
    {
        String item;
        Node Next;
    }

    public boolean isEmpty()
    {return first == null;}

    public void enqueue(String item)
    {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isempty()) first = last;
        else            oldlast.next = last;
    }

    public String dequeue()
    {
        String item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        return item;
    }
}