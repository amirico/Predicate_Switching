package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
    Generate solutions to classic N-queens problem.    

    author Kieran Herley Feb 2013
*/

public class NQueens
{

   /**
      Generate all legal solutions.
   */
   public int generateSolns(int numQueens)
   {  int position[] = new int[numQueens];
      return generateSolns(position, 0, numQueens);
   }

   /*
      Extend existing partial solution by adding queen (if possible)
      on row r.
   */
   private int generateSolns(int board[], int row, int numQueens)
   {  print(board, row, numQueens);
      if (trace((row == numQueens) ,24,10,24,26,"/C:/Users/Klant/workspace11/project15/src/NQueens.java"))
      {  System.out.println("*** Solution! ***");
         //print(board, numQueens);
      System.out.println("_________________________+");
      return 1;
      }
      else
      { int numSolutions = 0;
        for (int col = 0; col < numQueens; col++)
        {  if (trace((! attacked(board, row, col)),33,15,33,42,"/C:/Users/Klant/workspace11/project15/src/NQueens.java"))
           {  board[row] = col;
              numSolutions += generateSolns(board, row+1, numQueens);
           }     
        }
        System.out.println("_________________________");
        return numSolutions;
      }
   }


   /*
      Return true if a queen placed at (r, c) conflicts with any queen
      on any of the earlier rows of the board.
      @param r, c -- coordinates of proposed queen placement
      @return boolean flaf indicating whether placement is safe
   */
   private boolean attacked (int board[],  int r, int c)
   {  for (int i = 0; i < r; i++)
         if ((trace( conflict(r, c, i, board[i] ) ,52,14,52,42,"/C:/Users/Klant/workspace11/project15/src/NQueens.java")))
         {  return true;
         }
      return false;
   }

   /*
      Return true of a queen at position (r1, c1) attacks one at 
      position (r2, c2).
      @param r1, c1 -- coordinates of first queen
      @param r2, c2 -- coordinates of second queen
      @return boolean flag indicating whether two queens attack one another
   */
   private boolean conflict(int r1, int c1, int r2, int c2)
   {  boolean sameRow = (r1 == r2);
      boolean sameColumn = (c1 == c2);
      boolean sameDiagonal = ((r1 - c1) == (r2 - c2)); //c1 - r1
      boolean sameAntidiagonal = ((r1 + c1) == (r2 + c2));
      boolean tmp =   sameRow || sameColumn || sameDiagonal || sameAntidiagonal;

      return tmp;
   }

   /*
      Print out the current solution.
   */
   private void print(int board[], int numPlaced, int numQueens)
   {   System.out.println();
       System.out.println("----------------");
       System.out.printf("Solution\n");
       System.out.println("----------------");
       for (int r = 0; r < numQueens; r++)
       {
          for (int c = 0; c < numQueens; c++)
             if (trace((c == board[r] && r < numPlaced),86,17,86,47,"/C:/Users/Klant/workspace11/project15/src/NQueens.java")) 
                System.out.print('Q');
             else
                System.out.print('.');
          System.out.println();
       }
       System.out.println("----------------");
       //System.exit(1);
   }




   /*
   */
   public static void main(String args[])
   {  NQueens q = new NQueens();
      int numQueens = 4;
      int numSolns = q.generateSolns(numQueens);
  
      System.out.printf("\nThere %s %d solution%s  for %d queens.\n", 
                        (numSolns == 1? "was": "were"), numSolns, 
                        (numSolns == 1? "": "s"), numQueens);
   }

public static boolean trace(boolean b, int beginL, int beginC, int endL, int endC, String s){ 
  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("testtext.txt", true)))) {out.println("" + beginL + ", " + beginC + ", " + endL + ", " + endC  + ", " + s);}catch (IOException ioException) {} 
return b;}
}