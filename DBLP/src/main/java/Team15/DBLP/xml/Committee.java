package Team15.DBLP.xml;

/**
 * A Committee consists of Author, Role, Conference, and Year
 * 
 * @author paurav
 *
 */

public class Committee {
	private String conference;
	private String year;
	private String authorName;
	private String role;
	
	public Committee(String conference, String year) {
		this.conference = conference;
		this.year = year;
	}
	
	public String getConference() {
		return conference;
	}
	public void setConference(String conference) {
		this.conference = conference;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
