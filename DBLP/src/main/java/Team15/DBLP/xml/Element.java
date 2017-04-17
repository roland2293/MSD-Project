package Team15.DBLP.xml;

/**
 * Element determines the start and stop of the xml tags
 * 
 * @author paurav
 *
 */
public class Element {
	public static final int OTHER = 0;
	public static final int INPROCEEDING = 1;
	public static final int PROCEEDING = 2;
	public static final int ARTICLE = 3;

	public static int getElement(String name) {
		if (name.equals("inproceedings")) {
			return 1;
		} else if (name.equals("proceedings")) {
			return 2;
		} else if (name.equals("article")) {
			return 3;
		} else {
			return 0;
		}
	}

	public static String getElementName(int i) {
		if (i == INPROCEEDING) {
			return "inproceedings";
		} else if (i == PROCEEDING) {
			return "proceedings";
		} else if (i == ARTICLE) {
			return "article";
		} else {
			return "other";
		}
	}
}
