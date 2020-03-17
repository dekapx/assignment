package com.polarlake.calc.service.impl;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polarlake.calc.service.api.CalcService;

@Stateless
public class CalcServiceBean implements CalcService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CalcServiceBean.class);

	@Override
	public int multiply(final int firstArg, final int secondArg) {
		if (firstArg <= 0 || secondArg <= 0) {
			throw new IllegalArgumentException("Arguments must be a positive number...");
		}

		LOGGER.debug("CalcService.multiply invoked with {} and {} arguments...", firstArg, secondArg);
		return (firstArg * secondArg);
	}

}
