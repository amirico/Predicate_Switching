
/*
  File: TestMineSweeper.java

  Description: Backend of Minesweeper that feeds to given Minesweeper GUI

  Student Name: Marc Aldrich

  Student UT EID: MAA5665

  Partner's Name: Nick Jordan

  Partner's UT EID: ncj283

  Course Name: CS 312

  Unique Numbers: 90125

  Date Created: 11 August 2014

  Date Last Modified: 16 August 2014

*/

class minesweeper 
{
  /** 
    mine and clue values, 9 - mine, 0-8 clue values
  */
  public int[][] mines;

  /** 
    tile values:
       0 - open 
       1 - closed
       2 - question 
       3 - flag
  */
  public int[][] tiles;
  //Variables to control win condition
  private int cMines; //COUNT MINES
  private int cFlags; //COUNT FLAGS

  /** 
    Game status - win, lose, play
  */
  private String status;

  /** 
    Default constructor
      *	board size 9 x 9
      * create mines and tile arrays
      * place mines
      * calculate clues
      * set game status to play   
  */
  public minesweeper() 
  {
    this(9,9);
  }

  /** 
    Non-default constructor
      * use specifies board size
      * create mines and tile arrays
      * place mines
      * calculate clues
      * set game status to play
      * newRows number of rows for grid
      * newCols number of columns for grid
  */
  public minesweeper (int newRows, int newCols) 
  {
    initGame(newRows, newCols);
  }

  /** 
    Returns the game status - "play", "win", or "lose"
  */
  public String getStatus () 
  {
    return status;
  }

  /** 
    Returns number of rows in the board
  */
  public int getRows () 
  {
    return mines.length;
  }

  /** 
    Returns the number of columns in the board
  */
  public int getCols () 
  {
    return mines[0].length;
  }

  /** 
    Returns the value of the mines array at r,c
    * -1 is returned if invalid r,c
    * r row index
    * c column index
  */
  public int getMines (int r, int c) 
  {
    if (validIndex(r, c)) 
    {
      return mines[r][c];
    } 
    else 
    {
      return -1;
    }
  }

  /** 
    Returns the value of the tiles array at r,c
    * -1 is returned if invalid r,c
    * r row index
    * c column index
  */
  public int getTiles (int r, int c) 
  {
    if (validIndex(r, c)) 
    {
      return tiles[r][c];
    } 
    else 
    {
      return -1;
    }
  }

  /** 
    Mark tile - 
    * open tile 
    * flag tile as mine 
    * set tile as question mark 
    * close tile
    * r row index
    * c column index
    * t: 0 - open, 1 - close, 2 - question, 3 - mine
    * invalid r,c values must be ignored
    * a tile that is opened must stay open
    * a tile that is marked as a flag (ie. tile[][] value 3) can not be opened
    * tile values can only change when game status is "play"
    * game status must be updated after a tile is opened
    * implementation must be recursive; you can use a helper method
  */
  public void markTile (int r, int c, int t) //MUST BE RECURSIVE
  {
    if (status == "play" && validIndex(r,c)) 
    {
        //IF tile is clear set to clear
        if (getTiles(r,c) == 0)
            tiles[r][c] = 0;
        if (tiles[r][c] == 1 || tiles[r][c] == 2) 
        {
            tiles[r][c] = t;
            //CHECK TO SEE IF GAME OVER
            if (t == 0 && mines[r][c] == 9) 
            status = "lose";
            
            //CHECK FOR GAME WIN
            if (tiles[r][c] == 3 && mines[r][c] == 9) 
            {
                cFlags++;
                gameWon();
            }
            
            if (mines[r][c] == 0 && t == 0) 
            {
                
                markTile(r-1,c-1,0);//TOP LEFT
                markTile(r-1,c,0);//TOP CENTER
                markTile(r-1,c+1,0);//TOP RIGHT
                markTile(r,c-1,0);//MID LEFT
                markTile(r,c+1,0);//MID RIGHT
                markTile(r+1,c-1,0);//BOTTOM LEFT
                markTile(r+1,c,0);//BOTTOM MID
                markTile(r+1,c+1,0);//BOTTOM RIGHT
                
            }
        }//END 3RD IF
        
        if (tiles[r][c] == 3)//DONT UNCOVER FLAGGED TILES
        {
            if (t != 0)
                if (mines[r][c] == 9 && tiles[r][c] == 3 && t == 2) //DECREMENT NUM FLAGS IF SWITCHING TO QUESTIONMARK
                {
		  cFlags--;
		 }
                tiles[r][c] = t;
        }
        
    }//END IF BLOCK
  }//END MARKTILE

