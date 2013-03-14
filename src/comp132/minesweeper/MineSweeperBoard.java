package comp132.minesweeper;

/**
 * A MineSweeperBoard holds a representation of the contents of
 * the playing field for a Mine Sweeper game. The playing field
 * is represented using a 2 dimensional array of integer values.
 * The integer value stored in each cell of the array indicates
 * the icon which will appear in the coresponding cell of the
 * graphical user interface for the game. 
 *
 * @author Grant Braught
 * @author Tim Wahls
 * @author hieu le & Thao Vu
 * @version 1.0 Jan 2013
 */
public class MineSweeperBoard {
	private int[][] mineBoard;
    /**
     * A constant value representing a covered cell.
     * A covered cell is any cell which does not contains
     * a mine, has not been flagged and has not yet been
     * uncovered.
     */
    public static final int COVERED_CELL = -1;

    /**
     * A constant value representing a a cell that has
     * not been uncovered yet but contains a mine.
     */
    public static final int MINE = -2;

    /**
     * A constant value representing a cell which does not 
     * contain a mine but has had a flag placed on it.
     */
    public static final int FLAG = -3;

    /**
     * A constant value representing a cell which contains
     * a mine and has had a flag placed on it.
     */
    public static final int FLAGGED_MINE = -4;

    /**
     * A constant value representing a cell containing a mine that
     * has been uncovered.
     */
    public static final int UNCOVERED_MINE = -5;

    /**
     * A constant value representing the contents of an invalid cell.
     * This value is returned by the getCell method when an invalid
     * cell is specified.
     */
    public static final int INVALID_CELL = -10;

    /**
     * A constant value representing the easiest level of play.
     */
    public static final int BEGINNER_LEVEL = 1;
    
    /**
     * A constant value representing an intermediate level of play.
     */
    public static final int INTERMEDIATE_LEVEL = 2;
    
    /**
     * A constant value representing the hardest level of play. 
     */
    public static final int EXPERT_LEVEL = 3;

    /**
     * Construct a new fixed MineSweeperBoard for testing purposes.
     * The board should have 3 rows and 4 columns.  All cells should
     * contain COVERED_CELL, except that locations (0, 0) and (2, 1)
     * should contain MINE.
     */
    public MineSweeperBoard() {
    	mineBoard = new int[3][4];
    	for (int i = 0; i < mineBoard.length; i++){
    		for (int j =0; j < mineBoard[0].length; j++){
    			mineBoard[i][j] = COVERED_CELL;
    		}
    	}
    	mineBoard[0][0]= MINE;
    	mineBoard[2][1]= MINE;
    }

    /**
     * pre: level == BEGINNER_LEVEL || level == INTERMEDIATE_LEVEL ||
     *      level == EXPERT_LEVEL;
     * 
     * Construct a new MineSweeperBoard for play at the 
     * specified level.  The size of the board and the number
     * of mines are determined by the level of play. Valid levels
     * of play are indicated by the constants BEGINNER_LEVEL,
     * INTERMEDIATE_LEVEL and EXPERT_LEVEL.  The size of the board
     * and the number of cells which contain mines is as
     * follows:
     * <pre>
     * <U>
     * Level:              Board Size (RxC):   Mines:</U>        
     * BEGINNER_LEVEL      5x10                3
     * INTERMEDIATE_LEVEL  10x15               15
     * EXPERT_LEVEL        15x20               45
     * </pre>
     *
     * @param level the level of play.
     */
    public MineSweeperBoard(int level) {
    	assert level == BEGINNER_LEVEL || level == INTERMEDIATE_LEVEL ||
    	       level == EXPERT_LEVEL: "Invalid level!";
    	java.util.Random rnd = new java.util.Random();
    	if (level == BEGINNER_LEVEL){
    		mineBoard = new int[5][10];
        	for (int i = 0; i < mineBoard.length; i++){
        		for (int j =0; j < mineBoard[0].length; j++){
        			mineBoard[i][j] = COVERED_CELL;
        		}
        	}      
        	while (getNumMines() < 3) {
        		int row = rnd.nextInt(mineBoard.length);
        		int column = rnd.nextInt(mineBoard[0].length);
        		mineBoard[row][column] = MINE;
        	}
    	} else if (level == INTERMEDIATE_LEVEL){
    		mineBoard = new int[10][15];
        	for (int i = 0; i < mineBoard.length; i++){
        		for (int j =0; j < mineBoard[0].length; j++){
        			mineBoard[i][j] = COVERED_CELL;
        		}
        	}
        	while (getNumMines() < 15) {
        		int row = rnd.nextInt(mineBoard.length);
        		int column = rnd.nextInt(mineBoard[0].length);
        		mineBoard[row][column] = MINE;
        	}
    	} else {
    		mineBoard = new int[15][20];
        	for (int i = 0; i < mineBoard.length; i++){
        		for (int j =0; j < mineBoard[0].length; j++){
        			mineBoard[i][j] = COVERED_CELL;
        		}
        	}
        	while (getNumMines() < 45) {
        		int row = rnd.nextInt(mineBoard.length);
        		int column = rnd.nextInt(mineBoard[0].length);
        		mineBoard[row][column] = MINE;
        	}
    	}
    }

