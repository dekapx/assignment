package net.opsource.simpleapp.command;

import java.util.HashMap;
import java.util.Map;

public class CommandResponse {
	private Map<String, Object> attributes;

	public CommandResponse(final Map<String, Object> attributes) {
		this.attributes = new HashMap<>();
		this.attributes.putAll(attributes);
	}

	public Map<String, Object> getCommandResponse() {
		return attributes;
	}
}
