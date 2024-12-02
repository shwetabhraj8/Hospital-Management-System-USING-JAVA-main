package saini;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class PayBill extends JFrame implements ActionListener {
    private final String patientUsername;
    private final JLabel feeLabel;
    private final JButton payButton;
    private final JButton backButton;

    public PayBill(String patientUsername) {
        this.patientUsername = patientUsername;

        // Set up the GUI
        setTitle("Pay Fee");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        feeLabel = new JLabel("Fee: " + getFee());
        panel.add(feeLabel);

        payButton = new JButton("PAY");
        payButton.addActionListener(this);
        panel.add(payButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        panel.add(backButton);

        setVisible(true);
    }

    // Helper method to retrieve the fee from the file
    private String getFee() {
        String fee = "N/A";
        try {
            File file = new File("Fee.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                if (tokens[1].equals(patientUsername)) {
                    fee = tokens[2];
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fee;
    }

    // Helper method to remove the fee information from the file
    private void removeFee() {
        try {
            File inputFile = new File("Fee.txt");
            File tempFile = new File("Fee_temp.txt");
            Scanner scanner = new Scanner(inputFile);
            FileWriter writer = new FileWriter(tempFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                if (!tokens[1].equals(patientUsername)) {
                    writer.write(line + "\n");
                }
            }
            scanner.close();
            writer.close();
            inputFile.delete();
            tempFile.renameTo(inputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payButton) {
            // Remove the fee information from the file
            removeFee();

            // Update the fee label
            feeLabel.setText("Fee: " + getFee());

            // Show a message dialog to confirm payment
            JOptionPane.showMessageDialog(this, "Payment successful!");

        } else if (e.getSource() == backButton) {
            // Go back to the patient page
            new PatientPage(patientUsername);
            dispose();
        }
    }
}

