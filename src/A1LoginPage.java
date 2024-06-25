import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class A1LoginPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;

    public A1LoginPage() {
        setTitle("Academic Progress Tracker - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setupLayout();
        StyledComponents.setFixedSizeAndShow(this);
    }

    private void initComponents() {
        usernameField = new JTextField(30);
        passwordField = new JPasswordField(30);
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");

        StyledComponents.applyTextFieldStyle(usernameField);
        StyledComponents.applyTextFieldStyle(passwordField);
        StyledComponents.applyButtonStyle(loginButton, StyledComponents.myGreenButton());
        StyledComponents.applyButtonStyle(signUpButton, StyledComponents.myBlueButton());

        loginButton.setPreferredSize(new Dimension(400, 40));
        signUpButton.setPreferredSize(new Dimension(400, 40));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(A1LoginPage.this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Validate user credentials
                    Conn conn = new Conn();

                    //boolean isValidUser = conn.validateUser(username, password);
                    if (conn.AuthUser(username,password)) {
                        User user=new User(username,password);
                        user.updateinfo();
                        SessionManager.getInstance().login(user);

                        new B0DashboardPage().setVisible(true);
                     dispose();
                } else {
                       JOptionPane.showMessageDialog(A1LoginPage.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                 }
             }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new A2SignUp().setVisible(true);
                dispose();
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

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 24));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(loginLabel, gbc);
        gbc.gridy++;

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        StyledComponents.applyLabelStyle(usernameLabel);
        StyledComponents.applyLabelStyle(passwordLabel);

        panel.add(usernameLabel, gbc);
        gbc.gridy++;
        panel.add(usernameField, gbc);
        gbc.gridy++;
        panel.add(passwordLabel, gbc);
        gbc.gridy++;
        panel.add(passwordField, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(loginButton, gbc);
        gbc.gridy++;
        panel.add(signUpButton, gbc);

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new A1LoginPage());
    }
}
