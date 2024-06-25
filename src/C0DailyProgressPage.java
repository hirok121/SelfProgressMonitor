import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class C0DailyProgressPage extends JFrame {

    private JComboBox<String> course1Attendance;
    private JComboBox<String> course2Attendance;
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
        StyledComponents.applyLabelStyle(dayOfWeekLabel); // Apply label style for dayOfWeekLabel
        dayOfWeekLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dayOfWeekLabel.setHorizontalAlignment(SwingConstants.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitDailyProgress();
            }
        });
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new GridBagLayout()); //! keno use ??
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
        JLabel course1Label = new JLabel("Course 1:");
        JLabel course2Label = new JLabel("Course 2:");
        JLabel numActivitiesLabel = new JLabel("Number of Extra Activities:");
        JLabel dayOfWeekTitleLabel = new JLabel("Day of the Week:");

        StyledComponents.applyLabelStyle(dateLabel);
        StyledComponents.applyLabelStyle(course1Label);
        StyledComponents.applyLabelStyle(course2Label);
        StyledComponents.applyLabelStyle(numActivitiesLabel);
        StyledComponents.applyLabelStyle(dayOfWeekTitleLabel);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(prevDayButton);
        datePanel.add(dateSpinner);
        datePanel.add(nextDayButton);

        panel.add(dateLabel, gbc);
        gbc.gridx++;
        panel.add(datePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(dayOfWeekTitleLabel, gbc);
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
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        panel.add(buttonPanel, gbc);

        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void generateExtraActivityOptions(int numActivities) {
        extraActivityOptionsPanel.removeAll();
        extraActivityOptionsPanel.setLayout(new GridBagLayout()); //! keno use ??

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        for (int i = 1; i <= numActivities; i++) {
            JPanel activityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            // Activity number label
            JLabel activityLabel = new JLabel("Activity " + i + ":");
            activityPanel.add(activityLabel);

            // Course name dropdown
            JComboBox<String> courseSelect = new JComboBox<>(new String[]{"Course 1", "Course 2"});
            activityPanel.add(courseSelect);

            // Type of activity dropdown including "CT"
            JComboBox<String> activitySelect = new JComboBox<>(new String[]{"Assignment",  "Extra Class", "CT","Quiz"});
            activityPanel.add(activitySelect);

            // Marks input field (conditional based on activity type)
            JTextField marksField = new JTextField(10);
            if (activitySelect.getSelectedItem().equals("CT")) {
                // Create additional select boxes for CT (CT1, CT2, CT3, CT4)
                JComboBox<String> ctSelect = new JComboBox<>(new String[]{"CT1", "CT2", "CT3", "CT4"});
                activityPanel.add(ctSelect);
            } else {
                activityPanel.add(marksField);
            }

            extraActivityOptionsPanel.add(activityPanel, gbc);
            gbc.gridy++;
        }

        extraActivityOptionsPanel.revalidate();
        extraActivityOptionsPanel.repaint();
    }


    private void submitDailyProgress() {
        StringBuilder sb = new StringBuilder();
        sb.append("Date: ").append(dateSpinner.getValue()).append("\n");
        sb.append("Day of the Week: ").append(dayOfWeekLabel.getText()).append("\n");
        sb.append("Course 1 Attendance: ").append(course1Attendance.getSelectedItem()).append("\n");
        sb.append("Course 2 Attendance: ").append(course2Attendance.getSelectedItem()).append("\n");
        sb.append("Number of Extra Activities: ").append(numActivitiesSelect.getSelectedItem()).append("\n");

        for (Component comp : extraActivityOptionsPanel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel activityPanel = (JPanel) comp;
                JLabel activityLabel = (JLabel) activityPanel.getComponent(0);
                JComboBox<String> courseSelect = (JComboBox<String>) activityPanel.getComponent(1);
                JComboBox<String> activitySelect = (JComboBox<String>) activityPanel.getComponent(2);
                JTextField marksField = (JTextField) activityPanel.getComponent(3);

                sb.append(activityLabel.getText()).append(" ");
                sb.append(courseSelect.getSelectedItem()).append(" ");
                sb.append(activitySelect.getSelectedItem()).append(" ");
                sb.append(marksField != null ? marksField.getText() : "").append("\n");
            }
        }

        System.out.println(sb.toString());
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
        new B0DashboardPage().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(C0DailyProgressPage::new);
    }
}
