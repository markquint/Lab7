package Ex2;
/**
 * Contains methods and data fields needed to create a Referee class
 * @author Mark Quintin
 * @version 1.0
 * @since March 16 2018
 */

public class Referee {

    /**
     * Object Player for the player using the X mark
     */
    private Player xPlayer;

    /**
     * Object Player for the player using the O mark
     */
    private Player oPlayer;

    /**
     * Object Board for the player using the X mark
     */
    private Board board;

    /**
     * A default constructor for class Referee
     */
    public Referee(){

    }

    /**
     * Starts the game. Setting each players opponent field and asks first player to make their move
     */
    public void runTheGame(){
        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);

        xPlayer.getPlayerName();
        oPlayer.getPlayerName();

        System.out.println("Referee started the game...");
        board.display();

        xPlayer.play();
    }

    /**
     * Sets board variable
     * @param board is the board object that will be seen by the referee, as well as both players
     */
    public void setBoard(Board board){
        this.board = board;
    }

    /**
     * Sets 'O' player object
     * @param oPlayer object for player using 'O' mark
     */
    public void setoPlayer(Player oPlayer){
        this.oPlayer = oPlayer;
    }

    /**
     * Sets 'X' player object
     * @param xPlayer object for player using 'X' mark
     */
    public void setxPlayer(Player xPlayer){
        this.xPlayer = xPlayer;
    }

    /**
     * Get x player
     * @return x player object
     */
    public Player getxPlayer(){
        return xPlayer;
    }

    /**
     * Get o player
     * @return o player object
     */
    public Player getoPlayer(){
        return oPlayer;
    }
}
