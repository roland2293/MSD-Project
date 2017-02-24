import java.sql.ResultSet;

public interface BackEndInterface {

	Boolean connectToDB(String username, String password, String db);
	
	ResultSet executeQuery(String sqlQuery);
	
	Boolean parseData(Object xmlDataStream);
	
	Boolean insertData(String sqlInsertStatement);
}
