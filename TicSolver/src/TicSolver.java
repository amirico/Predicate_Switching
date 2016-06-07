

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
				if (b[r][c] == 'O')
					ocnt ++;
				if (b[r][c] == 'X')
					xcnt ++;
			}
		boolean omove = ocnt==xcnt ? true:false;
		
		if (omove && ocnt > xcnt+1) return "INVALID";
		if (!omove && xcnt > ocnt) return "INVALID";
			
		if (omove && hasWon('O')) return "FIRST";
		if (!omove && hasWon('X')) return "SECOND";
		
		int ret = recurse(omove);
		if (ret==2) return omove ? "SECOND":"FIRST";
		if (ret==1) return omove ? "FIRST":"SECOND";
		return "DRAW";
	}
	
	// 2 : !omove win
	// 1 : omove win
	// 0 : draw
	private int recurse(boolean omove) {
		if (hasWon(omove? 'X':'0'))
			return 2;
		boolean iwin = false;
		boolean ilose = true;
		boolean hasplayed = false;
		for(int r=0; r<3; r++)
			for(int c=0; c<3; c++)
				if (b[r][c]=='.') {
					hasplayed = true;
					b[r][c] = omove?'O':'X';
					int ret = recurse(!omove);
					if (ret==2) iwin = true;
					if (ret!=1) ilose = false;
					b[r][c] = '.';
				}
		if (!hasplayed) return 0;
		if (iwin) return 1;
		if (ilose) return 2;
		return 0;
	}
	
	private boolean hasWon(char player) {
		int r = 0, c = 0;
		for (r=0; r<3; r++) {
			boolean won = true;
			for (c=0; c<3; c++) 
				if (b[r][c] != player)
					won = false;
			if (won) return won;
		}
		
		for (c=0; c<3; c++) {
			boolean won = true;
			for (r=0; r<3; r++)
				if (b[r][c] != player)
					won = false;
			if (!won) //fault System.out.println("test");
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
		TicSolver test = new TicSolver();
		int mode = 1;
		if (mode == 1) {
			String[] board = new String[] {
					 "OXO",
					 "X.X",
					 "OXO"
			};
			System.out.println(test.whoWins(board));
		}
	}
}