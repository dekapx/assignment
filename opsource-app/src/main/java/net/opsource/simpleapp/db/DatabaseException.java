package net.opsource.simpleapp.db;

@SuppressWarnings("serial")
public class DatabaseException extends RuntimeException {

	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

}
