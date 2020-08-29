import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        try (var connection = DriverManager.getConnection(
                 "jdbc:mysql://db/test_database?user=root&password=1234")) {
            if (connection.isValid(5)) {
                try (var statement = connection.createStatement()) {
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS data (" +
                                            "id INT PRIMARY KEY AUTO_INCREMENT," +
                                            "string VARCHAR(60) NOT NULL);");
                    for (int i = 0; i < 10; i++) {
                        var randomString =
                                 String.valueOf((int) (Math.random() * 10000000));
                        statement.executeUpdate(
                                 "INSERT INTO data (string) VALUES " + "('" +
                                 randomString + "');");

                    }
                    try (var resultData = statement.executeQuery("SELECT * FROM data")) {
                        while (resultData.next()) {
                            // Retrieve column values
                            var id = resultData.getInt("id");
                            var string = resultData.getString("string");

                            System.out.printf("%d. %s%n", id, string);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
