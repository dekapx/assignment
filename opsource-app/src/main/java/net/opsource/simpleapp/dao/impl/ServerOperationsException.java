package net.opsource.simpleapp.dao.impl;

@SuppressWarnings("serial")
public class ServerOperationsException extends RuntimeException {

	public ServerOperationsException(String message, Throwable cause) {
		super(message, cause);
	}
}
