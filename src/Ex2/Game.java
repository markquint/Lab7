package Ex2;
import java.io.*;

/**
 * Contains methods and data fields needed to create a Game class
 * This class implements the Constants interface
 * @author Mark Quintin
 * @version 1.0
 * @since March 16 2018
 */

public class Game implements Constants, Runnable {

	/**
	 * Playing board that will be used in the game
	 */
	private Board theBoard;

	/**
	 * Referee object that will control the flow of the game
	 */
	private Referee theRef;

	/**
	 * Constructor for class Game
	 */
    public Game( ) {
        theBoard  = new Board();
	}

	/**
	 * Gives control of the game over to the referee, will start the game and continue to run
	 * until a winner or tie game
	 * @param r is the referee object
	 * @throws IOException
	 */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
        theRef.setBoard(theBoard);
        theRef.getxPlayer().setBoard(theBoard);
        theRef.getoPlayer().setBoard(theBoard);
    }

	/**
	 * Run method from runnable interface
	 */
	public void run() {
        theRef.runTheGame();
	}
}
