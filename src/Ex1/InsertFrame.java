package Ex1;

import javax.swing.*;
import java.awt.*;

public class InsertFrame {
    BinSearchTree bstdata;
    JFrame frame1 = new JFrame("Insert");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JTextField t1, t2, t3, t4;

    JButton button1 = new JButton("Insert");
    JButton button2 = new JButton("Return to Main Window");

    public InsertFrame(BinSearchTree inbst) {
        bstdata = inbst;

        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t1.setPreferredSize(new Dimension(100,25));
        t2.setPreferredSize(new Dimension(100,25));
        t3.setPreferredSize(new Dimension(100,25));
        t4.setPreferredSize(new Dimension(100,25));
        IFListener listener = new IFListener(this);

        button1.addActionListener(listener);
        button2.addActionListener(listener);

        t1.addActionListener(listener);
        t2.addActionListener(listener);
        t3.addActionListener(listener);
        t4.addActionListener(listener);

        Label title = new Label("Insert a New Ex1.Node", Label.CENTER);
        Label idlabel = new Label ("Enter Student ID");
        Label facultylabel= new Label ("Enter Faculty");
        Label majorlabel = new Label ("Enter Students Major");
        Label yearlabel = new Label ("Enter Year");

        frame1.setSize(500,200);

        FlowLayout fl = new FlowLayout();
        panel3.setLayout(fl);
        panel1.setLayout(new BorderLayout());
        panel2.setLayout(new FlowLayout());

        panel1.add("North", title);
        panel2.add(button1);
        panel2.add(button2);
        panel3.add(idlabel);
        panel3.add(t1);
        panel3.add(facultylabel);
        panel3.add(t2);
        panel3.add(majorlabel);
        panel3.add(t3);
        panel3.add(yearlabel);
        panel3.add(t4);
        panel1.add("South", panel2);
        panel1.add("Center", panel3);

        frame1.setContentPane(panel1);
        frame1.setVisible(true);
    }

    public BinSearchTree updatedTree() {
        return bstdata;
    }
}
