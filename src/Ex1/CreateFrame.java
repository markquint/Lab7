package Ex1;

import javax.swing.*;

public class CreateFrame {
    private BuildBST data;

    public CreateFrame(){
        String filename = JOptionPane.showInputDialog("Enter the file name: ");
        data = new BuildBST(filename);
    }

    public BinSearchTree getBST(){
        return data.bst;
    }
}
