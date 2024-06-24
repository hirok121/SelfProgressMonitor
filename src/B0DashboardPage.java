import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

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

        String[] icons = {
                "../svg/chart-line-solid.png",
                "../svg/chart-pie-solid.png",
                "../svg/user-cog-solid.png",
                "../svg/user-solid.png",
                "../svg/sign-out-alt-solid.png",
        };

        int[] iconWidths = { 29, 31, 31, 35, 29 };
        int[] iconHeights = { 30, 27, 27, 35, 29 };

        for (int i = 0; i < menuItems.length; i++) {
            JButton button = new JButton(menuItems[i]);
            StyledComponents.applyButtonStyle(button, StyledComponents.myGreenButton()); // Apply green button style
            button.setPreferredSize(new Dimension(300, 50)); // Set preferred button size

            ImageIcon icon = resizeIcon(icons[i], iconWidths[i], iconHeights[i]);
            button.setIcon(icon);

            // Resize icon to specified width and height and set on the left side
            button.setHorizontalTextPosition(SwingConstants.RIGHT); // Align text to the right of the icon
            button.setIconTextGap(10); // Add gap between icon and text
            button.setHorizontalAlignment(SwingConstants.CENTER); // Center-align text

            panel.add(button, gbc);
            gbc.gridy++;
            gbc.insets = new Insets(20, 10, 20, 10); // Add extra space between buttons
            button.addActionListener(new MenuActionListener(menuItems[i]));
        }

        topPanel.add(panel, BorderLayout.CENTER);
        add(topPanel);
    }

    private ImageIcon resizeIcon(String iconPath, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(iconPath));
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
