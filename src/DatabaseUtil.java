import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    // Ensure the URL includes the database name
    private static final String JDBC_URL = "jdbc:mysql://140.119.19.73:3315/112306079";
    private static final String JDBC_USERNAME = "112306079";
    private static final String JDBC_PASSWORD = "jkofu";

    

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
    }

    public static void main(String[] args) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            System.out.println("Connection successful!");
            // Perform database operations here

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }
}
