package net.opsource.app.command.server;

import net.opsource.app.model.Server;

public class AddServerRequest {
    private Server server;

    public Server getServer() {
        return server;
    }

    private AddServerRequest(AddServerRequestBuilder builder) {
        this.server = builder.server;
    }

    public static AddServerRequestBuilder builder() {
        return new AddServerRequestBuilder();
    }

    public static class AddServerRequestBuilder {
        private Server server;

        public AddServerRequestBuilder withServer(final Server server) {
            this.server = server;
            return this;
        }

        public AddServerRequest build() {
            return new AddServerRequest(this);
        }
    }
}
