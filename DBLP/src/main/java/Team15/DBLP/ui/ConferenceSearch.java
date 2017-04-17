package Team15.DBLP.ui;

import Team15.DBLP.QueryEngine.QueryEngine;
import Team15.DBLP.QueryEngine.SearchParameters;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConferenceSearch extends JFrame implements ActionListener {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField yearTextField;
	private JTextField keywordTextField;
	private JTextField conferenceTextField;
	private JCheckBox chckbxServedAsGeneral;
	private JCheckBox chckbxProgramChair;
	private JCheckBox chckbxConferenceChair;
	private JCheckBox chckbxCommitteMember;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConferenceSearch frame = new ConferenceSearch();
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
	public ConferenceSearch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		frame = this;

		JLabel lblAuthorName = new JLabel("Conference Name");
		lblAuthorName.setBounds(149, 51, 111, 21);
		contentPane.add(lblAuthorName);

		JLabel lblConferenceSearcg = new JLabel("Conference Search");
		lblConferenceSearcg.setBounds(135, 6, 149, 16);
		contentPane.add(lblConferenceSearcg);

		yearTextField = new JTextField();
		yearTextField.setBounds(291, 87, 203, 28);
		contentPane.add(yearTextField);
		yearTextField.setColumns(10);
		numValidator(yearTextField);

		keywordTextField = new JTextField();
		keywordTextField.setColumns(10);
		keywordTextField.setBounds(291, 127, 203, 28);
		contentPane.add(keywordTextField);

		conferenceTextField = new JTextField();
		conferenceTextField.setColumns(10);
		conferenceTextField.setBounds(291, 47, 203, 28);
		contentPane.add(conferenceTextField);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ConferenceSearchActionListner());
		btnNewButton.setBounds(274, 364, 117, 29);
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 117, 416);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblHome = new JLabel("Home");
		lblHome.setBounds(6, 6, 61, 16);
		panel.add(lblHome);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(6, 45, 61, 16);
		panel.add(lblSearch);

		JLabel lblConference = new JLabel("Conference");
		lblConference.setBounds(26, 79, 75, 16);
		panel.add(lblConference);

		JLabel lblJournal = new JLabel("Journal");
		lblJournal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				JournalSearch jFrame = new JournalSearch();
				jFrame.setVisible(true);
			}
		});
		lblJournal.setBounds(26, 107, 66, 16);
		panel.add(lblJournal);

		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(228, 93, 33, 16);
		contentPane.add(lblYear);

		JLabel lblKeyword = new JLabel("Keyword");
		lblKeyword.setBounds(204, 133, 61, 16);
		contentPane.add(lblKeyword);

		chckbxServedAsGeneral = new JCheckBox("General Chair");
		chckbxServedAsGeneral.setBounds(243, 212, 192, 23);
		contentPane.add(chckbxServedAsGeneral);

		JLabel lblSelectIfThe = new JLabel(
				"Has the author served as the following?");
		lblSelectIfThe.setBounds(194, 167, 280, 33);
		contentPane.add(lblSelectIfThe);

		chckbxProgramChair = new JCheckBox("Program Chair");
		chckbxProgramChair.setBounds(243, 247, 192, 23);
		contentPane.add(chckbxProgramChair);

		chckbxConferenceChair = new JCheckBox("Conference Chair");
		chckbxConferenceChair.setBounds(243, 282, 192, 23);
		contentPane.add(chckbxConferenceChair);

		chckbxCommitteMember = new JCheckBox("Committee Member");
		chckbxCommitteMember.setBounds(243, 317, 192, 23);
		contentPane.add(chckbxCommitteMember);
	}

	public void numValidator(JTextField txtField) {
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

	class ConferenceSearchActionListner implements ActionListener {

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
			AuthorResult authorFrame = new AuthorResult(authorNames,
					"conference", searchParameters);
			frame.setVisible(false);
			authorFrame.setVisible(true);

		}
	}

	public SearchParameters generateSearchParameters() {
		SearchParameters searchParameters = new SearchParameters();

		searchParameters.setSearchType("conference");

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

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
