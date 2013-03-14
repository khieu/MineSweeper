package comp132.minesweeper;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class MineSweeperBoardTest {

	@Test
	public void testConstructor() {
		MineSweeperBoard mineBoard = new MineSweeperBoard();
		Assert.assertEquals(3, mineBoard.getRows());
		Assert.assertEquals(4, mineBoard.getColumns());
		Assert.assertEquals(2, mineBoard.getNumMines());
		Assert.assertEquals(MineSweeperBoard.MINE, mineBoard.getCell(0, 0));
		Assert.assertEquals(MineSweeperBoard.COVERED_CELL, mineBoard.getCell(0, 1));
		Assert.assertEquals(MineSweeperBoard.INVALID_CELL, mineBoard.getCell(-1, 5));
		Assert.assertEquals(MineSweeperBoard.INVALID_CELL, mineBoard.getCell(1, 5));
	}
	@Test
	public void testConstructorBeginner(){
		MineSweeperBoard mineBoard = new MineSweeperBoard(MineSweeperBoard.BEGINNER_LEVEL);
		Assert.assertEquals(5, mineBoard.getRows());
		Assert.assertEquals(10, mineBoard.getColumns());
		Assert.assertEquals(3, mineBoard.getNumMines());
	}
	@Test
	public void testConstructorIntermediate(){
		MineSweeperBoard mineBoard = new MineSweeperBoard(MineSweeperBoard.INTERMEDIATE_LEVEL);
		Assert.assertEquals(10, mineBoard.getRows());
		Assert.assertEquals(15, mineBoard.getColumns());
		Assert.assertEquals(15, mineBoard.getNumMines());
	}
	@Test
	public void testConstructorExpert(){
		MineSweeperBoard mineBoard = new MineSweeperBoard(MineSweeperBoard.EXPERT_LEVEL);
		Assert.assertEquals(15, mineBoard.getRows());
		Assert.assertEquals(20, mineBoard.getColumns());
		Assert.assertEquals(45, mineBoard.getNumMines());
	}
	@Test
	public void testNumAdjMines(){
		MineSweeperBoard mineBoard = new MineSweeperBoard();
		Assert.assertEquals(0, mineBoard.numAdjMines(0, 2));
		Assert.assertEquals(1, mineBoard.numAdjMines(0, 1));
		Assert.assertEquals(2, mineBoard.numAdjMines(1, 1));
		
	}
	@Test
	public void testNumAdjMinesInvalid(){
		MineSweeperBoard mineBoard = new MineSweeperBoard();
		Assert.assertEquals(0, mineBoard.numAdjMines(-1, 2));
		Assert.assertEquals(0, mineBoard.numAdjMines(0, 5));
		Assert.assertEquals(0, mineBoard.numAdjMines(5, 2));
		Assert.assertEquals(0, mineBoard.numAdjMines(0, -1));
	}
	@Test
	public void testUncoverCell(){
		MineSweeperBoard mineBoard = new MineSweeperBoard();
		mineBoard.uncoverCell(0, 0);
		mineBoard.uncoverCell(0, 1);
		mineBoard.uncoverCell(1, 1);
		mineBoard.uncoverCell(0, 3);
		Assert.assertEquals(MineSweeperBoard.UNCOVERED_MINE, mineBoard.getCell(0, 0));
		Assert.assertEquals(1, mineBoard.getCell(0, 1));
		Assert.assertEquals(2, mineBoard.getCell(1, 1));
		Assert.assertEquals(0, mineBoard.getCell(0, 3));
		mineBoard.flagCell(2, 2);
		mineBoard.uncoverCell(2, 2);
		Assert.assertEquals(1, mineBoard.getCell(2, 2));
	}
	@Test
	public void testFlagCell(){
		MineSweeperBoard mineBoard = new MineSweeperBoard();
		mineBoard.flagCell(0, 3);
		Assert.assertEquals(MineSweeperBoard.FLAG, mineBoard.getCell(0, 3));
		mineBoard.flagCell(0, 3);
		Assert.assertEquals(MineSweeperBoard.COVERED_CELL, mineBoard.getCell(0, 3));
		mineBoard.flagCell(0, 0);
		Assert.assertEquals(MineSweeperBoard.FLAGGED_MINE, mineBoard.getCell(0, 0));
		mineBoard.flagCell(0, 0);
		Assert.assertEquals(MineSweeperBoard.MINE, mineBoard.getCell(0, 0));
		mineBoard.uncoverCell(2, 2);
		mineBoard.flagCell(2,2);
		Assert.assertEquals(1, mineBoard.getCell(2, 2));
	}
	@Test
	public void testRevealBoard(){
		MineSweeperBoard mineBoard1 = new MineSweeperBoard();
		mineBoard1.uncoverCell(2,2);
		mineBoard1.revealBoard();
		for (int i = 0; i < mineBoard1.getRows(); i++){
    		for (int j =0; j < mineBoard1.getColumns(); j++){
    			if ((i == 0 && j == 0) || (i == 2 && j == 1)) {
    				Assert.assertEquals(MineSweeperBoard.UNCOVERED_MINE, mineBoard1.getCell(i, j));
    			}
    			else {
    				Assert.assertEquals(mineBoard1.numAdjMines(i,j), mineBoard1.getCell(i, j));
    			}
    		}
    	}
	}
	@Test
	public void testGameLost(){
		MineSweeperBoard mineBoard = new MineSweeperBoard();
		mineBoard.uncoverCell(0, 0);
		Assert.assertTrue(mineBoard.gameLost());
	}
	@Test
	public void testGameLostFalse(){
		MineSweeperBoard mineBoard = new MineSweeperBoard();
		Assert.assertFalse(mineBoard.gameLost());
	}
	@Test
	public void testGameWon(){
		MineSweeperBoard mineBoard = new MineSweeperBoard();
		mineBoard.revealBoard();
		mineBoard.setCell(0,0, MineSweeperBoard.FLAGGED_MINE);
		mineBoard.setCell(2,1, MineSweeperBoard.FLAGGED_MINE);
		Assert.assertTrue(mineBoard.gameWon());
	}
	@Test
	public void testGameWonfalse(){
		MineSweeperBoard mineBoard = new MineSweeperBoard();
		mineBoard.flagCell(0, 1);
		mineBoard.flagCell(0, 0);
		Assert.assertFalse(mineBoard.gameWon());
		mineBoard.flagCell(0, 1);
		mineBoard.flagCell(0, 0);
		Assert.assertFalse(mineBoard.gameWon());
		mineBoard.flagCell(0 , 0);
		Assert.assertFalse(mineBoard.gameWon());
		mineBoard.flagCell(0, 0);
		mineBoard.uncoverCell(0, 0);
		Assert.assertFalse(mineBoard.gameWon());
	}
	@Test
	public void testNumbAdjMines() {
		MineSweeperBoard mineBoard = new MineSweeperBoard();
		mineBoard.setCell(0,1, MineSweeperBoard.MINE);
		mineBoard.setCell(0,2, MineSweeperBoard.MINE);
		mineBoard.setCell(1,0, MineSweeperBoard.MINE);
		mineBoard.setCell(1,2, MineSweeperBoard.MINE);
		mineBoard.setCell(2,0, MineSweeperBoard.MINE);
		mineBoard.setCell(2,2, MineSweeperBoard.MINE);
		Assert.assertEquals(8, mineBoard.numAdjMines(1, 1));
	}
}
