package comp132.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MineSweeper {

    private static int level = MineSweeperBoard.BEGINNER_LEVEL;
    private static boolean gameOn = false;

    private static JFrame window;

    private static final ImageIcon EMPTY_ICON = new ImageIcon("icons/empty.jpg");
    private static final ImageIcon FLAG_ICON = new ImageIcon("icons/flag.jpg");

    private static MineSweeperBoard theBoard;
    private static MineSweeperPanel thePanel;
    private static MineSweeperMenuHandler h;

    public static void main(String[] args) {
	
	window = new JFrame("Mine Sweeper");
	
        // Handle closing the window.
	// This is the old way but is is compatible with 
	// old JDK's.
        window.addWindowListener(new WindowAdapter() {
             public void windowClosing(WindowEvent e) {
                 System.exit(0);
             }
         });
       
	makeNewBoard();
	window.setJMenuBar(getMenuBar());

	window.pack();
	window.show();
    }

    public static void startGame() {
	gameOn = true;
    }

    public static void endGame() {
	gameOn = false;
    }
    
    public static void gameWasWon() {
	endGame();
	revealBoard();
	JOptionPane.showMessageDialog(window,"Way to go, you won!",
				      "Winner!",
				      JOptionPane.INFORMATION_MESSAGE);
	h.resetMenus();
    }

    public static void gameWasLost() {
	endGame();
	revealBoard();
	JOptionPane.showMessageDialog(window,"Too bad, you lose!",
				      "Looser!",
				      JOptionPane.INFORMATION_MESSAGE);
	h.resetMenus();
    }

    public static void startGameOnClick() {
	h.actLikeStart();
    }

    public static void reStartGameOnClick() {
	h.actLikeStart();
	makeNewBoard();
    }

    public static boolean isGameOn() {
	return gameOn;
    }

    public static void setLevel(int newLevel) {
	level = newLevel;
    }

    public static int getLevel() {
	return level;
    }

    public static void revealBoard() {
	theBoard.revealBoard();
	thePanel.revealBoard();
    }

    public static void makeNewBoard() {
	theBoard = new MineSweeperBoard(level);
	Container thePane = window.getContentPane();

	// Get Rid of old panel if one exists!
	thePane.removeAll();
	
	thePanel = new MineSweeperPanel(theBoard);
	thePane.add(thePanel);  
	window.pack();
	window.repaint();
    }

    private static JMenuBar getMenuBar() {

	JMenuBar menuBar = new JMenuBar();
	JMenu gameMenu = new JMenu("Game");
	JMenu levelMenu = new JMenu("Level");
	
	h = new MineSweeperMenuHandler(levelMenu, gameMenu);

	JMenuItem startGame = new JMenuItem("Start Game");
	startGame.addActionListener(h);
	startGame.setBackground(new Color(155,153,156));

	JMenuItem restartGame = new JMenuItem("Re-Start Game");
	restartGame.setEnabled(false);
	restartGame.addActionListener(h);
	restartGame.setBackground(new Color(155,153,156));

	JMenuItem revealGame = new JMenuItem("Reveal board");
	revealGame.addActionListener(h);
	revealGame.setBackground(new Color(155,153,156));

	JMenuItem exitGame = new JMenuItem("Exit");
	exitGame.setBackground(new Color(155,153,156));
	exitGame.addActionListener(h);

	gameMenu.add(startGame);
	gameMenu.add(restartGame);
	gameMenu.add(revealGame);
	gameMenu.add(exitGame);

	JMenuItem beginnerLevel = new JMenuItem("Beginner");
	beginnerLevel.addActionListener(h);
	beginnerLevel.setIcon(FLAG_ICON);
	beginnerLevel.setBackground(new Color(155,153,156));

	JMenuItem intermediateLevel = new JMenuItem("Intermediate");
	intermediateLevel.addActionListener(h);	
	intermediateLevel.setIcon(EMPTY_ICON);
	intermediateLevel.setBackground(new Color(155,153,156));

	JMenuItem expertLevel = new JMenuItem("Expert");
	expertLevel.addActionListener(h);
	expertLevel.setIcon(EMPTY_ICON);
	expertLevel.setBackground(new Color(155,153,156));
	
	levelMenu.add(beginnerLevel);
	levelMenu.add(intermediateLevel);
	levelMenu.add(expertLevel);

	menuBar.add(gameMenu);
	menuBar.add(levelMenu);

	return(menuBar);
    }
}
