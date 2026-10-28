import java.sql.*;

public class db2 {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Change if needed
        String username = "system"; // Your Oracle username
        String password = "manager"; // Your Oracle password

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to the database
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Oracle Database Successfully!\n");
            String insert = "INSERT INTO users (username, email) VALUES (?, ?)";
            // Create SQL statement
            stmt = conn.prepareStatement(insert);

            // Set the values for the placeholders
            stmt.setString(1, "John Doe");
            stmt.setString(2, "john.doe@example.com");

            // Execute the insert statement
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");

            // Execute query
            String query = "SELECT * FROM users";
            rs = stmt.executeQuery(query);

            // Display the results
            System.out.println("ID\tusername\temail");
            System.out.println("-----------------");

            while (rs.next()) {
                int eno = rs.getInt("id");
                String name = rs.getString("username");
                String email = rs.getString("email");
                System.out.println(eno + "\t" + name + "\t" + email);
            }
            /*
             * String query1 = "update emp set ename='krishna' where eno=2";
             * int n= stmt.executeUpdate(query1);
             * String query2 = "delete from emp where eno=1";
             * int n1= stmt.executeUpdate(query2);
             */

        } catch (ClassNotFoundException e) {
            System.out.println(" Oracle JDBC Driver not found! Add ojdbc8.jar to your classpath.");
        } catch (SQLException e) {
            System.out.println(" Database error!");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
                System.out.println("\n🔒 Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
