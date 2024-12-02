package saini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DoctorRegistration extends JFrame implements ActionListener {
    private JLabel nameLabel, ageLabel, qualificationLabel, bloodGroupLabel, usernameLabel, passwordLabel;
    private JTextField nameTextField, ageTextField, qualificationTextField, usernameTextField;
    private JComboBox<String> bloodGroupComboBox;
    private JPasswordField passwordField;
    private JButton registerButton, backButton;

    public DoctorRegistration() {
        // Setting the frame properties
        setTitle("Doctor Registration");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Creating labels and fields for input
        nameLabel = new JLabel("Name:");
        add(nameLabel);
        nameTextField = new JTextField();
        add(nameTextField);

        ageLabel = new JLabel("Age:");
        add(ageLabel);
        ageTextField = new JTextField();
        add(ageTextField);

        qualificationLabel = new JLabel("Qualification:");
        add(qualificationLabel);
        qualificationTextField = new JTextField();
        add(qualificationTextField);

        bloodGroupLabel = new JLabel("Blood Group:");
        add(bloodGroupLabel);
        String[] bloodGroups = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
        bloodGroupComboBox = new JComboBox<>(bloodGroups);
        add(bloodGroupComboBox);

        usernameLabel = new JLabel("Username:");
        add(usernameLabel);
        usernameTextField = new JTextField();
        add(usernameTextField);

        passwordLabel = new JLabel("Password:");
        add(passwordLabel);
        passwordField = new JPasswordField();
        add(passwordField);

        // Creating register and back buttons
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        add(registerButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        add(backButton);

        // Setting the frame visible
        setVisible(true);
    }

    // Action performed when a button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String name = nameTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            String qualification = qualificationTextField.getText();
            String bloodGroup = (String) bloodGroupComboBox.getSelectedItem();
            String username = usernameTextField.getText();
            String password = new String(passwordField.getPassword());

            // Writing doctor information to file
            try (PrintWriter writer = new PrintWriter(new FileWriter("Doctor.txt", true))) {
                writer.println(name + "," + age + "," + qualification + "," + bloodGroup + "," + username + "," + password);
                JOptionPane.showMessageDialog(null, "Registration successful!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error writing to file: " + ex.getMessage());
            }

            // Clearing input fields
            nameTextField.setText("");
            ageTextField.setText("");
            qualificationTextField.setText("");
            bloodGroupComboBox.setSelectedIndex(0);
            usernameTextField.setText("");
            passwordField.setText("");
        } else if (e.getSource() == backButton) {
            new HospitalHomepage();
            dispose();
        }
    }


}
