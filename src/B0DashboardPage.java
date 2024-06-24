import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class B0DashboardPage extends JFrame {

    public B0DashboardPage() {
        setTitle("Academic Progress Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        StyledComponents.setFixedSizeAndShow(this); // Apply fixed size and centering
    }

    private void initComponents() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        JLabel headerLabel = new JLabel("Main Menu");
        StyledComponents.applyHighlightedBiggerFontStyle(headerLabel);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(headerLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        String[] menuItems = {
            "Daily Progress",
            "Progress Summary",
            "Update Info",
            "My Profile",
            "Logout",
        };

        for (String menuItem : menuItems) {
            JButton button = new JButton(menuItem);
            StyledComponents.applyButtonStyle(button, StyledComponents.myGreenButton()); // Apply green button style
            button.setPreferredSize(new Dimension(400, 40)); // Set preferred button size
            panel.add(button, gbc);
            gbc.gridy++;
            button.addActionListener(new MenuActionListener(menuItem));
        }

        topPanel.add(panel, BorderLayout.CENTER);
        add(topPanel);
    }

    private class MenuActionListener implements ActionListener {
        private String menuItem;

        public MenuActionListener(String menuItem) {
            this.menuItem = menuItem;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (menuItem) {
                case "Logout":
                    new A1LoginPage().setVisible(true);
                    dispose();
                    break;
                case "Daily Progress":
                    new C0DailyProgressPage().setVisible(true);
                    dispose();
                    break;
                case "Progress Summary":
                    new D0ProgressSummaryPage().setVisible(true);
                    dispose();
                    break;
                case "Update Info":
                    new E0UpdateInfoPage().setVisible(true);
                    dispose();
                    break;
                case "My Profile":
                    new F0MyProfilePage().setVisible(true);
                    dispose();
                    break;
                default:
                    // Handle other menu items if needed
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(B0DashboardPage::new);
    }
}
