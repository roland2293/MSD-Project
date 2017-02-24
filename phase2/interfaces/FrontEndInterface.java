import java.util.List;

/**
 * It represents interface for the fornt-end interface(UI).
 */
public interface FrontEndInterface {

	/**
	 * This method will create the search parameters object 
	 * which will be used by the other components.
	 * @return SearchParameters object
	 */
	SearchParameters createSearchParameters();

	/**
	 * This will trigger the search using the search parameters.
	 * @param searchParameters
	 */
	void search(SearchParameters searchParameters);

	/**
	 * it will get the results from the query engine and
	 * populate them.
	 * @return
	 */
	List<Author> getResults();

}
