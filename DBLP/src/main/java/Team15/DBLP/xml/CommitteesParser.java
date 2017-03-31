package Team15.DBLP.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import Team15.DBLP.db.DBConnection;

/**
 * @author paurav
 *
 */
 // CommitteesParser is used to get the Committee from a file and store it in the database.
public class CommitteesParser {
	private File folder;
	private Connection conn;
	private PreparedStatement insertCommitteeMembers;
	public ArrayList<Committee> committees;

	public ArrayList<Committee> getCommittees() {
		return committees;
	}

	public void setCommittees(ArrayList<Committee> committees) {
		this.committees = committees;
	}

	BufferedReader reader;

	public void parser(Boolean isTest) throws SQLException, IOException {
		conn = DBConnection.getConn();
		insertCommitteeMembers = conn.prepareStatement(
				"insert into committee(authorname, conferenceName, year, role) values(?,?,?,?)");
		for (File file : folder.listFiles()) {
			parseFile(file, isTest);
		}
	}

	public void parseFile(File file, Boolean isTest) throws IOException,
			SQLException {
		committees = new ArrayList<Committee>();
		String name = file.getName();
		String[] split = name.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
		String conferenceName = split[0];
		String conferenceYear = split[1];
		reader = new BufferedReader(new FileReader(file));
		Committee committee = new Committee(conferenceName, conferenceYear);
		for (String line; (line = reader.readLine()) != null;) {
			committee = parseLine(line, committee);
			committees.add(committee);
			insertCommitteeMembers.setString(1, committee.getAuthorName());
			insertCommitteeMembers.setString(2, committee.getConference());
			insertCommitteeMembers.setString(3, committee.getYear());
			insertCommitteeMembers.setString(4, committee.getRole());
			insertCommitteeMembers.addBatch();
		}
		if (!isTest)
			insertCommitteeMembers.executeBatch();
		reader.close();
	}

	public Committee parseLine(String line, Committee committee) {
		committee = new Committee(committee.getConference(), committee
				.getYear());
		if (line.contains("G:")) {
			committee.setRole("General Chair");
			committee.setAuthorName(line.split(":")[1]);
		} else if (line.contains("P:")) {
			committee.setRole("Program Chair");
			committee.setAuthorName(line.split(":")[1]);
		} else if (line.contains("E:")) {
			committee.setRole("External Review Committee");
			committee.setAuthorName(line.split(":")[1]);
		} else if (line.contains("C:")) {
			committee.setRole("Conference Chair");
			committee.setAuthorName(line.split(":")[1]);
		} else {
			committee.setRole("Committee member");
			committee.setAuthorName(line);
		}

		return committee;
	}

	public void setFolder(String path) {
		folder = new File(path);
	}

}
