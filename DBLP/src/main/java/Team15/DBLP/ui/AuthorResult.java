package Team15.DBLP.ui;

import Team15.DBLP.QueryEngine.SearchParameters;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AuthorResult extends JFrame {

    private JFrame frame;
    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public AuthorResult(List<Author> authors, String searchType, SearchParameters searchParameters) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 581, 481);
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

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(135, 59, 440, 394);
        contentPane.add(scrollPane);

        // Use JList instead of JTextArea to make each row as selectable.
        List<String> authorNames = new ArrayList<String>();
        for(Author author: authors){
            authorNames.add(author.getName());
        }
        JList<String> listAuthors = new JList<>(generateString(authorNames));

        // Add listener to jlist to open a new window once the user clicks on
        // the row.
        listAuthors.addListSelectionListener(e -> {

            // To avoid invoking of this method twice.
            if (!e.getValueIsAdjusting()) {
                JPanel jpMain = new JPanel(new BorderLayout()); // Create the main panel
                jpMain.setPreferredSize(new Dimension(400, 200));
                jpMain.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

                Author selectedAuthor = null;

                for (Author author : authors) {
                    if (author.getName().equalsIgnoreCase(listAuthors.getSelectedValue())) {
                        selectedAuthor = author;
                    }
                }

                if (selectedAuthor == null) { // If author not found.
                    return; // Just return.
                }

                // Build center panel with all labels.
                JPanel jpCenter = new JPanel(new GridLayout(7, 2));
                jpCenter.add(new JLabel("Name: "));
                jpCenter.add(new JLabel(listAuthors.getSelectedValue()));
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
                JFrame jf = new JFrame("Profile Lookup - " + listAuthors.getSelectedValue());
                jf.add(jpMain);
                jf.pack();
                jf.setLocationRelativeTo(null);
                jf.setVisible(true);
            }
        });

        scrollPane.setViewportView(listAuthors);

        JButton btnBack = new JButton("Back");
        if (searchType.equals("conference")) {
            btnBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    ConferenceSearch cFrame = new ConferenceSearch();
                    cFrame.setVisible(true);
                }
            });
        } else {
            btnBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    JournalSearch jFrame = new JournalSearch();
                    jFrame.setVisible(true);
                }
            });
        }
        btnBack.setBounds(458, 18, 117, 29);
        contentPane.add(btnBack);

        JLabel lblNewLabel = new JLabel("Author Result");
        lblNewLabel.setBounds(135, 23, 86, 16);
        contentPane.add(lblNewLabel);

    }

    // This method will generate array of authors' names
    // instead of string to have an option instantiating
    // JList (JList has constructor with String [] as a parameter).
    private String[] generateString(List<String> authors) {
        String[] names = new String[authors.size()];
        names = authors.toArray(names);
        return names;
    }

}
