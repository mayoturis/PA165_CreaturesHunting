package cz.muni.fi.pa165.rest.exception;

import org.springframework.dao.DataAccessException;


/**
 * Created by zeman on 16-Dec-16.
 */
public class RestException extends RuntimeException {
	public RestException(DataAccessException ex) {
		super("An exception has been thrown while performing database operation.", ex);
	}

	public RestException() {

	}
}
