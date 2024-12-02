package saini;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PatientMedicine extends JFrame {
    private JTextArea medicineArea;

    public PatientMedicine(String username) {
        setTitle("Patient Medicine");
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel heading = new JLabel("Medicines Prescribed for " + username);
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        heading.setHorizontalAlignment(JLabel.CENTER);
        panel.add(heading, BorderLayout.NORTH);

        medicineArea = new JTextArea();
        medicineArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(medicineArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> {
            new HospitalHomepage();
            dispose();
        });
        panel.add(backButton, BorderLayout.SOUTH);

        try {
            File file = new File("Medicine.txt");
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                while (line != null) {
                    String[] medicineInfo = line.split(",");
                    if (medicineInfo[1].equals(username)) {
                        medicineArea.append("Doctor: " + medicineInfo[0] + "\n");
                        medicineArea.append("Disease: " + medicineInfo[2] + "\n");
                        medicineArea.append("Medicine 1: " + medicineInfo[3] + "\n");
                        medicineArea.append("Medicine 2: " + medicineInfo[4] + "\n");
                        medicineArea.append("Medicine 3: " + medicineInfo[5] + "\n\n");
                    }
                    line = reader.readLine();
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
