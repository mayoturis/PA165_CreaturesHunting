package cz.muni.fi.pa165.sampleData;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by zeman on 12-Jan-17.
 */
public interface SampleDataLoader {
	/**
	 * Loads sample data to the database.
	 *
	 * @throws IOException
	 */
	void loadData() throws IOException, ParseException;
}
