package Ex2;

import Ex3.Ex3GUI;
import Ex3.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class Ex2Listener implements ActionListener {
    private GameGUI frame;
    private Socket listenerSocket;
    /**
     * Used to print the board to the socket
     */
    private PrintWriter socketOutput;

    String mark;
    // constructor
    public Ex2Listener(GameGUI gui, Socket s) {
        frame = gui;
        listenerSocket = s;
        try {
            socketOutput = new PrintWriter(listenerSocket.getOutputStream(), true);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error in setboardsocket.");
        }
    }

    // performs an action in response to the event
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frame.button1) {
            System.out.println("pressed button 1");
            frame.button1.setText(mark);
            socketOutput.println("1");
        }
        else if (e.getSource() == frame.button2){
            System.out.println("pressed button 2");
            frame.button2.setText(mark);
            socketOutput.println("2");
        }
        else if (e.getSource() == frame.button3){
            System.out.println("pressed button 3");
            frame.button3.setText(mark);
            socketOutput.println("3");
        }
        else if (e.getSource() == frame.button4){
            System.out.println("pressed button 4");
            frame.button4.setText(mark);
            socketOutput.println("4");
        }
        else if (e.getSource() == frame.button5){
            System.out.println("pressed button 4");
            frame.button5.setText(mark);
            socketOutput.println("5");
        }
        else if (e.getSource() == frame.button6){
            System.out.println("pressed button 6");
            frame.button6.setText(mark);
            socketOutput.println("6");
        }
        else if (e.getSource() == frame.button7){
            System.out.println("pressed button 7");
            frame.button7.setText(mark);
            socketOutput.println("7");
        }
        else if (e.getSource() == frame.button8){
            System.out.println("pressed button 8");
            frame.button8.setText(mark);
            socketOutput.println("8");
        }
        else if (e.getSource() == frame.button9){
            System.out.println("pressed button 9");
            frame.button9.setText(mark);
            socketOutput.println("9");
        }
    }

    public void setMark(String s){
        this.mark = s;
    }
}
