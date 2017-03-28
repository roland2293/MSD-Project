package Team15.DBLP;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import Team15.DBLP.xml.Conference;
import Team15.DBLP.xml.Journal;
import Team15.DBLP.xml.Paper;
import Team15.DBLP.xml.Parser;

public class ParserTest {

	private Parser parser;
	private Paper paper;
	private Conference conf;
	private Journal journal;

	@Before
	public void setUp() throws SQLException {
		parser = new Parser("temp.xml", true);
		paper = parser.getPaper();
		conf = parser.getConf();
		journal = parser.getJournal();
	}

	@Test
	public void paperParsing() {
		String expectedPaper = "title: SHALON: Lightweight Anonymization "
				+ "Based on Open Standards. authors: [Andriy Panchenko, Benedikt"
				+ " Westermann, Lexi Pimenidis, Christer Andersson] citations: [] "
				+ "conference: ICCCN year: 2009 key: conf/icccn/PanchenkoWPA09";
		assertEquals(expectedPaper, paper.toString());
	}

	@Test
	public void journalParsing() {
		String expectedJournal = "title: Terminology model discovery using natural "
				+ "language processing and visualization techniques. authors: [Li Zhou, "
				+ "Ying Tao, James J. Cimino, Elizabeth S. Chen, Hongfang Liu, Yves A. Lussier, "
				+ "George Hripcsak, Carol Friedman] journal: Journal of Biomedical Informatics "
				+ "year: 2006 volume: key: journals/jbi/ZhouTCCLLHF06";
		assertEquals(expectedJournal, journal.toString());
	}

	@Test
	public void confParsing() {
		String expectedConference = "Conference [key=conf/icccn/2011, name=ICCCN, detail=August 4, 2011]";
		assertEquals(expectedConference, conf.toString());
	}

}
