package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;
import java.io.File;

//I have commented this program more than normal just for those who are
//interested in understanding it.
public class SudokuSolver
{
public static boolean trace (boolean b){return b;}
	public static void main(String[] args) throws Exception
  {
    Scanner fileScanner = new Scanner(new File("Sudoku.txt"));
	
    int[][] sudoku = new int[9][9]; //Will hold the puzzle in a 2D array.
    //We will use this variable to hold the next line of the puzzle, then parse
    //each digit on that line.
	String line = fileScanner.nextLine();
    
    //Loops round placing the digits into the correct place of the array.
	for (int y = 0; y < 9; y++)
	{
	  for (int x = 0; x < 9; x++)
	  {
        //Gets the digit converts it from a char to an in and places it in the array.
        sudoku[y][x] = Character.getNumericValue(line.charAt(x));   
        //At the end of each line we load the next line, but check there is a next
        //line before trying to load it so the program does not crash.
	    if (trace((trace(x == 8 && fileScanner.hasNextLine())),27,9,27,51,"/C:/Users/Klant/workspace2/project3/src/SudokuSolver.java"))
		{
		  line = fileScanner.nextLine();
		}
	  } //for x
    } //for y
    
    //The recursive function that actually solves the sudoku (starting at 0,0)
    solve(sudoku, 0, 0);
    
  } //main
  
  //This is a recursive function used to go through each cell and place a valid number.
  //Until they are filled in.
  private static void solve(int[][] sudoku, int cellX, int cellY)
  {         
    //If the y value is 9 then the sudoku has been solved.
    if(trace((trace(cellY > 8)),44,7,44,23,"/C:/Users/Klant/workspace2/project3/src/SudokuSolver.java"))
    {
      printSudoku(sudoku);
      System.out.println();
      //System.exit(1); // = This will end the program quicker as it does not have to
      //"go back up" through the levels of recursion, but means the main
      //routine will not continue running.
      //Also if there is more than one solution to the sudoku using this will only print
      //the first solution found.
    }
    else
    {
      //Here we calculate the next digit for the solve routine to try.
      int nextX = cellX;
      int nextY = cellY;
      if(trace((trace(cellX == 8)),59,9,59,26,"/C:/Users/Klant/workspace2/project3/src/SudokuSolver.java"))
      {
        //When at the end of a row add 1 to the row and reset the "column" to 0.
        nextX = 0;
        nextY++;
      }
      else
      {
        nextX++;
      }

      //If the digit was already given to us, we can move onto the next one.
      if(trace((trace(sudoku[cellY][cellX] != 0)),71,9,71,41,"/C:/Users/Klant/workspace2/project3/src/SudokuSolver.java"))
      {
        solve(sudoku, nextX, nextY);
      }
      else
      {
        //Otherwise, starting at 1 through 9 we check if the number is "legal"
        //and if so place that number, and move on to the next cell.
        for(int checkNum = 1; checkNum < 10; checkNum++)
        {
          if(trace((trace(checkSquare(sudoku, cellX, cellY,81,13,83,50,"/C:/Users/Klant/workspace2/project3/src/SudokuSolver.java")), checkNum) 
             && checkRow(sudoku, cellY, checkNum)
             && checkCol(sudoku, cellX, checkNum)))
          {
            sudoku[cellY][cellX] = checkNum;
            solve(sudoku, nextX, nextY);
          }
        }
        //If we get to here it means in it's current state the sudoku is impossible
        //which means that one of the numbers we "placed" earlier is incorrect.
        sudoku[cellY][cellX] = 0;
      }
    }
  }
  
  //This method is given a cell location and a number to check, it then checks
  //whether that number is already in the 3x3 square and returns false if so.
  private static boolean checkSquare(int[][] sudoku, int reqX, int reqY, int toCheck)
  {
    int rowY;
    int colX;
    
    //First we work out which column the "square" belongs to.    
    //We take the given x value and if it is below 3 then that means
    //the square we need is in the first column (out of 3). etc.
    if(trace((trace(reqX < 3)),106,7,106,22,"/C:/Users/Klant/workspace2/project3/src/SudokuSolver.java"))
    {
      colX = 0;
    }
    else if (trace((trace(reqX < 6)),110,13,110,28,"/C:/Users/Klant/workspace2/project3/src/SudokuSolver.java"))
    {
      colX = 3;
    }
    else
    {
      colX = 6;
    }
    
    //We do the same but for the rows. For example if the y value is 5 then
    //the related square would be on the second row.
    if(trace((trace(reqY < 3)),121,7,121,22,"/C:/Users/Klant/workspace2/project3/src/SudokuSolver.java"))
    {
      rowY = 0;
    }
    else if (trace((trace(reqY < 6)),125,13,125,28,"/C:/Users/Klant/workspace2/project3/src/SudokuSolver.java"))
    {
      rowY = 3;
    }
    else
    {
      rowY = 6;
    }
    
    //We have now defined the square we need to check and have the top left
    //co-ordinate stored in the variables rowY and colX.
    //We now loop round and check each digit in the square, and if a digit matches
    //we return false.
    for(int y = rowY; y < rowY + 3; y++)
    {
      for(int x = colX; x < colX + 3; x++)
      {
        if(trace((trace(sudoku[y][x] == toCheck)),142,11,142,41,"/C:/Users/Klant/workspace2/project3/src/SudokuSolver.java"))
          {
            return false;
          }
      }
    }

    return true; //number not in the square.
    
  }
  
  //Checks if a given number is in a given row and returns false if it is.
  private static boolean checkRow(int[][] sudoku, int rowY, int toCheck)
  {
    //loops round each digit in a row.
    for(int x = 0; x < 9; x++)
    {
      //Checks if the given number is the same as the current digit
      //and returns false if so.
      if (trace((trace(toCheck == sudoku[rowY][x])),161,10,161,43,"/C:/Users/Klant/workspace2/project3/src/SudokuSolver.java"))
      {
        return false;
      }
    }
    return true; //the number is not in the row.
  }
  
  //Checks if a given number is in a given column and returns false if it is.
  private static boolean checkCol(int[][] sudoku, int colX, int toCheck)
  {
    //Loops round each digit in a column.
    for(int y = 0; y < 9; y++)
    {
      //Checks if the current digit is the given digit and returns false if so.
      if (trace((trace(toCheck == sudoku[y][colX])),176,10,176,43,"/C:/Users/Klant/workspace2/project3/src/SudokuSolver.java"))
      {
        return false;
      }
    }
    return true; //the number is not in the column.
  }
  
  //Prints the sudoku to the screen.
  private static void printSudoku(int sudoku[][])
  {
    //Loops round each digit and prints it.
    for(int y = 0; y < 9; y++)
	{
	  for(int x = 0; x < 9; x++)
	  {
	    System.out.print(sudoku[y][x]);
	    //Starts a new line when at the end of a row.
        if(trace((trace(x == 8)),194,11,194,24,"/C:/Users/Klant/workspace2/project3/src/SudokuSolver.java"))
		{
		  System.out.println();
		}
	  } //for x
	} //for y
  } //printSudoku

public static boolean trace(boolean b, int beginL, int beginC, int endL, int endC, String s){ 
  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("testtext.txt", true)))) {out.println("" + beginL + ", " + beginC + ", " + endL + ", " + endC  + ", " + s);}catch (IOException ioException) {} 
return b;}
}