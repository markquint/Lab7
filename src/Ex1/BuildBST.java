package Ex1;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BuildBST {
    BinSearchTree bst = new BinSearchTree();

    public BuildBST(String filename){
        String [] args;
        try {
            Scanner in = new Scanner(new FileReader(filename));
            String line;
            while (in.hasNextLine()){
                line = in.nextLine();
                args = line.split("\\s+");
                //System.out.println(args[1]+" "+args[2]+" "+args[3]+" "+args[4]);
                bst.insert(args[1], args[2], args[3], args[4]);
            }
            in.close();
            JOptionPane.showMessageDialog(null,"File successfully loaded.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }catch(IOException e){
            System.err.println("Error reading in File");
            JOptionPane.showMessageDialog(null,"File not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args){
        BuildBST b = new BuildBST("input.txt");
    }


}
