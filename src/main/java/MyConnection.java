import java.sql.*;
import java.util.Properties;

public class MyConnection {
    public static final MyConnection INSTANCE = new MyConnection();
    private Connection connection = null;

    private MyConnection() {
    }

    public void startConnection() {
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

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void printAllFIO() {
        System.out.println();
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

    public void printAllFIOAndAddress() {
        System.out.println();
        System.out.println("All people and phone:");


        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM person " +
                    "LEFT OUTER JOIN address ON person.address_id = address.address_id";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.print(resultSet.getString("fio") + " - ");
                System.out.println(resultSet.getString("address_string"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printPhones() {
        System.out.println();
        String query = "SELECT * FROM person " +
                "WHERE fio = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);


            System.out.println("Ivanov's phone:");
            preparedStatement.setString(1, "Иванов Иван Иванович");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString("phone"));
            }

            System.out.println("Kiselve's phone:");
            preparedStatement.setString(1, "Киселев Максим Олегович");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString("phone"));
            }

            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPerson() {
        String fio = "Пупкин Василий Иванович";

        String query = "INSERT INTO person (person_id, fio) " +
                "VALUES (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, getMaxPersonId());
            statement.setString(2, fio);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getMaxPersonId() {
        return 3;
    }

    public void updatePhone() {
        String fio = "Иванов Иван Иванович";
        String newPhone = "+7111111111";

        String query = "UPDATE person " +
                "SET phone = ? " +
                "WHERE fio = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newPhone);
            statement.setString(2, fio);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePerson() {
        String fio = "Пупкин Василий Иванович";

        String query = "DELETE FROM person " +
                "WHERE fio = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, fio);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        String query = "DROP TABLE tablename";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
