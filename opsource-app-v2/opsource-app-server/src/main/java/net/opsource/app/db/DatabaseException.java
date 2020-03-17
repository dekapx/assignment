package net.opsource.app.db;

@SuppressWarnings("serial")
public class DatabaseException extends RuntimeException {

	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

}
