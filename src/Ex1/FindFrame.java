package Ex1;

import javax.swing.*;

public class FindFrame{
    private BinSearchTree data;

    public FindFrame(BinSearchTree inbst){
        data = inbst;
        JOptionPane enter = new JOptionPane();
        int key = -1;
        try {
            key = Integer.parseInt(enter.showInputDialog(null, "Enter the Student's ID: "));
            try {
                Node n = data.find(data.root, String.valueOf(key));
                JOptionPane.showMessageDialog(null, n.toString(),"Message", JOptionPane.PLAIN_MESSAGE);
            }catch(NullPointerException j){
                JOptionPane.showMessageDialog(null,"ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException j) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
