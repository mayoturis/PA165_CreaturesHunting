package cz.muni.fi.pa165.service.exception;

import org.springframework.dao.DataAccessException;

/**
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
