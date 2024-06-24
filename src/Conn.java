import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conn {
    private Connection c;

    public Conn() {
        String db = "academic_progress_tracker";
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "114084";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(url + db, user, password);
            System.out.println("Connected to database.");
        } catch (Exception e) {
            System.out.println("Error connecting to database.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return c;
    }

    public void saveUserInfo(String username, String roll, String password) {
        String query = "INSERT INTO A2SignUp (username, roll, password) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, roll);
            pstmt.setString(3, password);

            System.out.println("Executing SQL Query:");
            System.out.println(pstmt.toString());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("User information saved successfully.");
            } else {
                System.out.println("User information not saved.");
            }
        } catch (SQLException e) {
            System.out.println("Error saving user information.");
            e.printStackTrace();
        }
    }
}
