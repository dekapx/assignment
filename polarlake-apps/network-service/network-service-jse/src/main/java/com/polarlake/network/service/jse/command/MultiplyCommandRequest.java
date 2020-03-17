package com.polarlake.network.service.jse.command;

public class MultiplyCommandRequest extends CommandRequest {
	private int firstNum;
	private int secondNum;

	public MultiplyCommandRequest(final int firstNum, final int secondNum) {
		this.firstNum = firstNum;
		this.secondNum = secondNum;
	}

	public int getFirstNum() {
		return firstNum;
	}

	public int getSecondNum() {
		return secondNum;
	}
}
