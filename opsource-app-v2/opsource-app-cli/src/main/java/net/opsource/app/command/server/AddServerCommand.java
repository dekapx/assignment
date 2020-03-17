package net.opsource.app.command.server;

import net.opsource.app.command.AbstractCommand;
import net.opsource.app.command.Command;
import net.opsource.app.service.ServerService;

public class AddServerCommand extends AbstractCommand implements Command<AddServerRequest, AddServerResponse> {
    @Override
    public AddServerResponse execute(AddServerRequest addServerRequest) {
        final ServerService serverService = loadService(ServerService.class);
        serverService.add(addServerRequest.getServer());
        return null;
    }
}
