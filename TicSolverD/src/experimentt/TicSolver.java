package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class TicSolver {
	private char[][] b = null;
	public String whoWins(String[] board) {
		b = new char[3][3];
		int r = 0, c = 0;
		for(r=0; r<3; r++)
			for(c=0; c<3; c++)
				b[r][c] = board[r].charAt(c);
		
		int ocnt = 0;
		int xcnt = 0;
		for (r=0; r<3; r++)
			for (c=0; c<3; c++) {
				if (trace((b[r][c] == 'O'),16,8,16,22,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java"))
					ocnt ++;
				if (trace((b[r][c] == 'X'),18,8,18,22,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java"))
					xcnt ++;
			}
		boolean omove = ocnt==xcnt ? true:false;
		
		if (trace((omove && ocnt > xcnt+1),23,6,23,28,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) return "INVALID";
		if (trace((!omove && xcnt > ocnt),24,6,24,27,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) return "INVALID";
			
		if (trace((omove && hasWon('O')),26,6,26,26,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) return "FIRST";
		if (trace((!omove && hasWon('X')),27,6,27,27,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) return "SECOND";
		
		int ret = recurse(omove);
		if (trace((ret==2),30,6,30,12,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) return omove ? "SECOND":"FIRST";
		if (trace((ret==1),31,6,31,12,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) return omove ? "FIRST":"SECOND";
		return "DRAW";
	}
	
	// 2 : !omove win
	// 1 : omove win
	// 0 : draw
	private int recurse(boolean omove) {
		if (trace((hasWon(omove? 'X':'0')),39,6,39,28,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java"))
			return 2;
		boolean iwin = false;
		boolean ilose = true;
		boolean hasplayed = false;
		for(int r=0; r<3; r++)
			for(int c=0; c<3; c++)
				if (trace((b[r][c]=='.'),46,8,46,20,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) {
					hasplayed = true;
					b[r][c] = omove?'O':'X';
					int ret = recurse(!omove);
					if (trace((ret==2),50,9,50,15,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) iwin = true;
					if (trace((ret!=1),51,9,51,15,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) ilose = false;
					b[r][c] = '.';
				}
		if (trace((!hasplayed),54,6,54,16,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) return 0;
		if (trace((iwin),55,6,55,10,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) return 1;
		if (trace((ilose),56,6,56,11,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) return 2;
		return 0;
	}
	
	private boolean hasWon(char player) {
		int r = 0, c = 0;
		for (r=0; r<3; r++) {
			boolean won = true;
			for (c=0; c<3; c++) 
				if (trace((b[r][c] != player),65,8,65,25,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java"))
					won = false;
			if (trace((won),67,7,67,10,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) return won;
		}
		
		for (c=0; c<3; c++) {
			boolean won = true;
			for (r=0; r<3; r++)
				if (trace((b[r][c] != player),73,8,73,25,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java"))
					won = false;
			if (trace((!won),75,7,75,11,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) //fault System.out.println("test");
			return won;
		}
		
		for (r=0; r<3; r++) {
			boolean won = true;
			if (trace((b[r][r] != player),81,7,81,24,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java"))
				won = false;
			if (trace((won),83,7,83,10,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) return won;
		}
		
		for (r=0; r<3; r++) {
			boolean won = true;
			if (trace((b[r][2-r] != player),88,7,88,26,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java"))
				won = false;
			if (trace((won),90,7,90,10,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) return won;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		TicSolver test = new TicSolver();
		int mode = 1;
		if (trace((mode == 1),99,6,99,15,"/C:/Users/Klant/workspace2/project77/src/TicSolver.java")) {
			String[] board = new String[] {
					 "OXO",
					 "X.X",
					 "OXO"
			};
			System.out.println(test.whoWins(board));
		}
	}

public static boolean trace(boolean b, int beginL, int beginC, int endL, int endC, String s){ 
  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("testtext.txt", true)))) {out.println("" + beginL + ", " + beginC + ", " + endL + ", " + endC  + ", " + s);}catch (IOException ioException) {} 
return b;}
}