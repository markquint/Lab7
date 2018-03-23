package Ex2;
import java.io.*;
import java.net.Socket;

/**
 * Contains methods and data fields needed to create Player class
 * @author Mark Quintin
 * @version 1.0
 * @since March 16 2018
 */

public class Player {

    /**
     * Name of player
     */
    private String name;

    /**
     * Board object that the player will see/use
     */
    private Board board;

    /**
     * Player object of the opponent
     */
    private Player opponent;

    /**
     * Stores the mark the player uses, either 'X' or 'O'
     */
    private char mark;

    /**
     *  Server Socket
     */
    private Socket playerSocket;

    /**
     * Used to read from the socket
     */
    private BufferedReader socketInput;

    /**
     * Used to write to the socket
     */
    private PrintWriter socketOutput;

    /**
     * Constructor for Player
     * @param inSocket socket of server
     * @param mark either 'X' or 'O' for a player
     */
    public Player(Socket inSocket, char mark){
        this.playerSocket = inSocket;
        this.mark = mark;
        try {
            socketInput = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
            socketOutput = new PrintWriter(playerSocket.getOutputStream(), true);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error in constructing player.");
        }
    }

    /**
     * Prompts the user to enter their name
     */
    public void getPlayerName(){
        try{
            socketOutput.println("You are the " + mark + " player! Please enter your name below. ");
            getOpponent().socketOutput.println("Waiting for opponent...");
            while(true) {
                name = socketInput.readLine();
                if (name != null){
                    break;
                }
            }
//            socketOutput.println(String.valueOf(mark));
            socketOutput.flush();
        } catch(IOException e){
            e.printStackTrace();
            System.out.println("error getting name");
        }
    }

    /**
     * Plays the game, displays current board for both clients and calls makeMove()
     * to prompt the user for inputs.
     */
    public void play(){

        try {
            getOpponent().socketOutput.println("Waiting for opponent...");
            makeMove();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error making move");
        }

        board.display();
        if(board.xWins()||board.oWins()||board.isFull()) {
//            getOpponent().socketOutput.println("Game Over!");
            if(board.xWins()) {
                socketOutput.println(name + " wins!");
            }
            else if(board.oWins()) {
                socketOutput.println(name + " wins!");
            }
            else if(board.isFull()) {
                socketOutput.println("Tie Game!");
            }
            getOpponent().socketOutput.println("Game Over!");
            System.out.println("Exiting game...");
        }

        opponent.play();

    }

    /**
     * Takes in player parameters row number and column number to place their mark on the board.
     * Checks to make sure row number and column number is valid before placing the mark.
     */
    public void makeMove() throws IOException{
        int rownum = 0;
        int colnum = 0;
        int input;
        socketOutput.println(name + ", please click where you want to place your " + mark);
        input = Integer.parseInt(socketInput.readLine());

        if(input == 1){
            rownum = 0;
            colnum = 0;
        }
        else if(input == 2){
            rownum = 0;
            colnum = 1;
        }
        else if(input == 3){
            rownum = 0;
            colnum = 2;
//            socketOutput.println(String.valueOf(mark));
        }
        else if(input == 4){
            rownum = 1;
            colnum = 0;
//            socketOutput.println(String.valueOf(mark));
        }
        else if(input == 5){
            rownum = 1;
            colnum = 1;
        }
        else if(input == 6){
            rownum = 1;
            colnum = 2;
//            socketOutput.println(String.valueOf(mark));
        }
        else if(input == 7){
            rownum = 2;
            colnum = 0;
        }
        else if(input == 8){
            rownum = 2;
            colnum = 1;
        }
        else if(input == 9){
            rownum = 2;
            colnum = 2;
        }
//        while(true) {
//            while (true) {
//                socketOutput.println(name + ", what row should your next " + mark + " be placed in?");
//                line = socketInput.readLine();
//                move = Integer.parseInt(line);
//                if (move < 0 || move > 2)
//                    socketOutput.println("Please enter a row number between 0 and 2");
//                else
//                    break;
//            }
//            rownum = move;
//            while (true) {
//                socketOutput.println(name + ", what column should your next " + mark + " be placed in?");
//                line = socketInput.readLine();
//                move = Integer.parseInt(line);
//                if (move < 0 || move > 2)
//                    socketOutput.println("Please enter a column number between 0 and 2.");
//                else
//                    break;
//            }
//            colnum = move;
//            if (board.getMark(rownum, colnum) == ' ')
//                break;
//            else
//                socketOutput.println("That spot already has a mark in it, try again.");
//        }
        board.addMark(rownum, colnum, mark);
    }

    /**
     * Sets the players opponent
     * @param opponent is the opponent object for each player, both set to each other
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * Sets board for player, should be the same board seen by the referee and opponent
     * @param theBoard the playing board that will be used
     */
    public void setBoard(Board theBoard){
        this.board = theBoard;
    }

    /**
     * Gets a players character mark
     * @return mark being used by the player, either 'X' or 'O'
     */
    public char getMark(){
        return mark;
    }

    /**
     * Gets a players string name
     * @return name of player
     */
    public String getName(){
        return name;
    }

    /**
     * Sets a players string name
     */
    public void setName(String s){
        name = s;
    }

    /**
     * Gets a players opponent
     * @return player object of the opponent
     */
    public Player getOpponent(){
        return opponent;
    }

    /**
     * Gets a players playing board
     * @return board used by both players and referee
     */
    public Board getBoard(){
        return board;
    }
}
