package saini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DeletePatient extends JFrame implements ActionListener {

    private JLabel usernameLabel;
    private JTextField usernameField;
    private JButton deleteButton, backButton;

    public DeletePatient() {
        setTitle("Delete Patient");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(1,2));
        usernameLabel = new JLabel("Enter Patient Username:");
        usernameField = new JTextField();
        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);
        container.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        bottomPanel.add(deleteButton);
        bottomPanel.add(backButton);
        container.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            String patientUsername = usernameField.getText().trim();

            try {
                File inputFile = new File("Patient.txt");
                File tempFile = new File("temp.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                String line;
                boolean deleted = false;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[3].equals(patientUsername)) {
                        deleted = true;
                        continue;
                    }
                    writer.write(line + System.getProperty("line.separator"));
                }
                reader.close();
                writer.close();
                if (!deleted) {
                    JOptionPane.showMessageDialog(this, "Patient not found.");
                } else {
                    inputFile.delete();
                    tempFile.renameTo(inputFile);
                    JOptionPane.showMessageDialog(this, "Patient deleted successfully.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            dispose();
            new AdminSignIn();
        }
    }
}

