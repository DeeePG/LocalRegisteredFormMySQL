package gui;

import Constants.CommonConstants;

import javax.swing.*;
import java.awt.*;

public class Form extends JFrame {

    public Form(String title){

        super(title);                                    //Set the title of title bar
        setSize(520, 680);                  //Set the size of the GUI
        setDefaultCloseOperation(EXIT_ON_CLOSE);        //Configure GUI to end process after closing
        //Set Layout to null to disable layout management so we can use absolute positioning
        //to place the components wherever we want.
        setLayout(null);
        setLocationRelativeTo(null);                    //Load GUI in the center of the screen
        setResizable(false);                            //Prevent Gui from change size
        getContentPane().setBackground(CommonConstants.primary_Color);   //Background color
    }
}
