import java.sql.ResultSet;
import java.util.List;

/*
 * This is interface for the query engine.
 */
public interface QueryEngineInterface {
	
	
	/**
	 * Given Search parameters it generates a sql query.
	 * @param searchParameters
	 * @return
	 */
	String createSQLQuery(SearchParameters searchParameters);
	
	/**
	 * Execute the given sql query and return the result set for the same.
	 * @param query
	 * @return
	 */
	ResultSet executeSQLQuery(String query);
	
	/**
	 * Given a result set, it will generate a list of authors
	 * @param resultSet
	 * @return
	 */
	List<Author> parseResults(ResultSet resultSet);
	
	/**
	 * Send the results as list of authors
	 * @return
	 */
	List<Author> sendResult();
}
