import javax.swing.*;
import java.awt.*;

public class D0ProgressSummaryPage extends JFrame {

    public D0ProgressSummaryPage() {
        setTitle("Progress Summary");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the frame

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea summaryTextArea = new JTextArea();
        summaryTextArea.setEditable(false);
        summaryTextArea.setLineWrap(true);
        summaryTextArea.setWrapStyleWord(true);
        summaryTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        summaryTextArea.setText("This is a dummy Progress Summary page.\n\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Praesent ac ex ac lectus malesuada luctus nec in orci. " +
                "Curabitur ultricies, nunc in fermentum venenatis, " +
                "sem quam fermentum lorem, et commodo leo arcu ac justo.");

        JScrollPane scrollPane = new JScrollPane(summaryTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(closeButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new D0ProgressSummaryPage());
    }
}
