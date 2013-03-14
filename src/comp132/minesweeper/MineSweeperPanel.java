package comp132.minesweeper;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MineSweeperPanel
	 extends JPanel
	 implements MouseListener {

	private MineSweeperBoard theBoard;

	public MineSweeperPanel(MineSweeperBoard b) {

		theBoard = b;

		int rows = b.getRows();
		int cols = b.getColumns();

		setLayout(new GridLayout(rows, cols));
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				JButton but = new MineSweeperButton(r, c);
				add(but);
				but.addMouseListener(this);
			}
		}
	}

	public void revealBoard() {
		// iterate through all of the components
		// turn them into MineSweeperButtons and
		// set their icons according to the board.
		Component[] c = getComponents();
		for (int i = 0; i < c.length; i++) {
			MineSweeperButton b = (MineSweeperButton) c[i];
			b.setRevealIcon(theBoard.getCell(b.getRow(), b.getCol()));
		}
	}

	public void mouseClicked(MouseEvent e) {

		try {
			if (theBoard.gameLost() || theBoard.gameWon()) {
				MineSweeper.reStartGameOnClick();
			}
			else {
				if (!MineSweeper.isGameOn()) {
					MineSweeper.startGameOnClick();
				}

				MineSweeperButton b = (MineSweeperButton) (e.getSource());
				int row = b.getRow();
				int col = b.getCol();

				int mod = e.getModifiers();
				boolean button1 = (mod & InputEvent.BUTTON1_MASK) != 0;
				boolean button3 = (mod & InputEvent.BUTTON3_MASK) != 0;

				if (button1) {
					// 1.4.1 stuff: e.getButton() == MouseEvent.BUTTON1) {
					theBoard.uncoverCell(row, col);

					// Update the entire board because the bonus questions
					// require it.
					Component[] c = getComponents();
					for (int i = 0; i < c.length; i++) {
						MineSweeperButton tb = (MineSweeperButton) c[i];
						tb.setPlayingIcon(theBoard.getCell(tb.getRow(), tb.getCol()));
					}

					//b.setPlayingIcon(theBoard.getCell(row, col));
				}
				else if (button3) {
					// 1.4.1 stuff: e.getButton() == MouseEvent.BUTTON3) {
					theBoard.flagCell(row, col);
					b.setPlayingIcon(theBoard.getCell(row, col));
				}

				if (theBoard.gameWon()) {
					MineSweeper.gameWasWon();
				}
				else if (theBoard.gameLost()) {
					MineSweeper.gameWasLost();
				}
			}
		}
		catch (ClassCastException e2) {
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}

