package es.uvigo.esei.daa.rest;

import static es.uvigo.esei.daa.TestUtils.assertOkStatus;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import es.uvigo.esei.daa.TestUtils;
import es.uvigo.esei.daa.entities.Event;

public class EventResourceTest extends JerseyTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.createFakeContext();
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		
	}

	@Override
	protected Application configure() {
		return new ResourceConfig(EventResource.class)
			.register(JacksonJsonProvider.class)
			.property("com.sun.jersey.api.json.POJOMappingFeature", Boolean.TRUE);
	}

	@Override
	protected void configureClient(ClientConfig config) {
		super.configureClient(config);
		
		config.register(JacksonJsonProvider.class);
		config.property("com.sun.jersey.api.json.POJOMappingFeature", Boolean.TRUE);
	}
	
	@Test
	public void testlistRecomended() throws IOException {
		final Response response = target("event/Pablo").request().get();
		assertOkStatus(response);

		final List<Event> event = response.readEntity(new GenericType<List<Event>>(){});
		assertEquals(4, event.size());
	}

}
