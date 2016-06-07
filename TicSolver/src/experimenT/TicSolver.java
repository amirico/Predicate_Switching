package experimenT;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class TicSolver {
	public static int experimentalY = 0;
 public static int experimentalI;

public static boolean functionF(boolean b){  
experimentalY++;
 	if (experimentalI == experimentalY) return !b; else  return b;}
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
				if (functionF(b[r][c] == 'O'))
					ocnt ++;
				if (functionF(b[r][c] == 'X'))
					xcnt ++;
			}
		boolean omove = ocnt==xcnt ? true:false;
		
		if (functionF(omove && ocnt > xcnt+1)) return "INVALID";
		if (functionF(!omove && xcnt > ocnt)) return "INVALID";
			
		if (functionF(omove && hasWon('O'))) return "FIRST";
		if (functionF(!omove && hasWon('X'))) return "SECOND";
		
		int ret = recurse(omove);
		if (functionF(ret==2)) return omove ? "SECOND":"FIRST";
		if (functionF(ret==1)) return omove ? "FIRST":"SECOND";
		return "DRAW";
	}
	
	// 2 : !omove win
	// 1 : omove win
	// 0 : draw
	private int recurse(boolean omove) {
		if (functionF(hasWon(omove? 'X':'0')))
			return 2;
		boolean iwin = false;
		boolean ilose = true;
		boolean hasplayed = false;
		for(int r=0; r<3; r++)
			for(int c=0; c<3; c++)
				if (functionF(b[r][c]=='.')) {
					hasplayed = true;
					b[r][c] = omove?'O':'X';
					int ret = recurse(!omove);
					if (functionF(ret==2)) iwin = true;
					if (functionF(ret!=1)) ilose = false;
					b[r][c] = '.';
				}
		if (functionF(!hasplayed)) return 0;
		if (functionF(iwin)) return 1;
		if (functionF(ilose)) return 2;
		return 0;
	}
	
	private boolean hasWon(char player) {
		int r = 0, c = 0;
		for (r=0; r<3; r++) {
			boolean won = true;
			for (c=0; c<3; c++) 
				if (functionF(b[r][c] != player))
					won = false;
			if (functionF(won)) return won;
		}
		
		for (c=0; c<3; c++) {
			boolean won = true;
			for (r=0; r<3; r++)
				if (functionF(b[r][c] != player))
					won = false;
			if (functionF(!won)) //fault System.out.println("test");
			return won;
		}
		
		for (r=0; r<3; r++) {
			boolean won = true;
			if (b[r][r] != player)
				won = false;
			if (won) return won;
		}
		
		for (r=0; r<3; r++) {
			boolean won = true;
			if (b[r][2-r] != player)
				won = false;
			if (won) return won;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		experimentalI = Integer.parseInt((args[0]));;
TicSolver test = new TicSolver();
		int mode = 1;
		if (functionF(mode == 1)) {
			String[] board = new String[] {
					 "OXO",
					 "X.X",
					 "OXO"
			};
			try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Klant\\workspace11\\project77\\results.txt",true)))){out.println((test.whoWins(board) == "FIRST"));}catch(IOException ioException){} 
System.out.println(test.whoWins(board));
		}
	}
}