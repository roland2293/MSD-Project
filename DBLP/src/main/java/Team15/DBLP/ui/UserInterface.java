package Team15.DBLP.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import Team15.DBLP.QueryEngine.QueryEngine;
import Team15.DBLP.QueryEngine.SearchParameters;


import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class UserInterface extends JFrame {
	// Intial parameters with getters
	public static JFrame frame;
	private QueryEngine queryEngine = null;
	private JTabbedPane tabbedPaneSearch;
	private List<Author> authorDetails;
	
	public JTabbedPane getTabbedPaneSearchn() {
		return tabbedPaneSearch;
	}
	
	public List<Author> getauthorDetails(){
		return authorDetails;
	}
	
	QueryEngine getInstance(){
		if(queryEngine == null){
			queryEngine = new QueryEngine();
		}
		return queryEngine;
	}
	
	// Conference Search Fields declarations with getters
	private JTextField yearTextField;
	private JTextField keywordTextField;
	private JTextField conferenceTextField;
	private JCheckBox  chckbxServedAsGeneral;
	private JCheckBox  chckbxProgramChair;
	private JCheckBox  chckbxConferenceChair;
	private JCheckBox  chckbxCommitteMember;
	private JButton    btnConferenceSearchButton;
	
	public JTextField getYearTextField() {
		return yearTextField;
	}

	public JTextField getKeywordTextField() {
		return keywordTextField;
	}

	public JTextField getConferenceTextField() {
		return conferenceTextField;
	}

	public JCheckBox getChckbxServedAsGeneral() {
		return chckbxServedAsGeneral;
	}

	public JCheckBox getChckbxProgramChair() {
		return chckbxProgramChair;
	}

	public JCheckBox getChckbxConferenceChair() {
		return chckbxConferenceChair;
	}

	public JCheckBox getChckbxCommitteMember() {
		return chckbxCommitteMember;
	}
	
	public JButton getConferenceSearchButton() {
		return btnConferenceSearchButton;
	}
	
	// Journal Search Fields declarations with getters
	private JTextField jyearTextField;
	private JTextField jkeywordTextField;
	private JTextField journalTextfield;
	private JTextField volumeField;
	private JCheckBox  chckbxAssociateEditor;
	private JCheckBox  chckbxEditorinChief;
	private JButton    btnJournalSearchButton;
	
	public JTextField getJournalYearTextField() {
		return jyearTextField;
	}

	public JTextField getJournalKeywordTextField() {
		return jkeywordTextField;
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
	
	public JButton getJournalSearchButton() {
		return btnJournalSearchButton;
	}
	
	// Search Results Fields declarations with getters
	private JPanel panelResult;
	public List<String> authorNames;
	private DefaultListModel<String> listModel;
	private JList<String> list;
	private String searchType = "conference";
	
	public JPanel getPanelResult() {
		return panelResult;
	}
	
	public List<String> getAuthorNames() {
		return authorNames;
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public JList<String> getList() {
		return list;
	}
	
	public String getSearchType() {
		return searchType;
	}
	 
	public UserInterface() {
		// Frame initialization
		setBounds(0, 0, 600, 600);
		getContentPane().setLayout(null);
		
		frame = this;
		
		queryEngine = getInstance();
		
		// Home Tabbed Pane
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(6, 0, 564, 539);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		// Panel for Home Page
		JPanel panelHome = new JPanel();
		tabbedPane.addTab("Home", null, panelHome, null);
		panelHome.setLayout(null);
		
		String introText= "A diverse set of suitable and available reviewers for judging conference or journal paper "
			      +"submissions is a challenging and time-consuming problem for scientific publishers.<br><br>"
			      +"This tool helps to create a system that leverages existing conference and journal "
			      +"repositories to propose appropriate candidates who can adequately review submissions to a "
			      +"Program Committee Chair or Associate Editor.";
		String formatted = introText.replace("\n", "<br>");
		formatted = "<html>" + formatted + "</html>";
		
		final JPanel panel_intro = new JPanel();
		panel_intro.setBorder(new TitledBorder(null, "Introduction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_intro.setBounds(10, 11, 490, 164);
		panelHome.add(panel_intro);
		panel_intro.setLayout(null);
		JLabel textField = new JLabel(formatted);
		textField.setBounds(6, 16, 474, 137);
		panel_intro.add(textField);
		
		final JPanel panel_tips = new JPanel();
		panel_tips.setBorder(new TitledBorder(null, "Tips", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_tips.setBounds(10, 195, 490, 120);
		panelHome.add(panel_tips);
		panel_tips.setLayout(null);
		
		String line1 = "1.Searching using multiple keywords can be done by separating them using a comma.<br>";
		String line2 = "2.Journal Search cannot be done using Volume only.<br>";
		
		JLabel label = new JLabel("<html>"+line1+line2+"</html>");
		label.setBounds(6, 16, 474, 84);
		panel_tips.add(label);
		
		// Search Tabbed Pane
		tabbedPaneSearch = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Search", null, tabbedPaneSearch, null);
		
		// Panel for Conference Search
		JPanel panelConference = new JPanel();
		tabbedPaneSearch.addTab("Conference Search", null, panelConference, null);
		tabbedPaneSearch.setEnabledAt(0, true);
		panelConference.setLayout(null);
		
		JLabel lblAuthorName = new JLabel("Conference Name");
		lblAuthorName.setBounds(35, 51, 111, 21);
		panelConference.add(lblAuthorName);

		JLabel lblConferenceSearcg = new JLabel("Conference Search");
		lblConferenceSearcg.setBounds(199, 6, 149, 16);
		panelConference.add(lblConferenceSearcg);

		yearTextField = new JTextField();
		yearTextField.setBounds(173, 83, 203, 28);
		panelConference.add(yearTextField);
		yearTextField.setColumns(10);
		Numvalidator(yearTextField);

		keywordTextField = new JTextField();
		keywordTextField.setColumns(10);
		keywordTextField.setBounds(173, 122, 203, 28);
		panelConference.add(keywordTextField);

		conferenceTextField = new JTextField();
		conferenceTextField.setColumns(10);
		conferenceTextField.setBounds(173, 47, 203, 28);
		panelConference.add(conferenceTextField);

		JButton btnConferenceSearchButton = new JButton("Search");
		btnConferenceSearchButton.addActionListener(new ConferenceSearchActionListner());
		btnConferenceSearchButton.setBounds(205, 391, 117, 29);
		panelConference.add(btnConferenceSearchButton);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(122, 93, 33, 16);
		panelConference.add(lblYear);

		JLabel lblKeyword = new JLabel("Keyword");
		lblKeyword.setBounds(95, 133, 61, 16);
		panelConference.add(lblKeyword);

		chckbxServedAsGeneral = new JCheckBox("General Chair");
		chckbxServedAsGeneral.setBounds(173, 222, 192, 23);
		panelConference.add(chckbxServedAsGeneral);

		JLabel lblSelectIfThe = new JLabel("Has the author served as the following?");
		lblSelectIfThe.setBounds(122, 177, 280, 33);
		panelConference.add(lblSelectIfThe);

		chckbxProgramChair = new JCheckBox("Program Chair");
		chckbxProgramChair.setBounds(173, 257, 192, 23);
		panelConference.add(chckbxProgramChair);

		chckbxConferenceChair = new JCheckBox("Conference Chair");
		chckbxConferenceChair.setBounds(173, 292, 192, 23);
		panelConference.add(chckbxConferenceChair);

		chckbxCommitteMember = new JCheckBox("Committee Member");
		chckbxCommitteMember.setBounds(173, 327, 192, 23);
		panelConference.add(chckbxCommitteMember);
		
		// Panel for Journal Search
		JPanel panelJournal = new JPanel();
		tabbedPaneSearch.addTab("Journal Search", null, panelJournal, null);
		tabbedPaneSearch.setEnabledAt(1, true);
		
		panelJournal.setLayout(null);
		
		JLabel lblJournalName = new JLabel("Journal Name");
		lblJournalName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJournalName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJournalName.setBounds(47, 97, 96, 23);
		panelJournal.add(lblJournalName);

		JLabel lblJournalSearch = new JLabel("Journal Search");
		lblJournalSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJournalSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblJournalSearch.setBounds(191, 34, 115, 20);
		panelJournal.add(lblJournalSearch);

		jyearTextField = new JTextField();
		jyearTextField.setBounds(162, 138, 192, 27);
		panelJournal.add(jyearTextField);
		jyearTextField.setColumns(10);
		Numvalidator(jyearTextField);

		jkeywordTextField = new JTextField();
		jkeywordTextField.setColumns(10);
		jkeywordTextField.setBounds(162, 177, 192, 27);
		panelJournal.add(jkeywordTextField);

		journalTextfield = new JTextField();
		journalTextfield.setColumns(10);
		journalTextfield.setBounds(162, 94, 192, 27);
		panelJournal.add(journalTextfield);

		btnJournalSearchButton = new JButton("Search");
		btnJournalSearchButton.addActionListener(new JournalSearchActionListner());
		btnJournalSearchButton.setBounds(191, 395, 115, 29);
		panelJournal.add(btnJournalSearchButton);
		
		JLabel lblJournalYear = new JLabel("Year");
		lblJournalYear.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblJournalYear.setHorizontalAlignment(SwingConstants.CENTER);
		lblJournalYear.setBounds(110, 140, 33, 16);
		panelJournal.add(lblJournalYear);

		JLabel lblJournalKeyword = new JLabel("Keyword");
		lblJournalKeyword.setHorizontalTextPosition(SwingConstants.LEADING);
		lblJournalKeyword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJournalKeyword.setBounds(78, 179, 61, 16);
		panelJournal.add(lblJournalKeyword);

		volumeField = new JTextField();
		volumeField.setColumns(10);
		volumeField.setBounds(162, 217, 192, 27);
		panelJournal.add(volumeField);

		JLabel lblVolume = new JLabel("Volume");
		lblVolume.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVolume.setBounds(78, 223, 61, 16);
		panelJournal.add(lblVolume);

		JLabel lblJournalSelectIfThe = new JLabel("Has the author served as the following?");
		lblJournalSelectIfThe.setBounds(126, 269, 280, 33);
		panelJournal.add(lblJournalSelectIfThe);

		chckbxAssociateEditor = new JCheckBox("Associate Editor");
		chckbxAssociateEditor.setEnabled(true);
		chckbxAssociateEditor.setBounds(162, 314, 192, 23);
		panelJournal.add(chckbxAssociateEditor);

		chckbxEditorinChief = new JCheckBox("Editor in Chief");
		chckbxEditorinChief.setEnabled(true);
		chckbxEditorinChief.setBounds(162, 349, 192, 23);
		panelJournal.add(chckbxEditorinChief);
		
		// Panel for Search Results
		panelResult = new JPanel();
		tabbedPaneSearch.addTab("Search Results", null, panelResult, null);
		panelResult.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(UIManager.getBorder("ScrollPane.border"));
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 23, 467, 398);
		panelResult.add(scrollPane);
		
		listModel = new DefaultListModel<String>();
		
		list = new JList<String>(listModel);
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		list.addListSelectionListener(e -> {
			// To avoid invoking of this method twice.
            if (!e.getValueIsAdjusting()) {
                JPanel jpMain = new JPanel(new BorderLayout()); // Create the main.
                jpMain.setPreferredSize(new Dimension(400, 200));
                jpMain.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
                
                Author selectedAuthor = null;

                for (Author author : authorDetails) {
                    if (author.getName().equalsIgnoreCase(list.getSelectedValue())) {
                        selectedAuthor = author;
                    }
                }

                if (selectedAuthor == null) { // If author not found.
                    return; // Just return.
                }

                // Build center panel with all labels.
                JPanel jpCenter = new JPanel(new GridLayout(7, 2));
                jpCenter.add(new JLabel("Name: "));
                jpCenter.add(new JLabel(list.getSelectedValue()));
                jpCenter.add(new JLabel("Number of Conference Papers: "));
                jpCenter.add(new JLabel(selectedAuthor.getNumberOfConferencePaperPublished() + ""));
                jpCenter.add(new JLabel("Number of Journal Papers: "));
                jpCenter.add(new JLabel(selectedAuthor.getNumberOfJournalPaperPublished() + ""));
                jpCenter.add(new JLabel("Number of Citations: "));
                jpCenter.add(new JLabel(selectedAuthor.getCitations() + ""));
                jpCenter.add(new JLabel("University:"));
                jpCenter.add(new JLabel(selectedAuthor.getUniversityName()));
                jpCenter.add(new JLabel("HomepageURL:"));
                jpCenter.add(new JLabel(selectedAuthor.getHomePageURL()));
                jpCenter.add(new JLabel("Region:"));
                jpCenter.add(new JLabel(selectedAuthor.getRegion()));

                // Add center panel to the main panel.
                jpMain.add(jpCenter, BorderLayout.CENTER);


                // Create frame to display and add the main panel to it.
                JFrame jf = new JFrame("Profile Lookup - " + list.getSelectedValue());
                jf.add(jpMain);
                jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jf.pack();
                jf.setLocationRelativeTo(null);
                jf.setVisible(true);
            }
        });
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new BackToSearchActionListner());
		btnBack.setBounds(184, 433, 119, 29);
		panelResult.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Author Results");
		lblNewLabel.setBounds(189, 0, 99, 30);
		panelResult.add(lblNewLabel);
		
		frame.setVisible(true);
	}

	// Validate the Year field with numbers
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
	
	// Implements Search Button in Conference Search
	class ConferenceSearchActionListner implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			//btnConferenceSearchButton.setEnabled(false);
			SearchParameters searchParameters = generateConferenceSearchParameters();
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
			
		
			authorDetails = queryEngine.queryInfo(searchParameters);
			tabbedPaneSearch.setSelectedIndex(2);
			listModel.removeAllElements();
			authorNames = new ArrayList<String>();
	        for(Author author: authorDetails){
	        	listModel.addElement(author.getName());
	        }
	        //btnConferenceSearchButton.setEnabled(true);
		}
	}

	// Generate Conference Search Parameters
	public SearchParameters generateConferenceSearchParameters() {
		SearchParameters searchParameters = new SearchParameters();

		searchParameters.setSearchType("conference");
		searchType = "conference";
		
		if (chckbxServedAsGeneral.isSelected())
			searchParameters.setGeneralChair(true);
		if (chckbxCommitteMember.isSelected())
			searchParameters.setCommitteeMember(true);
		if (chckbxConferenceChair.isSelected())
			searchParameters.setConferenceChair(true);
		if (chckbxProgramChair.isSelected())
			searchParameters.setProgramChair(true);

		String conferenceNames = conferenceTextField.getText();
		ArrayList<String> conferenceNamesList = new ArrayList<String>();
		searchParameters.setConferenceNames(conferenceNamesList);
		if (!conferenceNames.isEmpty()) {
			String[] conferenceNamesArray = conferenceNames.split(",");
			for (int i = 0; i < conferenceNamesArray.length; i++) {
				conferenceNamesList.add(conferenceNamesArray[i].trim());
			}
			searchParameters.setConferenceNames(conferenceNamesList);
		}
		

		String keywordsString = keywordTextField.getText();
		ArrayList<String> keywordList = new ArrayList<String>();
		searchParameters.setKeywords(keywordList);
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
		
		return searchParameters;
	}
	
	// Implements Search Button in Journal Search
	class JournalSearchActionListner implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			//btnJournalSearchButton.setEnabled(false);
			SearchParameters searchParameters = generateJournalSearchParameters();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            if (searchParameters.getYearOfPublication() < 1900){
				JOptionPane.showMessageDialog(null,
						"Searched year is out of scope!", "WARNING!!",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (searchParameters.getYearOfPublication() > year){
				JOptionPane.showMessageDialog(null,
						"Searched year is in future!", "WARNING!!",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (searchParameters.isEmpty()){
				if(searchParameters.getVolume()!=null){
					JOptionPane.showMessageDialog(null,
							"Cannot search with volume alone!", "WARNING!!",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(null,
						"No Search parameters specified!", "WARNING!!",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			System.out.println(searchParameters.toString());
			authorDetails = queryEngine.queryInfo(searchParameters);
			tabbedPaneSearch.setSelectedIndex(2);
			listModel.removeAllElements();
			authorNames = new ArrayList<String>();
	        for(Author author: authorDetails){
	        	listModel.addElement(author.getName());
	        }
	        //btnJournalSearchButton.setEnabled(true);
		}
	}
	
	// Generate Journal Search Parameters
	public SearchParameters generateJournalSearchParameters() {
		SearchParameters searchParameters = new SearchParameters();

		searchParameters.setSearchType("journal");
		searchType = "journal";

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

		String keywordsString = jkeywordTextField.getText();
		ArrayList<String> keywordList = new ArrayList<String>();
		searchParameters.setKeywords(keywordList);
		if (!keywordsString.isEmpty()) {
			String[] keywordsArray = keywordsString.split(",");
			for (int i = 0; i < keywordsArray.length; i++) {
				keywordList.add(keywordsArray[i].trim());
			}
			searchParameters.setKeywords(keywordList);
		}

		String year = jyearTextField.getText();
		if (!year.isEmpty())
			searchParameters.setYearOfPublication(Integer.parseInt(year));

		String volume = volumeField.getText();
		if (!volume.isEmpty())
			searchParameters.setVolume(volume);

		return searchParameters;
	}
	
	
	class BackToSearchActionListner implements ActionListener{
	       public void actionPerformed(ActionEvent e){
	    	   if (searchType.equals("conference")){
	    		   tabbedPaneSearch.setSelectedIndex(0);
	    	   }
	    	   if (searchType.equals("journal")){
		    	   tabbedPaneSearch.setSelectedIndex(1);
	    	   }
	       }
	}
	
	public static UserInterface ui;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ui = new UserInterface();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}