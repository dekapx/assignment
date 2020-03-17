package com.polarlake.calc.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalcServiceBeanTest {
	private CalcServiceBean objectUnderTest = new CalcServiceBean();

	@Test(expected = IllegalArgumentException.class)
	public void multiply_withZeroNumbers_throwsIllegalArgumentException() {
		objectUnderTest.multiply(0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void multiply_withNegativeNumbers_throwsIllegalArgumentException() {
		objectUnderTest.multiply(-1, -2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void multiply_withOnePositiveAndOneNegativeNumber_throwsIllegalArgumentException() {
		objectUnderTest.multiply(-1, 2);
	}

	@Test
	public void multiply_withPositiveNumbers_returnCorrectResult() {
		final int actual = objectUnderTest.multiply(5, 10);
		assertEquals(50, actual);
	}
}
