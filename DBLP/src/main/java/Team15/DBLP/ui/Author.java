package Team15.DBLP.ui;

public class Author {

    private String Name;
    private int yearsOfExperience;
    private String Nationality;
    private int numberOfPaperPublished;
    private boolean isEligible;
    private String universityName;


    public Author(String name, int yearsOfExperience, String nationality, int numberOfPaperPublished, boolean isEligible, String universityName) {
        Name = name;
        this.yearsOfExperience = yearsOfExperience;
        Nationality = nationality;
        this.numberOfPaperPublished = numberOfPaperPublished;
        this.isEligible = isEligible;
        this.universityName = universityName;
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

    public int getNumberOfPaperPublished() {
        return numberOfPaperPublished;
    }

    public void setNumberOfPaperPublished(int numberOfPaperPublished) {
        this.numberOfPaperPublished = numberOfPaperPublished;

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
}
