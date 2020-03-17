package net.opsource.app.util;

@SuppressWarnings("serial")
public class PropertyLoaderException extends RuntimeException {
    private static final String ERROR_MESSAGE = "Unable to load properties file %s from classpath.";

    public PropertyLoaderException(final String fileName, final Throwable cause) {
        super(fileName, cause);
    }

    private String buildErrorMessage(final String fileName) {
        return String.format(ERROR_MESSAGE, fileName);
    }
}
