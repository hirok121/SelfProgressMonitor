import java.awt.*;
import javax.swing.*;

public class F0MyProfilePage extends JFrame {

  public F0MyProfilePage() {
    setTitle("Academic Progress Tracker");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    initComponents();
    setSizeAndShow();
  }

  private void initComponents() {
    JPanel container = new JPanel(new BorderLayout());
    container.setBackground(Color.WHITE);

    JLabel titleLabel = new JLabel("My Profile");
    StyledComponents.applyHighlightedBiggerFontStyle(titleLabel);
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    container.add(titleLabel, BorderLayout.NORTH);

    JPanel profilePanel = new JPanel(new GridLayout(3, 1));
    profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    profilePanel.setBackground(Color.WHITE);

    JPanel personalInfoPanel = new JPanel(new GridLayout(5, 1));
    personalInfoPanel.setBorder(
      BorderFactory.createTitledBorder("Personal Information")
    );
    personalInfoPanel.setBackground(Color.WHITE);

    String[] personalInfoLabels = {
      "Name: Isteak Ahmed Shajal",
      "Department: CSE",
      "Roll: 2103138",
      "Email: isteak.0023@gmail.com",
      "Phone: 123-456-7890",
      "Blood Group: O+",
    };
    addInfoLabels(personalInfoPanel, personalInfoLabels);

    profilePanel.add(personalInfoPanel);

    JPanel academicInfoPanel = new JPanel(new GridLayout(4, 1));
    academicInfoPanel.setBorder(
      BorderFactory.createTitledBorder("Academic Information")
    );
    academicInfoPanel.setBackground(Color.WHITE);

    String[] academicInfoLabels = {
      "Current Semester: 2-1",
      "Courses: CSE 2103 DLD",
      "Routine: Monday: Math 101 (9:00 AM - 10:30 AM), Physics 102 (11:00 AM - 12:30 PM)",
    };
    addInfoLabels(academicInfoPanel, academicInfoLabels);

    profilePanel.add(academicInfoPanel);

    container.add(profilePanel, BorderLayout.CENTER);

    // Positioning the Home button with E2UpdateCoursePage.java style
    JButton homeButton = new JButton("Home");
    StyledComponents.applyButtonStyle(
      homeButton,
      StyledComponents.myBlackMamba()
    );
    homeButton.addActionListener(e -> {
      // Add logic to redirect to the dashboard or home page
      // For example:
      new B0DashboardPage().setVisible(true);
      dispose();
    });
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(homeButton);
    container.add(buttonPanel, BorderLayout.SOUTH);

    getContentPane().add(container);
  }

  private void setSizeAndShow() {
    StyledComponents.setFixedSizeAndShow(this);
  }

  private void addInfoLabels(JPanel panel, String[] labels) {
    for (String label : labels) {
      JLabel infoLabel = new JLabel(label);
      StyledComponents.applyLabelStyle(infoLabel);
      panel.add(infoLabel);
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(F0MyProfilePage::new);
  }
}
