package net.opsource.app.db;

public enum JdbcConfigParams {
    JDBC_DRIVERCLASS("jdbc.driverClass"),
    JDBC_URL("jdbc.url"),
    JDBC_USERNAME("jdbc.username"),
    JDBC_PASSWORD("jdbc.password");

    private String configParam;

    private JdbcConfigParams(final String configParam) {
        this.configParam = configParam;
    }

    public String asString() {
        return configParam;
    }
}
