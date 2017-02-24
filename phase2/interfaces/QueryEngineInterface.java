import java.sql.ResultSet;
import java.util.List;

public interface QueryEngineInterface {
	
	String createSQLQuery(SearchParameters searchParameters);
	
	ResultSet executeSQLQuery(String query);
	
	List<Author> parseResults(ResultSet resultSet);
	
	List<Author> sendResult();
}
