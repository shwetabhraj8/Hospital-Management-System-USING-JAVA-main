package saini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DoctorPage extends JFrame implements ActionListener {

    private String username;

    public DoctorPage(String username) {
        this.username = username;
        setTitle("Doctor Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome, Dr. " + username);
        welcomeLabel.setBounds(20, 20, 200, 30);
        panel.add(welcomeLabel);

        JButton appointmentButton = new JButton("See Appointments");
        appointmentButton.setBounds(20, 70, 150, 30);
        appointmentButton.addActionListener(this);
        panel.add(appointmentButton);



        JButton medicineButton = new JButton("Write Medicine");
        medicineButton.setBounds(20, 170, 150, 30);
        medicineButton.addActionListener(this);
        panel.add(medicineButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(250, 200, 100, 30);
        backButton.addActionListener(this);
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("See Appointments")) {

            new SeeAppointment(username);
        }
        else if (e.getActionCommand().equals("Write Medicine")) {
            new WriteMedicine(username);
        } else if (e.getActionCommand().equals("Back")) {
            dispose();
            new HospitalHomepage();
        }
    }
}

