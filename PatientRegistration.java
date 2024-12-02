package saini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PatientRegistration extends JFrame implements ActionListener {

    private JLabel nameLabel, ageLabel, bgLabel, userLabel, passLabel;
    private JTextField nameField, ageField, userField;
    private JPasswordField passField;
    private JComboBox<String> bgComboBox;
    private JButton submitButton, backButton;

    public PatientRegistration() {
        super("Patient Registration");

        // create labels and fields for input
        nameLabel = new JLabel("Name:");
        ageLabel = new JLabel("Age:");
        bgLabel = new JLabel("Blood Group:");
        userLabel = new JLabel("Username:");
        passLabel = new JLabel("Password:");

        nameField = new JTextField();
        ageField = new JTextField();
        userField = new JTextField();
        passField = new JPasswordField();

        String[] bgOptions = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
        bgComboBox = new JComboBox<>(bgOptions);

        // create submit and back buttons
        submitButton = new JButton("Submit");
        backButton = new JButton("Back");

        // add action listeners to buttons
        submitButton.addActionListener(this);
        backButton.addActionListener(this);

        // set layout and add components to panel
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(bgLabel);
        panel.add(bgComboBox);
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(submitButton);
        panel.add(backButton);

        // set panel and window properties
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String age = ageField.getText();
            String bg = (String) bgComboBox.getSelectedItem();
            String username = userField.getText();
            String password = String.valueOf(passField.getPassword());

            try {
                FileWriter fw = new FileWriter("Patient.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                pw.println(name + "," + age + "," + bg + "," + username + "," + password);

                pw.close();
                JOptionPane.showMessageDialog(this, "Patient registered successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }

        } else if (e.getSource() == backButton) {
            new HospitalHomepage();
            dispose();
        }
    }
}

