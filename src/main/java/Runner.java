public class Runner {
    public static void main(String[] args) {

        // Step 1: Registration to  the Driver
        // Step 2: Create the Connection with database

        JdbcUtils.connectToDatabase("localhost", "postgres", "postgres","Coding2022");

        // Step 3: Create Statement

        JdbcUtils.createStatement();

        // Step 4: Execute the query
        //JdbcUtils.createTable("workers","worker_id VARCHAR (10)", "worker_name VARCHAR (50)", "worker_salary INT"  );
        //JdbcUtils.createTable("Students", "name VARCHAR (20)","id INT", "address VARCHAR (50) ",  "tel BIGINT");


        // Insert values to a table
        JdbcUtils.insertDataIntoTable("Students", "name 'John'");
        JdbcUtils.insertDataIntoTable("Students", "name 'Mark'", "id 123", "address 'Dubai'", "tel 50234543");





        // Drop the table
       // JdbcUtils.dropTable("workers");
        JdbcUtils.dropTable("Students");




        // Step 5: Close the connection and the statement
       // JdbcUtils.closeConnectionAndStatement();




    }
}
