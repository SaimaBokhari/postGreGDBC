import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Step 1: Registration to  the Driver
        Class.forName("org.postgresql.Driver"); // we added this dependency in pom xml file

        // Step 2: Create the Connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Coding2022");

        // Step 3: Create Statement
        Statement st = con.createStatement();

        // Step 4: Execute the query
        // Example 1: Update the number_of_employees to 18000 if the company name is 'IBM' by using PreparedStatement

        // Step 1: Create Prepared Statement Query
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ? ";

        // Step 2: Create object from the Prepared Statement
        PreparedStatement pst1 = con.prepareStatement(sql1);

        // Step 3: Assign the value by using setInt() or setString() etc.
        pst1.setInt(1, 18000);
        pst1.setString(2, "IBM");

        // Step 4: Execute the query
        int numOfRowsUpdated1 = pst1.executeUpdate();
        System.out.println("numOfRowsUpdated1 = " + numOfRowsUpdated1);

        // Step 5: To see the updated values on the console
        String sql2 = "SELECT * FROM companies";

        ResultSet resultSet1 = st.executeQuery(sql2);
        while (resultSet1.next()){
            System.out.println(resultSet1.getString("company")+"/ "+resultSet1.getInt("number_of_employees"));
        }
        System.out.println("--------------");

        // Example 2: Update the number_of_employees to 9999 if the company name is 'GOOGLE'
        // We will just use the already prepared statement here and start from step 3

        // Step 3: Assign the value by using setInt() or setString() etc.
        pst1.setInt(1, 9999);
        pst1.setString(2, "GOOGLE");

        // Step 4: Execute the query
        int numOfRowsUpdated2 = pst1.executeUpdate();
        System.out.println("numOfRowsUpdated2 = " + numOfRowsUpdated2);

        // Step 5: To see the updated values on the console
        String sql3 = "SELECT * FROM companies";

        ResultSet resultSet2 = st.executeQuery(sql3);
        while (resultSet2.next()){
            System.out.println(resultSet2.getString("company")+"/ "+resultSet2.getInt("number_of_employees"));
        }


        //  Step 5: Close the connection
        con.close();
        st.close();




    }
}
