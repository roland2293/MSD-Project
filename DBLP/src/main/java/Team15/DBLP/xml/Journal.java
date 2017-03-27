package Team15.DBLP.xml;

import java.util.ArrayList;

/**
 * @author paurav
 *
 */
public class Journal {
	public String key;
	public String title;
	public int year;
	public String volume;
	public String journal;
	public ArrayList<String> authors;
	public ArrayList<String> citations;

	public static final int OTHER = 0;
	public static final int ARTICLE = 1;
	public static final int AUTHOR = 2;
	public static final int TITLE = 3;
	public static final int YEAR = 4;
	public static final int CITE = 5;
	public static final int JOURNAL = 6;
	public static final int VOLUME = 7;

	public String toString(){
		return "title: " + title + " authors: " + authors.toString() +
				" journal: " + journal + " year: " + year + " volume:" + volume +" key: " + key;
	}
	public static int getElement(String name) {
		if (name.equals("article")) {
			return ARTICLE;
		} else if (name.equals("author")) {
			return AUTHOR;
		} else if (name.equals("title") || name.equals("sub") || name.equals("sup") || name.equals("i")||name.equals("tt")) {
			return TITLE;
		} else if (name.equals("year")) {
			return YEAR;
		} else if (name.equals("cite")) {
			return CITE;
		}
		 else if (name.equals("volume")) {
				return VOLUME;
		}else if (name.equals("journal") || name.equals("booktitle")) {
			return JOURNAL;
		} else {
			return OTHER;
		}
	}

	public static String getElementName(int i) {
		if (i == ARTICLE) {
			return "article";
		} else if (i == AUTHOR) {
			return "author";
		} else if (i == TITLE) {
			return "name";
		} else if (i == YEAR) {
			return "year";
		} else if (i == CITE) {
			return "cite";
		} else if (i == JOURNAL) {
			return "journal";
		}
		else if (i == VOLUME) {
				return "volume";
		}else {
			return "other";
		}
	}

	public Journal() {
		key = "";
		title = "";
		journal = "";
		year = 0;
		volume = "";
		authors = new ArrayList<String>();
		citations = new ArrayList<String>();
	}

}
