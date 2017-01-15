package cz.muni.fi.pa165.sampleData;

import java.io.IOException;
import java.text.ParseException;

/**
 * @ author Ondrej Zeman
 */
public interface SampleDataLoader {
	/**
	 * Loads sample data to the database.
	 *
	 * @throws IOException
	 */
	void loadData() throws IOException, ParseException;
}
