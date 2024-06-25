import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class E3UpdateRoutinePage extends JFrame {

    private JComboBox<String> dayComboBox;
    private JPanel coursePanel;
    private JComboBox<String> numCoursesSelect;

    public E3UpdateRoutinePage() {
        setTitle("Update Routine");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        StyledComponents.setFixedSizeAndShow(this);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel headerLabel = new JLabel("Update Routine");
        StyledComponents.applyHighlightedBiggerFontStyle(headerLabel);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.setBackground(Color.WHITE);

        JLabel dayLabel = new JLabel("Day:");
        StyledComponents.applyLabelStyle(dayLabel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(dayLabel, gbc);

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayComboBox = new JComboBox<>(days);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 20, 0); // Added extra gap between day and course line
        centerPanel.add(dayComboBox, gbc);

        JLabel numCoursesLabel = new JLabel("Number of Courses:");
        StyledComponents.applyLabelStyle(numCoursesLabel);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0); // Reset the insets
        centerPanel.add(numCoursesLabel, gbc);

        String[] courseNumbers = {"0", "1", "2", "3", "4", "5", "6"};
        numCoursesSelect = new JComboBox<>(courseNumbers);
        numCoursesSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateCourseDropdowns(Integer.parseInt((String) numCoursesSelect.getSelectedItem()));
            }
        });
        gbc.gridx = 1;
        centerPanel.add(numCoursesSelect, gbc);

        coursePanel = new JPanel(new GridBagLayout()); // Dynamic panel for courses
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        centerPanel.add(coursePanel, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        StyledComponents.applyButtonStyle(saveButton, StyledComponents.myGreenButton());
        buttonPanel.add(saveButton);

        JButton previousButton = new JButton("Previous");
        StyledComponents.applyButtonStyle(previousButton, StyledComponents.myBlueButton());
        buttonPanel.add(previousButton);

        JButton homeButton = new JButton("Home");
        StyledComponents.applyButtonStyle(homeButton, StyledComponents.myBlackMamba());
        buttonPanel.add(homeButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRoutine();
            }
        });

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new E0UpdateInfoPage().setVisible(true);
                dispose();
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToDashboard();
            }
        });

        getContentPane().add(mainPanel);

        setSize(600, 400); // Adjust the size as needed
        setLocationRelativeTo(null);
    }

    private void generateCourseDropdowns(int numCourses) {
        coursePanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10);

        for (int i = 1; i <= numCourses; i++) {
            JLabel courseLabel = new JLabel("Course " + i + ":");
            StyledComponents.applyLabelStyle(courseLabel);
            gbc.gridy = (i - 1) * 2;
            coursePanel.add(courseLabel, gbc);

            Conn conn = new Conn();
            String username=SessionManager.getInstance().getCurrentUser().getUsername();

            String querygetdata ="SELECT * FROM courses WHERE username = '" + username + "'";
            String[] courses = new String[10];
        try (Connection connection = conn.getConnection();
             PreparedStatement pstmtgetdata = connection.prepareStatement(querygetdata)) {

            try (ResultSet resultSet = pstmtgetdata.executeQuery()) {
                if (resultSet.next()) {

                    for (int j = 0; j < 10; j++) {
                        courses[j] = resultSet.getString("course" + (j + 1));
                        System.out.println(courses[j]);
                    }

                } else {
                    System.out.println("No entry found with primary key: " + username);
                }
            }
        }catch (SQLException e) {
                System.out.println("Error setting daily progress.");
                e.printStackTrace();
            }


            JComboBox<String> courseDropdown = new JComboBox<>(courses); // Replace with your options
            courseDropdown.setPreferredSize(new Dimension(250, 25));
            gbc.gridy++;
            coursePanel.add(courseDropdown, gbc);
        }

        coursePanel.revalidate();
        coursePanel.repaint();
    }

    private void saveRoutine() {
        String day = (String) dayComboBox.getSelectedItem();
        StringBuilder message = new StringBuilder("Routine saved successfully for " + day + "\n");


        ArrayList<String> arrayList = new ArrayList<>();
        String username=SessionManager.getInstance().getCurrentUser().getUsername();

        // Retrieve course selections
        for (Component component : coursePanel.getComponents()) {
            if (component instanceof JComboBox) {
                JComboBox<String> comboBox = (JComboBox<String>) component;
                String selection = (String) comboBox.getSelectedItem();
                arrayList.add(selection);
                message.append(comboBox.getParent().getComponentZOrder(comboBox) / 2 + 1).append(": ").append(selection).append("\n");
            }
        }

        String[] courses=arrayList.toArray(new String[arrayList.size()]);

        Conn conn = new Conn();
        conn.setRutine(username,day,courses);

        JOptionPane.showMessageDialog(this, message.toString());
    }

    private void navigateToDashboard() {
        new B0DashboardPage().setVisible(true);
        dispose(); // Close the current window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(E3UpdateRoutinePage::new);
    }
}
