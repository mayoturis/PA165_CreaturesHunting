package cz.muni.fi.pa165.service.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * Exception caused by persisting data to database.
 *
 * @author Marek Turis
 */
public class PersistenceException extends DataAccessException {

	public PersistenceException(String msg) {
		super(msg);
	}

	public PersistenceException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
