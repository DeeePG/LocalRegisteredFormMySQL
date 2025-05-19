package gui;

import Constants.CommonConstants;
import db.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFomGUI extends Form {

    public LoginFomGUI() {
        super("Login");
        addGuiComponents();
    }

    private void addGuiComponents(){

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(0, 25, 520, 100);
        loginLabel.setForeground(CommonConstants.text_Color);                          //Font color
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 40));              //Font size and style
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);                      //Placing it the Center
        add(loginLabel);                                                               //Add component to Gui

        //Username Label
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(CommonConstants.text_Color);                          //Font color
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 25));              //Font size and style
        add(usernameLabel);

        //Text Field
        JTextField textField = new JTextField();
        textField.setBounds(30, 185, 450, 55);
        textField.setForeground(CommonConstants.text_Color);                           //Font color
        textField.setBackground(CommonConstants.secondary_Color);                      //Background color
        textField.setFont(new Font("Dialog", Font.PLAIN, 24));              //Font size and style
        add(textField);

        //Password Label
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(30, 335, 400, 25);
        passwordLabel.setForeground(CommonConstants.text_Color);                          //Font color
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 25));             //Font size and style
        add(passwordLabel);

        //Password Text Field
        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBounds(30, 365, 450, 55);
        passwordTextField.setForeground(CommonConstants.text_Color);                           //Font color
        passwordTextField.setBackground(CommonConstants.secondary_Color);                      //Background color
        passwordTextField.setFont(new Font("Dialog", Font.PLAIN, 24));              //Font size and style
        add(passwordTextField);

        //Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(125, 520, 250, 50);
        loginButton.setBackground(CommonConstants.text_Color);                      //Background color
        loginButton.setFont(new Font("Dialog", Font.BOLD, 24));              //Font size and style
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = textField.getText();
                String password = new String(passwordTextField.getPassword());

                if(MyJDBC.validateLogin(username, password)) {
                    //Successfully Login
                    JOptionPane.showMessageDialog(LoginFomGUI.this, "Login Successfully \n" +
                            "Welcome");
                }
                else {
                    //Login Failed
                    JOptionPane.showMessageDialog(LoginFomGUI.this, "Login failed! \n"+
                            "Try Again");
                }
            }
        });
        add(loginButton);

        //Create registered Label(Used to load the registered GUI)
        JLabel registerLabel = new JLabel("Not a user? Register here!");
        registerLabel.setBounds(125, 600, 250, 30);
        registerLabel.setForeground(CommonConstants.text_Color);                      //Background color
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //Add functionality so that when clicked it will launched the register form
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginFomGUI.this.dispose();                             //This will dispose this GUI
                new RegisteredFormGUI().setVisible(true);               //This will launch the Register form
            }
        });
        add(registerLabel);
    }

}
