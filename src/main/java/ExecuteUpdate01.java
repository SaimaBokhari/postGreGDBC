import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Step 1: Registration to  the Driver
        Class.forName("org.postgresql.Driver"); // we added this dependency in pom xml file

        // Step 2: Create the Connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Coding2022");

        // Step 3: Create Statement
        Statement st = con.createStatement();

        // Step 4: Execute the query








//        //  Step 5: Close the connection
//        con.close();
//        st.close();


    }
}
