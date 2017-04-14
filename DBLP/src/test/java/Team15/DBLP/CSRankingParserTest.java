package Team15.DBLP;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import Team15.DBLP.ui.Author;
import Team15.DBLP.xml.CSRankingParser;

public class CSRankingParserTest {

	CSRankingParser csRankingParser;

	@Before
	public void setUp() throws SQLException, IOException {
		csRankingParser = new CSRankingParser();
		csRankingParser.parser(true);
	}

	@Test
	public void testInstitutionRegion() {
		HashMap<String, String> institutionRegion = csRankingParser
				.getInstitutionRegion();
		assertEquals("europe", institutionRegion.get(
				"Imperial College London"));
	}

	@Test
	public void testAuthorDetails() {
		HashMap<String, Author> authorDetails = csRankingParser
				.getAuthorDetails();
		assertEquals("[act, mod, arch]", authorDetails.get(
				"Chandra Chekuri").getArea().toString());
		assertEquals("University of Illinois at Urbana-Champaign", authorDetails.get(
				"Chandra Chekuri").getUniversityName());
		assertEquals("http://chekuri.cs.illinois.edu/", authorDetails.get(
				"Chandra Chekuri").getHomePageURL());
	}

}
