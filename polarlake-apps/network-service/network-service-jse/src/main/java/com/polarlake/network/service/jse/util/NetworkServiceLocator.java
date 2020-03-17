package com.polarlake.network.service.jse.util;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polarlake.network.service.api.NetworkServiceRemote;

/**
 * Singleton cache implementation based on Service Locator pattern. This can be enhanced to work like a generic cache.
 * 
 * @author ekalkur
 *
 */
public enum NetworkServiceLocator {
	INSTANCE;

	private static final Logger LOGGER = LoggerFactory.getLogger(NetworkServiceLocator.class);

	private Map<String, NetworkServiceRemote> cache = new ConcurrentHashMap<>();

	public NetworkServiceRemote getObject(final String key) {
		NetworkServiceRemote remoteObject = null;

		if (!cache.containsKey(key)) {
			LOGGER.debug("Perform remote JNDI lookup for NetworkServiceBean...");
			remoteObject = getNetworkServiceRemoteRemote();
			cache.put(key, remoteObject);
		}

		remoteObject = cache.get(key);

		return remoteObject;
	}

	private NetworkServiceRemote getNetworkServiceRemoteRemote() {
		NetworkServiceRemote networkServiceRemote = null;
		try {
			final Properties jndiProps = new Properties();
			jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			final Context context = new InitialContext(jndiProps);

			networkServiceRemote = (NetworkServiceRemote) context.lookup(createRemoteJndiName());
			context.close();
		} catch (NamingException e) {
			LOGGER.error("Unable to perform JNDI lookup for NetworkServiceBean");
		}
		return networkServiceRemote;
	}

	private String createRemoteJndiName() {
		final String appName = "network-service-web";
		final String beanName = "NetworkServiceBean";
		return appName + "/" + beanName + "!" + NetworkServiceRemote.class.getName();
	}

}
