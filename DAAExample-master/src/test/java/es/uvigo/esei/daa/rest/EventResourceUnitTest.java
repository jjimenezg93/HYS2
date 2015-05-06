
package es.uvigo.esei.daa.rest;


import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		
		expect(daoMock.listRecomended("Pablo", "Vigo")).andReturn(events);
		replay(daoMock);
		
		final Response response = resource.listRecomended("Pablo", "Vigo");
		assertEquals(events, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}

	@Test
	public void testAdd() throws Exception {
		
		expect(daoMock.addEventUser(8,"Pablo"))
			.andReturn(true);
		replay(daoMock);
		

		final Response response = resource.add(
			8, "Pablo");
		assertEquals(true, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}
	
	
}
