import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class E2UpdateCoursePage extends JFrame {

    private JComboBox<String> numberOfCoursesComboBox;
    private JPanel courseDetailsPanel;
    private JButton saveButton;
    private JButton previousButton;
    private JButton homeButton;

    public E2UpdateCoursePage() {
        setTitle("Update Courses");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        StyledComponents.setFixedSizeAndShow(this);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel();
        JLabel headerLabel = new JLabel("Update Courses");
        StyledComponents.applyHighlightedBiggerFontStyle(headerLabel);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(headerLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.setBackground(Color.WHITE);

        JLabel numberOfCoursesLabel = new JLabel("Number of Courses:");
        StyledComponents.applyLabelStyle(numberOfCoursesLabel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(numberOfCoursesLabel, gbc);

        numberOfCoursesComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5","6", "7", "8", "9", "10"});
        // Default styling for JComboBox
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 0, 0);
        centerPanel.add(numberOfCoursesComboBox, gbc);

        courseDetailsPanel = new JPanel(new GridBagLayout());
        courseDetailsPanel.setBackground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        centerPanel.add(courseDetailsPanel, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Save");
        StyledComponents.applyButtonStyle(saveButton, StyledComponents.myGreenButton());
        buttonPanel.add(saveButton);

        previousButton = new JButton("Previous");
        StyledComponents.applyButtonStyle(previousButton, StyledComponents.myBlueButton());
        buttonPanel.add(previousButton);

        homeButton = new JButton("Home");
        StyledComponents.applyButtonStyle(homeButton, StyledComponents.myBlackMamba());
        buttonPanel.add(homeButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners
        numberOfCoursesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCourseDetailsPanel();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCourses();
            }
        });

        previousButton.addActionListener(e -> {
            new E0UpdateInfoPage().setVisible(true);
            dispose();
        });

        homeButton.addActionListener(e -> {
            new B0DashboardPage().setVisible(true);
            dispose();
        });

        updateCourseDetailsPanel(); // Initialize the course details panel with default number of courses

        getContentPane().add(mainPanel);
    }

    private void updateCourseDetailsPanel() {
        courseDetailsPanel.removeAll();
        courseDetailsPanel.revalidate();
        courseDetailsPanel.repaint();

        int numberOfCourses = Integer.parseInt((String) numberOfCoursesComboBox.getSelectedItem());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        for (int i = 0; i < numberOfCourses; i++) {
            JLabel courseLabel = new JLabel("Course " + (i + 1));
            StyledComponents.applyLabelStyle(courseLabel);
            gbc.gridx = 0;
            courseDetailsPanel.add(courseLabel, gbc);

            JTextField courseCodeField = new JTextField(20);
            StyledComponents.applyTextFieldStyle(courseCodeField);
            gbc.gridx = 1;
            courseDetailsPanel.add(courseCodeField, gbc);

            gbc.gridy++;
        }
    }

    private void saveCourses() {
        int numberOfCourses = Integer.parseInt((String) numberOfCoursesComboBox.getSelectedItem());

        String[] courses = new String[numberOfCourses];

        for (int i = 0; i < numberOfCourses; i++) {
            Component[] components = courseDetailsPanel.getComponents();
            JTextField courseCodeField = (JTextField) components[i * 2 + 1];

            String courseCode = courseCodeField.getText();
            courses[i] = courseCode;
        }
        User user = SessionManager.getInstance().getCurrentUser();
        Conn conn = new Conn();
        conn.registerCourse(user.getUsername(), courses);

        JOptionPane.showMessageDialog(this, "Courses saved successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(E2UpdateCoursePage::new);
    }
}
