package Team15.DBLP;

import static org.junit.Assert.*;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import org.junit.Test;

import Team15.DBLP.ui.ConferenceSearch;
import Team15.DBLP.xml.SearchParameters;

public class ConferenceSearchTest {
	
	static ConferenceSearch  conferenceSearch = new ConferenceSearch();
	JTextField yearTextField = conferenceSearch.getYearTextField();
	JTextField keywordTextField = conferenceSearch.getKeywordTextField();
	JTextField conferenceTextField = conferenceSearch.getConferenceTextField();
	JCheckBox chckbxServedAsGeneral = conferenceSearch.getChckbxServedAsGeneral();
	JCheckBox chckbxProgramChair = conferenceSearch.getChckbxProgramChair();
	JCheckBox chckbxConferenceChair = conferenceSearch.getChckbxConferenceChair();
	JCheckBox chckbxCommitteMember = conferenceSearch.getChckbxCommitteMember();
	

	@Test
	public void testNotNull() {
		assertNotNull(yearTextField);
		assertNotNull(keywordTextField);
		assertNotNull(conferenceTextField);
		assertNotNull(chckbxServedAsGeneral);
		assertNotNull(chckbxProgramChair);
		assertNotNull(chckbxConferenceChair);
		assertNotNull(chckbxCommitteMember);
	}
	
	@Test
	public void testSearchParameters(){
		yearTextField.setText("2006");
		keywordTextField.setText("Next-Generation Networks, Service-Centric");
		conferenceTextField.setText("ICCCN");
		chckbxServedAsGeneral.setSelected(true);
		chckbxProgramChair.setSelected(true);
		chckbxConferenceChair.setSelected(true);
		chckbxCommitteMember.setSelected(true);
		
		SearchParameters searchParameters = conferenceSearch.generateSearchParameters();
		
		assertEquals("conference", searchParameters.getSearchType());
		assertEquals(2006, searchParameters.getYearOfPublication());
		assertEquals("[Next-Generation Networks, Service-Centric]", searchParameters.getKeywords().toString());
		assertEquals("[ICCCN]", searchParameters.getConferenceNames().toString());
		assertEquals(true, searchParameters.isGeneralChair());
		assertEquals(true, searchParameters.isProgramChair());
		assertEquals(true, searchParameters.isConferenceChair());
		assertEquals(true, searchParameters.isCommitteeMember());
	}

}
