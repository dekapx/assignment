package net.opsource.simpleapp.util;

import static net.opsource.simpleapp.util.ApplicationConstants.DATA_SOURCE_XML;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.opsource.simpleapp.model.Server;
import net.opsource.simpleapp.model.Servers;
import net.opsource.simpleapp.service.ServerMonitoringService;
import net.opsource.simpleapp.service.impl.ServerMonitoringServiceImpl;

public class AsyncDatabasePopulator {
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncDatabasePopulator.class);
	private static final AsyncDatabasePopulator INSTANCE = new AsyncDatabasePopulator();

	private class TaskExecutor implements Callable<Void> {
		private ServerMonitoringService monitoringService = new ServerMonitoringServiceImpl();

		private Server server;

		public TaskExecutor(final Server server) {
			this.server = server;
		}

		@Override
		public Void call() throws Exception {
			monitoringService.save(server);
			return null;
		}
	}

	private Properties properties;

	private AsyncDatabasePopulator() {
		loadProperties();
	}

	public static AsyncDatabasePopulator getInstance() {
		return INSTANCE;
	}

	public void loadXmlAndpopulateDatabase() {
		final String dataSourceXml = properties.getProperty(DATA_SOURCE_XML);
		final File xmlFile = new File(dataSourceXml);
		final Servers servers = JaxbUtil.unmarshall(Servers.class, xmlFile);

		final ExecutorService executorService = Executors.newFixedThreadPool(1);
		for (Server server : servers.getServer()) {
			final Callable<Void> task = new TaskExecutor(server);
			executorService.submit(task);
		}
		executorService.shutdown();
	}

	private void loadProperties() {
		try {
			properties = new Properties();
			properties.load(ClassLoader.getSystemResourceAsStream("config/application.properties"));
		} catch (IOException e) {
			LOGGER.error("Unable to load db connection properties.", e);
			throw new IllegalStateException("Error while loading application properties...", e);
		}
	}

}