  /** 
    Returns mines array as a String
  */
  public String toStringMines() 
  {
    String result = "\n";

    for (int r = 0; r < mines.length; r++) 
    {
      for (int c = 0; c < mines[r].length; c++)
	result +=  mines[r][c];

      result += "\n";
    }

    return result;
  }

  /**
    Returns tiles array as a String
  */
  public String toStringTiles() 
  {
    String result = "\n";

    for (int r = 0; r < tiles.length; r++) 
    {
      for (int c = 0; c < tiles[r].length; c++)
	result = result + tiles[r][c];

      result += "\n";
    }

    return result;
  }

  /** 
    Returns game board as String
  */
  public String toStringBoard () 
  {
    String result = "";

    for (int r = 0; r < tiles.length; r++) 
    {
      for (int c = 0; c < tiles[r].length; c++) 
      {
	result += this.getBoard(r, c);
      }
      result += "\n"; //advance to next line
    }

    return result;
  }

  /** 
    getBoard - determines current game board character for r,c position 
    * using the value of the mines[][] and tiles[][]array
    * '1'-'8'  opened tile showing clue value
    * ' '      opened tile blank
    * 'X'      tile closed
    * '?'      tile closed marked with ?
    * 'F'      tile closed marked with flag
    * '*'      mine
    * 
    * '-'      if game lost, mine that was incorrectly flagged
    * '!'      if game lost, mine that ended game
    * 'F'      if game won, all mines returned with F
    * TILES REFERENCE
    * 0 = OPEN TILE
    * 1 = CLOSED TILE
    * 2 = PLAYER MARKED QUESTIONABLE
    * 3 = PLAYER FLAGGED
  */
  public char getBoard (int r, int c) 
  {
    char[][] board = new char[mines.length][mines[0].length];
    if (status == "lose") { //HANDLE LOSE STATUS DRAWING
        if (tiles[r][c] == 0)
        {
            if (mines[r][c] == 9) //DRAW UN-FOUND MINES
                board[r][c] = '!';
            if (tiles[r][c] == 1 || tiles[r][c] == 2 || tiles[r][c] == 3 && mines[r][c] == 9)//DRAW FOUND MINE
                board[r][c] = '*';
            if (tiles[r][c] == 3 && mines[r][c] != 9) //DRAW INCORRECT FLAG
                board[r][c] = '-';
        }
    }//END LOSE STATUS
    
    if (status == "play") //HANDLE PLAY DRAWING
    {
        if (tiles[r][c] == 1) // IF CLOSED TILE
            board[r][c] = 'X';
        if (tiles[r][c] == 2) // IF QUESTIONED TILE
            board[r][c] = '?';
        if (tiles[r][c] == 3) // IF FLAGGED TILE
            board[r][c] = 'F';
        
        if (tiles[r][c] == 0) //IF OPEN TILE
        {
            if (mines[r][c] == 0) //IF OPEN ON MINES
                board[r][c] = ' '; //DRAW EMPTY SPACE
            if (mines[r][c] > 0 && mines[r][c] < 9) //DRAW CLUE ON BOARD
                board[r][c] = (char)(48+mines[r][c]);
        }
    }//END PLAY STATUS DRAWING
    
    if (status == "win")//HANDLE DRAWING BOARD POST-WIN
    {
        if (mines[r][c] == 9) //DRAW IF FLAG WAS CORRECT
            board[r][c] = 'F';
    }
    return board[r][c]; 
  }
  /** 
    Create mines & tiles array
    * place mines
    * update clues
    * newRows number of rows for grid
    * newCols number of columns for grid
  */
  private void initGame (int newRows, int newCols) 
  {
    //allocate space for mines and tiles array
    if ((newRows >= 1) && (newCols >= 1)) 
    {
      mines = new int[newRows][newCols];
      tiles = new int[newRows][newCols];

      //init tiles array
      resetTiles(0,0);

      //place mines
      placeMines();

      //update clues
      calculateClues();

      //set game status
      status = "play";
    }
  }

