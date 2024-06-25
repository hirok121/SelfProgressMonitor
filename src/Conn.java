import java.sql.*;

import javax.swing.RowFilter.Entry;

public class Conn {
    private Connection c;

    public Conn() {
        String db = "academic_progress_tracker6";
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "babu3rdjulY";
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

    public void saveUserInfo(String username, String roll, String email, String dept, String password) {
        String query = "INSERT INTO signUp (username, roll,email,dept, password) VALUES (?, ?, ?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, roll);
            pstmt.setString(3, email);
            pstmt.setString(4, dept);
            pstmt.setString(5, password);

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

    public void updateUserInfo(String username, String roll, String email, String dept) {
        String query = "UPDATE signUp SET email = ?, dept = ?,roll = ? WHERE username = ? ";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, email);
            pstmt.setString(2, dept);
            pstmt.setString(4, username);
            pstmt.setString(3, roll);

            System.out.println("Executing SQL Query:");
            System.out.println(pstmt.toString());

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User information updated successfully.");
            } else {
                System.out.println("User information not updated.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating user information.");
            e.printStackTrace();
        }
    }

    public void changePassword(String username, String password) {
        String query = "UPDATE signUp SET password = ? WHERE username = ? ";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, password);
            pstmt.setString(2, username);

            System.out.println("Executing SQL Query:");
            System.out.println(pstmt.toString());

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User information updated successfully.");
            } else {
                System.out.println("User information not updated.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating user information.");
            e.printStackTrace();
        }
    }

    public boolean AuthUser(String username, String password) {
        // get user password from tthe table A2SignUp

        String query = "SELECT * FROM signUp WHERE username = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, username);

            System.out.println("Executing SQL Query:");
            System.out.println(pstmt.toString());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");
                return dbPassword.equals(password);
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving user information.");
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnection() {
        try {
            c.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            System.out.println("Error closing connection.");
            e.printStackTrace();
        }
    }

    public void registerCourse(String username, String[] courses) {
        // SQL queries
        String query1 = "CREATE TABLE IF NOT EXISTS courses (username VARCHAR(50) PRIMARY KEY, course1 VARCHAR(255), course2 VARCHAR(255), course3 VARCHAR(255), course4 VARCHAR(255), course5 VARCHAR(255), course6 VARCHAR(255), course7 VARCHAR(255), course8 VARCHAR(255), course9 VARCHAR(255), course10 VARCHAR(255))";
        String queryCheck = "SELECT COUNT(*) FROM courses WHERE username = ?";
        String queryDelete = "DELETE FROM courses WHERE username = ?";
        String queryInsert = "INSERT INTO courses (username, course1, course2, course3, course4, course5, course6, course7, course8, course9, course10) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt1 = connection.prepareStatement(query1);
             PreparedStatement pstmtCheck = connection.prepareStatement(queryCheck);
             PreparedStatement pstmtDelete = connection.prepareStatement(queryDelete);
             PreparedStatement pstmtInsert = connection.prepareStatement(queryInsert)) {

            // Create the table if it doesn't exist
            pstmt1.executeUpdate();

            // Check if the entry with the given Roll exists
            pstmtCheck.setString(1, username);
            try (ResultSet rs = pstmtCheck.executeQuery()) {
                rs.next();
                int count = rs.getInt(1);

                if (count > 0) {
                    // Entry exists, delete it
                    pstmtDelete.setString(1, username);
                    pstmtDelete.executeUpdate();
                    System.out.println("Existing entry deleted.");
                }
            }

            // Insert the new entry
            pstmtInsert.setString(1, username);
            for (int i = 0; i < courses.length; i++) {
                pstmtInsert.setString(i + 2, courses[i]);

            }
            // Fill remaining course columns with NULL if courses array is shorter
            for (int i = courses.length; i < 10; i++) {
                pstmtInsert.setString(i + 2, null);
            }

            System.out.println("Executing SQL Query:");
            System.out.println(pstmtInsert.toString());

            int rowsInserted = pstmtInsert.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Courses registered successfully.");
            } else {
                System.out.println("Courses not registered.");
            }
        } catch (SQLException e) {
            System.out.println("Error registering courses.");
            e.printStackTrace();
        }
    }

    public void backup_registerCourse(String username, String[] courses) {
        // create new table for Coursee set Roll as primary key
        String query1 = "CREATE TABLE IF NOT EXISTS courses (username VARCHAR(50),course1 VARCHAR(255),course2 VARCHAR(255),course3 VARCHAR(255),course4 VARCHAR(255),course5 VARCHAR(255),course6 VARCHAR(255),course7 VARCHAR(255),course8 VARCHAR(255),course9 VARCHAR(255),course10 VARCHAR(255) PRIMARY KEY(username))";
        String query2 = "INSERT INTO courses (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt1 = connection.prepareStatement(query1);
             PreparedStatement pstmt2 = connection.prepareStatement(query2)) {

            pstmt1.executeUpdate();

            pstmt2.setString(1, username);
            for (int i = 0; i < courses.length; i++) {
                pstmt2.setString(i + 2, courses[i]);
            }

            System.out.println("Executing SQL Query:");
            System.out.println(pstmt2.toString());

            int rowsInserted = pstmt2.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Courses registered successfully.");
            } else {
                System.out.println("Courses not registered.");
            }
        } catch (SQLException e) {
            System.out.println("Error registering courses.");
            e.printStackTrace();
        }
    }

    public void Backup_updateCourse(int Roll, String[] courses) {
        String query = "UPDATE courses SET course1 = ?, course2 = ?, course3 = ?, course4 = ?, course5 = ?, course6 = ?, course7 = ?, course8 = ?, course9 = ?, course10 = ? WHERE Userroll = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            for (int i = 0; i < courses.length; i++) {
                pstmt.setString(i + 1, courses[i]);
            }
            pstmt.setInt(11, Roll);

            System.out.println("Executing SQL Query:");
            System.out.println(pstmt.toString());

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Courses updated successfully.");
            } else {
                System.out.println("Courses not updated.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating courses.");
            e.printStackTrace();
        }
    }

    public void setRutine(String username, String day, String[] rutine) {

        System.out.println(day);
        for (int i = 0; i < rutine.length; i++) {
            System.out.println(rutine[i]);
        }
        // create new table for rutine
        // check if day already exists then delete it and insert new rutine
        String tableName = "rutine" + username;
        String query2 = "INSERT INTO " + tableName + " (day, course1, course2, course3, course4, course5, course6, course7, course8, course9, course10) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String query1 = "CREATE TABLE IF NOT EXISTS rutine" + username
                + " (day VARCHAR(50) PRIMARY KEY,course1 VARCHAR(255),course2 VARCHAR(255),course3 VARCHAR(255),course4 VARCHAR(255),course5 VARCHAR(255),course6 VARCHAR(255),course7 VARCHAR(255),course8 VARCHAR(255),course9 VARCHAR(255),course10 VARCHAR(255))";
//        String query2 = "INSERT INTO rutine" + username + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String queryCheck = "SELECT COUNT(*) FROM rutine" + username + " WHERE day = ?";
        String queryDelete = "DELETE FROM rutine" + username + " WHERE day = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt1 = connection.prepareStatement(query1);
             PreparedStatement pstmt2 = connection.prepareStatement(query2);
             PreparedStatement pstmtCheck = connection.prepareStatement(queryCheck);
             PreparedStatement pstmtDelete = connection.prepareStatement(queryDelete)) {

            pstmt1.executeUpdate();

            // Check if the entry with the given day exists
            pstmtCheck.setString(1, day);
            try (ResultSet rs = pstmtCheck.executeQuery()) {
                rs.next();
                int count = rs.getInt(1);

                if (count > 0) {
                    // Entry exists, delete it
                    pstmtDelete.setString(1, day);
                    pstmtDelete.executeUpdate();
                    System.out.println("Existing entry deleted.");
                }
            }

            pstmt2.setString(1, day);
            for (int i = 0; i < rutine.length; i++) {
                    pstmt2.setString(i + 2, rutine[i]);
            }
//             Fill remaining course columns with NULL if courses array is shorter
            for (int i = rutine.length; i < 10; i++) {
                pstmt2.setString(i + 2, null);
            }

            System.out.println("Executing SQL Query:");
            System.out.println(pstmt2.toString());

            int rowsInserted = pstmt2.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Rutine set successfully.");
            } else {
                System.out.println("Rutine not set.");
            }
        } catch (SQLException e) {
            System.out.println("Error setting rutine.");
            e.printStackTrace();
        }
    }

