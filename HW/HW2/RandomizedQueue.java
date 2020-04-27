/* *****************************************************************************
 *  Name: Jing Yee Lin
 *  Date: 04/26/2020
 *  Description: HW 2 randomized Queue
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>
{
	private int size;
	private Item[] itms;
	private int cap = 10;


	// construct an empty randomized queue
	public RandomizedQueue()
	{
		size = 0;
		//itms = new Item[cap]; generic array creation not allowed
		itms = (Item[]) new Object[cap];
	}

	// is the randomized queue empty?
	public boolean isEmpty()
	{
		return size == 0;
	}

	// return the number of items on the randomized queue
	public int size()
	{
		return size;
	}

	// add the item
	public void enqueue(Item item)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("null argument");
		}
		if (size == itms.length) resize(2 * itms.length);
		itms[size++] = item;
	}

	// remove and return a random item
	public Item dequeue()
	{
		if (isEmpty())
		{
			throw new java.util.NoSuchElementException("Dequeue: empty Queue");
		}
		int removeInd = StdRandom.uniform(size);
		Item ans = itms[removeInd];
		for (int i = removeInd; i < size; i++)
		{
			if (i == size - 1)
			{
				itms[i] = null;
			}
			else
			{
				itms[i] = itms[i + 1];
			}
		}
		size--;
		if (size > 0 && size == itms.length / 4) resize(itms.length / 2);
		return ans;
	}

	// return a random item (but do not remove it)
	public Item sample()
	{
		if (isEmpty())
		{
			throw new java.util.NoSuchElementException("Sample: empty Queue");
		}
		return itms[StdRandom.uniform(size)];
	}

	private void resize(int capacity)
	{
		if (capacity >= size)
		{
			Item[] newArray = (Item[]) new Object[capacity];
			for (int i = 0; i < size; i++)
			{
				newArray[i] = itms[i];
			}
			itms = newArray;
		}
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator()
	{
		Item[] randItems = (Item[]) new Object[size];
		for (int i = 0; i < size; i++)
		{
			randItems[i] = itms[i];
		}
		for (int i = 0; i < size; i++)
		{
			int r = StdRandom.uniform(i + 1);
			Item swap = randItems[i];
			randItems[i] = randItems[r];
			randItems[r] = swap;
		}
		return new RandomizedQueueIterator(randItems);
	}

	private class RandomizedQueueIterator implements Iterator<Item>
	{
		private int i = size;
		private Item[] itrItems;

		public RandomizedQueueIterator(Item[] newItr)
		{
			itrItems = newItr;
		}

		public boolean hasNext()
		{
			return i > 0;
		}

		public void remove()
		{
			throw new UnsupportedOperationException("Not Supported");
		}

		public Item next()
		{
			if (i <= 0)
			{
				throw new java.util.NoSuchElementException("Iterator Next: no more items");
			}
			return itrItems[--i];
		}

	}

	// unit testing (required)
	public static void main(String[] args)
	{
		RandomizedQueue<String> randomStr = new RandomizedQueue<String>();
		In in = new In(args[0]);
		StdOut.println("Is Empty?: " + randomStr.isEmpty());
		StdOut.print("Inputs and enqueue: ");
		while (!in.isEmpty())
		{
			String input = in.readString();
			randomStr.enqueue(input);
			StdOut.print(input + " ");
		}
		StdOut.println("");

		StdOut.println("Deque Is Empty?: " + randomStr.isEmpty());
		int deSize = randomStr.size();
		StdOut.println("Size: " + deSize);
		StdOut.print("Random Iterator: ");
		for (String s : randomStr)
		{
			StdOut.print(s + " ");
		}
		StdOut.println();

		StdOut.print("Sample: ");
		for (int i = 0; i < deSize; i++)
		{
			String newItem = randomStr.sample();
			StdOut.print(newItem + " ");
		}
		StdOut.println();

		StdOut.print("Dequeue: ");
		for (int i = 0; i < deSize; i++)
		{
			String newItem = randomStr.dequeue();
			StdOut.print(newItem + " ");
		}
		StdOut.println();
	}
}
