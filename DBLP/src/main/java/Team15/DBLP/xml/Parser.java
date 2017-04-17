/* SAX Parser fro DBLP*/

package Team15.DBLP.xml;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import Team15.DBLP.db.DBConnection;

/**
 * Parse for parsing the DBLP data. It uses SAX parser to parse the xml.
 * 
 * @author paurav
 *
 */
public class Parser {
	private Connection conn;
	private int curElement = -1;
	private int ancestor = -1;
	private Paper paper;
	private Conference conf;

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Conference getConf() {
		return conf;
	}

	public void setConf(Conference conf) {
		this.conf = conf;
	}

	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	private Journal journal;
	int line = 0;
	PreparedStatement stmt_inproc, stmt_conf, stmt_author, stmt_cite,
			stmt_injournal;
	int errors = 0;
	StringBuffer author;

	private class ConfigHandler extends DefaultHandler {
		public void startElement(String namespaceURI, String localName,
				String rawName, Attributes atts) throws SAXException {
			if (rawName.equals("inproceedings")) {
				ancestor = Element.INPROCEEDING;
				curElement = Paper.INPROCEEDING;
				paper = new Paper();
				paper.key = atts.getValue("key");
			} else if (rawName.equals("proceedings")) {
				ancestor = Element.PROCEEDING;
				curElement = Conference.PROCEEDING;
				conf = new Conference();
				conf.key = atts.getValue("key");
			} else if (rawName.equals("article")) {
				ancestor = Element.ARTICLE;
				curElement = Journal.ARTICLE;
				journal = new Journal();
				journal.key = atts.getValue("key");
			} else if (rawName.equals("author")
					&& (ancestor == Element.INPROCEEDING
							|| ancestor == Element.ARTICLE)) {
				author = new StringBuffer();
			}

			if (ancestor == Element.INPROCEEDING) {
				curElement = Paper.getElement(rawName);
			} else if (ancestor == Element.PROCEEDING) {
				curElement = Conference.getElement(rawName);
			} else if (ancestor == Element.ARTICLE) {
				curElement = Journal.getElement(rawName);
			} else if (ancestor == -1) {
				ancestor = Element.OTHER;
				curElement = Element.OTHER;
			} else {
				curElement = Element.OTHER;
			}

			line++;
		}

		public void characters(char[] ch, int start, int length)
				throws SAXException {
			if (ancestor == Element.INPROCEEDING) {
				String str = new String(ch, start, length).trim();
				if (curElement == Paper.AUTHOR) {
					author.append(str);
				} else if (curElement == Paper.CITE) {
					paper.citations.add(str);
				} else if (curElement == Paper.CONFERENCE) {
					paper.conference = str;
				} else if (curElement == Paper.TITLE) {
					paper.title += str;
				} else if (curElement == Paper.YEAR) {
					paper.year = Integer.parseInt(str);
				}
			} else if (ancestor == Element.ARTICLE) {
				String str = new String(ch, start, length).trim();
				if (curElement == Journal.AUTHOR) {
					author.append(str);
				} else if (curElement == Journal.CITE) {
					journal.citations.add(str);
				} else if (curElement == Journal.JOURNAL) {
					journal.journal = str;
				} else if (curElement == Journal.TITLE) {
					journal.title += str;
				} else if (curElement == Journal.YEAR) {
					journal.year = Integer.parseInt(str);
				} else if (curElement == Journal.VOLUME) {
					journal.volume += str;
				}
			} else if (ancestor == Element.PROCEEDING) {
				String str = new String(ch, start, length).trim();
				if (curElement == Conference.CONFNAME) {
					conf.name = str;
				} else if (curElement == Conference.CONFDETAIL) {
					conf.detail = str;
				}
			}
		}

