package Ex3;

import javax.swing.*;
import java.awt.*;

/**
 * Contains methods to build a GUI for the client
 */
public class Ex3GUI {
    Message myMsg;
    Ex3Client client;

    JFrame frame1 = new JFrame("GUI");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel outer = new JPanel();
    JTextArea ta1;
    JTextField t1, t2, t3, t4, t5;

    JButton button1 = new JButton("Create Message");
    JButton button2 = new JButton("Clear");

    public Ex3GUI() {
        t1 = new JTextField(10);
        t2 = new JTextField(10);
        t3 = new JTextField(10);
        t4 = new JTextField(10);
        t5 = new JTextField(10);
        ta1 = new JTextArea();
        Ex3Listener listener = new Ex3Listener(this);

        button1.addActionListener(listener);
        button2.addActionListener(listener);

        t1.addActionListener(listener);
        t2.addActionListener(listener);
        t3.addActionListener(listener);
        t4.addActionListener(listener);

        JLabel title = new JLabel("Create a message: ");
        JLabel senderlabel = new JLabel ("From:");
        JLabel receiverlabel= new JLabel ("To:");
        JLabel subjectlabel = new JLabel("Subject:");
        JLabel num1label = new JLabel ("Enter 1st number:");
        JLabel num2label = new JLabel ("Enter 2nd number:");

        t1.setPreferredSize(new Dimension(10,10));

        frame1.setSize(500,500);


        GroupLayout gp = new GroupLayout(panel1);

        outer.setLayout(new BorderLayout());
        panel1.setLayout(gp);
        gp.setAutoCreateContainerGaps(true);
        gp.setAutoCreateGaps(true);

        gp.setHorizontalGroup(
                gp.createParallelGroup()
                        .addComponent(senderlabel)
                        .addComponent(receiverlabel)
                        .addComponent(subjectlabel)
                        .addComponent(num1label)
                        .addComponent(num2label)
                        .addGroup(gp.createParallelGroup(GroupLayout.Alignment.LEADING))
                        .addComponent(t1)
                        .addComponent(t2)
                        .addComponent(t3)
                        .addComponent(t4)
                        .addComponent(t5)

        );

        gp.setVerticalGroup(
                gp.createSequentialGroup()
                        .addGroup(gp.createParallelGroup(GroupLayout.Alignment.BASELINE))
                        .addComponent(senderlabel)
                        .addComponent(t1)
                        .addGroup(gp.createParallelGroup(GroupLayout.Alignment.BASELINE))
                        .addComponent(receiverlabel)
                        .addComponent(t2)
                        .addGroup(gp.createParallelGroup(GroupLayout.Alignment.BASELINE))
                        .addComponent(subjectlabel)
                        .addComponent(t3)
                        .addGroup(gp.createParallelGroup(GroupLayout.Alignment.BASELINE))
                        .addComponent(num1label)
                        .addComponent(t4)
                        .addGroup(gp.createParallelGroup(GroupLayout.Alignment.BASELINE))
                        .addComponent(num2label)
                        .addComponent(t5)
        );

        panel2.add(button1);
        panel2.add(button2);

        outer.add("West", panel1);
        outer.add("North", title);
        outer.add("Center", ta1);
        outer.add("South", panel2);

        frame1.setContentPane(outer);
        frame1.setVisible(true);
        client = new Ex3Client("localhost", 9091);
    }

    public Message getMyMsg() {
        return myMsg;
    }

    public static void main (String[] args){
        Ex3GUI test = new Ex3GUI();
        test.client.communicate();
    }
}
