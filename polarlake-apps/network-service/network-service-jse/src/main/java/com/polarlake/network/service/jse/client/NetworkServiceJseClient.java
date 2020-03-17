package com.polarlake.network.service.jse.client;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polarlake.network.service.api.NetworkServiceRemote;

public class NetworkServiceJseClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(NetworkServiceJseClient.class);

	private static final String JBOSS_INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String JBOSS_PROVIDER_URL = "http-remoting://localhost:8080";
	private static final String APP_NAME = "appName.name";
	private static final String BEAN_NAME = "bean.name";

	private static final int UPPER_BOUND = 500;

	private static Properties properties;

	static {
		loadProperties();
	}

	public static void main(String[] args) {
		try {
			final NetworkServiceRemote remote = getNetworkServiceRemoteRemote();
			final Random random = new Random();
			for (int i = 0; i < UPPER_BOUND; i++) {
				int firstNum = random.nextInt(UPPER_BOUND);
				final int output = remote.multiply(firstNum, i);
				LOGGER.info("{} * {} = {}", firstNum, i, output);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static NetworkServiceRemote getNetworkServiceRemoteRemote() throws Exception {
		final Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, JBOSS_INITIAL_CONTEXT_FACTORY);
		jndiProps.put(Context.PROVIDER_URL, JBOSS_PROVIDER_URL);
		final Context context = new InitialContext(jndiProps);

		final NetworkServiceRemote remote = (NetworkServiceRemote) context.lookup(createRemoteJndiName());
		context.close();
		return remote;
	}

	private static String createRemoteJndiName() {
		final String appName = properties.getProperty(APP_NAME);
		final String beanName = properties.getProperty(BEAN_NAME);
		return appName + "/" + beanName + "!" + NetworkServiceRemote.class.getName();
	}

	private static void loadProperties() {
		try {
			properties = new Properties();
			properties.load(ClassLoader.getSystemResourceAsStream("application.properties"));
		} catch (IOException e) {
			LOGGER.error("Unable to load application connection properties.", e);
		}
	}
}
