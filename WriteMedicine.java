package saini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class WriteMedicine extends JFrame implements ActionListener {
    private JLabel patientUsernameLabel, diseaseLabel, medicine1Label, medicine2Label, medicine3Label;
    private JTextField patientUsernameField, diseaseField, medicine1Field, medicine2Field, medicine3Field;
    private JButton saveButton, backButton;
    private String doctorUsername;

    public WriteMedicine(String doctorUsername) {
        this.doctorUsername = doctorUsername;
        setTitle("Write Medicine");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new GridLayout(6, 2));

        patientUsernameLabel = new JLabel("Patient Username:");
        c.add(patientUsernameLabel);

        patientUsernameField = new JTextField();
        c.add(patientUsernameField);

        diseaseLabel = new JLabel("Disease:");
        c.add(diseaseLabel);

        diseaseField = new JTextField();
        c.add(diseaseField);

        medicine1Label = new JLabel("Medicine 1:");
        c.add(medicine1Label);

        medicine1Field = new JTextField();
        c.add(medicine1Field);

        medicine2Label = new JLabel("Medicine 2:");
        c.add(medicine2Label);

        medicine2Field = new JTextField();
        c.add(medicine2Field);

        medicine3Label = new JLabel("Medicine 3:");
        c.add(medicine3Label);

        medicine3Field = new JTextField();
        c.add(medicine3Field);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        c.add(saveButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        c.add(backButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            String patientUsername = patientUsernameField.getText();
            String disease = diseaseField.getText();
            String medicine1 = medicine1Field.getText();
            String medicine2 = medicine2Field.getText();
            String medicine3 = medicine3Field.getText();
            String medicineData = doctorUsername + "," + patientUsername + "," + disease + "," + medicine1 + "," + medicine2 + "," + medicine3 + "\n";
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Medicine.txt", true));
                writer.write(medicineData);
                writer.close();
                JOptionPane.showMessageDialog(null, "Medicine data saved successfully");
                dispose();
                new ChargeFee(doctorUsername, patientUsername);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            dispose();
            new DoctorPage(doctorUsername);
        }
    }
}


