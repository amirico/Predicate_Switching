package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * <p>Sudoku3 is responsible for finding a solution to a Sudoku puzzle.
 * </p>
 * 
 * @author Christian Pape
 */
public class Sudoku {


  public static final int EMPTY_FIELD = -1;

  /**
   *  row[z][x] is true if and only the number x has been already placed in the
   *  row z.
   */
  private boolean [] [] row  = new boolean[9][10]; 

  /**
   *  column[c][x] is true if and only the number x has been already placed in the
   *  column c.
   */
  private boolean [] [] column = new boolean[9][10];

  /**
   *  box[boxNumber][x] is true if and only if  the number x has been already placed 
   *  into the box with the given number.
   *  For a row r and column c the corresponding box nubmer is  3 * (r / 3) + (c / 3)
   */
  private boolean [] [] box = new boolean[9][10];


  private int [] [] sudoku  = new int[9][9];

  /**
   * Alle  numbers given as hints for the Sudoku
   */
  private int [] [] partialSudoku ;

  /**
   * Creates a new Sudoku with the given partial Sudoku.
   */
  public Sudoku(int [] [] partialSudoku ) {
    this.partialSudoku = partialSudoku;
    initialize();
  }

  private void initialize() {
          for (int boxNumber = 0; boxNumber < 9; boxNumber++) {
              for (int number = 1; number < 10; number++) {
                this.box[boxNumber][number] = false;
              }
      }
          for (int row = 0; row < 9; row++) {
              for (int column = 0; column < 9; column++) {
                this.row[row][column + 1] = false;
                this.column[row][column + 1] = false;
                this.sudoku[row][column] = EMPTY_FIELD;

                if (trace((! isEmptyField(partialSudoku[row][column])),60,20,60,62,"/C:/Users/Klant/workspace11/project11/src/Sudoku.java")) {
                    this.sudoku[row][column] = partialSudoku[row][column];
                    this.row[row][partialSudoku[row][column]] = true;
                    this.column[column][partialSudoku[row][column]] = true;
                    this.box[getBoxNumber(row, column)][partialSudoku[row][column]] = true;
                }
              }
            }
  }

  private boolean isEmptyField(int number) {
        return number <= 0 || number > 9;
  }



  /**
   * Prints out the Sudoku
   */
  public void print() {
    for (int row = 0; row < 9; row++) {
      for (int column = 0; column < 9; column++) {
        System.out.print(sudoku[row][column]  + "\t");
      }
      System.out.println();
    }    
  }


  /**
   * Uses recursive backtracking to solve this Sudoku puzzle.
   * @param counter identifies each of the 81 fields  - initial value is  0
   */
  public boolean solve(int counter) {
    int row = counter / 9;
    int column = counter % 9;
    
    for (int number = 1; number < 10; number++) {
      if (trace((numberCanBePlaced(row, column, number)) ,98,10,98,48,"/C:/Users/Klant/workspace11/project11/src/Sudoku.java")) { 
                boolean r = this.row[row][number];
                boolean c = this.column[column][number];
                boolean b = this.box[getBoxNumber(row, column)][number];
                this.sudoku[row][column] = number;
                this.row[row][number] = true;
                this.column[column][number] = true;
                this.box[getBoxNumber(row, column)][number] = true;
        if (trace((counter < 80),106,12,106,24,"/C:/Users/Klant/workspace11/project11/src/Sudoku.java")) {
           if (trace((solve(counter + 1)) ,107,15,107,33,"/C:/Users/Klant/workspace11/project11/src/Sudoku.java")) {
               return true;
            } else {
                  this.sudoku[row][column] = partialSudoku[row][column];
                  this.row[row][number] = r;
                  this.column[column][number] = c;
                  this.box[getBoxNumber(row, column)][number] = b;
            }
        } else {
           return true; 
        }
      }
    }
    return false;
  }

    private int getBoxNumber(int row, int column) {
        return 3 * (row / 3) + (column / 3);
    }




  /**
   * Returns true if the geiven number is not yet placed in
   * the given row, column or the corresponding 3x3 box.
   */
  private boolean numberCanBePlaced(int row, int column, int number) {
    return  (partialSudoku[row][column] == number || isEmptyField(partialSudoku[row][column])
                || ! this.row[row][number] // ||
                || ! this.column[column][number] // ||
                || ! this.box[getBoxNumber(row, column)][number]); // ||
  }



  /**
   * Creates a new Sudoku with n random numbers (1-9).
        */
   public void createSudoku(int n) {
                do {
                        for (int row = 0; row < partialSudoku.length; row++) {
                                for (int column = 0; column < partialSudoku[row].length; column++) {
                                        partialSudoku[row][column] = EMPTY_FIELD;
                                }
                        }
                        initialize();
                        
                        for (int i = 0; i < n; i++) {
                                int row = (int) (Math.random() * 9);
                                int column = (int) (Math.random() * 9);
                                int number = 1 + (int) (Math.random() * 9);
                                if (trace((numberCanBePlaced(row, column, number)),159,36,159,74,"/C:/Users/Klant/workspace11/project11/src/Sudoku.java")) {
                                        this.sudoku[row][column] = number;
                                        this.row[row][number] = true;
                                        this.column[column][number] = true;
                                        this.box[getBoxNumber(row, column)][number] = true;
                                        partialSudoku[row][column] = number;
                                }
                        }
                        print();
                        System.out.println();
                        initialize();
                } while ( ! solve(0));

                print();
                initialize();
        }



  public static void main(String argv[]) {
    
    Sudoku sudoku = new Sudoku(new int[][] {
        { 9,  -1, 3, -1,  1, -1,  7, -1, -1},
        {-1,  6, -1, -1, -1,  8,  5, -1, -1},
        {-1,  5, -1, -1, -1, -1, -1,  9,  6},
        {-1, -1, -1, -1,  3, -1,  6, -1,  4},
        {-1,  4, -1,  9, -1,  1,  3,  7,  2},
        {-1, -1, -1,  8,  7,  4, -1,  1, -1},
        {-1, -1,  5, -1, -1,  3,  2, -1,  1},
        { 6,  8,  7, -1, -1, -1, -1, -1, -1},
        { 3, -1, -1, -1, -1, -1, -1,  5, -1}
    });
    
    long t1 = System.nanoTime();
    
    if ((trace( sudoku.solve(0) ,194,9,194,24,"/C:/Users/Klant/workspace11/project11/src/Sudoku.java"))) {
      System.out.println("Solution found");
      sudoku.print();
    } else {
      System.out.println("No solution has been found");
    }

    long t2 = System.nanoTime() - t1;
    
    System.out.println("Time used: " + t2 + " ns / " + (t2 / 1000000) + " ms");
    
    sudoku.createSudoku(20);
  }

public static boolean trace(boolean b, int beginL, int beginC, int endL, int endC, String s){ 
  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("testtext.txt", true)))) {out.println("" + beginL + ", " + beginC + ", " + endL + ", " + endC  + ", " + s);}catch (IOException ioException) {} 
return b;}
}

























































