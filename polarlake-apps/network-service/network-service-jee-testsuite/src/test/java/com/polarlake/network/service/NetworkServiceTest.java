package com.polarlake.network.service;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polarlake.calc.service.api.CalcService;
import com.polarlake.calc.service.impl.CalcServiceBean;
import com.polarlake.network.service.api.NetworkService;
import com.polarlake.network.service.api.NetworkServiceLocal;
import com.polarlake.network.service.api.NetworkServiceRemote;
import com.polarlake.network.service.impl.NetworkServiceBean;

@RunWith(Arquillian.class)
public class NetworkServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(NetworkServiceTest.class);
	private static final int MAX_LIMIT = 500;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war").addClasses(NetworkService.class, NetworkServiceLocal.class, NetworkServiceBean.class,
						NetworkServiceRemote.class, CalcService.class, CalcServiceBean.class, MultiplyTask.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private NetworkServiceLocal networkService;

	@Resource
	private ManagedExecutorService executorService;

	@Test
	@InSequence(1)
	public void multiply_withPositiveNumbers_returnCorrectResult() {
		final int firstArg = 3;
		final int secondArg = 5;
		final int result = networkService.multiply(3, 5);
		LOGGER.info("------------ Multiplication of {} & {} is: {}", firstArg, secondArg, result);
		assertEquals(15, result);
	}

	@Test
	@InSequence(2)
	public void multiply_withConcurrentRequests_returnCorrectResults() throws Exception {
		for (int i = 0; i < MAX_LIMIT; i++) {
			final int firstArg = i + 1;
			final int secondArg = i + 2;
			final MultiplyTask task = new MultiplyTask(networkService, firstArg, secondArg);
			final Future<Integer> future = executorService.submit(task);
			LOGGER.info("------------ Multiplication of {} & {} is: {}", firstArg, secondArg, future.get());
		}
	}
}
