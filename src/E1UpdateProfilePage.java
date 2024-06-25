import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class E1UpdateProfilePage extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField rollField;
    private JTextField semesterField;
    private JTextField phoneField;
    private JTextField bloodField;
    private JTextField departmentField;

    public E1UpdateProfilePage() {
        setTitle("Update Profile");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        StyledComponents.setFixedSizeAndShow(this);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel headerLabel = new JLabel("Update Profile");
        StyledComponents.applyHighlightedBiggerFontStyle(headerLabel);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Name:");
        StyledComponents.applyLabelStyle(nameLabel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 15, 10); // Increased bottom inset for spacing
        centerPanel.add(nameLabel, gbc);

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(400, 35)); // Set preferred size
        StyledComponents.applyTextFieldStyle(nameField);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 15, 0); // Increased bottom inset for spacing
        centerPanel.add(nameField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        StyledComponents.applyLabelStyle(emailLabel);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 15, 10); // Increased bottom inset for spacing
        centerPanel.add(emailLabel, gbc);

        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(400, 35)); // Set preferred size
        StyledComponents.applyTextFieldStyle(emailField);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 15, 0); // Increased bottom inset for spacing
        centerPanel.add(emailField, gbc);

        JLabel rollLabel = new JLabel("Roll:");
        StyledComponents.applyLabelStyle(rollLabel);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 15, 10); // Increased bottom inset for spacing
        centerPanel.add(rollLabel, gbc);

        rollField = new JTextField();
        rollField.setPreferredSize(new Dimension(400, 35)); // Set preferred size
        StyledComponents.applyTextFieldStyle(rollField);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 15, 0); // Increased bottom inset for spacing
        centerPanel.add(rollField, gbc);

        JLabel semesterLabel = new JLabel("Semester:");
        StyledComponents.applyLabelStyle(semesterLabel);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 15, 10); // Increased bottom inset for spacing
        centerPanel.add(semesterLabel, gbc);

        semesterField = new JTextField();
        semesterField.setPreferredSize(new Dimension(400, 35)); // Set preferred size
        StyledComponents.applyTextFieldStyle(semesterField);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 15, 0); // Increased bottom inset for spacing
        centerPanel.add(semesterField, gbc);

        JLabel phoneLabel = new JLabel("Phone:");
        StyledComponents.applyLabelStyle(phoneLabel);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 15, 10); // Increased bottom inset for spacing
        centerPanel.add(phoneLabel, gbc);

        phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(400, 35)); // Set preferred size
        StyledComponents.applyTextFieldStyle(phoneField);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 15, 0); // Increased bottom inset for spacing
        centerPanel.add(phoneField, gbc);

        JLabel bloodLabel = new JLabel("Blood:");
        StyledComponents.applyLabelStyle(bloodLabel);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 15, 10); // Increased bottom inset for spacing
        centerPanel.add(bloodLabel, gbc);

        bloodField = new JTextField();
        bloodField.setPreferredSize(new Dimension(400, 35)); // Set preferred size
        StyledComponents.applyTextFieldStyle(bloodField);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 15, 0); // Increased bottom inset for spacing
        centerPanel.add(bloodField, gbc);

        JLabel departmentLabel = new JLabel("Department:");
        StyledComponents.applyLabelStyle(departmentLabel);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 15, 10); // Increased bottom inset for spacing
        centerPanel.add(departmentLabel, gbc);

        departmentField = new JTextField();
        departmentField.setPreferredSize(new Dimension(400, 35)); // Set preferred size
        StyledComponents.applyTextFieldStyle(departmentField);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 15, 0); // Increased bottom inset for spacing
        centerPanel.add(departmentField, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        StyledComponents.applyButtonStyle(saveButton, StyledComponents.myGreenButton());
        buttonPanel.add(saveButton);

        JButton previousButton = new JButton("Previous");
        StyledComponents.applyButtonStyle(previousButton, StyledComponents.myBlueButton());
        buttonPanel.add(previousButton);

        JButton homeButton = new JButton("Home");
        StyledComponents.applyButtonStyle(homeButton, StyledComponents.myBlackMamba());
        buttonPanel.add(homeButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProfile();
            }
        });

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new E0UpdateInfoPage().setVisible(true);
                dispose(); // Close current frame
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new B0DashboardPage().setVisible(true);
                dispose(); // Close current frame
            }
        });

        getContentPane().add(mainPanel);

        pack(); // Pack the frame to size
    }

    private void saveProfile() {
        String name = nameField.getText();
        String email = emailField.getText();
        String roll = rollField.getText();
        String semester = semesterField.getText();
        String phone = phoneField.getText();
        String blood = bloodField.getText();
        String department = departmentField.getText();

        User user=SessionManager.getInstance().getCurrentUser();
        Conn c1 = new Conn();
        c1.updateUserInfo(user.getUsername(),roll,email,department);

        JOptionPane.showMessageDialog(this,
                "Profile saved successfully!\nName: " + name +
                        "\nEmail: " + email +
                        "\nRoll: " + roll +
                        "\nSemester: " + semester +
                        "\nPhone: " + phone +
                        "\nBlood: " + blood +
                        "\nDepartment: " + department);
        user.updateinfo();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(E1UpdateProfilePage::new);
    }
}
