import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Step 1: Registration to  the Driver
        Class.forName("org.postgresql.Driver"); // we added this dependency in pom xml file

        // Step 2: Create the Connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Coding2022");

        // Step 3: Create Statement
        Statement st = con.createStatement();

        // Step 4: Execute the query
        // Example 1: Select the country names whose region_id is 1
        String sql1 = "SELECT country_name FROM countries WHERE region_id =1";
        boolean result1 =st.execute(sql1);
        System.out.println("result1 = " + result1);

        // if you want to see the actual result(records) on the console
        ResultSet result2 = st.executeQuery(sql1);
        while (result2.next()){
            System.out.println(result2.getString("country_name"));
        }

        System.out.println("--------------------");
        // Example 2: Select the country_id and country_name whose region_id is greater than 2

        String sql3 = "SELECT country_id, country_name FROM countries WHERE region_id>2";
        ResultSet resultSet2 = st.executeQuery(sql3);

        while (resultSet2.next()){
            System.out.println(resultSet2.getString(1)+"/ "+resultSet2.getString(2));
        }

        System.out.println("----------------");
        // There are 5 aggregate functions: max, min, avg, sum, count
        // Example 3: Select the company whose number_of_employees is the lowest from the companies table

        String sql4 = "SELECT company FROM companies WHERE number_of_employees= (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet resultSet3 = st.executeQuery(sql4);

        while (resultSet3.next()){
            System.out.println(resultSet3.getString(1));
        }

        /*
        => If the column data type is String, then we use getString(), inside the (), we can give
           the column number or name it doesn't matter
        => If the column data type is int, then we use getInt(), we can give the column number or
           name it doesn't matter

         */
        System.out.println("---------------");

        // Example 4: Select all columns whose number_of_employees is the lowest from the companies table

        String sql5 = "SELECT * FROM companies WHERE number_of_employees= (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet resultSet4 = st.executeQuery(sql5);

        while (resultSet4.next()){
            System.out.println(resultSet4.getInt(1)+"/ "+resultSet4.getString(2)+"/ "+resultSet4.getInt("number_of_employees"));
        }









        //  Step 5: Close the connection
//        con.close();
//        st.close();



    }
}
