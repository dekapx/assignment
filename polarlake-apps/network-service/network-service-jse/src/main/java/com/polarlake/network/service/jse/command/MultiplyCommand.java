package com.polarlake.network.service.jse.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polarlake.network.service.api.NetworkServiceRemote;
import com.polarlake.network.service.jse.util.NetworkServiceLocator;

public class MultiplyCommand implements Command {
	private static final Logger LOGGER = LoggerFactory.getLogger(MultiplyCommand.class);

	private static final int EMPTY_RESPONSE = -1;

	@Override
	public MultiplyCommandResponse execute(final CommandRequest commandRequest) {
		MultiplyCommandResponse commandResponse = null;

		final MultiplyCommandRequest multiplyCommandRequest = (MultiplyCommandRequest) commandRequest;
		try {
			final String cacheKey = NetworkServiceRemote.class.getSimpleName();
			final NetworkServiceRemote remote = NetworkServiceLocator.INSTANCE.getObject(cacheKey);
			final int output = remote.multiply(multiplyCommandRequest.getFirstNum(), multiplyCommandRequest.getSecondNum());
			commandResponse = new MultiplyCommandResponse(output);
		} catch (Exception e) {
			commandResponse = new MultiplyCommandResponse(EMPTY_RESPONSE);
			LOGGER.error("Unable to execute MultiplyCommand...", e);
		}

		return commandResponse;
	}

}
