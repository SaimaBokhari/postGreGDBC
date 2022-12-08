import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Step 1: Registration to  the Driver
        Class.forName("org.postgresql.Driver"); // we added this dependency in pom xml file

        // Step 2: Create the Connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Coding2022");

        // Step 3: Create Statement
        Statement st = con.createStatement();

        // Step 4: Execute the query

        // Example 1: Create a function which uses 2 parameters and returns the sum of those parameters
        // Create the function
        String sql1 = "CREATE OR REPLACE FUNCTION additionF(x NUMERIC, y NUMERIC) RETURNS NUMERIC LANGUAGE plpgsql AS $$ BEGIN RETURN x+y; END $$";
        // execute it
        st.execute(sql1);

        // call the function
        CallableStatement cst1 = con.prepareCall("{? = call additionF(?,?)}");

        // NEXT STEP
        cst1.registerOutParameter(1, Types.NUMERIC); // this is for the result
        cst1.setInt(2, 6);
        cst1.setInt(3, 2);

        // Execute the query
        cst1.execute();
        System.out.println(cst1.getObject(1));


        //  Step 5: Close the connection
        con.close();
        st.close();



    }
}
