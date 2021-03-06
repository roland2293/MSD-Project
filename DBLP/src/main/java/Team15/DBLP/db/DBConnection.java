package Team15.DBLP.db;

import java.sql.*;

public class DBConnection {
	// Change the parameters accordingly.
//	private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/dblp?useUnicode=true&characterEncoding=utf-8";
	private static String dbUrl = "jdbc:mysql://msd-team15.clseyn6egrcm.us-east-1.rds.amazonaws.com:3306/dblp";

	private static String user = "team15user";
	private static String password = "team15password";
	
//	private static String user = "root";
//	private static String password = "";

	public static Connection getConn() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			return DriverManager.getConnection(dbUrl, user, password);
		} catch (Exception e) {
			System.out.println("Error while opening a conneciton to database server: "
								+ e.getMessage());
			return null;
		}
	}
}
