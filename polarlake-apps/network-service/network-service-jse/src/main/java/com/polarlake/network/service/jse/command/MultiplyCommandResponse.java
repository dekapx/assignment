package com.polarlake.network.service.jse.command;

public class MultiplyCommandResponse extends CommandResponse {
	private int output;

	public MultiplyCommandResponse(final int output) {
		this.output = output;
	}

	public int getOutput() {
		return output;
	}
}
