package Team15.DBLP;

import static org.junit.Assert.*;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import Team15.DBLP.ui.UserInterface;
import Team15.DBLP.QueryEngine.SearchParameters;

public class JournalSearchTest {

	static UserInterface ui = new UserInterface();;
	JTextField yearTextField;
	JTextField keywordTextField;
	JTextField journalTextfield;
	JTextField volumeField;
	JCheckBox chckbxAssociateEditor;
	JCheckBox chckbxEditorinChief;

	@Before
	public void setUp() {
		yearTextField = ui.getJournalYearTextField();
		keywordTextField = ui.getJournalKeywordTextField();
		journalTextfield = ui.getJournalTextfield();
		volumeField = ui.getVolumeField();
		chckbxAssociateEditor = ui.getChckbxAssociateEditor();
		chckbxEditorinChief = ui.getChckbxEditorinChief();
	}

	@Test
	public void testNotNull() {
		assertNotNull(yearTextField);
		assertNotNull(keywordTextField);
		assertNotNull(journalTextfield);
		assertNotNull(volumeField);
		assertNotNull(chckbxAssociateEditor);
		assertNotNull(chckbxEditorinChief);
	}

	@Test
	public void testSearchParameters() {
		yearTextField.setText("2006");
		keywordTextField.setText(
				"natural language processing, visualization techniques");
		journalTextfield.setText("Journal of Biomedical Informatics, jbi");
		volumeField.setText("30");
		chckbxAssociateEditor.setSelected(true);
		chckbxEditorinChief.setSelected(false);
		
		SearchParameters searchParameters = ui
				.generateJournalSearchParameters();

		assertEquals("journal", searchParameters.getSearchType());
		assertEquals(2006, searchParameters.getYearOfPublication());
		assertEquals("[natural language processing, visualization techniques]",
				searchParameters.getKeywords().toString());
		assertEquals("[Journal of Biomedical Informatics, jbi]",
				searchParameters.getJournalNames().toString());
		assertEquals(true, chckbxAssociateEditor.isSelected());
		assertEquals(false, chckbxEditorinChief.isSelected());
	}

}
