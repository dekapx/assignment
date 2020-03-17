package net.opsource.app.command.server;

import net.opsource.app.model.Server;

import java.util.ArrayList;
import java.util.List;

public class ListServerResponse {
    private List<Server> servers;

    public List<Server> getServers() {
        return servers;
    }

    private ListServerResponse(final ListServerResponseBuilder builder) {
        this.servers = new ArrayList<>(builder.servers.size());
        this.servers.addAll(builder.servers);
    }

    public static ListServerResponseBuilder builder() {
        return new ListServerResponseBuilder();
    }

    public static class ListServerResponseBuilder {
        private List<Server> servers;

        public ListServerResponseBuilder withServers(final List<Server> servers) {
            this.servers = servers;
            return this;
        }

        public ListServerResponse build() {
            return new ListServerResponse(this);
        }
    }
}
