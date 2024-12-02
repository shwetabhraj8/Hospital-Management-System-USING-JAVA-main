package saini;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class BookAppointment extends JFrame implements ActionListener {
    private JLabel doctorLabel;
    private JTextField doctorField;
    private JButton bookButton, backButton;
    private String patientUsername;

    public BookAppointment(String patientUsername) {
        this.patientUsername = patientUsername;

        // create UI elements
        doctorLabel = new JLabel("Doctor Name:");
        doctorField = new JTextField();
        bookButton = new JButton("Book Appointment");
        backButton = new JButton("Back to Home");

        // set layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // add UI elements to content pane
        add(doctorLabel);
        add(doctorField);
        add(bookButton);
        add(backButton);

        // add action listeners
        bookButton.addActionListener(this);
        backButton.addActionListener(this);

        // set frame properties
        setTitle("Book Appointment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null); // center the frame
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookButton) {
            String doctorName = doctorField.getText();
            String doctorUsername = null;

            try {
                // search for doctor's username in Doctor.txt
                BufferedReader reader = new BufferedReader(new FileReader("Doctor.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",");
                    String name = fields[0];
                    String username = fields[4];

                    if (name.equalsIgnoreCase(doctorName)) {
                        doctorUsername = username;
                        break;
                    }
                }
                reader.close();

                if (doctorUsername == null) {
                    JOptionPane.showMessageDialog(this, "Doctor not found");
                    return;
                }

                // append appointment to Appointment.txt
                BufferedWriter writer = new BufferedWriter(new FileWriter("Appointment.txt", true));
                writer.write(doctorUsername + "," + patientUsername);
                writer.newLine();
                writer.close();

                JOptionPane.showMessageDialog(this, "Appointment booked successfully");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }

        } else if (e.getSource() == backButton) {
            // go back to home page
            new HospitalHomepage();
            dispose();
        }
    }
}

