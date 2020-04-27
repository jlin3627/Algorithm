/* *****************************************************************************
 *  Name: Jing
 *  Date: 04/26/2020
 *  Description: HW2 Permutation
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation
{
	public static void main(String[] args)
	{
		RandomizedQueue<String> randomQ = new RandomizedQueue<>();
		int n = Integer.parseInt(args[0]);
		while (!StdIn.isEmpty())
		{
			String value = StdIn.readString();
			//StdOut.println(value);
			randomQ.enqueue(value);
		}
		for (int i = 0; i < n; i++)
		{
			String output = randomQ.dequeue();
			StdOut.println(output);
		}

	}
}
