import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentManagementJDBC {
    // Set these environment variables before running the program.
    private static final String URL = System.getenv("UPS_DB_URL");
    private static final String USER = System.getenv("UPS_DB_USER");
    private static final String PASSWORD = System.getenv("UPS_DB_PASSWORD");

    public static void main(String[] args) {
        if (URL == null || USER == null || PASSWORD == null) {
            System.out.println("Set UPS_DB_URL, UPS_DB_USER and UPS_DB_PASSWORD first.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n1. Add student  2. List students  3. Exit");
                System.out.print("Choose: ");
                String choice = scanner.nextLine();

                if ("1".equals(choice)) {
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Department: ");
                    String department = scanner.nextLine();

                    String sql = "INSERT INTO students(name, email, department) VALUES (?, ?, ?)";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, name);
                        statement.setString(2, email);
                        statement.setString(3, department);
                        System.out.println(statement.executeUpdate() == 1
                                ? "Student added." : "No student added.");
                    }
                } else if ("2".equals(choice)) {
                    String sql = "SELECT id, name, email, department FROM students ORDER BY id";
                    try (PreparedStatement statement = connection.prepareStatement(sql);
                         ResultSet result = statement.executeQuery()) {
                        while (result.next()) {
                            System.out.printf("%d | %s | %s | %s%n",
                                    result.getInt("id"), result.getString("name"),
                                    result.getString("email"), result.getString("department"));
                        }
                    }
                } else if ("3".equals(choice)) {
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException exception) {
            System.out.println("Database operation failed: " + exception.getMessage());
        }
    }
}