    /**
     * Get the number of rows in this MineSweeperBoard.
     *
     * @return the number of rows in this MineSweeperBoard.
     */
    public int getRows() {
        return mineBoard.length;
    }

    /**
     * Get the number of columns in this MineSweeperBoard.
     *
     * @return the number of columns in this MineSweeperBoard.
     */
    public int getColumns() {
        return mineBoard[0].length;
    }

    /**
     * Get the number of mines in this MineSweeperBoard.
     *
     * @return the number of mines in this MineSweeperBoard.
     */
    public int getNumMines() {
    	int count = 0;
        for (int[] row: mineBoard){
        	for (int column: row){
        		if (column == MINE){
        			count++;
        		}
        	}
        }
        return count;
    }

    /**
     * 
     * Get the contents of the specified cell on this MineSweeperBoard.
     * The value returned from this method must be one of the defined
     * constants (COVERED_CELL, MINE, FLAG, FLAGGED_MINE, UNCOVERED_MINE)
     * or a non-negative integer representing the number of mines adjacent
     * to the cell.
     *
     * @param row the row containing the cell.
     * @param col the column containing the cell.
     * @return the value contained in the cell specified by row and col,
     *         or INVALID_CELL if the specified cell does not exist.
     */
    public int getCell(int row, int col) {  	
    	if ((row <0 || row >= getRows()) || (col <0 || col >=getColumns())){
        	return INVALID_CELL;
        }
    	return mineBoard[row][col];
        
    }

    /**
     * Count the number of mines that appear in cells that 
     * are adjacent to the specified cell. 
     * 
     * @param row the row of the cell.
     * @param col the column of the cell.
     * @return the number of mines adjacent to the specified cell.
     *         if the specified cell is invalid (not on the board),
     *         return 0;
     */
    public int numAdjMines(int row, int col) {
    	int count = 0;
    	if (row < 0 || col < 0 || row >= getRows() || col >= getColumns()) {
    		return count;
    	} else {
    		if (getCell(row,col+1) == MINE || getCell(row,col+1) == UNCOVERED_MINE || getCell(row,col+1) == FLAGGED_MINE) {
    			count++;
    		}
    		if (getCell(row+1,col+1) == MINE || getCell(row+1,col+1) == UNCOVERED_MINE || getCell(row+1,col+1) == FLAGGED_MINE) {
    			count++;
    		}
    		if (getCell(row-1,col+1) == MINE || getCell(row-1,col+1) == UNCOVERED_MINE || getCell(row-1,col+1) == FLAGGED_MINE) {
    			count++;
    		}
    		if (getCell(row-1,col-1) == MINE || getCell(row-1,col-1) == UNCOVERED_MINE || getCell(row-1,col-1) == FLAGGED_MINE) {
    			count++;
    		}
    		if (getCell(row+1,col-1) == MINE || getCell(row+1,col-1) == UNCOVERED_MINE || getCell(row+1,col-1) == FLAGGED_MINE) {
    			count++;
    		}
    		if (getCell(row,col-1) == MINE || getCell(row,col-1) == UNCOVERED_MINE || getCell(row,col-1) == FLAGGED_MINE) {
    			count++;
    		}
    		if (getCell(row+1,col) == MINE || getCell(row+1,col) == UNCOVERED_MINE || getCell(row+1,col) == FLAGGED_MINE) {
    			count++;
    		}
    		if (getCell(row-1,col) == MINE || getCell(row-1,col) == UNCOVERED_MINE || getCell(row-1,col) == FLAGGED_MINE) {
    			count++;
    		}
    	}
    		
    	return count;
    }

