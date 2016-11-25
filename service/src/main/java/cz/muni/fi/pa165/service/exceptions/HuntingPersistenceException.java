package cz.muni.fi.pa165.service.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * Exception caused by persisting data to database.
 *
 * @author Marek Turis
 */
public class HuntingPersistenceException extends DataAccessException {

	public HuntingPersistenceException(String msg) {
		super(msg);
	}

	public HuntingPersistenceException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
