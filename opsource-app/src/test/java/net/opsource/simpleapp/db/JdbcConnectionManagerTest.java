package net.opsource.simpleapp.db;

import static net.opsource.simpleapp.db.JdbcConstants.DATABASE_URL;
import static net.opsource.simpleapp.db.JdbcConstants.DRIVER_CLASS;
import static net.opsource.simpleapp.db.JdbcConstants.PASSWORD;
import static net.opsource.simpleapp.db.JdbcConstants.USER_NAME;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

@RunWith(MockitoJUnitRunner.class)
public class JdbcConnectionManagerTest {
	private JdbcConnectionManager objectUnderTest = new JdbcConnectionManager();

	@Mock
	private Properties mockedProperties;

	@Test
	public void getConnection_returnValidConnection() {
		final Connection connection = objectUnderTest.getConnection();
		assertTrue(connection instanceof Connection);
	}

	@Test(expected = DatabaseException.class)
	public void getConnection_withInvalidCredentials_throwsDatabaseException() {
		when(mockedProperties.getProperty(DRIVER_CLASS)).thenReturn("com.mysql.jdbc.Driver");
		when(mockedProperties.getProperty(DATABASE_URL)).thenReturn("jdbc:mysql://localhost:3306/test?autoReconnect=true");
		when(mockedProperties.getProperty(USER_NAME)).thenReturn("invalid-user");
		when(mockedProperties.getProperty(PASSWORD)).thenReturn("invalid-password");
		Whitebox.setInternalState(objectUnderTest, "properties", mockedProperties);
		objectUnderTest.getConnection();
	}

}
