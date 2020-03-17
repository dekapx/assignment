package com.polarlake.network.service;

import java.util.concurrent.Callable;

import com.polarlake.network.service.api.NetworkServiceLocal;

public class MultiplyTask implements Callable<Integer> {
	private NetworkServiceLocal networkService;
	private int firstArg;
	private int secondArg;

	public MultiplyTask(final NetworkServiceLocal networkService, final int firstArg, final int secondArg) {
		this.networkService = networkService;
		this.firstArg = firstArg;
		this.secondArg = secondArg;
	}

	@Override
	public Integer call() throws Exception {
		final int result = networkService.multiply(firstArg, secondArg);
		return Integer.valueOf(result);
	}

}
