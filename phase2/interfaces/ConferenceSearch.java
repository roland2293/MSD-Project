/*
 * This class represents the search parameters for the search of 
 * authors for a particular conference.
 */
public class ConferenceSearch extends SearchParameters {
	private String name;
	private String acronym;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
}