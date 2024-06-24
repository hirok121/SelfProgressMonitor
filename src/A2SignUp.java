import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class A2SignUp extends JFrame {

  private JTextField usernameField;
  private JTextField rollField;
  private JPasswordField passwordField;
  private JPasswordField confirmPasswordField;
  private JButton A2signUpButton;
  private JButton backButton;

  public A2SignUp() {
    setTitle("Academic Progress Tracker");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    initComponents();
    setupLayout();
    StyledComponents.setFixedSizeAndShow(this);
  }

  private void initComponents() {
    usernameField = new JTextField(30); // Corrected initialization
    rollField = new JTextField(30);
    passwordField = new JPasswordField(30);
    confirmPasswordField = new JPasswordField(30);
    A2signUpButton = new JButton("Sign Up");
    backButton = new JButton("Back");

    StyledComponents.applyTextFieldStyle(usernameField);
    StyledComponents.applyTextFieldStyle(rollField);
    StyledComponents.applyTextFieldStyle(passwordField);
    StyledComponents.applyTextFieldStyle(confirmPasswordField);
    StyledComponents.applyButtonStyle(
        A2signUpButton,
        StyledComponents.myGreenButton());
    StyledComponents.applyButtonStyle(
        backButton,
        StyledComponents.myBlueButton());
    A2signUpButton.setPreferredSize(new Dimension(400, 40));
    backButton.setPreferredSize(new Dimension(400, 40));

    A2signUpButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String roll = rollField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(
                confirmPasswordField.getPassword());

            if (username.isEmpty() ||
                roll.isEmpty() ||
                password.isEmpty() ||
                confirmPassword.isEmpty()) {
              JOptionPane.showMessageDialog(
                  A2SignUp.this,
                  "Please fill in all fields",
                  "Error",
                  JOptionPane.ERROR_MESSAGE);

            } else if (!password.equals(confirmPassword)) {
              JOptionPane.showMessageDialog(
                  A2SignUp.this,
                  "Passwords don't match",
                  "Error",
                  JOptionPane.ERROR_MESSAGE);
            } else {
              // Save user info using Conn class
              Conn conn = new Conn();
              System.out.println("Saving user info");
              conn.saveUserInfo(username, roll, password);

              // Optionally, navigate to a new page (example B0DashboardPage)
              new B0DashboardPage().setVisible(true);
              dispose(); // Close the current window

            }
          }
        });

    backButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            new A1LoginPage().setVisible(true);
            dispose(); // Close the current window
          }
        });
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
    createAccountLabel.setVerticalAlignment(SwingConstants.CENTER);
    panel.add(createAccountLabel, gbc);
    gbc.gridy++;

    JLabel usernameLabel = new JLabel("Username:");
    JLabel rollLabel = new JLabel("Roll:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel confirmPasswordLabel = new JLabel("Confirm Password:");

    StyledComponents.applyLabelStyle(usernameLabel);
    StyledComponents.applyLabelStyle(rollLabel);
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