		public void endElement(String namespaceURI, String localName,
				String rawName) throws SAXException {
			if (rawName.equals("author") && ancestor == Element.INPROCEEDING) {
				paper.authors.add(author.toString().trim());
			}
			if (rawName.equals("author") && ancestor == Element.ARTICLE) {
				journal.authors.add(author.toString().trim());
			}

			if (Element.getElement(rawName) == Element.INPROCEEDING) {
				ancestor = -1;
				try {
					if (paper.title.equals("") || paper.conference.equals("")
							|| paper.year == 0 || paper.authors.isEmpty()) {
						System.out.println("Error in parsing " + paper);
						errors++;
						return;
					}
					stmt_inproc.setString(1, paper.title);
					stmt_inproc.setInt(2, paper.year);
					stmt_inproc.setString(3, paper.conference);
					stmt_inproc.setString(4, paper.key);
					stmt_inproc.addBatch();

					for (String author : paper.authors) {
						stmt_author.setString(1, author);
						stmt_author.setString(2, paper.key);
						stmt_author.addBatch();
					}
					for (String cited : paper.citations) {
						if (!cited.equals("...")) {
							stmt_cite.setString(1, paper.key);
							stmt_cite.setString(2, cited);
							stmt_cite.addBatch();
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("line:" + line);
					System.exit(0);
				}
			} else if (Element.getElement(rawName) == Element.ARTICLE) {
				ancestor = -1;
				try {
					if (journal.title.equals("") || journal.journal.equals("")
							|| journal.year == 0 || journal.authors.isEmpty()) {
						System.out.println("Error in parsing " + journal);
						errors++;
						return;
					}
					stmt_injournal.setString(1, journal.title);
					stmt_injournal.setInt(2, journal.year);
					stmt_injournal.setString(3, journal.journal);
					stmt_injournal.setString(4, journal.key);
					stmt_injournal.setString(5, journal.volume);
					stmt_injournal.addBatch();

					for (String author : journal.authors) {
						stmt_author.setString(1, author);
						stmt_author.setString(2, journal.key);
						stmt_author.addBatch();
					}
					for (String cited : journal.citations) {
						if (!cited.equals("...")) {
							stmt_cite.setString(1, journal.key);
							stmt_cite.setString(2, cited);
							stmt_cite.addBatch();
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("line:" + line);
					System.exit(0);
				}
			} else if (Element.getElement(rawName) == Element.PROCEEDING) {
				ancestor = -1;
				try {
					if (conf.name.equals(""))
						conf.name = conf.detail;
					if (conf.key.equals("") || conf.name.equals("")
							|| conf.detail.equals("")) {
						System.out.println("Line:" + line);
						System.exit(0);
					}
					stmt_conf.setString(1, conf.key);
					stmt_conf.setString(2, conf.name);
					stmt_conf.setString(3, conf.detail);
					stmt_conf.addBatch();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("line:" + line);
					System.exit(0);
				}
			}

			if (line % 10000 == 0) {
				try {
					stmt_inproc.executeBatch();
					stmt_conf.executeBatch();
					stmt_author.executeBatch();
					stmt_cite.executeBatch();
					stmt_injournal.executeBatch();
					conn.commit();
					System.out.println("Processing " + line);
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
			}
		}

		private void Message(String mode, SAXParseException exception) {
			System.out.println(mode + " Line: " + exception.getLineNumber()
					+ " URI: " + exception.getSystemId() + "\n" + " Message: "
					+ exception.getMessage());
		}

		public void warning(SAXParseException exception) throws SAXException {
			Message("**Parsing Warning**\n", exception);
			throw new SAXException("Warning encountered");
		}

		public void error(SAXParseException exception) throws SAXException {
			Message("**Parsing Error**\n", exception);
			throw new SAXException("Error encountered");
		}

		public void fatalError(SAXParseException exception)
				throws SAXException {
			Message("**Parsing Fatal Error**\n", exception);
			throw new SAXException("Fatal Error encountered");
		}
	}

	public Parser(String uri, Boolean test) throws SQLException {
		try {
			System.out.println("Parsing...");
			conn = DBConnection.getConn();
			conn.setAutoCommit(false);
			stmt_inproc = conn.prepareStatement(
					"insert into paper(title,year,conference,paper_key) values (?,?,?,?)");
			stmt_injournal = conn.prepareStatement(
					"insert into article(title,year,journal,journal_key,volume) values (?,?,?,?,?)");

			stmt_author = conn.prepareStatement(
					"insert into author(name,paper_key) values (?,?)");

			stmt_cite = conn.prepareStatement(
					"insert into citation(paper_cite_key,paper_cited_key) values (?,?)");

			stmt_conf = conn.prepareStatement(
					"insert into conference(conf_key,name,detail) values (?,?,?)");

			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
			ConfigHandler handler = new ConfigHandler();
			parser.getXMLReader().setFeature(
					"http://xml.org/sax/features/validation", true);
			parser.parse(new File(uri), handler);
			if (!test) {
				try {
					stmt_inproc.executeBatch();
					stmt_conf.executeBatch();
					stmt_author.executeBatch();
					stmt_cite.executeBatch();
					stmt_injournal.executeBatch();
					conn.commit();
					System.out.println("Processed " + line);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("num of errors: " + errors);
		} catch (IOException e) {
			System.out.println("Error reading URI: " + e.getMessage());
		} catch (SAXException e) {
			System.out.println("Error in parsing: " + e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println("Error in XML parser configuration: " + e
					.getMessage());
		} finally {
			conn.close();
		}
	}

	public static void main(String[] args) throws SQLException,
			ClassNotFoundException, IOException {
		Long start = System.currentTimeMillis();
		Parser p = new Parser(args[0], false);
		// CommitteesParser committeesParser = new CommitteesParser();
		// committeesParser.setFolder(args[1]);
		// committeesParser.parser(false);

		Long end = System.currentTimeMillis();
		System.out.println("Used: " + (end - start) / 1000 + " seconds");

	}
}
