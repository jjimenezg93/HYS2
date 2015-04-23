
package es.uvigo.esei.daa.rest;

import static org.easymock.EasyMock.anyInt;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.grizzly.http.util.TimeStamp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uvigo.esei.daa.dao.DAOException;
import es.uvigo.esei.daa.dao.EventDAO;
import es.uvigo.esei.daa.entities.Event;

public class EventResourceUnitTest {
	private EventDAO daoMock;
	private EventResource resource;

	@Before
	public void setUp() throws Exception {
		daoMock = createMock(EventDAO.class);
		resource = new EventResource(daoMock);
	}

	@After
	public void tearDown() throws Exception {
		try {
			verify(daoMock);
		} finally {
			daoMock = null;
			resource = null;
		}
	}

	@Test
	public void testlistRecomended() throws Exception {
		final List<Event> events = Arrays.asList(
			new Event(1,"Nombre", new Timestamp(05464564654),new Timestamp(05464564654),new Timestamp(05464564654),"Descripcion","Categoria", "Ourense", "/img/prueba.jpg","Pablo"),
			new Event(1,"Nombre", new Timestamp(05464564654),new Timestamp(05464564654),new Timestamp(05464564654),"Descripcion","Categoria", "Ourense", "/img/prueba.jpg","Pablo"),
			new Event(1,"Nombre", new Timestamp(05464564654),new Timestamp(05464564654),new Timestamp(05464564654),"Descripcion","Categoria", "Ourense", "/img/prueba.jpg","Pablo")
		);
		
		expect(daoMock.listRecomended("Pablo")).andReturn(events);
		replay(daoMock);
		
		final Response response = resource.listRecomended("Pablo");
		assertEquals(events, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}

	/*@Test
	public void testListDAOException() throws Exception {
		expect(daoMock.list()).andThrow(new DAOException());
		replay(daoMock);
		
		final Response response = resource.list();
		assertEquals(Status.INTERNAL_SERVER_ERROR, response.getStatusInfo());
	}

	@Test
	public void testGet() throws Exception {
		final Person person = new Person(1, "Pepe", "Pérez");
		
		expect(daoMock.get(person.getId())).andReturn(person);
		replay(daoMock);
		
		final Response response = resource.get(person.getId());
		assertEquals(person, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}

	@Test
	public void testGetDAOException() throws Exception {
		expect(daoMock.get(anyInt())).andThrow(new DAOException());
		replay(daoMock);
		
		final Response response = resource.get(1);
		assertEquals(Status.INTERNAL_SERVER_ERROR, response.getStatusInfo());
	}

	@Test
	public void testGetIllegalArgumentException() throws Exception {
		expect(daoMock.get(anyInt())).andThrow(new IllegalArgumentException());
		replay(daoMock);
		
		final Response response = resource.get(1);
		assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
	}
	
	@Test
	public void testDelete() throws Exception {
		daoMock.delete(anyInt());
		replay(daoMock);
		
		final Response response = resource.delete(1);
		assertEquals(Status.OK, response.getStatusInfo());
	}

	@Test
	public void testDeleteDAOException() throws Exception {
		daoMock.delete(anyInt());
		expectLastCall().andThrow(new DAOException());
		replay(daoMock);
		
		final Response response = resource.delete(1);
		assertEquals(Status.INTERNAL_SERVER_ERROR, response.getStatusInfo());
	}

	@Test
	public void testDeleteIllegalArgumentException() throws Exception {
		daoMock.delete(anyInt());
		expectLastCall().andThrow(new IllegalArgumentException());
		replay(daoMock);
		
		final Response response = resource.delete(1);
		assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
	}

	@Test
	public void testModify() throws Exception {
		final Person person = new Person(1, "Pepe", "Pérez");
		
		expect(daoMock.modify(person.getId(), person.getName(), person.getSurname()))
			.andReturn(person);
		replay(daoMock);
		

		final Response response = resource.modify(
			person.getId(), person.getName(), person.getSurname());
		assertEquals(person, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}

	@Test
	public void testModifyDAOException() throws Exception {
		expect(daoMock.modify(anyInt(), anyString(), anyString()))
			.andThrow(new DAOException());
		replay(daoMock);

		final Response response = resource.modify(1, "Paco", "Pérez");
		assertEquals(Status.INTERNAL_SERVER_ERROR, response.getStatusInfo());
	}

	@Test
	public void testModifyIllegalArgumentException() throws Exception {
		expect(daoMock.modify(anyInt(), anyString(), anyString()))
			.andThrow(new IllegalArgumentException());
		replay(daoMock);
		
		final Response response = resource.modify(1, "Paco", "Pérez");
		assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
	}

	@Test
	public void testAdd() throws Exception {
		final Person person = new Person(1, "Pepe", "Pérez");
		
		expect(daoMock.add(person.getName(), person.getSurname()))
			.andReturn(person);
		replay(daoMock);
		

		final Response response = resource.add(
			person.getName(), person.getSurname());
		assertEquals(person, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}

	@Test
	public void testAddDAOException() throws Exception {
		expect(daoMock.add(anyString(), anyString()))
			.andThrow(new DAOException());
		replay(daoMock);

		final Response response = resource.add("Paco", "Pérez");
		assertEquals(Status.INTERNAL_SERVER_ERROR, response.getStatusInfo());
	}

	@Test
	public void testAddIllegalArgumentException() throws Exception {
		expect(daoMock.add(anyString(), anyString()))
			.andThrow(new IllegalArgumentException());
		replay(daoMock);
		
		final Response response = resource.add("Paco", "Pérez");
		assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
	}*/
}
