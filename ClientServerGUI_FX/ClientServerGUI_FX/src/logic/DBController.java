package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBController {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/libraryDB?serverTimezone=IST&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "AhmadTawil?!1";

    /**
     * Establish a connection to the database.
     *
     * @return Connection object or null if connection fails.
     */
    public Connection connectToDB() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("Driver definition succeeded.");

            // Establish connection
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception ex) {
            System.out.println("Error establishing connection: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        Connection connection = connectToDB();

        if (connection == null) {
            System.out.println("Failed to connect to the database.");
            return students;
        }

        String query = "SELECT * FROM Subscriber";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("subscriber_id"); // Get ID as an int
                String name = rs.getString("subscriber_name");
                int history = rs.getInt("detailed_subscription_history"); // Get history as an int
                String number = rs.getString("subscriber_phone_number");
                String email = rs.getString("subscriber_email");

                // Create a Student object and add it to the list
                Student student = new Student(id, name, history, number, email);
                students.add(student);
            }
        } catch (Exception e) {
            System.out.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }

    
    public boolean updateStudent(int id, String name, int history, String phone, String email) {
        Connection connection = connectToDB();
        if (connection == null) {
            System.out.println("Failed to connect to the database.");
            return false;
        }

        String query = "UPDATE Subscriber SET subscriber_name = ?, detailed_subscription_history = ?, " +
                       "subscriber_phone_number = ?, subscriber_email = ? WHERE subscriber_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            // Set parameters for the query
            pstmt.setString(1, name);
            pstmt.setInt(2, history);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            pstmt.setInt(5, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was updated
        } catch (SQLException ex) {
            System.out.println("Error updating student: " + ex.getMessage());
            return false;
        }
    }
}
