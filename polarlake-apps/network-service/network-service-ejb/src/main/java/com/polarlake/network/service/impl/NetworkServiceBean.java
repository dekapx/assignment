package com.polarlake.network.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polarlake.calc.service.api.CalcService;
import com.polarlake.network.service.api.NetworkServiceLocal;
import com.polarlake.network.service.api.NetworkServiceRemote;

@Stateless
public class NetworkServiceBean implements NetworkServiceLocal, NetworkServiceRemote {
	private static final Logger LOGGER = LoggerFactory.getLogger(NetworkServiceBean.class);

	@Inject
	private CalcService calcService;

	@Override
	public int multiply(final int firstArg, final int secondArg) {
		LOGGER.debug("delegating request to CalcService with {} and {} arguments...", firstArg, secondArg);
		return calcService.multiply(firstArg, secondArg);
	}
}
