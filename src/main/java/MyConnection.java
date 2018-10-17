import java.sql.*;
import java.util.Properties;

public class MyConnection {
    public static final MyConnection INSTANCE = new MyConnection();
    private Connection connection = null;

    private MyConnection() {
    }

    private void startConnection() {
        String url = "jdbc:postgresql://localhost/test1";
        Properties properties = new Properties();
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "Qwer123");

        try {
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllFIO() {
        if (connection == null) {
            startConnection();
        }

        System.out.println("All people:");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllFIOAndPhone() {
        if (connection == null) {
            startConnection();
        }

        System.out.println("All people and phone:");


        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM person " +
                    "LEFT OUTER JOIN address ON person.address_id = address.address_id";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
