package Ex1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class IFListener implements ActionListener{
    InsertFrame iframe;

    public IFListener(InsertFrame ifr){
        iframe = ifr;
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == iframe.button1) {
            try {
                String id = iframe.t1.getText();
                String faculty = iframe.t2.getText();
                String major = iframe.t3.getText();
                String year = iframe.t4.getText();
                iframe.bstdata.insert(id, faculty, major, year);
                JOptionPane.showMessageDialog(null,"Ex1.Node successfully inserted.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }catch (NullPointerException j){
                JOptionPane.showMessageDialog(null, "Unable to insert new Ex1.Node.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == iframe.button2){
            iframe.frame1.dispatchEvent(new WindowEvent(iframe.frame1, WindowEvent.WINDOW_CLOSING));
        }
    }
}
