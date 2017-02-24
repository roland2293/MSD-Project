import java.io.InputStream;
import java.sql.ResultSet;

/*
 * This is interface for the Back end (labeled as front-end in the 
 * given requirements).
 *
 */
public interface BackEndInterface {

	/**
	 * Connection to the Database server
	 * @param username for the connection
	 * @param password for the connection
	 * @param db database name
	 * @return true iff the connection is made.
	 */
	Boolean connectToDB(String username, String password, String db);
	
	/**
	 * Execute the SQL query and return the resultSet.
	 * @param sqlQuery representing the SQL query to be executed
	 * @return resultSet after executing the query
	 */
	ResultSet executeQuery(String sqlQuery);
	
	/**
	 * Parse the given data stream and generate the data structures
	 * @param xmlDataStream stream of the data read from files
	 * @return true iff the parsing done without any inconsistency 
	 */
	Boolean parseData(InputStream xmlDataStream);
	
	/**
	 * Insert data into the database using various DML.
	 * @param sqlInsertStatement a DML statement
	 * @return true iff the SQL statement was executed successfully
	 */
	Boolean insertData(String sqlInsertStatement);
}
