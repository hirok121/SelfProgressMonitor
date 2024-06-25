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
        gbc.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add empty labels for spacing
        JLabel emptyLabel1 = new JLabel();
        contentPanel.add(emptyLabel1, gbc);
        gbc.gridy++;

        JLabel headerLabel = new JLabel("Update Info");
        StyledComponents.applyHighlightedBiggerFontStyle(headerLabel); // Apply styling
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center align
        contentPanel.add(headerLabel, gbc);
        gbc.gridy++;

        // Reset grid width
        gbc.gridwidth = 1;

        JButton updateProfileBtn = createButton("Update Profile");
        JButton updateCourseBtn = createButton("Update Course");
        JButton updateRoutineBtn = createButton("Update Routine");
        JButton uploadPhotoBtn = createButton("Upload Photo");
        JButton changePasswordBtn = createButton("Change Password");

        gbc.gridx = 0; // Reset grid x position
        gbc.gridy++;
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

        container.add(contentPanel, BorderLayout.CENTER);
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
