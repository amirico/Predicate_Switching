package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Maze {

	

	public static boolean solve (int [][] grid,int row,int col){

		boolean done = false;

		if (trace((trace((valid (grid,row,col)),9,6,9,26,"/C:/Users/Klant/workspace2/project10/src/Maze.java")),15,6,15,97,"/C:/Users/Klant/workspace2/project10/src/experimentt/Maze.java")){ 

			grid[row][col] = 3; // cell has been tried

			if (trace((trace((row == grid.length -1 && col == grid[0].length -1),13,7,13,56,"/C:/Users/Klant/workspace2/project10/src/Maze.java")),19,7,19,129,"/C:/Users/Klant/workspace2/project10/src/experimentt/Maze.java"))

				done = true; // maze is solved

 			else{

 				done = solve (grid,row+1,col); //try down

 				if (trace((trace((!done),21,9,21,14,"/C:/Users/Klant/workspace2/project10/src/Maze.java")),27,9,27,87,"/C:/Users/Klant/workspace2/project10/src/experimentt/Maze.java"))

 					done = solve(grid,row,col+1);// try right

 				if (trace((trace((!done),25,9,25,14,"/C:/Users/Klant/workspace2/project10/src/Maze.java")),31,9,31,87,"/C:/Users/Klant/workspace2/project10/src/experimentt/Maze.java"))

 					done = solve(grid,row-1,col);// try up

 				if (trace((trace((!done),29,9,29,14,"/C:/Users/Klant/workspace2/project10/src/Maze.java")),35,9,35,87,"/C:/Users/Klant/workspace2/project10/src/experimentt/Maze.java"))

 					done = solve(grid,row , col - 1);// try left

 			}

 			if (trace((trace((done),35,8,35,12,"/C:/Users/Klant/workspace2/project10/src/Maze.java")),41,8,41,85,"/C:/Users/Klant/workspace2/project10/src/experimentt/Maze.java"))

 				grid[row][col] = 7; // part of the final path

		}

		return done;

	} // function solve

	

	private static boolean valid(int [ ][ ] grid,int row,int col){

		boolean result = false;

		// check if cell is in the bounds of the matrix

		if (trace((trace((row >= 0 && row < grid.length && col  >= 0 && col < grid[0].length),53,6,53,72,"/C:/Users/Klant/workspace2/project10/src/Maze.java")),59,6,59,145,"/C:/Users/Klant/workspace2/project10/src/experimentt/Maze.java"))

			// check if cell is not blocked and not previously tried

			if (trace((trace((grid [row][col] == 0),57,7,57,27,"/C:/Users/Klant/workspace2/project10/src/Maze.java")),63,7,63,100,"/C:/Users/Klant/workspace2/project10/src/experimentt/Maze.java")) // fault 0

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
		
		if (trace((trace((ans),109,6,109,9,"/C:/Users/Klant/workspace2/project10/src/Maze.java")),115,6,115,83,"/C:/Users/Klant/workspace2/project10/src/experimentt/Maze.java")){
			
			System.out.println ("Maze solved!:");

			print_maze (grid );

		}

		else

			System.out.println ("there is no solution");

	}

	


public static boolean trace(boolean b, int beginL, int beginC, int endL, int endC, String s){ 
  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("testtext.txt", true)))) {out.println("" + beginL + ", " + beginC + ", " + endL + ", " + endC  + ", " + s);}catch (IOException ioException) {} 
return b;}
}