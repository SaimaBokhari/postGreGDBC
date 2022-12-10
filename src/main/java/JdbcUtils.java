import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils {

    // we are creating this class so we can handle all the exceptions which otherwise would stop the operations

    private static Connection connection;
    private static Statement statement;

    // Step 1: Registration to  the Driver
    // Step 2: Create the Connection with database


    public static Connection connectToDatabase(String hostName, String dataBaseName,String username,String password){

        try {
            Class.forName("org.postgresql.Driver"); // we added this dependency in pom xml file
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName + ":5432/"+dataBaseName+"", username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connected to the database.");
        return connection;

    }

    // Step 3: Create Statement
    public static Statement createStatement(){

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Statement is created.");
        return statement;
    }

    // Step 4: Execute the query
    public static boolean execute(String query){
        boolean isExecuted;
        try {
            isExecuted = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Table is created");
        return isExecuted;
    }

    // Step 5: Close the connection and the statement

    public static void closeConnectionAndStatement(){

        try {
            connection.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(connection.isClosed() && statement.isClosed()){
                System.out.println("Connection and Statement are closed.");
            }else{
                System.out.println("Connection and Statement are not closed.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void dropTable(String tableName){
        try {
            statement.execute("DROP TABLE "+ tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Table " + tableName + " is dropped!");
    }

    // Create the table
    public static void createTable(String tableName, String... columnName_DataType){
        StringBuilder columnName_DataTypeInString = new StringBuilder();
        for (String w: columnName_DataType){
            columnName_DataTypeInString.append(w).append(",");
        }
        columnName_DataTypeInString.deleteCharAt(columnName_DataTypeInString.lastIndexOf(","));

        try {
            statement.execute("CREATE TABLE "+tableName+"("+ columnName_DataTypeInString +")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Table " + tableName+ " is created.");

    }

    // Create method to Insert data/values to a table
    // INSERT INTO  tableName (columnName1, columnName2...) VALUES (value1, value2...)
    // we'll use varargs to add unlimited columns and values

    public static void insertDataIntoTable(String tableName, String... columnName_Value ){
        StringBuilder columnName = new StringBuilder("");
        StringBuilder value = new StringBuilder("");

        for (String w: columnName_Value){
            columnName.append(w.split(" ")[0]).append(",");
            value.append(w.split(" ")[1]).append(",");
        }
        columnName.deleteCharAt(columnName.lastIndexOf(","));
        value.deleteCharAt(value.lastIndexOf(","));


        String command = "INSERT INTO "+ tableName + " (" + columnName + ") VALUES (" + value + ")";
        try {
            statement.execute(command);
            System.out.println("Data inserted into table "+ tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    // Create a method to get column data into a list
    public static List<Object> getColumnList(String columnName, String tableName){
        ResultSet resultSet=;
        List<Object> columnData = new ArrayList<>();

        String query ="SELECT "+columnName+" FROM "+tableName;

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                columnData.add(resultSet.getObject(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return columnData;
    }




}
