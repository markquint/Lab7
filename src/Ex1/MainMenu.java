package Ex1;

import javax.swing.*;
import java.awt.*;

public class MainMenu{
    JButton b1, b2, b3, b4;
    JFrame f1;
    JPanel p1, p2, p3;
    MMListener listener;
    JTextArea ta1;
    JScrollPane sp1;
    Container c;

    public MainMenu() {
        f1 = new JFrame("Main Window");
        p1 = new JPanel();
        p2 = new JPanel();
        ta1 = new JTextArea();

        listener = new MMListener(this);

        b1 = new JButton("Insert");
        b2 = new JButton("Find");
        b3 = new JButton("Browse");
        b4 = new JButton("Create Tree from File");

        sp1 = new JScrollPane(ta1);

        b1.addActionListener(listener);//registering b1 to a listener
        b2.addActionListener(listener);
        b3.addActionListener(listener);//registering b1 to a listener
        b4.addActionListener(listener);

        BorderLayout bl = new BorderLayout();
        Label title = new Label("An Application to Maintain Student Records", Label.CENTER);

        bl.setHgap(10);

        f1.setSize(500,300);

        p1.setLayout(bl);

        p2.setLayout(new FlowLayout());

        p1.add("South", p2);
        p1.add("North", title);
        p1.add("Center", sp1);

        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);

        f1.setContentPane(p1);

        f1.setVisible(true);

    }

    public static void main(String args[]) {
        // Create the frame
        MainMenu frame = new MainMenu();
    }
}