  /** 
    Sets all tiles to 1 - closed
  */
  private void resetTiles (int r, int c) 
  {
    if (r == tiles.length || c == tiles[r].length) //END FUNCTION WHEN OUT OF ROWS
        return;
    if (c == tiles[r].length-1) //GO DOWN A ROW AT END OF COLS
        resetTiles(r+1,0);
    tiles[r][c] = 1; //OTHERWISE SET TO CLOSED TILE (1)
    resetTiles(r,c+1);
  }

  /** 
    Places mines randomly on grid
    * integer value 9 represents a mine
    * number of mines = 1 + (number of columns * number rows) / 10
    * minimum number of mines = 1
  */
  private void placeMines () 
  {
    cMines = 1 + (mines.length * mines[0].length)/10;
    
    for (int i = 0; i < cMines; i++)
    {
        int r = (int) (mines.length * Math.random()); //GENS A # 0-END OF ROWS
        int c = (int) (mines[0].length * Math.random()); //GENS A # 0-END OF COLS
        
        if (mines[r][c] == 9) //IF RANDOM PLACE IS ALREADY MINE DEC I AND DO IT AGAIN
        {
            i--;
            continue;
        }
        mines[r][c] = 9;
    }
  }

  /** 
    Calculates clue values and updates
    * clue values in mines array
    * integer value 9 represents a mine
    * clue values will be 0 ... 8
  */
  private void calculateClues () 
  {
    for (int r = 0; r < mines.length; r++) 
    {
        for (int c = 0; c < mines[r].length; c++) 
        {
            if (mines[r][c] == 9) 
            {
                if (r-1 >= 0 && c-1 >= 0 && mines[r-1][c-1] != 9) // r-1, c-1
                    mines[r-1][c-1]++;
                if (r-1 >= 0 && c >= 0 && mines[r-1][c] != 9) //r-1, c
                    mines[r-1][c]++;
                if (r-1 >= 0 && c+1 < mines[r].length && mines[r-1][c+1] != 9)//r-1,c+1
                    mines[r-1][c+1]++;
                if (c-1 >= 0 && mines[r][c-1] != 9) //r, c-1
                    mines[r][c-1]++;
                if (c+1 < mines[r].length && mines[r][c+1] != 9)//r,c+1
                    mines[r][c+1]++;
                if (r+1 < mines.length && c-1 >= 0 && mines[r+1][c-1] != 9) // r+1, c-1
                    mines[r+1][c-1]++;
                if (r+1 < mines.length && mines[r+1][c] != 9) //r+1, c
                    mines[r+1][c]++;
                if (r+1 < mines.length && c+1 < mines[r].length && mines[r+1][c+1] != 9) //r+1, c+1
                    mines[r+1][c+1]++;
            }//END MINE IF CHECK
        }//END INNER FOR
    }//END OUTER FOR
  }

  /** 
    Determines if x,y is valid position
    * x row index
    * y column index
    * true if valid position on board,
    * false if not valid board position
  */
  private boolean validIndex (int r, int c) 
  {
    if (r >= mines.length || r < 0 || c < 0 || c >= mines[r].length) 
        return false;
    return true;
  }

  /** 
    Returns game status - true if game won or false
                          if game not won
  */
  private boolean gameWon() 
  {
    if (cMines == cFlags)
    {
        status = "win";
        return true;
    }
    return false; //this line must be modified
  }
}

public class TestMineSweeper 
{ 
  public static void main (String[] args) 
  {
    // create new minesweeper instance 2 rows by 5 columns
    minesweeper game = new minesweeper(9, 9); 

    // display mines
    System.out.println ( game.toStringMines() );

    // display tiles
    System.out.println ( game.toStringTiles() );

    // display board
    System.out.println ( game.toStringBoard() );

    // mark tile at (0, 0) as Open
    game.markTile (0, 0, 0);

    // mark tile at (0, 1) as Question Mark
    game.markTile (0, 1, 2);

    // mark tile at (0, 0) as Mine
    game.markTile (0, 2, 3);

    // display tiles 
    System.out.println ( game.toStringTiles() );

    // display board
    System.out.println ( game.toStringBoard() );
 } 
}