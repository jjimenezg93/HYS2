package es.uvigo.esei.daa;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.core.Response;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;



public final class TestUtils {
	private final static SimpleNamingContextBuilder CONTEXT_BUILDER
		= new SimpleNamingContextBuilder();
	
	private TestUtils() {}
	
	public static void createFakeContext() throws NamingException {
		createFakeContext(createTestingDataSource());
	}

	public static void createFakeContext(DataSource datasource)
	throws IllegalStateException, NamingException {
		CONTEXT_BUILDER.bind("java:/comp/env/jdbc/daaexampletest", datasource);
		CONTEXT_BUILDER.activate();
	}
	
	public static void clearContextBuilder() {
		CONTEXT_BUILDER.clear();
		CONTEXT_BUILDER.deactivate();
	}

	public static BasicDataSource createTestingDataSource() {
		final BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/daaexampletest?allowMultiQueries=true");
		ds.setUsername("root");
		ds.setPassword("");
		ds.setMaxActive(100);
		ds.setMaxIdle(30);
		ds.setMaxWait(10000);
		return ds;
	}

	public static BasicDataSource createEmptyDataSource() {
		return new BasicDataSource();
	}
	
	public static void clearTestDatabase() throws SQLException {
		final String queries = new StringBuilder()
			.append("DELETE FROM `eventuser`;")	
			.append("DELETE FROM `event`;")
			.append("DELETE FROM `users`;")
			.toString();

		final DataSource ds = createTestingDataSource();
		try (Connection connection = ds.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				statement.execute(queries);
			}
		}
	}
	
	public static void initTestDatabase() throws SQLException {
		final String queries = new StringBuilder()
			.append("INSERT INTO `event` (`id`,`nameEvent`,`dateCreate`,`dateInit`,`dateFinal`,`description`,`category`) VALUES (0, 'vamos de parranda','2015-07-15 13:30:00','2015-08-15 13:30:00','2015-08-15 15:30:00','vamos a comer una parrillada','parranda');")
			.append("INSERT INTO `users` (`login`,`password`,`name`,`surname`) VALUES ('logon','bitch','hiper','bitch');")
			.append("INSERT INTO `event` (`id`,`nameEvent`,`dateCreate`,`dateInit`,`dateFinal`,`description`,`category`) VALUES (0, 'club de lectura'  ,'2015-06-03 16:30:00','2015-06-03 16:30:00','2015-06-15 18:30:00','hablaremos sobre algun libro','parranda');")
			.toString();

		final DataSource ds = createTestingDataSource();
		try (Connection connection = ds.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				statement.execute(queries,Statement.RETURN_GENERATED_KEYS);
				
				try (PreparedStatement statement2 = connection.prepareStatement("INSERT INTO `eventuser` (`id`,`login`) VALUES (?,'logon');")) {
					ResultSet rs=statement.getGeneratedKeys();
					while(rs.next())
					statement2.setInt(1,rs.getInt(1));
					statement2.execute();
				}
			}
		}

		
	}

	public static void assertOkStatus(final Response response) {
		assertEquals("Unexpected status code", Response.Status.OK.getStatusCode(), response.getStatus());
	}

	public static void assertBadRequestStatus(final Response response) {
		assertEquals("Unexpected status code", Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}
}
