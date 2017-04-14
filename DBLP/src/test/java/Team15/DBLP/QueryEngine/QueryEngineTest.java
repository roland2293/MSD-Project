package Team15.DBLP.QueryEngine;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


import Team15.DBLP.QueryEngine.QueryEngine;
import Team15.DBLP.QueryEngine.SearchParameters;

public class QueryEngineTest {
	
	QueryEngine qengine;
	SearchParameters searchparam;
	ArrayList<String> keywords;
	ArrayList<String> confNames;
	ArrayList<String> jourNames;
	
	@Before
	public void setUp() throws SQLException {
		 qengine = new QueryEngine();
	}
	
	@Test
	public void journSearch() {
		searchparam = new SearchParameters();
		keywords = new ArrayList<String>();		
		jourNames = new ArrayList<String>();
		jourNames.add("jbi");
		searchparam.setYearOfPublication(2006);
		searchparam.setSearchType("journal");
		searchparam.setJournalNames(jourNames);
		
		int expectedAuthors = 386;
		assertEquals(expectedAuthors, qengine.query(searchparam).size());
	}
	
	@Test
	public void journSearchByName() {
		searchparam = new SearchParameters();
		keywords = new ArrayList<String>();		
		jourNames = new ArrayList<String>();
		jourNames.add("Notre Dame Journal of Formal Logic");
		searchparam.setYearOfPublication(1989);
		searchparam.setSearchType("journal");
		searchparam.setJournalNames(jourNames);
		
		int expectedAuthors = 48;
		assertEquals(expectedAuthors, qengine.query(searchparam).size());
	}
	
	@Test
	public void confSearchByAcronym() {
		searchparam = new SearchParameters();
		keywords = new ArrayList<String>();		
		confNames = new ArrayList<String>();
		confNames.add("icccn");
		searchparam.setYearOfPublication(2006);
		searchparam.setSearchType("conference");
		searchparam.setConferenceNames(confNames);
		
		int expectedAuthors = 181;
		assertEquals(expectedAuthors, qengine.query(searchparam).size());
	}
	
	@Test
	public void confSearchByName() {
		searchparam = new SearchParameters();
		keywords = new ArrayList<String>();		
		confNames = new ArrayList<String>();
		confNames.add("Conference on Computer Communications and Networks");
		searchparam.setYearOfPublication(2006);
		searchparam.setSearchType("conference");
		searchparam.setConferenceNames(confNames);
		
		int expectedAuthors = 0;
		assertEquals(expectedAuthors, qengine.query(searchparam).size());
	}
	
	@Test
	public void searchByKeyword() {
		searchparam = new SearchParameters();
		keywords = new ArrayList<String>();	
		keywords.add("Map Reduce");
		keywords.add("Machine Learning");
		confNames = new ArrayList<String>();
		searchparam.setYearOfPublication(1989);
		searchparam.setSearchType("conference");
		searchparam.setKeywords(keywords);
				
		int expectedAuthors = 20;
		assertEquals(expectedAuthors, qengine.query(searchparam).size());
	}
	
	@Test
	public void searchByKeyword2() {
		searchparam = new SearchParameters();
		keywords = new ArrayList<String>();	
		keywords.add("Map Reduce");
		keywords.add("Machine Learning");
		confNames = new ArrayList<String>();
		searchparam.setYearOfPublication(1989);
		searchparam.setSearchType("conference");
		searchparam.setKeywords(keywords);
				
		int expectedAuthors = 20;
		assertEquals(expectedAuthors, qengine.queryInfo(searchparam).size());
	}
	
	
	@Test
	public void CreateSQLQueryTest() {
		searchparam = new SearchParameters();
		keywords = new ArrayList<String>();	
		keywords.add("Map Reduce");
		keywords.add("Machine Learning");
		confNames = new ArrayList<String>();
		searchparam.setYearOfPublication(1989);
		searchparam.setSearchType("conference");
		searchparam.setKeywords(keywords);

		assertNotNull(qengine.createSQLQuery(searchparam));
	}
}
