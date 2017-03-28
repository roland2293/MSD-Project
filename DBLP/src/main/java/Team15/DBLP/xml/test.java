package src.xml;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.db.DBConnection;

class test{
	public static void main(String args[]){
		QueryEngine q = new QueryEngine();
		SearchParameters sp = new SearchParameters();
		ArrayList<String> lis = new ArrayList<String>();
		lis.add("Map Reduce");
		lis.add("Machine Learning");
		
		sp.setKeywords(lis);
		sp.setYearOfPublication(2009);
		sp.setSearchType("conference");
		
		try {
			List<String> res = q.query(sp);
			for(String author:res){
				System.out.println("Authors : "+res);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
