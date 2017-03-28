package src.xml;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import src.db.DBConnection;

public class QueryEngine {
	
	PreparedStatement pStmt;
	Connection conn;
	private int paramCount=0;
	SearchParameters searchParameters;

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
		
		if(searchParameters != null){		
			if(searchParameters.getSearchType().equalsIgnoreCase("conference")){
				query = new StringBuilder("select a.name from AuthorPaper as a where");	
				confType ="conference";
			}else{
				query = new StringBuilder("select a.name from AuthorArticle as a where");
				confType ="journal";
			}	
			if(searchParameters.getYearOfPublication()>0){
				query.append(" a.year = ? ");
			}
			
			if(searchParameters.getKeywords() != null && searchParameters.getKeywords().size()>0){
				query.append(" and( ");
				boolean first = true;
				for(String strr : searchParameters.getKeywords())	{
					if (first){
						query.append("  a.title like ? ");
						first = false;
						continue;
					}
					
					query.append("or  a.title like ? ");
				    ++paramCount;
				}
				query.append(")");
			}
			
			if(searchParameters.getConferenceNames() != null && searchParameters.getConferenceNames().size()>0){
				query.append(" and( ");
				boolean first = true;
				for(String strr : searchParameters.getConferenceNames())	{
					if (first){
						query.append(confType +" like ? ");
						first = false;
						continue;
					}
					query.append("or  "+confType+" like ? ");
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
				            pStmt.setString(curr, "%/"+strr+"/%");
				            ++curr;
				        }
			    	}
		    	}
		    	else{
			    	if(searchParameters.getJournalNames() != null){
				    	for(String strr : searchParameters.getConferenceNames()){
				            pStmt.setString(curr, "%/"+strr+"/%");
				            ++curr;
				        }
			    	}
		    		
		    	}
		    }

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
            System.out.println("Result Set"+authorName);
            authorList.add(authorName);
            
		}
		
		return authorList;
		
	}
	
	List<String> query (SearchParameters searchparam) throws SQLException{
		conn =  DBConnection.getConn();
		this.searchParameters = searchparam;
		List<String> authors = null;
		try {
			String sqlQuery = createSQLQuery(searchParameters);
			System.out.println(sqlQuery);
			ResultSet rs = executeSQLQuery(sqlQuery);
			authors = parseResults(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			//pStmt.close();
			conn.close();
		}
		return authors;
	}
	

		
}
