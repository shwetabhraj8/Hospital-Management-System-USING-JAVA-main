package saini;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;

public class AdminSignIn implements ActionListener {
    private JFrame frame;
    private JLabel userLabel, passwordLabel, messageLabel;
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;
    private JComboBox<String> optionsBox;

    public AdminSignIn() {
        // create frame and components
        frame = new JFrame("Admin Sign In");
        userLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        userField = new JTextField();
        passwordField = new JPasswordField();
        messageLabel = new JLabel();
        loginButton = new JButton("Login");
        backButton = new JButton("Back");
        optionsBox = new JComboBox<>(new String[]{"Delete Doctor", "Delete Patient"});

        // set layout and add components
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(messageLabel);
        panel.add(new JLabel());
        panel.add(loginButton);
        panel.add(backButton);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(optionsBox, BorderLayout.PAGE_END);

        // add action listeners
        loginButton.addActionListener(this);
        backButton.addActionListener(this);

        // set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userField.getText().trim();
            String password = new String(passwordField.getPassword());

            // check credentials
            boolean success = false;
            try {
                BufferedReader reader = new BufferedReader(new FileReader("Admin.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",");
                    String storedUsername = fields[3].trim();
                    String storedPassword = fields[4].trim();
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        success = true;
                        break;
                    }
                }
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // handle login success
            if (success) {
                messageLabel.setText("Login successful!");
                int option = optionsBox.getSelectedIndex();
                if (option == 0) {
                    frame.dispose();
                    new DeleteDoctor();
                } else if (option == 1) {
                    frame.dispose();
                    new DeletePatient();
                }
            } else {
                messageLabel.setText("Invalid username or password.");
            }
        } else if (e.getSource() == backButton) {
            frame.dispose();
            new HospitalHomepage();
        }
    }


}
