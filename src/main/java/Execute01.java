import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Class with Ms Sara

        /* We need to follow these 5 steps

        // Step 1: Registration to  the Driver
        // Step 2: Create the Connection with database
        // Step 3: Create Statement
        // Step 4: Execute the query
        // Step 5: Close the connection

         */



        // Step 1: Registration to  the Driver
        Class.forName("org.postgresql.Driver"); // we added this dependency in pom xml file

        // Step 2: Create the Connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Coding2022");

        // Step 3: Create Statement
        Statement st = con.createStatement();

        // Step 4: Execute the query
        //Example 1:
        String sql = "CREATE TABLE workers(worker_id VARCHAR (10), worker_name VARCHAR (50), worker_salary INT)";
       // st.execute(sql); // we just created the table, we are not retrieving any data (DDL), so this execute() will return false.. so we need to put it in boolean container
        // execute() returns true/false if we are fetching data from the table or updating it (DQL... using SELECT command)
        boolean sqlResult = st.execute(sql);
        System.out.println("sqlResult = " + sqlResult);

        // we are using DDL here in this class (CRUD operations are DDL)

        //Example 2:
        String sql1 = "ALTER TABLE workers ADD worker_address VARCHAR(80)";
        st.execute(sql1);

        // Example 3: Drop the table
        String sql2 = "DROP TABLE workers";
        st.execute(sql2);


       //  Step 5: Close the connection
        con.close();
        st.close();












    }
}
