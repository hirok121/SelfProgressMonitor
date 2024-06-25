import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import java.sql.*;
public class C0DailyProgressPage extends JFrame {

    // Assuming these are your course names fetched dynamically
    private String[] courseNames = {"Mathematics", "Physics", "Chemistry"};

    private JComboBox<String>[] courseAttendanceSelectors;
    private JComboBox<String> numActivitiesSelect;
    private JPanel extraActivityOptionsPanel;
    private JButton submitButton;
    private JButton homeButton;
    private JSpinner dateSpinner;
    private JLabel dayOfWeekLabel;
    private JButton prevDayButton;
    private JButton nextDayButton;

    public C0DailyProgressPage() {
        setTitle("Academic Progress Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setupLayout();
        updateDayOfWeek(); // Initialize the day of the week display
        StyledComponents.setFixedSizeAndShow(this); // Commented out for compiling purposes
    }

    private void initComponents() {
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(
                dateSpinner,
                "MM/dd/yyyy"
        );
        dateSpinner.setEditor(dateEditor);

        dateSpinner.addChangeListener(e -> updateDayOfWeek());

        prevDayButton = new JButton("<");
        nextDayButton = new JButton(">");

        prevDayButton.addActionListener(e -> updateDate(-1));

        nextDayButton.addActionListener(e -> updateDate(1));

        // Initialize course attendance selectors dynamically
        courseAttendanceSelectors = new JComboBox[courseNames.length];
        for (int i = 0; i < courseNames.length; i++) {
            courseAttendanceSelectors[i] = new JComboBox<>(new String[]{"Present", "Absent", "Postponed"});
        }

        numActivitiesSelect = new JComboBox<>(new String[]{"0", "1", "2", "3", "4", "5"});
        numActivitiesSelect.addActionListener(e -> generateExtraActivityOptions(
                Integer.parseInt((String) numActivitiesSelect.getSelectedItem())
        ));

        extraActivityOptionsPanel = new JPanel();
        generateExtraActivityOptions(0); // Generate initial extra activity options

        submitButton = new JButton("Submit");
        StyledComponents.applyButtonStyle(submitButton); // Apply button style

        homeButton = new JButton("Home");
        StyledComponents.applyButtonStyle(
        homeButton,
        StyledComponents.myBlackMamba()
        ); // Apply button style with custom color

        homeButton.addActionListener(e -> navigateToDashboard()); // Navigate to dashboard page

        dayOfWeekLabel = new JLabel();
        //StyledComponents.applyLabelStyle(dayOfWeekLabel); // Apply label style for dayOfWeekLabel
        dayOfWeekLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dayOfWeekLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER; // Center align components
        gbc.insets = new Insets(5, 5, 5, 5); // Adjust insets for consistent spacing

        JLabel headerLabel = new JLabel("Daily Progress");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);

        panel.add(headerLabel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_START; // Align components to the left

        JLabel dateLabel = new JLabel("Date:");
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(prevDayButton);
        datePanel.add(dateSpinner);
        datePanel.add(nextDayButton);

        panel.add(dateLabel, gbc);
        gbc.gridx++;
        panel.add(datePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Day of the Week:"), gbc);
        gbc.gridx++;
        panel.add(dayOfWeekLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        for (int i = 0; i < courseNames.length; i++) {
            panel.add(new JLabel(courseNames[i] + ":"), gbc);
            gbc.gridx++;
            panel.add(courseAttendanceSelectors[i], gbc);
            gbc.gridx = 0;
            gbc.gridy++;
        }

        panel.add(new JLabel("Number of Extra Activities:"), gbc);
        gbc.gridx++;
        panel.add(numActivitiesSelect, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(extraActivityOptionsPanel, gbc);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel innerButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        innerButtonPanel.add(submitButton);
        innerButtonPanel.add(homeButton);
        buttonPanel.add(innerButtonPanel, BorderLayout.LINE_END);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        panel.add(buttonPanel, gbc);

        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void generateExtraActivityOptions(int numActivities) {
        extraActivityOptionsPanel.removeAll();
        extraActivityOptionsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        for (int i = 1; i <= numActivities; i++) {
            final int activityIndex = i; // Capture the current index

            JLabel activityLabel = new JLabel("Activity " + i + ":");
            JComboBox<String> extraCourseSelect = new JComboBox<>(
                    new String[]{"Course a", "Course b", "Course c", "Course d", "Course e"}
            );
            JComboBox<String> activitySelect = new JComboBox<>(
                    new String[]{"Extra Class", "Assignment", "CT", "Quiz"}
            );

            JPanel activityPanel = new JPanel(new GridBagLayout());
            GridBagConstraints apGbc = new GridBagConstraints();
            apGbc.insets = new Insets(5, 5, 5, 5);
            apGbc.anchor = GridBagConstraints.WEST;
            apGbc.gridx = 0;
            apGbc.gridy = 0;
            activityPanel.add(activityLabel, apGbc);

            apGbc.gridx++;
            activityPanel.add(extraCourseSelect, apGbc);

            apGbc.gridx++;
            activityPanel.add(activitySelect, apGbc);

            JLabel ctNoLabel = new JLabel("CT No:");
            JComboBox<String> ctSelect = new JComboBox<>(new String[]{"1", "2", "3", "4"});
            apGbc.gridx++;
            activityPanel.add(ctNoLabel, apGbc);
            apGbc.gridx++;
            activityPanel.add(ctSelect, apGbc);
            ctNoLabel.setVisible(false);
            ctSelect.setVisible(false);

            JLabel markLabel = new JLabel("Mark:");
            JTextField markField = new JTextField(10);
            StyledComponents.applyTextFieldStyle(markField);
            apGbc.gridx++;
            activityPanel.add(markLabel, apGbc);
            apGbc.gridx++;
            activityPanel.add(markField, apGbc);
            markLabel.setVisible(false);
            markField.setVisible(false);

            activitySelect.addActionListener(e -> {
                String selectedActivity = (String) activitySelect.getSelectedItem();
                ctNoLabel.setVisible(false);
                ctSelect.setVisible(false);
                markLabel.setVisible(false);
                markField.setVisible(false);
                if (selectedActivity.equals("CT")) {
                    ctNoLabel.setVisible(true);
                    ctSelect.setVisible(true);
                    markLabel.setVisible(true);
                    markField.setVisible(true);
                } else if (selectedActivity.equals("Assignment") || selectedActivity.equals("Quiz")) {
                    markLabel.setVisible(true);
                    markField.setVisible(true);
                }
                activityPanel.revalidate();
                activityPanel.repaint();
            });

            gbc.gridy = i - 1;
            extraActivityOptionsPanel.add(activityPanel, gbc);
        }

        extraActivityOptionsPanel.revalidate();
        extraActivityOptionsPanel.repaint();
    }

    private void updateDate(int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date) dateSpinner.getValue());
        cal.add(Calendar.DAY_OF_MONTH, amount);
        dateSpinner.setValue(cal.getTime());
        updateDayOfWeek();
    }

    private void updateDayOfWeek() {
        Date date = (Date) dateSpinner.getValue();
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        String dayOfWeek = dayFormat.format(date);
        dayOfWeekLabel.setText(dayOfWeek);
    }

    private void navigateToDashboard() {
        // Implement navigation to the dashboard page
        new B0DashboardPage().setVisible(true);
        dispose(); // Close the current window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(C0DailyProgressPage::new);
    }
}
