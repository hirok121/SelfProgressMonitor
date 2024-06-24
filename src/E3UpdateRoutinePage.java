import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class E3UpdateRoutinePage extends JFrame {

    private JComboBox<String> dayComboBox;
    private JPanel classPanel;
    private JPanel labPanel;
    private JComboBox<String> numClassesSelect;
    private JComboBox<String> numLabsSelect;

    public E3UpdateRoutinePage() {
        setTitle("Update Routine");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        // setupLayout();
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
        gbc.insets = new Insets(0, 10, 0, 0);
        centerPanel.add(dayComboBox, gbc);

        JLabel numClassesLabel = new JLabel("Number of Classes:");
        StyledComponents.applyLabelStyle(numClassesLabel);
        gbc.gridx = 0;
        gbc.gridy++;
        centerPanel.add(numClassesLabel, gbc);

        String[] classNumbers = {"0", "1", "2", "3", "4", "5"};
        numClassesSelect = new JComboBox<>(classNumbers);
        numClassesSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateClassDropdowns(Integer.parseInt((String) numClassesSelect.getSelectedItem()));
            }
        });
        gbc.gridx = 1;
        centerPanel.add(numClassesSelect, gbc);

        classPanel = new JPanel(new GridBagLayout()); // Dynamic panel for classes
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        centerPanel.add(classPanel, gbc);

        JLabel numLabsLabel = new JLabel("Number of Labs:");
        StyledComponents.applyLabelStyle(numLabsLabel);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        centerPanel.add(numLabsLabel, gbc);

        String[] labNumbers = {"0", "1", "2", "3", "4", "5"};
        numLabsSelect = new JComboBox<>(labNumbers);
        numLabsSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateLabDropdowns(Integer.parseInt((String) numLabsSelect.getSelectedItem()));
            }
        });
        gbc.gridx = 1;
        centerPanel.add(numLabsSelect, gbc);

        labPanel = new JPanel(new GridBagLayout()); // Dynamic panel for labs
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        centerPanel.add(labPanel, gbc);

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

        // Set a fixed size for the frame
        setSize(600, 400); // Adjust the size as needed

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    private void generateClassDropdowns(int numClasses) {
        classPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10);

        for (int i = 1; i <= numClasses; i++) {
            JLabel classLabel = new JLabel("Class " + i + ":");
            StyledComponents.applyLabelStyle(classLabel);
            gbc.gridy = i - 1;
            classPanel.add(classLabel, gbc);

            JComboBox<String> classDropdown = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"}); // Replace with your options
            classPanel.add(classDropdown, gbc);
            gbc.gridy++;
        }

        classPanel.revalidate();
        classPanel.repaint();
    }

    private void generateLabDropdowns(int numLabs) {
        labPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10);

        for (int i = 1; i <= numLabs; i++) {
            JLabel labLabel = new JLabel("Lab " + i + ":");
            StyledComponents.applyLabelStyle(labLabel);
            gbc.gridy = i - 1;
            labPanel.add(labLabel, gbc);

            JComboBox<String> labDropdown = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"}); // Replace with your options
            labPanel.add(labDropdown, gbc);
            gbc.gridy++;
        }

        labPanel.revalidate();
        labPanel.repaint();
    }

    private void saveRoutine() {
        String day = (String) dayComboBox.getSelectedItem();
        StringBuilder message = new StringBuilder("Routine saved successfully for " + day + "\n");

        // Retrieve class selections
        for (Component component : classPanel.getComponents()) {
            if (component instanceof JComboBox) {
                JComboBox<String> comboBox = (JComboBox<String>) component;
                String selection = (String) comboBox.getSelectedItem();
                message.append(comboBox.getParent().getComponentZOrder(comboBox) / 2 + 1).append(": ").append(selection).append("\n");
            }
        }

        // Retrieve lab selections
        for (Component component : labPanel.getComponents()) {
            if (component instanceof JComboBox) {
                JComboBox<String> comboBox = (JComboBox<String>) component;
                String selection = (String) comboBox.getSelectedItem();
                message.append(comboBox.getParent().getComponentZOrder(comboBox) / 2 + 1).append(": ").append(selection).append("\n");
            }
        }

        JOptionPane.showMessageDialog(this, message.toString());
    }

    private void navigateToPreviousPage() {
        // Implement navigation to the previous page
        dispose(); // Close the current window
    }

    private void navigateToDashboard() {
        // Implement navigation to the dashboard page
        new B0DashboardPage().setVisible(true);
        dispose(); // Close the current window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(E3UpdateRoutinePage::new);
    }
}
