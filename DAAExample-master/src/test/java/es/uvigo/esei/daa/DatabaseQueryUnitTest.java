package es.uvigo.esei.daa;

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import com.mysql.jdbc.PreparedStatement;

public abstract class DatabaseQueryUnitTest {
	protected DataSource datasource;
	protected Connection connection;
	protected PreparedStatement statement;
	protected ResultSet result;
	protected ResultSet res;
	
	protected boolean verify;
	
	@BeforeClass
	public static void setUpBeforeClass()
	throws Exception {
		TestUtils.clearContextBuilder();
	}

	@Before
	public void setUp() throws Exception {
		datasource = createMock(DataSource.class);
		connection = createMock(Connection.class);
		statement = createNiceMock(PreparedStatement.class);
		result = createMock(ResultSet.class);
		res = createMock(ResultSet.class);
		
		expect(datasource.getConnection())
			.andReturn(connection);
		expect(connection.prepareStatement(anyString()))
			.andReturn(statement);
		expect(statement.executeQuery())
			.andReturn(result)
			.anyTimes();
		expect(statement.executeQuery())
		.andReturn(res)
		.anyTimes();// executeQuery is optional;
		
		statement.close();
		connection.close();
		
		verify = true;
	}
	
	protected void resetAll() {
		reset(result, statement, connection, datasource,res);
		verify = false;
	}
	
	protected void replayAll()
	throws Exception {
		replay(result, statement, connection, datasource,res);
		
		TestUtils.createFakeContext(datasource);
	}
	
	@After
	public void tearDown() throws Exception {
		TestUtils.clearContextBuilder();
		
		try {
			if (verify) {
				verify(datasource, connection, statement, result);
				verify = false;
			}
		} finally {
			datasource = null;
			connection = null;
			statement = null;
			result = null;
		}
	}
}
