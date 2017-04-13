package Team15.DBLP.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuthorResult extends JFrame {

	private JFrame frame;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AuthorResult(List<String> authors, String searchType) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(135, 30, 500, 500);
		textArea.setText(generateString(authors));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(135, 59, 430, 382);
		scrollPane.setViewportView(textArea);
		contentPane.add(scrollPane);

		JButton btnBack = new JButton("Back");
		if (searchType.equals("conference")) {
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					ConferenceSearch cFrame = new ConferenceSearch();
					cFrame.setVisible(true);
				}
			});
		}else{
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					JournalSearch jFrame = new JournalSearch();
					jFrame.setVisible(true);
				}
			});
		}
		btnBack.setBounds(458, 18, 117, 30);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Author Results");
		lblNewLabel.setBounds(135, 23, 86, 30);
		contentPane.add(lblNewLabel);

	}

	private String generateString(List<String> authors) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String author : authors)
			stringBuilder.append(author + "\n");
		return stringBuilder.toString();
	}
}
