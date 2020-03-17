package com.polarlake.network.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.polarlake.calc.service.api.CalcService;

@RunWith(MockitoJUnitRunner.class)
public class NetworkServiceBeanTest {
	@Mock
	private CalcService mockedCalcService;

	@InjectMocks
	private NetworkServiceBean objectUnderTest;

	@SuppressWarnings("unchecked")
	@Test(expected = IllegalArgumentException.class)
	public void multiply_withZeroNumbers_throwsIllegalArgumentException() {
		when(mockedCalcService.multiply(any(int.class), any(int.class))).thenThrow(IllegalArgumentException.class);
		objectUnderTest.multiply(0, 0);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = IllegalArgumentException.class)
	public void multiply_withNegativeNumbers_throwsIllegalArgumentException() {
		when(mockedCalcService.multiply(any(int.class), any(int.class))).thenThrow(IllegalArgumentException.class);
		objectUnderTest.multiply(-1, -2);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = IllegalArgumentException.class)
	public void multiply_withOnePositiveAndOneNegativeNumber_throwsIllegalArgumentException() {
		when(mockedCalcService.multiply(any(int.class), any(int.class))).thenThrow(IllegalArgumentException.class);
		objectUnderTest.multiply(-1, 2);
	}

	@Test
	public void multiply_withPositiveNumbers_returnCorrectResult() {
		when(mockedCalcService.multiply(any(int.class), any(int.class))).thenReturn(15);
		final int actual = objectUnderTest.multiply(3, 5);
		assertEquals(15, actual);
	}

}
