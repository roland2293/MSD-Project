package Team15.DBLP;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Team15.DBLP.xml.Committee;
import Team15.DBLP.xml.CommitteesParser;

public class CommitteeParserTest {

	CommitteesParser committeesParser;
	ArrayList<Committee> committees;

	@Before
	public void setUp() throws SQLException, IOException {
		committeesParser = new CommitteesParser();
		committeesParser.setFolder("committees_test");
		committeesParser.parser(true);
		committees = committeesParser.getCommittees();
	}

	@Test
	public void generalChairTest() {
		String role = committees.get(0).getRole();
		assertEquals("General Chair", role);
	}

	@Test
	public void authorNameTest() {
		String authorName = committees.get(0)
				.getAuthorName();
		assertEquals("Debra J. Richardson", authorName);
	}

	@Test
	public void conferenceTest() {
		String conference = committees.get(0)
				.getConference();
		assertEquals("ase", conference);
	}

	@Test
	public void yearTest() {
		String year = committees.get(0).getYear();
		assertEquals("2001", year);
	}

	@Test
	public void externalReviewCommitteeTest() {
		String role = committees.get(52).getRole();
		assertEquals("External Review Committee", role);
	}

	@Test
	public void programChairTest() {
		String role = committees.get(1).getRole();
		assertEquals("Program Chair", role);
	}

	@Test
	public void conferenceChairTest() {
		String role = committees.get(3).getRole();
		assertEquals("Conference Chair", role);
	}

	@Test
	public void committeeMemberTest() {
		String role = committees.get(10).getRole();
		assertEquals("Committee member", role);
	}
}
