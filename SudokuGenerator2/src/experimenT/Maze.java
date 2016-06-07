package experimenT;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Maze {

	

	public static int experimentalY = 0;
 public static int experimentalI;

public static boolean functionF(boolean b){  
experimentalY++;
 	if (experimentalI == experimentalY) return !b; else  return b;}
public static boolean solve (int [][] grid,int row,int col){

		boolean done = false;

		if (functionF(valid (grid,row,col))){ 

			grid[row][col] = 3; // cell has been tried

			if (functionF(row == grid.length -1 && col == grid[0].length -1))

				done = true; // maze is solved

 			else{

 				done = solve (grid,row+1,col); //try down

 				if (!done)

 					done = solve(grid,row,col+1);// try right

 				if (!done)

 					done = solve(grid,row-1,col);// try up

 				if (!done)

 					done = solve(grid,row , col - 1);// try left

 			}

 			if (done)

 				grid[row][col] = 7; // part of the final path

		}

		return done;

	} // function solve

	

	private static boolean valid(int [ ][ ] grid,int row,int col){

		boolean result = false;

		// check if cell is in the bounds of the matrix

		if (row >= 0 && row < grid.length && col  >= 0 && col < grid[0].length)

			// check if cell is not blocked and not previously tried

			if (grid [row][col] == 0) // fault 0

				result = true;

		return result;

	}// function valid

	

	public static void print_maze (int[][] maze){

		for (int i=0; i<maze.length; i++){

			for (int j=0; j<maze[0].length; j++)

				System.out.print (maze[i][j]);

			System.out.println();

		}

	}// function print_maze

		

	

	public static void main (String [] args){

		experimentalI = Integer.parseInt((args[0]));;
//		try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Klant\\workspace2\\project10\\results.txt",true)))){out.println("WOW");}catch(IOException ioException){} 

		int [][] grid = {{1,1,1,0,1,1,0,0,0,1,1,1,1},

		 					 {1,0,1,1,1,0,1,1,1,1,0,0,1},

		 					 {0,0,0,0,1,0,1,0,1,0,1,0,0},

		 					 {1,1,1,0,1,1,1,0,1,0,1,1,1},

		 					 {1,0,1,0,0,0,0,1,1,1,0,0,1},

		 					 {1,0,1,1,1,1,1,1,0,1,1,1,1},

		 					 {1,0,0,0,0,0,0,0,0,0,0,0,0},

		 					 {1,1,1,1,1,1,1,1,1,1,1,1,1}};

		System.out.println ("the labyrinth:");

		print_maze ( grid );

		boolean ans = solve (grid,0,0);
		try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Klant\\workspace2\\project10\\results.txt",true)))){out.println(ans);}catch(IOException ioException){} 
		System.out.println(ans);
		if (functionF(ans)){
			System.out.println("here");
			System.out.println ("Maze solved!:");

			print_maze (grid );

		}

		else

			System.out.println ("there is no solution");

	}

	

}