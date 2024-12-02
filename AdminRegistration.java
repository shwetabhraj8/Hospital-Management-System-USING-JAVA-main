package saini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AdminRegistration extends JFrame implements ActionListener {

    private JLabel nameLabel, ageLabel, bloodGroupLabel, usernameLabel, passwordLabel;
    private JTextField nameField, ageField, usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> bloodGroupBox;
    private JButton submitButton, backButton;

    public AdminRegistration() {
        super("Admin Registration");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        nameLabel = new JLabel("Name:");
        add(nameLabel);
        nameField = new JTextField(20);
        add(nameField);

        ageLabel = new JLabel("Age:");
        add(ageLabel);
        ageField = new JTextField(20);
        add(ageField);

        bloodGroupLabel = new JLabel("Blood Group:");
        add(bloodGroupLabel);
        String[] bloodGroups = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
        bloodGroupBox = new JComboBox<>(bloodGroups);
        add(bloodGroupBox);

        usernameLabel = new JLabel("Username:");
        add(usernameLabel);
        usernameField = new JTextField(20);
        add(usernameField);

        passwordLabel = new JLabel("Password:");
        add(passwordLabel);
        passwordField = new JPasswordField(20);
        add(passwordField);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        add(backButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String age = ageField.getText();
            String bloodGroup = (String) bloodGroupBox.getSelectedItem();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Admin.txt", true));
                writer.write(name + "," + age + "," + bloodGroup + "," + username + "," + password);
                writer.newLine();
                writer.close();
                JOptionPane.showMessageDialog(this, "Admin registered successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            dispose();
            new HospitalHomepage();
        }
    }
}