    /**
     * Uncover the specified cell. If the cell already contains a flag it should
     * not be uncovered. If there is not a mine under the
     * specified cell then the value in that cell is changed to the
     * number of mines that appear in adjacent cells. If there is a mine 
     * under the specified cell the game is over and the player has lost. 
     * If the specified cell is already uncovered or is invalid, no change 
     * is made to the board.
     *
     * @param row the row of the cell to be uncovered.
     * @param col the column of the cell to be uncovered. 
     */
    public void uncoverCell(int row, int col) {
    	if (getCell(row,col) == COVERED_CELL){
    		mineBoard[row][col] = numAdjMines(row,col);
    		// super bonus part 
    		if (getCell(row,col) == 0) {
           		uncoverCell(row - 1 , col);
           		uncoverCell(row +1, col);
           		uncoverCell(row -1, col-1);
           		uncoverCell(row +1, col -1);
           		uncoverCell(row +1, col+1);
           		uncoverCell(row -1, col +1);
           		uncoverCell(row , col +1);
           		uncoverCell(row , col -1);
    		}
    	} else if (getCell(row,col) == MINE){
    		mineBoard[row][col] = UNCOVERED_MINE;
       	}
    }

    /**
     * Place or remove a flag from the specified cell. If the cell currently
     * covered then place a flag on the cell.  If the cell currently contains
     * a flag, remove that flag but do not uncover the cell. If the cell has
     * already been uncovered or is invalid, no change is made to the board.
     *
     * @param row the row of the cell to be flagged/unflagged.
     * @param col the column of the cell to be flagged/unflagged.
     */
    public void flagCell(int row, int col) {
    	if (getCell(row,col) == COVERED_CELL){
    		mineBoard[row][col] = FLAG;
    	} else if (getCell(row,col) == FLAG){
    		mineBoard[row][col] = COVERED_CELL;
    	} else if (getCell(row,col) == FLAGGED_MINE){
    		mineBoard[row][col] = MINE;
    	} else if (getCell(row, col) == MINE){
    		mineBoard[row][col] = FLAGGED_MINE;
    	}
    }

    /**
     * Uncover all of the cells on the board.
     */
    public void revealBoard() {
    	for (int i = 0; i < mineBoard.length; i++){
    		for (int j =0; j < mineBoard[0].length; j++){
    			if (mineBoard[i][j] == COVERED_CELL || mineBoard[i][j] == FLAG) {
    				mineBoard[i][j] = numAdjMines(i,j);
    			} else if (mineBoard[i][j] == MINE || mineBoard[i][j] == FLAGGED_MINE){
    				mineBoard[i][j] = UNCOVERED_MINE;
    			}
    		}
    	}
    }

    /**
     * Determine if the player has lost the current game. The game
     * is lost if the player has uncovered a mine.
     *
     * @return true if the current game has been lost and false
     *         otherwise.
     */
    public boolean gameLost() {
    	for (int[] row: mineBoard){
    		for (int col: row){
    			if (col == UNCOVERED_MINE) {
    				return true;
    			}
    		}
    	}
        return false;
    }
    
    /**
     * Determine if the player has won the current game.
     * The game is won when three conditions are met:
     * <OL>
     * <LI>Flags have been placed on all of the mines.
     * <LI>No flags have been placed incorrectly.
     * <LI>All non-flagged cells have been uncovered.
     * </OL>
     *
     * @return true if the current game has been won and
     *         false otherwise.
     */
    public boolean gameWon() {
    	for (int[] row: mineBoard){
    		for (int col: row){
    			if (col == FLAG) {
    				return false;
    			} else if (col == MINE) {
    				return false;
    			} else if (col == COVERED_CELL) {
    				return false;
    			} else if (gameLost()){
    				return false;
    			}
    		}
    	}
        return true;
    }
    /**
     * pre : numbRow < mineBoard.length && numbRow >=0 , numbCol < mineBoard[0].length && 
     *       numbCol >= 0, value >= -5 && value <=-1
     * set the value of the cell
     * @param numbRow the number of the row
     * @param numbCol the number of the column
     * @param value the value of the cell
     */
    public void setCell(int numbRow, int numbCol, int value) {
    	assert numbRow < mineBoard.length && numbRow >=0 : "invalid Number of row";
    	assert numbCol < mineBoard[0].length && numbCol >= 0 : "invalid number of column";
    	assert value >= -5 && value <=-1 : "invalid value!";
    	mineBoard[numbRow][numbCol] = value;
    }

}

