import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class A2SignUp extends JFrame {

    private JTextField usernameField, rollField, emailField, departmentField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton A2signUpButton, backButton;

    public A2SignUp() {
        setTitle("Academic Progress Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setupLayout();
        StyledComponents.setFixedSizeAndShow(this);
        setSize(950, 850); 
    }
    private void initComponents() {
        usernameField = new JTextField(30);
        rollField = new JTextField(30);
        emailField = new JTextField(30);
        departmentField = new JTextField(30);
        passwordField = new JPasswordField(30);
        confirmPasswordField = new JPasswordField(30);
        A2signUpButton = new JButton("Sign Up");
        backButton = new JButton("Back");

        StyledComponents.applyTextFieldStyle(usernameField);
        StyledComponents.applyTextFieldStyle(rollField);
        StyledComponents.applyTextFieldStyle(emailField);
        StyledComponents.applyTextFieldStyle(departmentField);
        StyledComponents.applyTextFieldStyle(passwordField);
        StyledComponents.applyTextFieldStyle(confirmPasswordField);
        StyledComponents.applyButtonStyle(A2signUpButton, StyledComponents.myGreenButton());
        StyledComponents.applyButtonStyle(backButton, StyledComponents.myBlueButton());
        A2signUpButton.setPreferredSize(new Dimension(400, 40));
        backButton.setPreferredSize(new Dimension(400, 40));

        A2signUpButton.addActionListener(e -> signUp());
        backButton.addActionListener(e -> {
            new A1LoginPage().setVisible(true);
            dispose();
        });
    }

    private void signUp() {
        String username = usernameField.getText();
        String roll = rollField.getText();
        String email = emailField.getText();
        String department = departmentField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (username.isEmpty() || roll.isEmpty() || email.isEmpty()|| department.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords don't match", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Conn c1 = new Conn();
                c1.saveUserInfo(username, roll, email, department, password);
                JOptionPane.showMessageDialog(this, "Sign up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new A1LoginPage().setVisible(true);
                dispose();
            } catch(Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error signing up", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel createAccountLabel = new JLabel("Create Account");
        createAccountLabel.setFont(new Font("Arial", Font.BOLD, 24));
        createAccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(createAccountLabel, gbc);
        gbc.gridy++;

        JLabel usernameLabel = new JLabel("Username:");
        JLabel rollLabel = new JLabel("Roll:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel departmentLabel = new JLabel("Department:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");

        StyledComponents.applyLabelStyle(usernameLabel);
        StyledComponents.applyLabelStyle(rollLabel);
        StyledComponents.applyLabelStyle(emailLabel);
        StyledComponents.applyLabelStyle(departmentLabel);
        StyledComponents.applyLabelStyle(passwordLabel);
        StyledComponents.applyLabelStyle(confirmPasswordLabel);

        panel.add(usernameLabel, gbc);
        gbc.gridy++;
        panel.add(usernameField, gbc);
        gbc.gridy++;
        panel.add(rollLabel, gbc);
        gbc.gridy++;
        panel.add(rollField, gbc);
        gbc.gridy++;
        panel.add(emailLabel, gbc);
        gbc.gridy++;
        panel.add(emailField, gbc);
        gbc.gridy++;
        panel.add(departmentLabel, gbc);
        gbc.gridy++;
        panel.add(departmentField, gbc);
        gbc.gridy++;
        panel.add(passwordLabel, gbc);
        gbc.gridy++;
        panel.add(passwordField, gbc);
        gbc.gridy++;
        panel.add(confirmPasswordLabel, gbc);
        gbc.gridy++;
        panel.add(confirmPasswordField, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(A2signUpButton, gbc);
        gbc.gridy++;
        panel.add(backButton, gbc);

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new A2SignUp());
    }
}
