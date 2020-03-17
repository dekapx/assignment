package com.polarlake.calc.service.api;

import javax.ejb.Local;

@Local
public interface CalcService {
	int multiply(int firstArg, int secondArg);
}
