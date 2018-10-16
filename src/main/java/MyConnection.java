import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnection {
    public static void startConnection() {
        String url = "jdbc:postgresql://localhost/test1";
        Properties properties = new Properties();
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "Qwer123");

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("connection successfully!");
        }
    }
}
