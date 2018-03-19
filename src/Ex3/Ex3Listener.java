package Ex3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for Ex3GUI class
 */
class Ex3Listener implements ActionListener {
    private Ex3GUI frame;


    // constructor
    public Ex3Listener(Ex3GUI gui) {
        frame = gui;
    }

    // performs an action in response to the event
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frame.button1) {
            System.out.println("pressed create message");
            try {
                String sender = frame.t1.getText();
                String receiver = frame.t2.getText();
                String subject = frame.t3.getText();
                double firstNum = Double.parseDouble(frame.t4.getText());
                double secondNum = Double.parseDouble(frame.t5.getText());
                frame.client.sendMessageToServer(new Message(subject, sender, receiver, firstNum, secondNum));
                frame.ta1.append(frame.client.showMsg(frame.client.fromServer));
                JOptionPane.showMessageDialog(null,"Successfully created message.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }catch (NullPointerException j){
                JOptionPane.showMessageDialog(null, "Unable to send message.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == frame.button2){
            System.out.println("pressed clear");
            frame.t1.setText("");
            frame.t2.setText("");
            frame.t3.setText("");
            frame.t4.setText("");
            frame.t5.setText("");
        }
    }
}

