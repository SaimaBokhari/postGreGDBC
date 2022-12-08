import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Step 1: Registration to  the Driver
        Class.forName("org.postgresql.Driver"); // we added this dependency in pom xml file

        // Step 2: Create the Connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Coding2022");

        // Step 3: Create Statement
        Statement st = con.createStatement();

        // Step 4: Execute the query

        // Example 1: Find the company and number_of_employees whose number_of_employees is the second highest

        //1st way:
        String sql1 = "SELECT company, number_of_employees FROM companies ORDER BY number_of_employees DESC OFFSET 1 ROW FETCH NEXT 1 ROW ONLY";
        ResultSet resultSet1 = st.executeQuery(sql1);
        while (resultSet1.next()){
            System.out.println(resultSet1.getString(1)+"/ "+resultSet1.getInt(2));
        }

        System.out.println("--------------");

        // 2nd way: using subquery
        String sql2 = "SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees = (SELECT MAX(number_of_employees)FROM companies\n" +
                "WHERE number_of_employees < (SELECT MAX(number_of_employees)FROM companies));\n" +
                "\n" +
                "\n";

        ResultSet resultSet2 = st.executeQuery(sql2);
        while (resultSet2.next()){
            System.out.println(resultSet2.getString(1)+"/ "+resultSet2.getInt(2));
        }

        System.out.println("-------------");

        // Example 2: Find the company and number_of_employees whose number_of_employees is less than the average
        String sql3 = " SELECT company, number_of_employees FROM companies WHERE number_of_employees < (SELECT AVG(number_of_employees) FROM companies)";
        ResultSet resultSet3 = st.executeQuery(sql3);
        while (resultSet3.next()){
            System.out.println(resultSet3.getString("company")+"/ "+resultSet3.getInt("number_of_employees"));
        }



        //  Step 5: Close the connection
        con.close();
        st.close();



    }
}
