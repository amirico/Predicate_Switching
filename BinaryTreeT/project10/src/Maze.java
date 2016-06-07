public class Maze {

	

	public static boolean solve (int [][] grid,int row,int col){

		boolean done = false;

		if (valid (grid,row,col)){ 

			grid[row][col] = 3; // cell has been tried

			if (row == grid.length -1 && col == grid[0].length -1)

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
		
		if (ans){
			
			System.out.println ("Maze solved!:");

			print_maze (grid );

		}

		else

			System.out.println ("there is no solution");

	}

	

}