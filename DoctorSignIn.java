package saini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DoctorSignIn extends JFrame implements ActionListener {

    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signInButton, backButton;

    public DoctorSignIn() {
        setTitle("Doctor Sign In");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2));
        usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);
        usernameField = new JTextField();
        panel.add(usernameField);
        passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        panel.add(passwordField);
        signInButton = new JButton("Sign In");
        signInButton.addActionListener(this);
        panel.add(signInButton);
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        panel.add(backButton);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("Sign In")) {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            boolean validLogin = false;

            try {
                BufferedReader br = new BufferedReader(new FileReader("Doctor.txt"));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(",");
                    String uniqueUsername = fields[4];
                    String uniquePassword = fields[5];
                    if (uniqueUsername.equals(username) && uniquePassword.equals(password)) {
                        validLogin = true;
                        break;
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (validLogin) {
                dispose();
                String text = usernameField.getText();
                new DoctorPage(text);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login. Please try again.");
            }
        } else if (action.equals("Back")) {
            dispose();
            new HospitalHomepage();
        }
    }

    public static void main(String[] args) {
        new DoctorSignIn();
    }
}
