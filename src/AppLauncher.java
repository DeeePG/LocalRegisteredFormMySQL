import db.MyJDBC;
import gui.LoginFomGUI;
import gui.RegisteredFormGUI;

import javax.swing.*;
import java.util.Scanner;

public class AppLauncher {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFomGUI().setVisible(true);

            }
        });

    }
}
