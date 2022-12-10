import org.junit.jupiter.api.Test;

public class CountriesTest {
    /*
        Given
          User connects to the database

        When
          User sends the query to get the region ids from "countries" table

        Then
          Verify that the number of region ids greater than 1 is 17.

        And
          User closes the connection
       */

    @Test
    public void countryTest(){

        // User connects to the database
        JdbcUtils.connectToDatabase("localhost", "postgres","postgres","Coding2022");
        JdbcUtils.createStatement();

        //User sends the query to get the region ids from "countries" table
        JdbcUtils.getColumnList("region_id")







    }
}
