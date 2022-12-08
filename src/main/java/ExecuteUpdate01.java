import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Step 1: Registration to  the Driver
        Class.forName("org.postgresql.Driver"); // we added this dependency in pom xml file

        // Step 2: Create the Connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Coding2022");

        // Step 3: Create Statement
        Statement st = con.createStatement();

        // Step 4: Execute the query
        // We use DML.. Data Manipulation Language (Create update and delete operations)

        // Example 1: Update the number of employees to 16000 if the number of employees is less
        //             than the average number of employees

        String sql1 = "UPDATE companies\n" +
                "SET number_of_employees = 16000\n" +
                "WHERE number_of_employees < (SELECT AVG(number_of_employees) FROM companies)";

        int numOfRowsUpdated= st.executeUpdate(sql1);
        System.out.println("numOfRowsUpdated = " + numOfRowsUpdated);

        //to see on the console

        String sql2 ="SELECT * FROM companies";
        ResultSet resultSet1  = st.executeQuery(sql2);
        while (resultSet1.next()){
            System.out.println(resultSet1.getString("company")+"/ "+resultSet1.getInt("number_of_employees"));
        }


        //  Step 5: Close the connection
        con.close();
        st.close();


    }
}
