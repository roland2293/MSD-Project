package Team15.DBLP.xml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Team15.DBLP.QueryEngine.QueryEngine;
import Team15.DBLP.QueryEngine.SearchParameters;
import Team15.DBLP.db.DBConnection;

class test {
	public static void main(String args[]) {
		QueryEngine q = new QueryEngine();
		SearchParameters sp = new SearchParameters();
		ArrayList<String> lis = new ArrayList<String>();
		lis.add("MapReduce");

		sp.setKeywords(lis);
		sp.setYearOfPublication(2009);
		sp.setSearchType("conference");

		List<String> res = q.query(sp);
	}

}
