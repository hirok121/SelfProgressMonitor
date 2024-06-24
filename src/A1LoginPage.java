import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class A1LoginPage extends JFrame {

    private JTextField usernameField;
    private JTextField rollField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton A2signUpButton;

    public A1LoginPage() {
        setTitle("Academic Progress Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setupLayout();
        StyledComponents.setFixedSizeAndShow(this);
    }

    private void initComponents() {
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0));
        JLabel headerLabel = new JLabel("Welcome to Academic Progress Tracker");
        StyledComponents.applyHighlightedBiggerFontStyle(headerLabel);
        topPanel.add(headerLabel);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        usernameField = new JTextField(30);
        rollField = new JTextField(30);
        passwordField = new JPasswordField(30);
        loginButton = new JButton("Log in");
        A2signUpButton = new JButton("Sign Up");
        StyledComponents.applyTextFieldStyle(usernameField);
        StyledComponents.applyTextFieldStyle(rollField);
        StyledComponents.applyTextFieldStyle(passwordField);
        StyledComponents.applyButtonStyle(loginButton, StyledComponents.myGreenButton());
        StyledComponents.applyButtonStyle(A2signUpButton, StyledComponents.myBlueButton());

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String roll = rollField.getText();
                String password = new String(passwordField.getPassword());
                saveUserInfo(username, roll, password);
            }
        });

        A2signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new A2SignUp();
                dispose();
            }
        });

        JLabel usernameLabel = new JLabel("Username:");
        JLabel rollLabel = new JLabel("Roll:");
        JLabel passwordLabel = new JLabel("Password:");
        StyledComponents.applyLabelStyle(usernameLabel);
        StyledComponents.applyLabelStyle(rollLabel);
        StyledComponents.applyLabelStyle(passwordLabel);

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
        gbc.gridwidth = 1;
        panel.add(loginButton, gbc);
        gbc.gridy++;
        panel.add(A2signUpButton, gbc);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void setupLayout() {}

    private void saveUserInfo(String username, String roll, String password) {
        Conn conn = new Conn();
        String query = "INSERT INTO users (username, roll, password) VALUES (?, ?, ?)";

        try (Connection connection = conn.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, roll);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "User information saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving user information.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new A1LoginPage());
    }
}
