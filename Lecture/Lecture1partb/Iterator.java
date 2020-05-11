// Iterable interface
// has a method that returns an Iterator1
public interface Iterable
{
    Iterator<Item> iterator();
}

// Iterable interface
public interface Iterator<Item>
{
    boolean hasNext();
    Item next();
    // optional: use at your own risk
    void remove();
}

/*
Iterator<String> i = stack.iterator();
while ( i.hasNext())
{
    String s = i.next();
    StdOut.println(s);
}

for (String s : stack)
{
    StdOut.println(s);
}
*/