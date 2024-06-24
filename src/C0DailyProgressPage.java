import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class C0DailyProgressPage extends JFrame {

    private JComboBox<String> course1Attendance;
    private JComboBox<String> course2Attendance;
    private JComboBox<String> numActivitiesSelect;
    private JPanel extraActivityOptionsPanel;
    private JButton submitButton;
    private JButton homeButton; // Added home button
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
        StyledComponents.setFixedSizeAndShow(this);
    }

    private void initComponents() {
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "MM/dd/yyyy");
        dateSpinner.setEditor(dateEditor);

        dateSpinner.addChangeListener(e -> updateDayOfWeek());

        prevDayButton = new JButton("<");
        nextDayButton = new JButton(">");

        prevDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDate(-1);
            }
        });

        nextDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDate(1);
            }
        });

        course1Attendance = new JComboBox<>(new String[]{"Present", "Bunk", "Postponed"});
        course2Attendance = new JComboBox<>(new String[]{"Present", "Bunk", "Postponed"});

        numActivitiesSelect = new JComboBox<>(new String[]{"0", "1", "2", "3", "4", "5"});
        numActivitiesSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateExtraActivityOptions(Integer.parseInt((String) numActivitiesSelect.getSelectedItem()));
            }
        });

        extraActivityOptionsPanel = new JPanel();
        generateExtraActivityOptions(0); // Generate initial extra activity options

        submitButton = new JButton("Submit");
        StyledComponents.applyButtonStyle(submitButton); // Apply button style

        homeButton = new JButton("Home"); // Create home button
        StyledComponents.applyButtonStyle(homeButton, StyledComponents.myBlackMamba()); // Apply button style with custom color

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToDashboard(); // Navigate to dashboard page
            }
        });

        dayOfWeekLabel = new JLabel();
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
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel headerLabel = new JLabel("Daily Progress");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Adjust y position to have 20px space from top
        gbc.anchor = GridBagConstraints.PAGE_START; // Aligns at the top of the area
        gbc.insets = new Insets(20, 10, 10, 10); // 20px top, 10px left, right, bottom
        panel.add(headerLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;

        JLabel dateLabel = new JLabel("Date:");
        JLabel course1Label = new JLabel("Course 1:");
        JLabel course2Label = new JLabel("Course 2:");
        JLabel numActivitiesLabel = new JLabel("Number of Extra Activities:");

        StyledComponents.applyLabelStyle(dateLabel); // Apply label style
        StyledComponents.applyLabelStyle(course1Label); // Apply label style
        StyledComponents.applyLabelStyle(course2Label); // Apply label style
        StyledComponents.applyLabelStyle(numActivitiesLabel); // Apply label style

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
        panel.add(course1Label, gbc);
        gbc.gridx++;
        panel.add(course1Attendance, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(course2Label, gbc);
        gbc.gridx++;
        panel.add(course2Attendance, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(numActivitiesLabel, gbc);
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
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING; // Align at the bottom-right corner
        panel.add(buttonPanel, gbc);

        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void generateExtraActivityOptions(int numActivities) {
        extraActivityOptionsPanel.removeAll();
        extraActivityOptionsPanel.setLayout(new GridLayout(numActivities, 2, 10, 10));

        for (int i = 1; i <= numActivities; i++) {
            JLabel activityLabel = new JLabel("Activity " + i + ":");
            JComboBox<String> activitySelect = new JComboBox<>(new String[]{"Assignment", "Quiz", "Extra Class"});

            extraActivityOptionsPanel.add(activityLabel);
            extraActivityOptionsPanel.add(activitySelect);
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
