package Team15.DBLP.QueryEngine;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import Team15.DBLP.db.DBConnection;

public class QueryEngine {

	PreparedStatement pStmt;
	PreparedStatement filtStmt;
	Connection conn;
	private int paramCount=0;
	SearchParameters searchParameters;
	List<String> authList=null;

	/**
	 * Given Search parameters it generates a sql query.
	 * @param searchParameters
	 * @return
	 */
	String createSQLQuery(SearchParameters searchParameters){
		StringBuilder query=null;
		String str=null;
		this.searchParameters = searchParameters;
		String confType=null;
		String key=null;

		if(searchParameters != null){		
			if(searchParameters.getSearchType().equalsIgnoreCase("conference")){
				query = new StringBuilder("select distinct a.name from AuthorPaper as a where");	
				confType ="conference";
				key = "paper_key";
			}else{
				query = new StringBuilder("select distinct a.name from AuthorArticle as a where");
				confType ="journal";
				key = "journal_key";
			}	
			if(searchParameters.getYearOfPublication()>0){
				query.append(" a.year = ? ");
				++paramCount;
			}

			if(searchParameters.getKeywords() != null && searchParameters.getKeywords().size()>0){
				if(searchParameters.getYearOfPublication()>0){
					query.append(" and( ");
				}
				boolean first = true;
				for(String strr : searchParameters.getKeywords())	{
					if (first){
						query.append("  a.title like ? ");
						++paramCount;
						first = false;
						continue;
					}

					query.append("or  a.title like ? ");
					++paramCount;
				}
				
				if(searchParameters.getYearOfPublication()>0){
				query.append(")");
				}
			}

			if(searchParameters.getConferenceNames() != null && searchParameters.getConferenceNames().size()>0 && confType.equals("conference")){
				query.append(" and( ");
				boolean first = true;
				for(String strr : searchParameters.getConferenceNames())	{
					if (first){
						query.append("a."+confType +" like ? ");
						++paramCount;
						query.append("or  a."+key+" like ? ");
						++paramCount;
						first = false;
						continue;
					}
					query.append("or  a."+confType+" like ? ");
					++paramCount;
					query.append("or  a."+key+" like ? ");
					++paramCount;
				}
				query.append(")");
			}

			if(searchParameters.getJournalNames() != null && searchParameters.getJournalNames().size()>0 && confType.equals("journal") ){
				query.append(" and( ");
				boolean first = true;
				for(String strr : searchParameters.getJournalNames())	{
					if (first){
						query.append("a."+confType +" like ? ");
						++paramCount;
						query.append("or  a."+key+" like ? ");
						++paramCount;
						first = false;
						continue;
					}
					query.append("or  a."+confType+" like ? ");
					++paramCount;
					query.append("or  a."+key+" like ? ");
					++paramCount;
				}
				query.append(")");
			}
			str= query.toString();

		}
		return str;
	}

	/**
	 * Execute the given sql query and return the result set for the same.
	 * @param query
	 * @return
	 */
	ResultSet executeSQLQuery(String query){
		ResultSet result=null;
		int curr = 1;
		try {
			pStmt = conn.prepareStatement(query);
			while(curr <= paramCount){
				if(searchParameters.getYearOfPublication()>0){
					pStmt.setInt(curr,searchParameters.getYearOfPublication());
					++curr;
				}
				if(searchParameters.getKeywords() != null){
					for(String strr : searchParameters.getKeywords()){
						pStmt.setString(curr, "%"+strr+"%");
						++curr;
					}
				}
				if(searchParameters.getSearchType().equalsIgnoreCase("conference")){
					if(searchParameters.getConferenceNames() != null){
						for(String strr : searchParameters.getConferenceNames()){
							pStmt.setString(curr, "%"+strr+"%");
							++curr;
							pStmt.setString(curr, "%"+strr+"%");
							++curr;
						}
					}
				}
				else{
					if(searchParameters.getJournalNames() != null){
						for(String strr : searchParameters.getJournalNames()){
							pStmt.setString(curr, "%"+strr+"%");
							++curr;
							pStmt.setString(curr, "%"+strr+"%");
							++curr;
						}
					}

				}
			}
			System.out.println(pStmt);
			result = pStmt.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Given a result set, it will generate a list of authors
	 * @param resultSet
	 * @return
	 * @throws SQLException 
	 */
	List<String> parseResults(ResultSet resultSet) throws SQLException{
		List<String> authorList = new ArrayList<String>();
		while(resultSet.next()){
			String authorName = resultSet.getString("name");
			authorList.add(authorName); 
		}
		return authorList;

	}

	public List<String> query (SearchParameters searchparam){
		conn =  DBConnection.getConn();
		this.searchParameters = searchparam;
		List<String> authors = null;
		try {
			String sqlQuery = createSQLQuery(searchParameters);
			ResultSet rs = executeSQLQuery(sqlQuery);
			authors = parseResults(rs);
			this.authList=authors;
			filterEligibleAuth();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return authors;
	}
	
	private void filterEligibleAuth(){
		Calendar now = Calendar.getInstance();   // Gets the current date and time
		int year = now.get(Calendar.YEAR);       // The current year
		ArrayList<String> authorList = new ArrayList<String>();
		
		String sqlQuery = "SELECT DISTINCT authorname FROM committee WHERE year IN(?,?,?)";
		try {
			filtStmt = conn.prepareStatement(sqlQuery);
			filtStmt.setInt(1, year-1);
			filtStmt.setInt(2, year-2);
			filtStmt.setInt(3, year-3);
			
			// execute select SQL stetement
			ResultSet rs = filtStmt.executeQuery();

			while (rs.next()) {

				String userid = rs.getString("authorname");
				authorList.add(userid);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		authList.removeAll(authorList);
	}

}
