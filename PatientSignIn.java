package saini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PatientSignIn {
    private JFrame frame;
    private JLabel labelUsername, labelPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnBack;

    public PatientSignIn() {
        frame = new JFrame("Patient Sign In");
        frame.setSize(350, 200);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelUsername = new JLabel("Username:");
        labelUsername.setBounds(30, 20, 80, 25);
        frame.add(labelUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(120, 20, 200, 25);
        frame.add(txtUsername);

        labelPassword = new JLabel("Password:");
        labelPassword.setBounds(30, 50, 80, 25);
        frame.add(labelPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 50, 200, 25);
        frame.add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 90, 80, 25);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                if (login(username, password)) {
                    frame.setVisible(false);
                    new PatientPage(username).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                }
            }
        });
        frame.add(btnLogin);

        btnBack = new JButton("Back");
        btnBack.setBounds(190, 90, 80, 25);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new HospitalHomepage().setVisible(true);
            }
        });
        frame.add(btnBack);

        frame.setVisible(true);
    }

    private boolean login(String username, String password) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Patient.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                if (data[3].equals(username) && data[4].equals(password)) {
                    reader.close();
                    return true;
                }
                line = reader.readLine();
            }
            reader.close();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
