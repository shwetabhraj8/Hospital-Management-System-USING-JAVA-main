package saini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientPage extends JFrame implements ActionListener {

    private JLabel titleLabel, optionsLabel;
    private JButton bookAppointmentButton, payBillButton, patientMedicineButton, backButton;
    private String username;

    public PatientPage(String username) {
        this.username = username;

        setTitle("Patient Page");
        setSize(500, 500);
        setLayout(null);

        titleLabel = new JLabel("Welcome " + username + "!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(150, 50, 300, 50);
        add(titleLabel);

        optionsLabel = new JLabel("Please select an option:");
        optionsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        optionsLabel.setBounds(50, 150, 200, 25);
        add(optionsLabel);

        bookAppointmentButton = new JButton("Book an Appointment");
        bookAppointmentButton.setBounds(50, 200, 200, 25);
        bookAppointmentButton.addActionListener(this);
        add(bookAppointmentButton);

        payBillButton = new JButton("Pay Bill");
        payBillButton.setBounds(50, 250, 200, 25);
        payBillButton.addActionListener(this);
        add(payBillButton);

        patientMedicineButton = new JButton("Patient Medicine");
        patientMedicineButton.setBounds(50, 300, 200, 25);
        patientMedicineButton.addActionListener(this);
        add(patientMedicineButton);

        backButton = new JButton("Back");
        backButton.setBounds(50, 400, 100, 25);
        backButton.addActionListener(this);
        add(backButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookAppointmentButton) {
            // Redirect to BookAppointment class
            new BookAppointment(username);
            dispose();
        } else if (e.getSource() == payBillButton) {
            // Redirect to PayBill class
            new PayBill(username);
            dispose();
        } else if (e.getSource() == patientMedicineButton) {
            // Redirect to PatientMedicine class
            new PatientMedicine(username);
            dispose();
        } else if (e.getSource() == backButton) {
            // Redirect to Home page
            new HospitalHomepage();
            dispose();
        }
    }
}

