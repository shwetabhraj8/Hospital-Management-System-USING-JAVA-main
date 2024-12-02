package saini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HospitalHomepage extends JFrame implements ActionListener {

    JLabel nameLabel, addressLabel, phoneLabel;
    JButton aboutBtn, doctorRegBtn, patientRegBtn, adminRegBtn, doctorSignBtn, patientSignBtn, adminSignBtn;

    public HospitalHomepage() {

        nameLabel = new JLabel("JAN SEVA KENDRA");
        addressLabel = new JLabel("NAI DILLI");
        phoneLabel = new JLabel("1234567890");

        aboutBtn = new JButton("About Page");
        doctorRegBtn = new JButton("Doctor Registration");
        patientRegBtn = new JButton("Patient Registration");
        adminRegBtn = new JButton("Admin Registration");
        doctorSignBtn = new JButton("Doctor Sign-in");
        patientSignBtn = new JButton("Patient Sign-in");
        adminSignBtn = new JButton("Admin Sign-in");

        aboutBtn.addActionListener(this);
        doctorRegBtn.addActionListener(this);
        patientRegBtn.addActionListener(this);
        adminRegBtn.addActionListener(this);
        doctorSignBtn.addActionListener(this);
        patientSignBtn.addActionListener(this);
        adminSignBtn.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(nameLabel);
        panel.add(addressLabel);
        panel.add(phoneLabel);
        panel.add(aboutBtn);
        panel.add(doctorRegBtn);
        panel.add(patientRegBtn);
        panel.add(adminRegBtn);
        panel.add(doctorSignBtn);
        panel.add(patientSignBtn);
        panel.add(adminSignBtn);

        getContentPane().add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Hospital Page");
        setSize(400, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aboutBtn) {
            JOptionPane.showMessageDialog(this, "i am hospital");
        } else if (e.getSource() == doctorRegBtn) {
            new DoctorRegistration();
        } else if (e.getSource() == patientRegBtn) {
            new PatientRegistration();
        } else if (e.getSource() == adminRegBtn) {
            new AdminRegistration();
        } else if (e.getSource() == doctorSignBtn) {
            new DoctorSignIn();
        } else if (e.getSource() == patientSignBtn) {
            new PatientSignIn();
        } else if (e.getSource() == adminSignBtn) {
            new AdminSignIn();
        }
    }

    public static void main(String[] args) {
        new HospitalHomepage();
    }
}

