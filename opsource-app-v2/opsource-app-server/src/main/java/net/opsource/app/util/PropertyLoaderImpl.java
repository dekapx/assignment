package net.opsource.app.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoaderImpl implements PropertyLoader {
    private String propertyFile;
    private Properties properties;

    public PropertyLoaderImpl(final String propertyFile) {
        this.propertyFile = propertyFile;
        init();
    }

    private void init() {
        properties = new Properties();
        try {
            final InputStream inputStream = ClassLoader.getSystemResourceAsStream(this.propertyFile);
            properties.load(inputStream);
        } catch (final FileNotFoundException e) {
            throw new PropertyLoaderException(propertyFile, e);
        } catch (final IOException e) {
            throw new PropertyLoaderException(propertyFile, e);
        }
    }

    public String getProperty(final String key) {
        return properties.getProperty(key);
    }
}
