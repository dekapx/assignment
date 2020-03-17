package net.opsource.app.model;

public class Server {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private Server(final ServerBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static ServerBuilder builder() {
        return new ServerBuilder();
    }

    public static class ServerBuilder {
        private long id;
        private String name;

        public ServerBuilder withId(final long id) {
            this.id = id;
            return this;
        }

        public ServerBuilder withName(final String name) {
            this.name = name;
            return this;
        }

        public Server build() {
            return new Server(this);
        }
    }
}
