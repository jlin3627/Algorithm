/******************************************************************************
 *  Name: Jing Yee Lin
 *  Date: 04/26/2020
 *  Description: HW2
 ****************************************************************************
 * */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>
{
	private Node first, last;
	private int size;

	private class Node
	{
		Item item;
		Node next;
		Node prev;
	}

	// construct an empty deque
	public Deque()
	{
		size = 0;
	}

	// is the deque empty?
	public boolean isEmpty()
	{
		return first == null;
	}

	// return the number of items on the deque
	public int size()
	{
		return size;
	}

	// add the item to the front
	public void addFirst(Item item)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("null argument");
		}
		Node data = first;
		first = new Node();
		first.item = item;
		first.next = data;
		if (data != null)
		{
			data.prev = first;
		}
		else
		{
			last = first;
		}
		size++;
	}

	// add the item to the back
	public void addLast(Item item)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("null argument");
		}
		Node data = last;
		last = new Node();
		last.item = item;
		last.prev = data;
		if (data != null)
		{
			data.next = last;
		}
		else
		{
			first = last;
		}
		size++;
	}

	// remove and return the item from the front
	public Item removeFirst()
	{
		if (first == null)
		{
			throw new java.util.NoSuchElementException("removeFirst: deque is empty");
		}
		Item item = first.item;

		if (first.next != null)
		{
			first = first.next;
			first.prev = null;
		}
		else
		{
			first.prev = null;
			first = null;
			last = null;
		}
		size--;

		return item;
	}

	// remove and return the item from the back
	public Item removeLast()
	{
		if (last == null)
		{
			throw new java.util.NoSuchElementException("removeLast: deque is empty");
		}
		Item item = last.item;

		if (last.prev != null)
		{
			last = last.prev;
			last.next = null;
		}
		else
		{
			last.next = null;
			last = null;
			first = null;
		}
		size--;
		return item;
	}

	// return an iterator over items in order from front to back
	public Iterator<Item> iterator()
	{
		return new DequeArrayIterator();
	}

	private class DequeArrayIterator implements Iterator<Item>
	{
		private Node current = first;

		public boolean hasNext()
		{
			return current != null;
		}

		public void remove()
		{/*Not supported*/
			throw new UnsupportedOperationException("Not Supported");
		}

		public Item next()
		{
			if (current == null)
			{
				throw new java.util.NoSuchElementException(
						"Iterator next: no more items to return");
			}
			Item item = current.item;
			current = current.next;
			return item;
		}

	}

	// unit testing (required)
	public static void main(String[] args)
	{
		Deque<String> deStr = new Deque<String>();
		Deque<String> enStr = new Deque<String>();
		In in = new In(args[0]);
		StdOut.println("Is Empty?: " + deStr.isEmpty());
		StdOut.print("Inputs: ");
		while (!in.isEmpty())
		{
			String input = in.readString();
			deStr.addFirst(input);
			StdOut.print(input + " ");
		}
		StdOut.println("");
		StdOut.println("Deque Is Empty?: " + deStr.isEmpty());
		int deSize = deStr.size();
		StdOut.println("Size: " + deSize);
		StdOut.print("Iterator: ");
		for (String s : deStr)
		{
			StdOut.print(s + " ");
		}
		StdOut.println();

		StdOut.print("Remove First and Add Last: ");
		for (int i = 0; i < deSize; i++)
		{
			String newItem = deStr.removeFirst();
			enStr.addLast(newItem);
			StdOut.print(newItem + " ");
		}
		StdOut.println();

		StdOut.print("Iterator: ");
		for (String s : enStr)
		{
			StdOut.print(s + " ");
		}
		StdOut.println();

		StdOut.print("Remove Last: ");
		for (int i = 0; i < deSize; i++)
		{
			String newItem = enStr.removeLast();
			StdOut.print(newItem + " ");
		}
		StdOut.println();

		StdOut.println("Deque Is Empty?: " + deStr.isEmpty());
		StdOut.println("Enque Is Empty?: " + deStr.isEmpty());

		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(1);
		deque.removeLast();
		StdOut.println("Deque Is Empty?: " + deque.isEmpty());
	}
}
