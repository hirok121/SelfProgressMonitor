import java.sql.*;

public class User {
    private String username;
    private int roll;
    private String passwaord;
    private String email;
    private String dept;
    private String phone;
    private String bloodGroup;

    public User(String username, String password) {
        this.username = username;
//        this.roll = -1;
        this.passwaord = password;
        this.phone = "123-456-7890";
        this.bloodGroup = "O+";
    }

    public void updateinfo() {
        // update the user info from the database
        Conn conn = new Conn();
        String query = "SELECT * FROM signUp WHERE username = '" + username + "'";
        try (Connection connection = conn.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                this.roll = resultSet.getInt("roll");
                this.email = resultSet.getString("email");
                this.dept = resultSet.getString("dept");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getDept() {
        return dept;
    }

    public String getPhone() {
        return phone;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getPassword() {
        return passwaord;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", role='" + roll + '\'' +
                '}';
    }
}
