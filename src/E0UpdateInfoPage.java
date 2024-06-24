import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class E0UpdateInfoPage extends JFrame {

    public E0UpdateInfoPage() {
        setTitle("Academic Progress Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setSizeAndShow();
    }

    private void initComponents() {
        JPanel container = new JPanel(new BorderLayout());
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        container.add(contentPanel, BorderLayout.CENTER);

        JLabel headerLabel = new JLabel("Update Info");
        StyledComponents.applyHighlightedBiggerFontStyle(headerLabel);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(headerLabel, BorderLayout.NORTH);

        JButton updateProfileBtn = createButton("Update Profile");
        JButton updateCourseBtn = createButton("Update Course");
        JButton updateRoutineBtn = createButton("Update Routine");
        JButton uploadPhotoBtn = createButton("Upload Photo");
        JButton changePasswordBtn = createButton("Change Password");

        contentPanel.add(updateProfileBtn, gbc);
        gbc.gridy++;
        contentPanel.add(updateCourseBtn, gbc);
        gbc.gridy++;
        contentPanel.add(updateRoutineBtn, gbc);
        gbc.gridy++;
        contentPanel.add(uploadPhotoBtn, gbc);
        gbc.gridy++;
        contentPanel.add(changePasswordBtn, gbc);

        // Home button in bottom right corner
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton homeButton = new JButton("Home");
        StyledComponents.applyButtonStyle(homeButton, StyledComponents.myBlackMamba());
        buttonPanel.add(homeButton);
        container.add(buttonPanel, BorderLayout.SOUTH);

        homeButton.addActionListener(e -> {
            new B0DashboardPage().setVisible(true);
            dispose();
        });

        getContentPane().add(container);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        StyledComponents.applyButtonStyle(button, StyledComponents.myGreenButton());
        Dimension preferredSize = new Dimension(400, 40);
        button.setMinimumSize(preferredSize);
        button.setMaximumSize(preferredSize);
        button.setPreferredSize(preferredSize);
        button.addActionListener(e -> {
            switch (text) {
                case "Update Profile":
                    new E1UpdateProfilePage().setVisible(true);
                    dispose();
                    break;
                case "Update Course":
                    new E2UpdateCoursePage().setVisible(true);
                    dispose();
                    break;
                case "Update Routine":
                    new E3UpdateRoutinePage().setVisible(true);
                    dispose();
                    break;
                case "Upload Photo":
                    // Add logic to handle photo upload
                    break;
                case "Change Password":
                    new E5ChangePassword().setVisible(true);
                    dispose();
                    break;
                default:
                    System.out.println("Button clicked: " + text);
                    break;
            }
        });
        return button;
    }

    private void setSizeAndShow() {
        StyledComponents.setFixedSizeAndShow(this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(E0UpdateInfoPage::new);
    }
}
