package net.opsource.simpleapp.command;

import static net.opsource.simpleapp.util.ApplicationConstants.SERVER_MODELS;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_CODE;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_MESSAGE;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.opsource.simpleapp.model.Server;

public abstract class AbstractCommand {

	protected CommandResponse buildCommandResponse(final int statusCode, final String statusMessage) {
		final Map<String, Object> attributes = new HashMap<>(2);
		attributes.put(STATUS_CODE, statusCode);
		attributes.put(STATUS_MESSAGE, statusMessage);
		return new CommandResponse(attributes);
	}

	protected CommandResponse buildCommandResponse(final int statusCode, final String statusMessage, final Collection<Server> servers) {
		final Map<String, Object> attributes = new HashMap<>(3);
		attributes.put(STATUS_CODE, statusCode);
		attributes.put(STATUS_MESSAGE, statusMessage);
		attributes.put(SERVER_MODELS, servers);

		return new CommandResponse(attributes);
	}

}
