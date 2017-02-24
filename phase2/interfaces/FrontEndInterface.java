import java.util.List;

public interface FrontEndInterface {

	SearchParameters createSearchParameters();

	void search(SearchParameters searchParameters);

	List<Author> getResults();

}
