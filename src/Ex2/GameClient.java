package Ex2;
import java.io.*;
import java.net.Socket;

/**
 * Contains methods and data fields needed to create the client for the game
 * @author Mark Quintin
 * @version 1.0
 * @since March 16 2018
 */

public class GameClient {

    /**
     * Used to print to socket
     */
    private PrintWriter socketOut;

    /**
     * Clients socket
     */
    private Socket gameSocket;

    /**
     * Used to take in keyboard input from user
     */
    private BufferedReader stdIn;

    /**
     * Used to read from socket
     */
    private BufferedReader socketIn;

    /**
     * Constructs game client
     * @param serverName Name of server
     * @param portNumber Port number (9090 for this application)
     */
    public GameClient(String serverName, int portNumber) {
        try {
            gameSocket = new Socket(serverName, portNumber);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(gameSocket.getInputStream()));
            socketOut = new PrintWriter((gameSocket.getOutputStream()), true);
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
            System.out.println("Error constructing client");
        }
    }

    /**
     * Client communication with the server and its game
     */
    public void communicate()  {
        String line = "";
        String response = "";
        try {
            System.out.println("Message: WELCOME TO THE GAME.");

            while (!(response.equals("2 players found! Game starting"))) {
                response = socketIn.readLine();
                System.out.println(response);
            }
            System.out.println("Connection successful!");

            while(true) {
                response = socketIn.readLine();
                System.out.println(response);

                if (response.equals("Displaying board")) {
                    while(!response.equals("Done displaying board")){
                        response = socketIn.readLine();
                        System.out.println(response);
                    }
                    socketOut.flush();
                }
                else if (response.equals("Game Over!")){
                    System.out.println("Game Over! Exiting game...");
                    break;
                }
                else if(!response.equals("Waiting for opponent...")){
                    line = stdIn.readLine();
                    socketOut.println(line);
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.out.println("Sending error: " + e.getMessage());
        } finally {
            try {
                stdIn.close();
                socketIn.close();
                socketOut.close();
            } catch (IOException e) {
                System.out.println("Closing error: " + e.getMessage());
            }
        }
    }

//    public void getCurrBoard() throws IOException{
//        is = gameSocket.getInputStream();
//        objin = new ObjectInputStream(is);
//        try {
//            theBoard = (Board) objin.readObject();
//            theBoard.display();
//        }catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            System.err.println("Class not found exception caught.");
//        }catch(Exception e){
//            System.err.println("Cant get current board");
//        }
//    }

    public static void main(String[] args) throws IOException  {
        GameClient aClient = new GameClient("localhost", 9090);
        aClient.communicate();
    }
}
