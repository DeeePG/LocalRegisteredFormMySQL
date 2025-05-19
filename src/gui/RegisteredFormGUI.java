package gui;

import Constants.CommonConstants;
import db.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisteredFormGUI extends Form {

    public RegisteredFormGUI() {
        super("Register");
        addGuiComponents();
    }

    private void addGuiComponents(){

        JLabel registerLabel = new JLabel("Register");
        registerLabel.setBounds(0, 25, 520, 100);
        registerLabel.setForeground(CommonConstants.text_Color);                          //Font color
        registerLabel.setFont(new Font("Dialog", Font.BOLD, 40));              //Font size and style
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);                      //Placing it the Center
        add(registerLabel);                                                               //Add component to Gui

        //Username Label
        JLabel usernameLabel = new JLabel("Username : ");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(CommonConstants.text_Color);                          //Font color
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 25));             //Font size and style
        add(usernameLabel);

        //Text Field
        JTextField textField = new JTextField();
        textField.setBounds(30, 185, 450, 50);
        textField.setForeground(CommonConstants.text_Color);                           //Font color
        textField.setBackground(CommonConstants.secondary_Color);                      //Background color
        textField.setFont(new Font("Dialog", Font.PLAIN, 24));              //Font size and style
        add(textField);

        //Password Label
        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setBounds(30, 260, 400, 25);
        passwordLabel.setForeground(CommonConstants.text_Color);                          //Font color
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 25));             //Font size and style
        add(passwordLabel);

        //Password Text Field
        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBounds(30, 295, 450, 50);
        passwordTextField.setForeground(CommonConstants.text_Color);                           //Font color
        passwordTextField.setBackground(CommonConstants.secondary_Color);                      //Background color
        passwordTextField.setFont(new Font("Dialog", Font.PLAIN, 24));              //Font size and style
        add(passwordTextField);

        //Re-enter Password Label
        JLabel reEnterPasswordLabel = new JLabel("Re-enter Password : ");
        reEnterPasswordLabel.setBounds(30, 365, 400, 25);
        reEnterPasswordLabel.setForeground(CommonConstants.text_Color);                          //Font color
        reEnterPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 25));             //Font size and style
        add(reEnterPasswordLabel);

        //Password Text Field
        JPasswordField reEnterPasswordTextField = new JPasswordField();
        reEnterPasswordTextField.setBounds(30, 400, 450, 50);
        reEnterPasswordTextField.setForeground(CommonConstants.text_Color);                           //Font color
        reEnterPasswordTextField.setBackground(CommonConstants.secondary_Color);                      //Background color
        reEnterPasswordTextField.setFont(new Font("Dialog", Font.PLAIN, 24));              //Font size and style
        add(reEnterPasswordTextField);

        //Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(125, 520, 250, 50);
        registerButton.setBackground(CommonConstants.text_Color);                      //Background color
        registerButton.setFont(new Font("Dialog", Font.BOLD, 24));              //Font size and style
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(passwordTextField.getPassword());
                String rePassword = new String(reEnterPasswordTextField.getPassword());

                if(validateUserInput(username,password,rePassword)) {
                    if(MyJDBC.register(username,password)) {
                        //Now let's dispose this register form and open login form
                        RegisteredFormGUI.this.dispose();
                        LoginFomGUI login = new LoginFomGUI();
                        login.setVisible(true);

                        //Create a dialog to show the message of successfully registered
                        JOptionPane.showMessageDialog(login, "Account Registered Successfully");
                    }
                    else {
                        //Registered failed
                        JOptionPane.showMessageDialog(RegisteredFormGUI.this, "Error: User already exists");
                    }
                }
                else {
                    //Invalid user input
                    JOptionPane.showMessageDialog(RegisteredFormGUI.this, "Error: Your input credentials are wrong. Try again.");
                }

            }
        });
        add(registerButton);

        //Create registered Label(Used to load the registered GUI)
        JLabel loginHereLabel = new JLabel("Already have and account? Login here!");
        loginHereLabel.setBounds(125, 600, 250, 30);
        loginHereLabel.setForeground(CommonConstants.text_Color);                      //Background color
        loginHereLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginHereLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //add functionality so that when clicked it will launch the Login form
        loginHereLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisteredFormGUI.this.dispose();                           //This will dispose this form
                new LoginFomGUI().setVisible(true);
            }
        });
        add(loginHereLabel);
    }

    private boolean validateUserInput(String username, String password, String rePassword) {

        //All the fields must have a input
        if(username.length()==0 || password.length()==0 || rePassword.length()==0) return false;
        //Length of all the fields must be 6 or greater
        if(username.length() < 6) return false;
        //password and reEnterPassword must be the same
        if(!password.equals(rePassword)) return false;

        return true;
    }
}
