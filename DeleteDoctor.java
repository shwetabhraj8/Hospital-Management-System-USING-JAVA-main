package saini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DeleteDoctor extends JFrame implements ActionListener {
    private JLabel label;
    private JTextField usernameField;
    private JButton deleteButton, backButton;

    public DeleteDoctor() {
        // Setting the frame properties
        setTitle("Delete Doctor");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Creating a panel for the heading
        JPanel headingPanel = new JPanel(new FlowLayout());
        label = new JLabel("Delete Doctor Information");
        label.setFont(new Font("Serif", Font.BOLD, 20));
        headingPanel.add(label);
        add(headingPanel, BorderLayout.NORTH);

        // Creating a panel for the input fields and buttons
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(deleteButton);
        inputPanel.add(backButton);
        add(inputPanel, BorderLayout.CENTER);

        // Setting the frame visible
        setVisible(true);
    }

    // Action performed when a button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            String username = usernameField.getText();

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid username.");
                return;
            }

            try {
                // Reading the contents of the Doctor.txt file
                File file = new File("Doctor.txt");
                BufferedReader br = new BufferedReader(new FileReader(file));

                // Creating a temporary file
                File tempFile = new File("temp.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

                String line;
                boolean found = false;

                // Writing all the lines except the one with the specified username to the temporary file
                while ((line = br.readLine()) != null) {
                    String[] doctorData = line.split(",");
                    if (!doctorData[4].equals(username)) {
                        bw.write(line);
                        bw.newLine();
                    } else {
                        found = true;
                    }
                }

                bw.close();
                br.close();

                // Replacing the Doctor.txt file with the temporary file
                if (found) {
                    file.delete();
                    tempFile.renameTo(new File("Doctor.txt"));
                    JOptionPane.showMessageDialog(null, "Doctor information deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Doctor with username '" + username + "' not found.");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == backButton) {
            new AdminSignIn();
            dispose();
        }
    }

    public static void main(String[] args) {
        new DeleteDoctor();
    }
}
