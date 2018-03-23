package Ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class GameGUI {

    /**
     * Socket of the server
     */
    private Socket boardSocket;

    /**
     * Used to print the board to the socket
     */
    private PrintWriter socketOutput;

    Ex2Listener listener;
    int move;
    boolean sem = false;
    JFrame frame1 = new JFrame("GUI");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel outer = new JPanel();
    JTextArea messageArea = new JTextArea();
    JLabel nameLabel = new JLabel("Name: ");
    JTextField nameArea = new JTextField();

    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JButton button5 = new JButton();
    JButton button6 = new JButton();
    JButton button7 = new JButton();
    JButton button8 = new JButton();
    JButton button9 = new JButton();

    public GameGUI(Socket s){
        boardSocket = s;
        listener = new Ex2Listener(this, boardSocket);
        GridLayout grid1 = new GridLayout(3,3,2,2);
        GridBagLayout grid2 = new GridBagLayout();
        GridBagLayout grid3 = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        outer.setLayout(grid2);
        panel1.setLayout(grid1);
        panel2.setLayout(grid3);


        button1.addActionListener(listener);
        button2.addActionListener(listener);
        button3.addActionListener(listener);
        button4.addActionListener(listener);
        button5.addActionListener(listener);
        button6.addActionListener(listener);
        button7.addActionListener(listener);
        button8.addActionListener(listener);
        button9.addActionListener(listener);

        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);
        panel1.add(button5);
        panel1.add(button6);
        panel1.add(button7);
        panel1.add(button8);
        panel1.add(button9);

        frame1.setSize(800,500);

        c.insets = new Insets(0,10,0,10);
        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 100;
        c.ipady = 100;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        outer.add(panel1, c);

        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 200;
        c.ipady = 110;
        outer.add(messageArea, c);

        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 0;
        c.ipady = 0;
        panel2.add(nameLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 100;
        c.ipady = 15;
        panel2.add(nameArea, c);

        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 0;
        c.ipady = 0;
        outer.add(panel2, c);


        frame1.setContentPane(outer);

        frame1.setVisible(true);

    }

    public int checkBoard(){
        return move;
    }

    public static void main(String[] args){
//        GameGUI test = new GameGUI();
    }
}