//    public void dailyProg(String username, String day,Entry< String,Integer>[] prog) {
//        // create new table for daily progress
//        // check if day already exists then delete it and insert new progress
//        // check the table exixts or not
//
//        String query1 = "CREATE TABLE IF NOT EXISTS dailyProg" + username
//                + " (course VARCHAR PRIMARY KEY,present INT,absent INT,ct1 INT,ct2 INT,ct3 INT,ct4 INT,assignment INT)";
//
//        String queryCheck = "SELECT COUNT(*) FROM dailyProg" + username + " WHERE day = ?";
//        String queryDelete = "DELETE FROM dailyProg" + username + " WHERE day = ?";
//        String querygetdata = "SELECT * FROM courses WHERE id = ?";
//
//        try (Connection connection = getConnection();
//             PreparedStatement pstmt1 = connection.prepareStatement(query1);
//             PreparedStatement pstmtCheck = connection.prepareStatement(queryCheck);
//             PreparedStatement pstmtDelete = connection.prepareStatement(queryDelete);
//             PreparedStatement pstmtgetdata = connection.prepareStatement(querygetdata)) {
//
//            pstmtgetdata.setString(1, username);
//            String[] courses=new String[10];
//            try (ResultSet resultSet = pstmtgetdata.executeQuery()) {
//                if (resultSet.next()) {
//                    // Assuming the table has 11 columns
//                    String usernamet = resultSet.getString(username);
//                    for (int i = 0; i < 10; i++) {
//                        courses[i] = resultSet.getString("course" + (i + 1));
//                    }
//
//                } else {
//                    System.out.println("No entry found with primary key: " + username);
//                }
//
//                // Check if the table exists
//                DatabaseMetaData metaData = connection.getMetaData();
//                ResultSet tables = metaData.getTables(null, null, "dailyProg" + username, null);
//                if (tables.next()) {
//                    // Table exists
////                String query2 =
//                    System.out.println("Table exists");
//                } else {
//                    // Table does not exist
//                    System.out.println("Table does not exist");
//                    pstmt1.executeUpdate();
//                    for(int i=0;i<10;i++){
//                        if(courses[i]!=null){
//                            String query3 = "INSERT INTO dailyProg" + username + " (course,present,absent,ct1,ct2,ct3,ct4,assignment) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//                            PreparedStatement pstmt3 = connection.prepareStatement(query3);
//                            pstmt3.setString(1, courses[i]);
//                            pstmt3.setInt(2, 0);
//                            pstmt3.setInt(3, 0);
//                            pstmt3.setInt(4, 0);
//                            pstmt3.setInt(5, 0);
//                            pstmt3.setInt(6, 0);
//                            pstmt3.setInt(7, 0);
//                            pstmt3.setInt(8, 0);
//
//                            pstmt3.executeUpdate();
//                        }
//                    }
//                }
//
//                // Check if the entry with the given day exists
//                pstmtCheck.setString(1, day);
//                try (ResultSet rs = pstmtCheck.executeQuery()) {
//                    rs.next();
//                    int count = rs.getInt(1);
//
//                    if (count > 0) {
//                        // Entry exists, delete it
//                        pstmtDelete.setString(1, day);
//                        pstmtDelete.executeUpdate();
//                        System.out.println("Existing entry deleted.");
//                    }
//                }
//
//                pstmt2.setString(1, day);
//                for (int i = 0; i < prog.length; i++) {
//                    pstmt2.setString(i + 2, prog[i]);
//                }
//
//                System.out.println("Executing SQL Query:");
//                System.out.println(pstmt2.toString());
//
//                int rowsInserted = pstmt2.executeUpdate();
//
//                if (rowsInserted > 0) {
//                    System.out.println("Daily progress set successfully.");
//                } else {
//                    System.out.println("Daily progress not set.");
//                }
//            } catch (SQLException e) {
//                System.out.println("Error setting daily progress.");
//                e.printStackTrace();
//            }
//        }
//
//    }
}