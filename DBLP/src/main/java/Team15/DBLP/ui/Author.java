package Team15.DBLP.ui;

import java.util.HashSet;

public class Author {

	private String Name;
	private int yearsOfExperience;
	private String Nationality;
	private int numberOfConferencePaperPublished;
	private boolean isEligible;
	private String universityName;
	private String region;
	private String homePageURL;
	private HashSet<String> area;
	private int numberOfJournalPaperPublished;
	private int citations;

	public int getCitations() {
		return citations;
	}

	public void setCitations(int citations) {
		this.citations = citations;
	}

	public HashSet<String> getArea() {
		return area;
	}

	public void setArea(HashSet<String> area) {
		this.area = area;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getHomePageURL() {
		return homePageURL;
	}

	public void setHomePageURL(String homePageURL) {
		this.homePageURL = homePageURL;
	}

	public Author() {
		this.Name = null;
		this.yearsOfExperience = -1;
		this.Nationality = null;
		this.numberOfConferencePaperPublished = -1;
		this.isEligible = false;
		this.universityName = null;
		this.region = null;
		this.homePageURL = null;
		this.area = new HashSet<String>();
		this.numberOfJournalPaperPublished = -1;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getNationality() {
		return Nationality;
	}

	public void setNationality(String nationality) {
		Nationality = nationality;
	}

	public int getNumberOfConferencePaperPublished() {
		return numberOfConferencePaperPublished;
	}

	public void setNumberOfConferencePaperPublished(
			int numberOfConferencePaperPublished) {
		this.numberOfConferencePaperPublished = numberOfConferencePaperPublished;
	}

	public int getNumberOfJournalPaperPublished() {
		return numberOfJournalPaperPublished;
	}

	public void setNumberOfJournalPaperPublished(
			int numberOfJournalPaperPublished) {
		this.numberOfJournalPaperPublished = numberOfJournalPaperPublished;
	}

	public boolean isEligible() {
		return isEligible;
	}

	public void setEligible(boolean eligible) {
		isEligible = eligible;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	@Override
	public String toString() {
		return "Author [Name=" + Name + ", yearsOfExperience="
				+ yearsOfExperience + ", Nationality=" + Nationality
				+ ", numberOfConferencePaperPublished="
				+ numberOfConferencePaperPublished + ", isEligible="
				+ isEligible + ", universityName=" + universityName
				+ ", region=" + region + ", homePageURL=" + homePageURL
				+ ", area=" + area + ", numberOfJournalPaperPublished="
				+ numberOfJournalPaperPublished + "]";
	}

}
