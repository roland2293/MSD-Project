/*
 * Middleware Query Engine
 * @author: Tariq Anwar
 * @function: The function of the middleware is to receive Query parameters and generate query
 *            and excute it on the database. The retrieved results are parsed and sent it back to UI 
 * 
 * */

package Team15.DBLP.QueryEngine;

import Team15.DBLP.db.DBConnection;
import Team15.DBLP.ui.Author;

import java.sql.*;
import java.util.*;

public class QueryEngine {
	HashMap<String, ArrayList<Integer>> confCount;
	HashMap<String, ArrayList<Integer>> jorCount;
	HashMap<String, ArrayList<String>> authInfo;

	PreparedStatement pStmt;
	PreparedStatement filtStmt;
	Connection conn;
	private int paramCount = 0;
	SearchParameters searchParameters;
	List<String> authList = null;

	public QueryEngine() {
		super();
		confCount = new HashMap<>();
		jorCount = new HashMap<>();
		authInfo = new HashMap<>();
		Connection con = DBConnection.getConn();
		String query = "SELECT * from authorConferenceCount";
		String query2 = "SELECT * from authorJournalCount";
		String query3 = "SELECT * from authorDetails";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String confName = rs.getString("name");
				int conCount = rs.getInt("conferenceCount");
				int citCount = rs.getInt("citationCount");
				confCount.put(confName, new ArrayList<>(Arrays.asList(conCount,
						citCount)));
			}
			rs = stmt.executeQuery(query2);
			while (rs.next()) {
				String confName = rs.getString("name");
				int jouCount = rs.getInt("journalCount");
				int citCount = rs.getInt("citationCount");
				jorCount.put(confName, new ArrayList<>(Arrays.asList(jouCount,
						citCount)));
			}
			rs = stmt.executeQuery(query3);
			while (rs.next()) {
				String name = rs.getString("authorname");
				String region = rs.getString("region");
				String homepage = rs.getString("homepage");
				String area = rs.getString("area");
				authInfo.put(name, new ArrayList<String>(Arrays.asList(region,
						homepage, area)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Given Search parameters(Object containing queries) it parses every
	 * parameter and generates a sql query.
	 * 
	 * @param searchParameters
	 * @return Sql query string
	 */
	String createSQLQuery(SearchParameters searchParameters) {
		StringBuilder query = null;
		String str = null;
		this.searchParameters = searchParameters;
		String confType = null;
		String key = null;
		paramCount = 0;

		if (searchParameters != null) {
			if (searchParameters.getSearchType().equalsIgnoreCase(
					"conference")) {
				query = new StringBuilder(
						"select distinct a.name from AuthorPaper as a where ");
				confType = "conference";
				key = "paper_key";
			} else {
				query = new StringBuilder(
						"select distinct a.name from AuthorArticle as a where ");
				confType = "journal";
				key = "journal_key";
			}
			if (searchParameters.getYearOfPublication() > 0) {
				query.append(" a.year = ? ");
				++paramCount;
			}

			if (searchParameters.getKeywords() != null && searchParameters
					.getKeywords().size() > 0) {
				if (searchParameters.getYearOfPublication() > 0) {
					query.append(" and( ");
				}
				boolean first = true;
				for (String strr : searchParameters.getKeywords()) {
					if (first) {
						query.append("  a.title like ? ");
						++paramCount;
						first = false;
						continue;
					}

					query.append("or  a.title like ? ");
					++paramCount;
				}

				if (searchParameters.getYearOfPublication() > 0) {
					query.append(")");
				}
			}

			if (searchParameters.getConferenceNames() != null
					&& searchParameters.getConferenceNames().size() > 0
					&& confType.equals("conference")) {
				if (searchParameters.getYearOfPublication() > 0
						|| !searchParameters.getKeywords().isEmpty()) {
					query.append(" and( ");
				}
				boolean first = true;
				for (String strr : searchParameters.getConferenceNames()) {
					if (first) {
						query.append("a." + confType + " like ? ");
						++paramCount;
						query.append("or  a." + key + " like ? ");
						++paramCount;
						first = false;
						continue;
					}
					query.append("or  a." + confType + " like ? ");
					++paramCount;
					query.append("or  a." + key + " like ? ");
					++paramCount;
				}
				if (searchParameters.getYearOfPublication() > 0
						|| !searchParameters.getKeywords().isEmpty()) {
					query.append(")");
				}
			}

			if (searchParameters.getJournalNames() != null && searchParameters
					.getJournalNames().size() > 0 && confType.equals(
							"journal")) {
				if (searchParameters.getYearOfPublication() > 0
						|| !searchParameters.getKeywords().isEmpty()) {
					query.append(" and( ");
				}
				boolean first = true;
				for (String strr : searchParameters.getJournalNames()) {
					if (first) {
						query.append("a." + confType + " like ? ");
						++paramCount;
						query.append("or  a." + key + " like ? ");
						++paramCount;
						first = false;
						continue;
					}
					query.append("or  a." + confType + " like ? ");
					++paramCount;
					query.append("or  a." + key + " like ? ");
					++paramCount;
				}
				if (searchParameters.getYearOfPublication() > 0
						|| !searchParameters.getKeywords().isEmpty()) {
					query.append(")");
				}
			}
			str = query.toString();

		}
		return str;
	}

	/**
	 * Execute the given sql query and return the result set for the same.
	 * 
	 * @param query
	 * @return resultset object
	 */
	ResultSet executeSQLQuery(String query) {
		ResultSet result = null;
		int curr = 1;
		try {
			pStmt = conn.prepareStatement(query);
			while (curr <= paramCount) {
				if (searchParameters.getYearOfPublication() > 0) {
					pStmt.setInt(curr, searchParameters.getYearOfPublication());
					++curr;
				}
				if (searchParameters.getKeywords() != null) {
					for (String strr : searchParameters.getKeywords()) {
						pStmt.setString(curr, "%" + strr + "%");
						++curr;
					}
				}
				if (searchParameters.getSearchType().equalsIgnoreCase(
						"conference")) {
					if (searchParameters.getConferenceNames() != null) {
						for (String strr : searchParameters
								.getConferenceNames()) {
							pStmt.setString(curr, "%" + strr + "%");
							++curr;
							pStmt.setString(curr, "%" + strr + "%");
							++curr;
						}
					}
				} else {
					if (searchParameters.getJournalNames() != null) {
						for (String strr : searchParameters.getJournalNames()) {
							pStmt.setString(curr, "%" + strr + "%");
							++curr;
							pStmt.setString(curr, "%" + strr + "%");
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
	 * Given a result set, it will generate a list of authors by extracting name
	 * parameters
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	List<String> parseResults(ResultSet resultSet) throws SQLException {
		List<String> authorList = new ArrayList<String>();
		while (resultSet.next()) {
			String authorName = resultSet.getString("name");
			authorList.add(authorName);
		}
		return authorList;

	}

	/**
	 * This method is called from UI when user submits query It creates the
	 * connection object , generates SQL statement , execute and return the
	 * result.
	 * 
	 * @param searchparam
	 * @return List<String>
	 */
	public List<String> query(SearchParameters searchparam) {
		conn = DBConnection.getConn();
		this.searchParameters = searchparam;
		List<String> authors = null;
		try {
			String sqlQuery = createSQLQuery(searchParameters);
			ResultSet rs = executeSQLQuery(sqlQuery);
			authors = parseResults(rs);
			this.authList = authors;
			filterEligibleAuth();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authors;
	}

	public List<Author> queryInfo(SearchParameters searchparam) {

		List<String> authors = query(searchparam);
		List<Author> authList = new ArrayList<>();

		for (String authorName : authors) {
			int confcount = confCount.containsKey(authorName) ? confCount.get(
					authorName).get(0) : 0;
			int jorcount = jorCount.containsKey(authorName) ? jorCount.get(
					authorName).get(0) : 0;

			int citCount = 0;
			if (confcount != 0 && jorcount != 0) {
				citCount = confCount.get(authorName).get(1) + jorCount.get(
						authorName).get(1);
			}

			String region = authInfo.containsKey(authorName) ? authInfo.get(
					authorName).get(0) : " ";
			String homepage = authInfo.containsKey(authorName) ? authInfo.get(
					authorName).get(1) : " ";
			String area = authInfo.containsKey(authorName) ? authInfo.get(
					authorName).get(2) : " ";

			Author author = new Author();
			author.setName(authorName);
			author.setRegion(region);
			HashSet<String> hs = new HashSet<>();
			hs.add(area);
			author.setArea(hs);
			author.setHomePageURL(homepage);
			author.setNumberOfConferencePaperPublished(confcount);
			author.setNumberOfJournalPaperPublished(jorcount);
			author.setCitations(citCount);

			authList.add(author);

		}

		return authList;
	}

	/**
	 * this method filters the author who have already served in last 3 years
	 * commitee, such authors are not eligible for committee membership for
	 * current year.
	 * 
	 */
	private void filterEligibleAuth() {
		Calendar now = Calendar.getInstance(); // Gets the current date and time
		int year = now.get(Calendar.YEAR); // The current year
		ArrayList<String> authorList = new ArrayList<String>();

		String sqlQuery = "SELECT DISTINCT authorname FROM committee WHERE year IN(?,?,?)";
		try {
			filtStmt = conn.prepareStatement(sqlQuery);
			filtStmt.setInt(1, year - 1);
			filtStmt.setInt(2, year - 2);
			filtStmt.setInt(3, year - 3);

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
