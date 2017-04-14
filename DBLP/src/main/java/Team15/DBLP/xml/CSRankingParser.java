package Team15.DBLP.xml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import Team15.DBLP.db.DBConnection;
import Team15.DBLP.ui.Author;

public class CSRankingParser {

	private Connection conn;
	private PreparedStatement insertAuthorDetails;
	private String cvsSplitBy = ",";
	private HashMap<String, String> institutionRegion;
	private HashMap<String, Author> authorDetails;
	BufferedReader reader;

	public CSRankingParser() {
		institutionRegion = new HashMap<String, String>();
		authorDetails = new HashMap<String, Author>();
	}

	public HashMap<String, String> getInstitutionRegion() {
		return institutionRegion;
	}

	public HashMap<String, Author> getAuthorDetails() {
		return authorDetails;
	}

	public void parser(Boolean isTest) throws SQLException, IOException {
		conn = DBConnection.getConn();
		insertAuthorDetails = conn.prepareStatement(
				"insert into authorDetails(authorname, region, homepage, area) values(?,?,?,?)");
		parseInstitutionRegion("country-info.csv");
		parseAuthorInfo("generated-author-info.csv");
		parseHomepages("homepages.csv");
		insertIntoDB(isTest);
	}

	private void insertIntoDB(Boolean isTest) throws SQLException {
		Author author;
		for (String key : authorDetails.keySet()) {
			author = authorDetails.get(key);
			insertAuthorDetails.setString(1, author.getName());
			insertAuthorDetails.setString(2, author.getRegion());
			insertAuthorDetails.setString(3, author.getHomePageURL());
			insertAuthorDetails.setString(4, author.getArea().toString());
			insertAuthorDetails.addBatch();
		}
		if (!isTest)
			insertAuthorDetails.executeBatch();
	}

	private void parseHomepages(String filePath) throws IOException {
		reader = new BufferedReader(new FileReader(filePath));
		String line = "";
		line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			// use comma as separator
			String[] homePageString = line.split(cvsSplitBy);
			Author author;
			if (authorDetails.containsKey(homePageString[0])) {
				author = authorDetails.get(homePageString[0]);
				author.setHomePageURL(homePageString[1]);
				authorDetails.put(homePageString[0], author);
			} else {
				author = new Author();
				author.setName(homePageString[0]);
				author.setHomePageURL(homePageString[1]);
				authorDetails.put(homePageString[0], author);
			}

		}
		reader.close();
	}

	private void parseAuthorInfo(String filePath) throws IOException {
		reader = new BufferedReader(new FileReader(filePath));
		String line = "";
		line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			// use comma as separator
			String[] authorInfoString = line.split(cvsSplitBy);
			Author author;
			if (authorDetails.containsKey(authorInfoString[0])) {
				author = authorDetails.get(authorInfoString[0]);
				author.setUniversityName(authorInfoString[1]);
				if (!author.getArea().contains(authorInfoString[2])) {
					author.getArea().add(authorInfoString[2]);
				}
				authorDetails.put(authorInfoString[0], author);
			} else {
				author = new Author();
				author.setName(authorInfoString[0]);
				author.setUniversityName(authorInfoString[1]);
				author.getArea().add(authorInfoString[2]);
				author.setRegion(institutionRegion.get(authorInfoString[1]));
				authorDetails.put(authorInfoString[0], author);
			}

		}
		reader.close();
	}

	private void parseInstitutionRegion(String filePath) throws IOException {
		reader = new BufferedReader(new FileReader(filePath));
		String line = "";
		line = reader.readLine();
		line = reader.readLine();
		while (line != null) {
			// use comma as separator
			String[] institutionRegionString = line.split(cvsSplitBy);
			institutionRegion.put(institutionRegionString[0],
					institutionRegionString[1]);
			line = reader.readLine();
		}
		reader.close();
	}

	public static void main(String[] args) throws SQLException, IOException {
		CSRankingParser csRankingParser = new CSRankingParser();
		// uncomment below line before execution.
		// csRankingParser.parser(false);
	}

}
