package es.uvigo.esei.daa.rest;

import static es.uvigo.esei.daa.TestUtils.assertBadRequestStatus;
import static es.uvigo.esei.daa.TestUtils.assertOkStatus;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
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
		TestUtils.initTestDatabase();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		
		TestUtils.clearTestDatabase();
	}

	@Override
	protected Application configure() {
		return new ResourceConfig(PeopleResource.class)
			.register(JacksonJsonProvider.class)
			.property("com.sun.jersey.api.json.POJOMappingFeature", Boolean.TRUE);
	}

	@Override
	protected void configureClient(ClientConfig config) {
		super.configureClient(config);
		
		config.register(JacksonJsonProvider.class);
		config.property("com.sun.jersey.api.json.POJOMappingFeature", Boolean.TRUE);
	}
	
	/*@Test
	public void testList() throws IOException {
		final Response response = target("event").request().get();
		assertOkStatus(response);

		final List<Event> event = response.readEntity(new GenericType<List<Event>>(){});
		assertEquals(2, event.size());
	}

	@Test
	public void testGet() throws IOException {
		final Response response = target("event/1").request().get();
		assertOkStatus(response);
		
		final Event event = response.readEntity(Event.class);
		assertEquals("vamos de parranda", event.getNameEvent());
		assertEquals("2015-07-15 13:30:00", event.getDateCreate());
		assertEquals("2015-08-15 13:30:00", event.getDateInit());
		assertEquals("2015-08-15 15:30:00", event.getDateFinal());
		assertEquals("vamos a comer una parrillada", event.getDescription());
		assertEquals("parranda", event.getCategory());
	}

	@Test
	public void testGetInvalidId() throws IOException {
		assertBadRequestStatus(target("event/100").request().get());
	}

	@Test
	public void testAdd() throws IOException {
		final Form form = new Form();
		form.param("nameEvent", "vamos de parranda");
		form.param("dateCreate", "2015-07-15 13:30:00");
		form.param("dateInit", "2015-08-15 13:30:00");
		form.param("dateFinal", "2015-08-15 15:30:00");
		form.param("description", "vamos a comer una parrillada");
		form.param("category", "parranda");
		
		final Response response = target("event")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		assertOkStatus(response);
		
		final Event event = response.readEntity(Event.class);
		assertEquals("vamos de parranda", event.getNameEvent());
		assertEquals("2015-07-15 13:30:00", event.getDateCreate());
		assertEquals("2015-08-15 13:30:00", event.getDateInit());
		assertEquals("2015-08-15 15:30:00", event.getDateFinal());
		assertEquals("vamos a comer una parrillada", event.getDescription());
		assertEquals("parranda", event.getCategory());
	}

	@Test
	public void testAddMissingName() throws IOException {
		final Form form = new Form();
		form.param("dateCreate", "2015-07-15 13:30:00");
		form.param("dateInit", "2015-08-15 13:30:00");
		form.param("dateFinal", "2015-08-15 15:30:00");
		form.param("description", "vamos a comer una parrillada");
		form.param("category", "parranda");
		
		final Response response = target("event")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		
		assertBadRequestStatus(response);
	}
	

	@Test
	public void testAddMissingDateCreate() throws IOException {
		final Form form = new Form();
		form.param("nameEvent", "vamos de parranda");
		form.param("dateInit", "2015-08-15 13:30:00");
		form.param("dateFinal", "2015-08-15 15:30:00");
		form.param("description", "vamos a comer una parrillada");
		form.param("category", "parranda");
		
		final Response response = target("event")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		
		assertBadRequestStatus(response);
	}
	
	@Test
	public void testAddMissingDateInit() throws IOException {
		final Form form = new Form();
		form.param("nameEvent", "vamos de parranda");
		form.param("dateCreate", "2015-07-15 13:30:00");
		form.param("dateFinal", "2015-08-15 15:30:00");
		form.param("description", "vamos a comer una parrillada");
		form.param("category", "parranda");
		
		final Response response = target("event")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		
		assertBadRequestStatus(response);
	}
	@Test
	public void testAddMissingDateFinal() throws IOException {
		final Form form = new Form();
		form.param("nameEvent", "vamos de parranda");
		form.param("dateCreate", "2015-07-15 13:30:00");
		form.param("dateInit", "2015-08-15 13:30:00");
		form.param("description", "vamos a comer una parrillada");
		form.param("category", "parranda");
		
		final Response response = target("event")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		
		assertBadRequestStatus(response);
	}
	@Test
	public void testAddMissingDescription() throws IOException {
		final Form form = new Form();
		form.param("nameEvent", "vamos de parranda");
		form.param("dateCreate", "2015-07-15 13:30:00");
		form.param("dateInit", "2015-08-15 13:30:00");
		form.param("dateFinal", "2015-08-15 15:30:00");
		form.param("category", "parranda");
		
		final Response response = target("event")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		
		assertBadRequestStatus(response);
	}
	@Test
	public void testAddMissingCategory() throws IOException {
		final Form form = new Form();
		form.param("nameEvent", "vamos de parranda");
		form.param("dateCreate", "2015-07-15 13:30:00");
		form.param("dateInit", "2015-08-15 13:30:00");
		form.param("dateFinal", "2015-08-15 15:30:00");
		form.param("description", "vamos a comer una parrillada");
		
		final Response response = target("event")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		
		assertBadRequestStatus(response);
	}

	@Test
	public void testModify() throws IOException {
		final Form form = new Form();
		form.param("nameEvent", "vamos de parrand");
		form.param("dateCreate", "2015-07-15 13:20:00");
		form.param("dateInit", "2015-08-15 15:30:00");
		form.param("dateFinal", "2015-08-15 16:30:00");
		form.param("description", "vamos a comer una parrillad");
		form.param("category", "musica");
		//Acordarse de la id del evento si hay un fallo
		final Response response = target("event/1")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		assertOkStatus(response);
		
		final Event event = response.readEntity(Event.class);
		assertEquals(1, event.getId());
		assertEquals("vamos de parrand", event.getNameEvent());
		assertEquals("2015-07-15 13:20:00", event.getDateCreate());
		assertEquals("2015-08-15 15:30:00", event.getDateInit());
		assertEquals("2015-08-15 16:30:00", event.getDateFinal());
		assertEquals("vamos a comer una parrillad", event.getDescription());
		assertEquals("musica", event.getCategory());
	}

	
	@Test
	public void testModifyInvalidId() throws IOException {
		final Form form = new Form();
		form.param("nameEvent", "vamos de parrand");
		form.param("dateCreate", "2015-07-15 13:20:00");
		form.param("dateInit", "2015-08-15 15:30:00");
		form.param("dateFinal", "2015-08-15 16:30:00");
		form.param("description", "vamos a comer una parrillad");
		form.param("category", "musica");
		
		final Response response = target("event/100")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

		assertBadRequestStatus(response);
	}

	@Test
	public void testDelete() throws IOException {
		final Response response = target("event/1").request().delete();
		assertOkStatus(response);
		
		assertEquals(1, (int) response.readEntity(Integer.class));
	}

	@Test
	public void testDeleteInvalidId() throws IOException {
		assertBadRequestStatus(target("event/100").request().delete());
	}*/
}
