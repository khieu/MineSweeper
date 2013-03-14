package comp132.minesweeper;

import java.awt.event.*;
import javax.swing.*;

public class MineSweeperMenuHandler 
    implements ActionListener {

    private JMenu levelMenu;
    private JMenu gameMenu;

    private static final ImageIcon EMPTY_ICON = new ImageIcon("icons/empty.jpg");
    private static final ImageIcon FLAG_ICON = new ImageIcon("icons/flag.jpg");

    public MineSweeperMenuHandler(JMenu lm, JMenu gm) {
	levelMenu = lm;
	gameMenu = gm;
    }

    public void resetMenus() {
	levelMenu.setEnabled(true);
	gameMenu.getItem(0).setEnabled(true);
	gameMenu.getItem(1).setEnabled(false);
	gameMenu.getItem(2).setEnabled(false);
    }

    public void actLikeStart() {
	levelMenu.setEnabled(false);
	gameMenu.getItem(0).setEnabled(false);
	gameMenu.getItem(1).setEnabled(true);
	gameMenu.getItem(2).setEnabled(true);
	MineSweeper.startGame();
	//MineSweeper.makeNewBoard();
    }

    public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand().equals("Beginner")) {
	    levelMenu.getItem(0).setIcon(FLAG_ICON);
	    levelMenu.getItem(1).setIcon(EMPTY_ICON);
	    levelMenu.getItem(2).setIcon(EMPTY_ICON);
	    MineSweeper.setLevel(MineSweeperBoard.BEGINNER_LEVEL);
	    MineSweeper.makeNewBoard();
	}
	else if (e.getActionCommand().equals("Intermediate")) {
	    levelMenu.getItem(0).setIcon(EMPTY_ICON);
	    levelMenu.getItem(1).setIcon(FLAG_ICON);
	    levelMenu.getItem(2).setIcon(EMPTY_ICON);
	    MineSweeper.setLevel(MineSweeperBoard.INTERMEDIATE_LEVEL);
	    MineSweeper.makeNewBoard();
	}
	else if (e.getActionCommand().equals("Expert")) {
	    levelMenu.getItem(0).setIcon(EMPTY_ICON);
	    levelMenu.getItem(1).setIcon(EMPTY_ICON);
	    levelMenu.getItem(2).setIcon(FLAG_ICON);
	    MineSweeper.setLevel(MineSweeperBoard.EXPERT_LEVEL);	    
	    MineSweeper.makeNewBoard();
	}
	else if (e.getActionCommand().equals("Start Game")) {
	    levelMenu.setEnabled(false);
	    gameMenu.getItem(0).setEnabled(false);
	    gameMenu.getItem(1).setEnabled(true);
	    gameMenu.getItem(2).setEnabled(true);
	    MineSweeper.startGame();
	    MineSweeper.makeNewBoard();
	}
	else if (e.getActionCommand().equals("Re-Start Game")) {
	    levelMenu.setEnabled(false);
	    gameMenu.getItem(0).setEnabled(false);
	    gameMenu.getItem(1).setEnabled(true);
	    gameMenu.getItem(2).setEnabled(true);
	    MineSweeper.startGame();
	    MineSweeper.makeNewBoard();
	}
	else if (e.getActionCommand().equals("Reveal board")) {
	    levelMenu.setEnabled(true);
	    gameMenu.getItem(0).setEnabled(true);
	    gameMenu.getItem(1).setEnabled(false);
	    gameMenu.getItem(2).setEnabled(false);
	    MineSweeper.endGame();
	    MineSweeper.revealBoard();
	}
	else if (e.getActionCommand().equals("Exit")) {
	    System.exit(0);
	}
    }
}
