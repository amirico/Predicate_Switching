package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.*;
import java.text.*;
/**
*The SudokuGenerator class creates a random standard (9x9)
*Sudoku board through the use of backtracking techniques.
*/
public class SudokuGenerator
{
	public static final int BOARD_WIDTH = 9;
   public static final int BOARD_HEIGHT = 9;
	
	/**
	 *Constructor.  Resets board to zeros
	 */
   public SudokuGenerator() {
   	board = new int[BOARD_WIDTH][BOARD_HEIGHT];
   }
	
	/**
	 *Driver method for nextBoard.
	 *@param  difficult the number of blank spaces to insert
	 *@return board, a partially completed 9x9 Sudoku board
	 */
	public int[][] nextBoard(int difficulty)
	{
		board = new int[BOARD_WIDTH][BOARD_HEIGHT];
		nextCell(0,0);
		makeHoles(difficulty);
		return board;
		
	}
	
	/**
	 *Recursive method that attempts to place every number in a cell.
	 *
	 *@param x x value of the current cell
	 *@param y y value of the current cell
	 *@return  true if the board completed legally, false if this cell
	 *has no legal solutions.
	 */
	public boolean nextCell(int x, int y)
	{
		int nextX = x;
		int nextY = y;
		int[] toCheck = {1,2,3,4,5,6,7,8,9};
		Random r = new Random();
		int tmp = 0;
		int current = 0;
		int top = toCheck.length;

   		for(int i=top-1;i>0;i--)
		{
		    current = r.nextInt(i);
		    tmp = toCheck[current];
		    toCheck[current] = toCheck[i];
		    toCheck[i] = tmp;
    	}
		
		for(int i=0;i<toCheck.length;i++)
		{
			if(trace((legalMove(x, y, toCheck[i])),61,6,61,33,"/C:/Users/Klant/workspace2/project1/src/SudokuGenerator.java"))
			{
				board[x][y] = toCheck[i];
				if(trace((x == 8),64,7,64,13,"/C:/Users/Klant/workspace2/project1/src/SudokuGenerator.java"))
				{
					if(trace((y == 8),66,8,66,14,"/C:/Users/Klant/workspace2/project1/src/SudokuGenerator.java"))
						return true;//We're done!  Yay!
					else
					{
						nextX = 0;
						nextY = y + 1;
					}
				}
				else
				{
					nextX = x + 1;
				}
				if(nextCell(nextX, nextY))
					return true;
			}
		}
		board[x][y] = 0;
		return false;
	}
	
	/**
	 *Given a cell's coordinates and a possible number for that cell,
	 *determine if that number can be inserted into said cell legally.
	 *
	 *@param x       x value of cell
	 *@param y       y value of cell
	 *@param current The value to check in said cell.
	 *@return        True if current is legal, false otherwise.
	 */
	private boolean legalMove(int x, int y, int current) {
		for(int i=0;i<9;i++) {
			if(current != board[x][i]) // fault != / ==
				return false;
		}
		for(int i=0;i<9;i++) {
			if(current == board[i][y])
				return false;
		}
		int cornerX = 0;
		int cornerY = 0;
		if(x > 2)
			if(trace((x > 5),107,6,107,11,"/C:/Users/Klant/workspace2/project1/src/SudokuGenerator.java"))
				cornerX = 6;
			else
				cornerX = 3;
		if(y > 2)
			if(trace((y > 5),112,6,112,11,"/C:/Users/Klant/workspace2/project1/src/SudokuGenerator.java"))
				cornerY = 6;
			else
				cornerY = 3;
		for(int i=cornerX;i<10 && i<cornerX+3;i++)
			for(int j=cornerY;j<10 && j<cornerY+3;j++)
				if(current == board[i][j])
					return false;
		return true;
	}
	
	/**
	 *Given a completed board, replace a given amount of cells with 0s
	 *(to represent blanks)
	 *@param holesToMake How many 0s to put in the board.
	 */
	public void makeHoles(int holesToMake)
	{
		/* We define difficulty as follows:
			Easy: 32+ clues (49 or fewer holes)
			Medium: 27-31 clues (50-54 holes)
			Hard: 26 or fewer clues (54+ holes)
			This is human difficulty, not algorighmically (though there is some correlation)
		*/
		double remainingSquares = 81;
		double remainingHoles = (double)holesToMake;
		
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
			{
				double holeChance = remainingHoles/remainingSquares;
				if(trace((Math.random() <= holeChance),143,7,143,34,"/C:/Users/Klant/workspace2/project1/src/SudokuGenerator.java"))
				{
					board[i][j] = 0;
					remainingHoles--;
				}
				remainingSquares--;
			}
	}
	
	/**
	 *Prints a representation of board on stdout
	 */
	public void print()
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
				System.out.print(board[i][j] + "  ");
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
//		System.out.println(Arrays.toString(board[0]));//Collections.frequency(Arrays.asList(board[0]), 0));
		
		SudokuGenerator sg = new SudokuGenerator();
		sg.nextBoard(35);
//		System.out.println(Collections.frequency(Arrays.asList(board[0]), 0) != 9);
//		Arrays.asList(board[0]).contains(yourValue);
//		board[0] for (int i = 0; i<=9; i++){
//			
//		}
		sg.print();
	}
	
	int[][] board;
	private int operations;

public static boolean trace(boolean b, int beginL, int beginC, int endL, int endC, String s){ 
  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("testtext.txt", true)))) {out.println("" + beginL + ", " + beginC + ", " + endL + ", " + endC  + ", " + s);}catch (IOException ioException) {} 
return b;}
}