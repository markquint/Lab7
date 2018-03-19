package Ex2;
import java.io.*;
import java.net.Socket;

/**
 * Contains methods and data fields needed to create a Board class
 * This class implements the Constants interface
 * @author Mark Quintin
 * @version 1.0
 * @since March 16 2018
 */

public class Board implements Constants{
	/**
	 * 2-D array used as the playing board
	 */
	private char theBoard[][];

	/**
	 * Stores the number of marks placed on the board
	 */
	private int markCount;

    /**
     * Socket of the server
     */
    private Socket boardSocket;

    /**
     * Used to print the board to the socket
     */
    private PrintWriter socketOutput;

	/**
	 * Constructor for class board, sets the board to 3*3
	 * thus making markCount have a maximum of 9 marks possible
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

    /**
     * Sets the boardSocket to the same socket of the server
     * @param in Socket of server
     */
	public void setBoardSocket(Socket in){
	    this.boardSocket = in;
        try {
            socketOutput = new PrintWriter(boardSocket.getOutputStream(), true);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error in setboardsocket.");
        }
    }

	/**
	 * Gets the mark on a specified row and column
	 * @param row row number on the board
	 * @param col column number on the board
	 * @return mark mark at the given row and column numbers
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/**
	 * Checks to see if the board is full of marks,
	 * signalling a tie game
	 * @return true if the number of marks on the board is 9
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	/**
	 * Checks if the 'X' player wins,
     * by calling checkWinner();
	 * @return true if the player using 'X' marks won, false otherwise
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

    /**
     * Checks if the 'O' player wins,
     * also by calling checkWinner();
     * @return true if the player using 'O' marks won, false otherwise
     */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Displays playing board on server
	 */
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			System.out.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				System.out.print("|  " + getMark(row, col) + "  ");
			System.out.println("|");
			addSpaces();
			addHyphens();
		}
	}

    /**
     * Displays playing board to client
     */
	public void displayToClient() {
		displayColumnHeadersToClient();
		addHyphensToClient();
		for (int row = 0; row < 3; row++) {
			addSpacesToClient();
			socketOutput.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
                socketOutput.print("|  " + getMark(row, col) + "  ");
            socketOutput.println("|");
			addSpacesToClient();
			addHyphensToClient();
		}
	}

	/**
	 * Places a mark on the board
	 * @param row row number where player wants to place their mark
	 * @param col column number where player wants to place their mark
	 * @param mark the mark of the player, 'X' or 'O' that is to be placed
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}

	/**
	 * Clears board of all marks
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * Checks if the player with the specified mark won
	 * @param mark mark of player, either 'X' or 'O'
	 * @return 1 if player with input mark has won, 0 otherwise
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

	/**
	 * Displays column numbers on server
	 */
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}

    /**
     * Displays column numbers to client
     */
    void displayColumnHeadersToClient() {
        socketOutput.print("          ");
        for (int j = 0; j < 3; j++)
            socketOutput.print("|col " + j);
        socketOutput.println();
    }

	/**
	 * Displays borders of board on server
	 */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}

    /**
     * Displays borders of board on client
     */
    void addHyphensToClient() {
        socketOutput.print("          ");
        for (int j = 0; j < 3; j++)
            socketOutput.print("+-----");
        socketOutput.println("+");
    }

	/**
	 * Adds spaces when displaying the board to ensure it displays properly on server
	 */
	void addSpaces() {
        System.out.print("          ");
        for (int j = 0; j < 3; j++)
            System.out.print("|     ");
        System.out.println("|");
    }

    /**
     * Adds spaces when displaying the board to ensure it displays properly on client
     */
    void addSpacesToClient() {
        socketOutput.print("          ");
        for (int j = 0; j < 3; j++)
            socketOutput.print("|     ");
        socketOutput.println("|");
    }
}
