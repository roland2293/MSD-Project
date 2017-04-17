package Team15.DBLP;

import static org.junit.Assert.*;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import Team15.DBLP.ui.UserInterface;
import Team15.DBLP.QueryEngine.SearchParameters;

public class ConferenceSearchTest {

	static UserInterface ui = new UserInterface();
	JTextField yearTextField;
	JTextField keywordTextField;
	JTextField conferenceTextField;
	JCheckBox chckbxServedAsGeneral;
	JCheckBox chckbxProgramChair;
	JCheckBox chckbxConferenceChair;
	JCheckBox chckbxCommitteMember;

	@Before
	public void startUp() {
		yearTextField = ui.getYearTextField();
		keywordTextField = ui.getKeywordTextField();
		conferenceTextField = ui.getConferenceTextField();
		chckbxServedAsGeneral = ui.getChckbxServedAsGeneral();
		chckbxProgramChair = ui.getChckbxProgramChair();
		chckbxConferenceChair = ui.getChckbxConferenceChair();
		chckbxCommitteMember = ui.getChckbxCommitteMember();
	}

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
	public void testSearchParameters() {
		yearTextField.setText("2006");
		keywordTextField.setText("Next-Generation Networks, Service-Centric");
		conferenceTextField.setText("ICCCN");
		chckbxServedAsGeneral.setSelected(true);
		chckbxProgramChair.setSelected(true);
		chckbxConferenceChair.setSelected(true);
		chckbxCommitteMember.setSelected(true);

		SearchParameters searchParameters = ui
				.generateConferenceSearchParameters();

		assertEquals("conference", searchParameters.getSearchType());
		assertEquals(2006, searchParameters.getYearOfPublication());
		assertEquals("[Next-Generation Networks, Service-Centric]",
				searchParameters.getKeywords().toString());
		assertEquals("[ICCCN]", searchParameters.getConferenceNames()
				.toString());
		assertEquals(true, searchParameters.isGeneralChair());
		assertEquals(true, searchParameters.isProgramChair());
		assertEquals(true, searchParameters.isConferenceChair());
		assertEquals(true, searchParameters.isCommitteeMember());
	}

}
