//package Ex2;
//
//import Ex3.Ex3GUI;
//import Ex3.Message;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//class Ex2Listener implements ActionListener {
//    private GameGUI frame;
//
//    String currmark;
//    // constructor
//    public Ex2Listener(GameGUI gui) {
//        frame = gui;
//    }
//
//    // performs an action in response to the event
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == frame.button1) {
//            System.out.println("pressed button 1");
//            frame.button1.setText(currmark);
//        }
//        else if (e.getSource() == frame.button2){
//            System.out.println("pressed button 2");
//            frame.button2.setText(currmark);
//        }
//        else if (e.getSource() == frame.button3){
//            System.out.println("pressed button 3");
//            frame.button3.setText(currmark);
//        }
//        else if (e.getSource() == frame.button4){
//            System.out.println("pressed button 4");
//            frame.button4.setText(currmark);
//        }
//        else if (e.getSource() == frame.button5){
//            System.out.println("pressed button 4");
//            frame.button5.setText(currmark);
//        }
//        else if (e.getSource() == frame.button6){
//            System.out.println("pressed button 6");
//            frame.button6.setText(currmark);
//        }
//        else if (e.getSource() == frame.button7){
//            System.out.println("pressed button 7");
//            frame.button7.setText(currmark);
//        }
//        else if (e.getSource() == frame.button8){
//            System.out.println("pressed button 8");
//            frame.button8.setText(currmark);
//        }
//        else if (e.getSource() == frame.button9){
//            System.out.println("pressed button 9");
//            frame.button9.setText(currmark);
//        }
//    }
//}
