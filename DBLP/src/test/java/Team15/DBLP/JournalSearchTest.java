package Team15.DBLP;

import static org.junit.Assert.*;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import org.junit.Test;

import Team15.DBLP.ui.JournalSearch;
import Team15.DBLP.xml.SearchParameters;

public class JournalSearchTest {

	static JournalSearch journalSearch = new JournalSearch();
	JTextField yearTextField = journalSearch.getYearTextField();
	JTextField keywordTextField = journalSearch.getKeywordTextField();
	JTextField journalTextfield = journalSearch.getJournalTextfield();
	JTextField volumeField = journalSearch.getVolumeField();
	JCheckBox chckbxAssociateEditor = journalSearch.getChckbxAssociateEditor();
	JCheckBox chckbxEditorinChief = journalSearch.getChckbxEditorinChief();

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
	public void testSearchParameters(){
		yearTextField.setText("2006");
		keywordTextField.setText("natural language processing, visualization techniques");
		journalTextfield.setText("Journal of Biomedical Informatics, jbi");
		volumeField.setText("30");
		
		SearchParameters searchParameters = journalSearch.generateSearchParameters();
		
		assertEquals("journal", searchParameters.getSearchType());
		assertEquals(2006, searchParameters.getYearOfPublication());
		assertEquals("[natural language processing, visualization techniques]", searchParameters.getKeywords().toString());
		assertEquals("[Journal of Biomedical Informatics, jbi]", searchParameters.getConferenceNames().toString());
		assertEquals(false, chckbxAssociateEditor.isEnabled());
		assertEquals(false, chckbxEditorinChief.isEnabled());
	}

}
