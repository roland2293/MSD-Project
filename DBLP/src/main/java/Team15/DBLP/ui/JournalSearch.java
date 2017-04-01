package Team15.DBLP.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Team15.DBLP.QueryEngine.QueryEngine;
import Team15.DBLP.QueryEngine.SearchParameters;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JournalSearch extends JFrame implements ActionListener {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField yearTextField;
	private JTextField keywordTextField;
	private JTextField journalTextfield;
	private JTextField volumeField;
	private JCheckBox chckbxAssociateEditor;
	private JCheckBox chckbxEditorinChief;

	public JTextField getYearTextField() {
		return yearTextField;
	}

	public JTextField getKeywordTextField() {
		return keywordTextField;
	}

	public JTextField getJournalTextfield() {
		return journalTextfield;
	}

	public JTextField getVolumeField() {
		return volumeField;
	}

	public JCheckBox getChckbxAssociateEditor() {
		return chckbxAssociateEditor;
	}

	public JCheckBox getChckbxEditorinChief() {
		return chckbxEditorinChief;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JournalSearch frame = new JournalSearch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JournalSearch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAuthorName = new JLabel("Journal Name");
		lblAuthorName.setBounds(179, 51, 84, 21);
		contentPane.add(lblAuthorName);

		JLabel lblConferenceSearcg = new JLabel("Journal Search");
		lblConferenceSearcg.setBounds(135, 6, 149, 16);
		contentPane.add(lblConferenceSearcg);

		yearTextField = new JTextField();
		yearTextField.setBounds(289, 84, 192, 28);
		contentPane.add(yearTextField);
		yearTextField.setColumns(10);
		Numvalidator(yearTextField);

		keywordTextField = new JTextField();
		keywordTextField.setColumns(10);
		keywordTextField.setBounds(289, 127, 192, 28);
		contentPane.add(keywordTextField);

		journalTextfield = new JTextField();
		journalTextfield.setColumns(10);
		journalTextfield.setBounds(289, 47, 192, 28);
		contentPane.add(journalTextfield);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new JournalSearchActionListner());
		btnNewButton.setBounds(269, 364, 117, 29);
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 117, 447);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblHome = new JLabel("Home");
		lblHome.setBounds(6, 6, 61, 16);
		panel.add(lblHome);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(6, 45, 61, 16);
		panel.add(lblSearch);

		frame = this;

		JLabel lblConference = new JLabel("Conference");
		lblConference.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ConferenceSearch cFrame = new ConferenceSearch();
				cFrame.setVisible(true);
			}
		});
		lblConference.setBounds(26, 79, 75, 16);
		panel.add(lblConference);

		JLabel lblJournal = new JLabel("Journal");
		lblJournal.setBounds(26, 107, 66, 16);
		panel.add(lblJournal);

		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(228, 90, 33, 16);
		contentPane.add(lblYear);

		JLabel lblKeyword = new JLabel("Keyword");
		lblKeyword.setBounds(203, 133, 61, 16);
		contentPane.add(lblKeyword);

		volumeField = new JTextField();
		volumeField.setColumns(10);
		volumeField.setBounds(289, 167, 192, 28);
		contentPane.add(volumeField);

		JLabel lblVolume = new JLabel("Volume");
		lblVolume.setBounds(203, 173, 61, 16);
		contentPane.add(lblVolume);

		JLabel lblSelectIfThe = new JLabel(
				"Has the author served as the following?");
		lblSelectIfThe.setBounds(179, 231, 280, 33);
		contentPane.add(lblSelectIfThe);

		chckbxAssociateEditor = new JCheckBox("Associate Editor");
		chckbxAssociateEditor.setEnabled(false);
		chckbxAssociateEditor.setBounds(255, 276, 192, 23);
		contentPane.add(chckbxAssociateEditor);

		chckbxEditorinChief = new JCheckBox("Editor in Chief");
		chckbxEditorinChief.setEnabled(false);
		chckbxEditorinChief.setBounds(255, 311, 192, 23);
		contentPane.add(chckbxEditorinChief);
	}

	public void Numvalidator(JTextField txtField) {
		txtField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					e.consume();
					JOptionPane.showMessageDialog(null,
							"Only numbers are allowed!", "WARNING!!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	class JournalSearchActionListner implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// numValidator(yearTextField);
			SearchParameters searchParameters = generateSearchParameters();
            int year = Calendar.getInstance().get(Calendar.YEAR);
			if (searchParameters.getYearOfPublication() > year){
				JOptionPane.showMessageDialog(null,
						"Searched year is in future!", "WARNING!!",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (searchParameters.isEmpty()){
				JOptionPane.showMessageDialog(null,
						"No Search parameters specified!", "WARNING!!",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			System.out.println(searchParameters.toString());
			QueryEngine queryEngine = new QueryEngine();
			List<String> authorNames = queryEngine.query(searchParameters);
			AuthorResult authorFrame = new AuthorResult(authorNames, "journal");
			frame.setVisible(false);
			authorFrame.setVisible(true);

		}
	}

	public SearchParameters generateSearchParameters() {
		SearchParameters searchParameters = new SearchParameters();

		searchParameters.setSearchType("journal");

		if (chckbxAssociateEditor.isSelected())
			searchParameters.setAssociateEditor(true);
		if (chckbxEditorinChief.isSelected())
			searchParameters.setEditorinChief(true);

		String journalNames = journalTextfield.getText();
		ArrayList<String> journalNamesList = new ArrayList<String>();
		searchParameters.setJournalNames(journalNamesList);
		if (!journalNames.isEmpty()) {
			String[] journalNamesArray = journalNames.split(",");
			for (int i = 0; i < journalNamesArray.length; i++) {
				journalNamesList.add(journalNamesArray[i].trim());
			}
			searchParameters.setJournalNames(journalNamesList);
		}

		String keywordsString = keywordTextField.getText();
		ArrayList<String> keywordList = new ArrayList<String>();
		searchParameters.setKeywords(journalNamesList);
		if (!keywordsString.isEmpty()) {
			String[] keywordsArray = keywordsString.split(",");
			for (int i = 0; i < keywordsArray.length; i++) {
				keywordList.add(keywordsArray[i].trim());
			}
			searchParameters.setKeywords(keywordList);
		}

		String year = yearTextField.getText();
		if (!year.isEmpty())
			searchParameters.setYearOfPublication(Integer.parseInt(year));

		String volume = volumeField.getText();
		if (!volume.isEmpty())
			searchParameters.setVolume(volume);

		return searchParameters;
	}
}
