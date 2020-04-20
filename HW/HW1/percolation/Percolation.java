/* *****************************************************************************
 *  Name: Jing Yee Lin
 *  Date: 4/19/2020
 *  Description:
 **************************************************************************** */

public class Percolation
{
	private int[][] rootgrid;
	private int[][] bottomgrid;
	private boolean[][] checkedGrid;
	private int openCount;
	private boolean bpercolate;
	private int highestRow;


	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n)
	{
		if (n <= 0)
		{
			throw new IllegalArgumentException("Out of bound");
		}
		rootgrid = new int[n][n];
		bottomgrid = new int[n][n];
		checkedGrid = new boolean[n][n];
		for (int j = 0; j < rootgrid.length; j++)
		{
			for (int i = 0; i < rootgrid[j].length; i++)
			{
				rootgrid[j][i] = -1;
				bottomgrid[j][i] = -1;
				checkedGrid[j][i] = false;
			}
		}
		openCount = 0;
		bpercolate = false;

	}

	// opens the site (row, col) if it is not open already
	public void open(int row, int col)
	{
		checkOutofBound(row, col);
		if (rootgrid[row - 1][col - 1] < 0)
		{
			highestRow = 0;
			rootgrid[row - 1][col - 1] = row - 1;
			bottomgrid[row - 1][col - 1] = row - 1;
			rootgrid[row - 1][col - 1] = findLowestRow(row - 1, col - 1);
			setLowestRow(row - 1, col - 1, rootgrid[row - 1][col - 1]);
			openCount++;
			if (rootgrid[row - 1][col - 1] == 0 && highestRow == rootgrid.length - 1)
			{
				bpercolate = true;
			}
		}


	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col)
	{
		checkOutofBound(row, col);
		return rootgrid[row - 1][col - 1] != -1;
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col)
	{
		checkOutofBound(row, col);
		if (rootgrid[row - 1][col - 1] == 0)
		{
			return true;
		}
		return false;
	}

	// returns the number of open sites
	public int numberOfOpenSites()
	{
		return openCount;

	}

	// does the system percolate?
	public boolean percolates()
	{
		return bpercolate;
	}

	private int findLowestRow(int row, int col)
	{
		int newRow = row;
		rootgrid[row][col] = row;
		bottomgrid[row][col] = row;
		checkedGrid[row][col] = true;
		int compareRow;
		if (row > highestRow)
		{
			highestRow = row;
		}
		//check north

		if (row > 0
				&& rootgrid[row - 1][col] != -1
				&& checkedGrid[row - 1][col] != true)
		{
			compareRow = findLowestRow(row - 1, col);
			if (compareRow < newRow)
			{
				newRow = compareRow;
			}
		}

		if (row < rootgrid.length - 1
				&& rootgrid[row + 1][col] != -1
				&& checkedGrid[row + 1][col] != true)
		{
			compareRow = findLowestRow(row + 1, col);
			if (compareRow < newRow)
			{
				newRow = compareRow;
			}
		}

		if (col > 0
				&& rootgrid[row][col - 1] != -1
				&& checkedGrid[row][col - 1] != true)
		{
			compareRow = findLowestRow(row, col - 1);
			if (compareRow < newRow)
			{
				newRow = compareRow;
			}
		}

		if (col < rootgrid[0].length - 1
				&& rootgrid[row][col + 1] != -1
				&& checkedGrid[row][col + 1] != true)
		{
			compareRow = findLowestRow(row, col + 1);
			if (compareRow < newRow)
			{
				newRow = compareRow;
			}
		}
		return newRow;

	}

	private void setLowestRow(int row, int col, int new_low)
	{
		rootgrid[row][col] = new_low;
		bottomgrid[row][col] = highestRow;
		checkedGrid[row][col] = false;
		//check north
		if (row > 0 &&
				rootgrid[row - 1][col] != -1
				&& checkedGrid[row - 1][col] == true)
		{
			setLowestRow(row - 1, col, new_low);
		}

		if (row < rootgrid.length - 1
				&& rootgrid[row + 1][col] != -1
				&& checkedGrid[row + 1][col] == true)
		{
			setLowestRow(row + 1, col, new_low);
		}

		if (col > 0
				&& rootgrid[row][col - 1] != -1
				&& checkedGrid[row][col - 1] == true)
		{
			setLowestRow(row, col - 1, new_low);
		}

		if (col < rootgrid[0].length - 1
				&& rootgrid[row][col + 1] != -1
				&& checkedGrid[row][col + 1] == true)
		{
			setLowestRow(row, col + 1, new_low);
		}

	}

	private void checkOutofBound(int row, int col)
	{
		if (row < 1 || col < 1 || row > rootgrid.length || col > rootgrid.length)
		{
			throw new IllegalArgumentException("Out of bound");
		}
	}

	// test client (optional)
	// public static void main(String[] args)
}
