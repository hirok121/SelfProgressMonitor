
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {

    static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    static final String USER = "your_username";
    static final String PASS = "your_password";

    public static void main(String[] args) {
        int primaryKey = 1; // Replace with the actual primary key value
        getDataByPrimaryKey(primaryKey);
    }

    public static void getDataByPrimaryKey(int primaryKey) {
        String query = "SELECT * FROM courses WHERE id = ?";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, primaryKey);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Assuming the table has 11 columns
                    int id = resultSet.getInt("id");
                    String col1 = resultSet.getString("column1");
                    String col2 = resultSet.getString("column2");
                    String col3 = resultSet.getString("column3");
                    String col4 = resultSet.getString("column4");
                    String col5 = resultSet.getString("column5");
                    String col6 = resultSet.getString("column6");
                    String col7 = resultSet.getString("column7");
                    String col8 = resultSet.getString("column8");
                    String col9 = resultSet.getString("column9");
                    String col10 = resultSet.getString("column10");
                    String col11 = resultSet.getString("column11");

                    System.out.println("ID: " + id);
                    System.out.println("Column1: " + col1);
                    System.out.println("Column2: " + col2);
                    System.out.println("Column3: " + col3);
                    System.out.println("Column4: " + col4);
                    System.out.println("Column5: " + col5);
                    System.out.println("Column6: " + col6);
                    System.out.println("Column7: " + col7);
                    System.out.println("Column8: " + col8);
                    System.out.println("Column9: " + col9);
                    System.out.println("Column10: " + col10);
                    System.out.println("Column11: " + col11);
                } else {
                    System.out.println("No entry found with primary key: " + primaryKey);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving data.");
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
