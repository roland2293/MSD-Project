
/**
 * 
 * 
 * @author paurav
 *
 */
public class Author {
	
	private String Name;
	private int yearsOfExperience;
	private String Nationality;
	private int numberOfPaperPublished;
	private boolean isEligible;
	private String conferenceName;

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

	public int getNumberOfPaperPublished() {
		return numberOfPaperPublished;
	}

	public void setNumberOfPaperPublished(int numberOfPaperPublished) {
		this.numberOfPaperPublished = numberOfPaperPublished;
	}

	public boolean isEligible() {
		return isEligible;
	}

	public void setEligible(boolean isEligible) {
		this.isEligible = isEligible;
	}

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

}
