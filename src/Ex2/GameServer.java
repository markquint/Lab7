package Ex2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Contains methods and data fields needed to create the server for the game
 * @author Mark Quintin
 * @version 1.0
 * @since March 16 2018
 */

public class GameServer {

    /**
     * Server socket for game
     */
    private ServerSocket serverSocket;

    /**
     * Two sockets for two clients
     */
    private Socket aSocket, bSocket;

    /**
     * Used to read from respective socket
     */
    private BufferedReader socketInput1, socketInput2;

    /**
     * Used to write to respective socket
     */
    private PrintWriter socketOutput1, socketOutput2;

    /**
     * Thread pool used to service threads, fixed size to two
     */
    private ExecutorService pool;

    /**
     * Construct a Server with a Port 9090
     */
    public GameServer() {
        try {
            serverSocket = new ServerSocket(9090);
            System.out.println("Game Server is now running.");
            pool = Executors.newFixedThreadPool(2);
        } catch (IOException e) {
        }
    }

    /**
     * Runs game, executes the threads in the thread pool (max 2 players)
     */
    public void runGame(){
        try{
            aSocket = serverSocket.accept();
            socketInput1 = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOutput1 = new PrintWriter(aSocket.getOutputStream(), true);
            Player xPlayer = new Player(aSocket, 'X');

            System.out.println("After accept 1");
            socketOutput1.println("Player 1 connected, waiting for opponent.");

            bSocket = serverSocket.accept();
            socketInput2 = new BufferedReader(new InputStreamReader(bSocket.getInputStream()));
            socketOutput2 = new PrintWriter(bSocket.getOutputStream(), true);
            Player oPlayer = new Player(bSocket, 'O');
            System.out.println("After accept 2");
            socketOutput2.println("Player 2 connected");

            Referee theRef = new Referee();
            theRef.setoPlayer(oPlayer);
            theRef.setxPlayer(xPlayer);

            Game theGame = new Game();
            theGame.appointReferee(theRef);

            System.out.println("2 players found! Game starting");
            socketOutput1.println("2 players found! Game starting");
            socketOutput2.println("2 players found! Game starting");
            socketOutput1.flush();
            socketOutput2.flush();
            pool.execute(theGame);

        } catch(Exception e){
            e.printStackTrace();
            pool.shutdownNow();
            System.out.println("Error in running game");
        }
    }

    /**
     * Run the Server.
     *
     * @param args
     */
    public static void main(String[] args) {
        GameServer ds = new GameServer();
        ds.runGame();
    }
}
