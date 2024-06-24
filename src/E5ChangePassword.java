import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class E5ChangePassword extends JFrame {

    private JTextField oldPasswordTextField;
    private JTextField newPasswordTextField;
    private JTextField confirmPasswordTextField;

    public E5ChangePassword() {
        setTitle("Change Password");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        StyledComponents.setFixedSizeAndShow(this);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Change Password");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(titleLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel oldPasswordLabel = new JLabel("Old Password:");
        JLabel newPasswordLabel = new JLabel("New Password:");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");

        StyledComponents.applyLabelStyle(oldPasswordLabel);
        StyledComponents.applyLabelStyle(newPasswordLabel);
        StyledComponents.applyLabelStyle(confirmPasswordLabel);

        centerPanel.add(oldPasswordLabel, gbc);
        gbc.gridy++;
        oldPasswordTextField = new JPasswordField(30);
        StyledComponents.applyTextFieldStyle(oldPasswordTextField);
        centerPanel.add(oldPasswordTextField, gbc);

        gbc.gridy++;
        centerPanel.add(newPasswordLabel, gbc);
        gbc.gridy++;
        newPasswordTextField = new JPasswordField(30);
        StyledComponents.applyTextFieldStyle(newPasswordTextField);
        centerPanel.add(newPasswordTextField, gbc);

        gbc.gridy++;
        centerPanel.add(confirmPasswordLabel, gbc);
        gbc.gridy++;
        confirmPasswordTextField = new JPasswordField(30);
        StyledComponents.applyTextFieldStyle(confirmPasswordTextField);
        centerPanel.add(confirmPasswordTextField, gbc);

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
                String oldPassword = oldPasswordTextField.getText();
                String newPassword = newPasswordTextField.getText();
                String confirmPassword = confirmPasswordTextField.getText();

                if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(E5ChangePassword.this, "Please fill in all fields.");
                } else if (!newPassword.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(E5ChangePassword.this, "New password and confirm password do not match.");
                } else {
                    JOptionPane.showMessageDialog(E5ChangePassword.this, "Password changed successfully!");
                    oldPasswordTextField.setText("");
                    newPasswordTextField.setText("");
                    confirmPasswordTextField.setText("");
                }
            }
        });

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new E0UpdateInfoPage().setVisible(true);
                dispose();
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new B0DashboardPage().setVisible(true);
                dispose();
            }
        });

        getContentPane().add(mainPanel);

        pack(); // Pack the frame to size
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(E5ChangePassword::new);
    }
}
