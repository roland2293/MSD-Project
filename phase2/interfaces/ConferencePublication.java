/*
 * This class represents the publications for conference, 
 * also it extends Publication.
 */
public class ConferencePublication extends Publication {

	private Conference conference;

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}
}
