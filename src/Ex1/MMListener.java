package Ex1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class MMListener implements ActionListener {
    private MainMenu frame;
    private InsertFrame iframe;
    private BinSearchTree data;

    private StringWriter w = new StringWriter();
    private PrintWriter pw = new PrintWriter(w, true);

    // constructor
    public MMListener(MainMenu mfr) {
        frame = mfr;
    }

    // performs an action in response to the event
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frame.b4) {
            System.out.println("pressed create tree");
            CreateFrame createF = new CreateFrame();
            data = createF.getBST();
        }
        else if (e.getSource() == frame.b3) {
            System.out.println("pressed browse");
           try{
                data.print_tree(data.root, pw);
                frame.ta1.setText(w.toString());
                w.close();
            }
            catch (IOException j) {
                JOptionPane.showMessageDialog(null, "Error printing tree.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (NullPointerException j){
                JOptionPane.showMessageDialog(null, "No loaded file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == frame.b2) {
            System.out.println("pressed find");
            FindFrame findF = new FindFrame(data);
        }
        else if (e.getSource() == frame.b1) {
            System.out.println("pressed insert");
            try {
                iframe = new InsertFrame(data);
                data = iframe.updatedTree();
            }

            catch(NullPointerException j){
                JOptionPane.showMessageDialog(null,"No loaded file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //TODO: fix browse

}
