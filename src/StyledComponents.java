import javax.swing.*;
import java.awt.*;

public class StyledComponents {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 700;

    public static void applyButtonStyle(JButton button) {
        applyButtonStyle(button, myGreenButton());
    }

    public static void setFixedSizeAndShow(JFrame frame) {
        setFixedSize(frame);
        centerFrame(frame);
        frame.setVisible(true);
    }

    public static void setFixedSize(JFrame frame) {
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        frame.setResizable(true);
    }

    public static void applyButtonStyle(JButton button, Color color) {
        Font font = new Font("Arial", Font.PLAIN, 17);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
    }

    public static void applyTextFieldStyle(JTextField textField) {
        Font font = new Font("Arial", Font.PLAIN, 17);
        textField.setFont(font);
    }

    public static void applyLabelStyle(JLabel label) {
        Font font = new Font("Arial", Font.BOLD, 18);
        label.setFont(font);
    }

    public static Color myGreenButton() {
        return new Color(4, 170, 109);
    }

    public static Color myBlueButton() {
        return new Color(70, 130, 180);
    }

    public static Color myBlackMamba() {
        return Color.gray;
    }

    public static void applyHighlightedBiggerFontStyle(JLabel label) {
        Font font = new Font("Arial", Font.BOLD, 24);
        label.setFont(font);
        label.setForeground(Color.black);
    }

    private static void centerFrame(JFrame frame) {
        frame.setLocationRelativeTo(null);
    }
}
