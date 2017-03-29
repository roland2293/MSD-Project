package Team15.DBLP.xml;

import java.util.List;

public class SearchParameters {

	private List<String> conferenceNames;
	private List<String> journalNames;
	private List<String> keywords;
	private List<String> authorNames;
	private String volume;
	private int yearOfPublication;
	private boolean generalChair;
	private boolean programChair;
	private boolean conferenceChair;
	private boolean committeeMember;
	private boolean associateEditor;
	private boolean editorinChief;
	private String searchType;
	
	

	@Override
	public String toString() {
		return "SearchParameters [conferenceNames=" + conferenceNames
				+ ", journalNames=" + journalNames + ", keywords=" + keywords
				+ ", authorNames=" + authorNames + ", volume=" + volume
				+ ", yearOfPublication=" + yearOfPublication + ", generalChair="
				+ generalChair + ", programChair=" + programChair
				+ ", conferenceChair=" + conferenceChair + ", committeeMember="
				+ committeeMember + ", associateEditor=" + associateEditor
				+ ", editorinChief=" + editorinChief + ", searchType="
				+ searchType + "]";
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public boolean isAssociateEditor() {
		return associateEditor;
	}

	public void setAssociateEditor(boolean associateEditor) {
		this.associateEditor = associateEditor;
	}

	public boolean isEditorinChief() {
		return editorinChief;
	}

	public void setEditorinChief(boolean editorinChief) {
		this.editorinChief = editorinChief;
	}

	public boolean isGeneralChair() {
		return generalChair;
	}

	public void setGeneralChair(boolean generalChair) {
		this.generalChair = generalChair;
	}

	public boolean isProgramChair() {
		return programChair;
	}

	public void setProgramChair(boolean programChair) {
		this.programChair = programChair;
	}

	public boolean isConferenceChair() {
		return conferenceChair;
	}

	public void setConferenceChair(boolean conferenceChair) {
		this.conferenceChair = conferenceChair;
	}

	public boolean isCommitteeMember() {
		return committeeMember;
	}

	public void setCommitteeMember(boolean committeeMember) {
		this.committeeMember = committeeMember;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<String> getAuthorNames() {
		return authorNames;
	}

	public List<String> getConferenceNames() {
		return conferenceNames;
	}

	public void setConferenceNames(List<String> conferenceNames) {
		this.conferenceNames = conferenceNames;
	}

	public List<String> getJournalNames() {
		return journalNames;
	}

	public void setJournalNames(List<String> journalNames) {
		this.journalNames = journalNames;
	}

	public void setAuthorNames(List<String> authorNames) {
		this.authorNames = authorNames;
	}

	public int getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(int yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

}
