package net.opsource.app.util;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(final String message) {
        super(message);
    }
}
