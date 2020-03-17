package com.dekapx.serverservice.util;

import java.io.IOException;
import java.util.Properties;

public enum PropertiesManager {
    INSTANCE;

    private Properties properties;
    private void init() {
        try {
            properties = new Properties();
            properties.load(ClassLoader.getSystemResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(final String key) {
        if (properties == null) {
            init();
        }

        return properties.getProperty(key);
    }
}
