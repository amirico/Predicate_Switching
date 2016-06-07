package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The <code>Sudoku</code> class povides a static <code>main</code>
 * method allowing it to be called from the command line to print the
 * solution to a specified Sudoku problem.
 *
 * <p>The following is an example of a Sudoku problem:
 *
 * <pre>
 * -----------------------
 * |   8   | 4   2 |   6   |
 * |   3 4 |       | 9 1   |
 * | 9 6   |       |   8 4 |
 *  -----------------------
 * |       | 2 1 6 |       |
 * |       |       |       |
 * |       | 3 5 7 |       |
 *  -----------------------
 * | 8 4   |       |   7 5 |
 * |   2 6 |       | 1 3   |
 * |   9   | 7   1 |   4   |
 *  -----------------------
 * </pre>
 *
 * The goal is to fill in the missing numbers so that
 * every row, column and box contains each of the numbers
 * <code>1-9</code>.  Here is the solution to the
 * problem above:
 *
 * <pre>
 *  -----------------------
 * | 1 8 7 | 4 9 2 | 5 6 3 |
 * | 5 3 4 | 6 7 8 | 9 1 2 |
 * | 9 6 2 | 1 3 5 | 7 8 4 |
 *  -----------------------
 * | 4 5 8 | 2 1 6 | 3 9 7 |
 * | 2 7 3 | 8 4 9 | 6 5 1 |
 * | 6 1 9 | 3 5 7 | 4 2 8 |
 *  -----------------------
 * | 8 4 1 | 9 6 3 | 2 7 5 |
 * | 7 2 6 | 5 8 4 | 1 3 9 |
 * | 3 9 5 | 7 2 1 | 8 4 6 |
 *  -----------------------
 * </pre>
 *
 * Note that the first row <code>187492563</code> contains
 * each number exactly once, as does the first column
 * <code>159426873</code>, the upper-left box
 * <code>187534962</code>, and every other row, column
 * and box.
 *
 * <p>The {@link #main(String[])} method encodes a problem as an array
 * of strings, with one string encoding each constraint in the problem
 * in row-column-value format.  Here is the problem again with
 * the indices indicated:
 *
 * <pre>
 *     0 1 2   3 4 5   6 7 8
 *    -----------------------
 * 0 |   8   | 4   2 |   6   |
 * 1 |   3 4 |       | 9 1   |
 * 2 | 9 6   |       |   8 4 |
 *    -----------------------
 * 3 |       | 2 1 6 |       |
 * 4 |       |       |       |
 * 5 |       | 3 5 7 |       |
 *   -----------------------
 * 6 | 8 4   |       |   7 5 |
 * 7 |   2 6 |       | 1 3   |
 * 8 |   9   | 7   1 |   4   |
 *    -----------------------
 * </pre>
 *
 * The <code>8</code> in the upper left box of the puzzle is encoded
 * as <code>018</code> (<code>0</code> for the row, <code>1</code> for
 * the column, and <code>8</code> for the value).  The <code>4</code>
 * in the lower right box is encoded as <code>874</code>.
 *
 * <p>The full command-line invocation for the above puzzle is:
 *
 * <pre>
 * % java -cp . Sudoku 018 034 052 076 \
 *                     113 124 169 171 \
 *                     209 216 278 284 \
 *                     332 341 356     \
 *                     533 545 557     \
 *                     608 614 677 685 \
 *                     712 726 761 773 \
 *                     819 837 851 874 \
 * </pre>
 *
 * <p>See <a href="http://en.wikipedia.org/wiki/Sudoku">Wikipedia:
 * Sudoku</a> for more information on Sudoku.
 *
 * <p>The algorithm employed is similar to the standard backtracking
 * <a href="http://en.wikipedia.org/wiki/Eight_queens_puzzle">eight
 * queens algorithm</a>.
 *
 * @version 1.0
 * @author <a href="http://www.colloquial.com/carp">Bob Carpenter</a>
 */
public class Sudoku {

    /**
     * Print the specified Sudoku problem and its solution.  The
     * problem is encoded as specified in the class documentation
     * above.
     *
     * @param args The command-line arguments encoding the problem.
     */
    public static void main(String[] args) {
        int[][] matrix = parseProblem(args);
        writeMatrix(matrix);
        if (trace((solve(0,0,matrix)),113,12,113,29,"/C:/Users/Klant/workspace11/Sudoku2/src/Sudoku.java"))    // solves in place
            writeMatrix(matrix);
        else
            System.out.println("NONE");
    }

    static boolean solve(int i, int j, int[][] cells) {
        if (trace((i == 9),120,12,120,18,"/C:/Users/Klant/workspace11/Sudoku2/src/Sudoku.java")) {
            i = 0;
            if (trace((++j == 9),122,16,122,24,"/C:/Users/Klant/workspace11/Sudoku2/src/Sudoku.java"))
                return true;
        }
        if (trace((cells[i][j] != 0),125,12,125,28,"/C:/Users/Klant/workspace11/Sudoku2/src/Sudoku.java"))  // skip filled cells
            return solve(i+1,j,cells);

        for (int val = 1; val <= 9; ++val) {
            if (trace((legal(i,j,val,cells)),129,16,129,36,"/C:/Users/Klant/workspace11/Sudoku2/src/Sudoku.java")) {
                cells[i][j] = val;
                if (trace((solve(i+1,j,cells)),131,20,131,38,"/C:/Users/Klant/workspace11/Sudoku2/src/Sudoku.java"))
                    return true;
            }
        }
        cells[i][j] = 0; // reset on backtrack
        return false;
    }

    static boolean legal(int i, int j, int val, int[][] cells) {
        for (int k = 0; k < 9; ++k)  // row
            if (trace((val == cells[k][j]),141,16,141,34,"/C:/Users/Klant/workspace11/Sudoku2/src/Sudoku.java"))
                return false;

        for (int k = 0; k < 9; ++k) // col
            if (trace((val == cells[i][k]),145,16,145,34,"/C:/Users/Klant/workspace11/Sudoku2/src/Sudoku.java"))
                return false;

        int boxRowOffset = (i / 3);
        int boxColOffset = (j / 3);
        for (int k = 0; k < 3; ++k) // box
            for (int m = 0; m < 3; ++m)
                if (trace((val == cells[boxRowOffset+k][boxColOffset+m]),152,20,152,64,"/C:/Users/Klant/workspace11/Sudoku2/src/Sudoku.java"))
                    return false;

        return true; // no violations, so it's legal
    }

    static int[][] parseProblem(String[] args) {
        int[][] problem = new int[9][9]; // default 0 vals
        for (int n = 0; n < args.length; ++n) {
            int i = Integer.parseInt(args[n].substring(0,1));
            int j = Integer.parseInt(args[n].substring(1,2));
            int val = Integer.parseInt(args[n].substring(2,3));
            problem[i][j] = val;
        }
        return problem;
    }

    static void writeMatrix(int[][] solution) {
        for (int i = 0; i < 9; ++i) {
            if (trace((i % 3 == 0),171,16,171,26,"/C:/Users/Klant/workspace11/Sudoku2/src/Sudoku.java"))
                System.out.println(" -----------------------");
            for (int j = 0; j < 9; ++j) {
                if (trace((j % 3 == 0),174,20,174,30,"/C:/Users/Klant/workspace11/Sudoku2/src/Sudoku.java")) System.out.print("| ");
                System.out.print(solution[i][j] == 0
                    ? " "
                    : Integer.toString(solution[i][j]));

                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }


public static boolean trace(boolean b, int beginL, int beginC, int endL, int endC, String s){ 
  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("testtext.txt", true)))) {out.println("" + beginL + ", " + beginC + ", " + endL + ", " + endC  + ", " + s);}catch (IOException ioException) {} 
return b;}
}