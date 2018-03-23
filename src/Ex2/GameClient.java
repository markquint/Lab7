package Ex2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    String name;

    GameGUI gui;

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
            gui = new GameGUI(gameSocket);
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
        boolean sem = true;
        try {
            System.out.println("Message: WELCOME TO THE GAME.");
            gui.messageArea.setText("Message: WELCOME TO THE GAME.");

            while (!(response.equals("2 players found! Game starting"))) {
                response = socketIn.readLine();
                System.out.println(response);
                gui.messageArea.setText(response);
            }

            System.out.println("Connection successful!");
            gui.messageArea.setText("Connection successful!");

            while(true) {
                response = socketIn.readLine();
                System.out.println(response);
                gui.messageArea.setText(response);

                if (response.equals("Game Over!")){
                    System.out.println("Game Over! Exiting game...");
                    break;
                }
                else if(!response.equals("Waiting for opponent...")){
                    gui.nameArea.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("entered name");
                            name = gui.nameArea.getText();
                            System.out.println(name);
                            socketOut.println(name);
                        }
                    });
                    if(response.charAt(response.length()-1) == 'X')
                        gui.listener.setMark("X");
                    else if(response.charAt(response.length()-1) == 'O')
                        gui.listener.setMark("O");
//                    if(response.charAt(response.length()-1) == 'n') {
//                        while(true) {
//                            System.out.println("in loop");
//                            if (gui.sem) {
//                                System.out.println("in move");
//                                gui.sem = false;
//                                move = gui.checkBoard();
//                                socketOut.println(move);
//                            }
//                        }
//                    }
//                    gui.button1.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            System.out.println("pressed button 1");
//                            socketOut.println("1");
//                                gui.button1.setText("X");
//                        }
//                    });
//                    gui.button2.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            System.out.println("pressed button 2");
//                            socketOut.println("2");
//                            gui.button2.setText("X");
////                            try {
////                                gui.button2.setText(socketIn.readLine());
////                            } catch(IOException j){
////                                j.printStackTrace();
////                            }
//                        }
//                    });
//                    gui.button3.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            System.out.println("pressed button 3");
//                            socketOut.println("3");
//                            gui.button3.setText("");
////                            try {
////                                gui.button3.setText(socketIn.readLine());
////                            } catch(IOException j){
////                                j.printStackTrace();
////                            }
//                        }
//                    });
//                    gui.button4.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            System.out.println("pressed button 4");
//                            socketOut.println("4");
//                            gui.button4.setText("X");
////                            try {
////                                gui.button4.setText(socketIn.readLine());
////                            } catch(IOException j){
////                                j.printStackTrace();
////                            }
//                        }
//                    });
//                    gui.button5.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            System.out.println("pressed button 5");
//                            socketOut.println("5");
//                            gui.button5.setText("X");
////                            try {
////                                gui.button5.setText(socketIn.readLine());
////                            } catch(IOException j){
////                                j.printStackTrace();
////                            }
//                        }
//                    });
//                    gui.button6.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            System.out.println("pressed button 6");
//                            socketOut.println("6");
//                            gui.button6.setText("X");
////                            try {
////                                gui.button6.setText(socketIn.readLine());
////                            } catch(IOException j){
////                                j.printStackTrace();
////                            }
//                        }
//                    });
//                    gui.button7.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            System.out.println("pressed button 7");
//                            socketOut.println("7");
//                            gui.button7.setText("X");
////                            try {
////                                gui.button7.setText(socketIn.readLine());
////                            } catch(IOException j){
////                                j.printStackTrace();
////                            }
//                        }
//                    });
//                    gui.button8.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            System.out.println("pressed button 8");
//                            socketOut.println("8");
//                            gui.button8.setText("X");
////                            try {
////                                gui.button8.setText(socketIn.readLine());
////                            } catch(IOException j){
////                                j.printStackTrace();
////                            }
//                        }
//                    });
//                    gui.button9.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            System.out.println("pressed button 9");
//                            socketOut.println("9");
//                            gui.button9.setText("X");
////                            try {
////                                gui.button9.setText(socketIn.readLine());
////                            } catch(IOException j){
////                                j.printStackTrace();
////                            }
//                        }
//                    });

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

    public static void main(String[] args) throws IOException  {
        GameClient aClient = new GameClient("localhost", 9090);
        aClient.communicate();
    }
}
