package es.uvigo.esei.daa.dao;

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.reset;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import es.uvigo.esei.daa.DatabaseQueryUnitTest;
import es.uvigo.esei.daa.entities.Event;

public class EventDAOUnitTest extends DatabaseQueryUnitTest {
	/*@Test
	public void testGet() throws Exception {
		final Person person = new Person(1, "Pepe", "Pérez");
		
		expect(result.next()).andReturn(true);
		expect(result.getInt("id")).andReturn(person.getId());
		expect(result.getString("name")).andReturn(person.getName());
		expect(result.getString("surname")).andReturn(person.getSurname());
		result.close();
		
		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		
		assertEquals("Unexpected person data",
			person, peopleDAO.get(person.getId())
		);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetMissing() throws Exception {
		expect(result.next()).andReturn(false);
		result.close();
		
		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		peopleDAO.get(2);
	}
	
	@Test(expected = DAOException.class)
	public void testGetUnexpectedException() throws Exception {
		expect(result.next()).andThrow(new SQLException());
		result.close();
		
		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		peopleDAO.get(2);
	}*/

	@Test
	public void testlistRecomended() throws Exception {
		final List<Event> events = Arrays.asList(
				new Event(3,"Pachanga en Ourense", new Timestamp(1439483400),new Timestamp(1442161800),new Timestamp(1442169000),"Partido de futbol entre amigos en el campo del campus de ourense","Fiesta", "Ourense", "img/prueba3.jpg","Pablo"),
				new Event(1,"Fiesta de Disfraces", new Timestamp(1436959800),new Timestamp(1439638200),new Timestamp(1439645400),"Fiesta de disfraces para celebrar el inicio de la primavera","Fiesta", "Ourense", "img/prueba1.jpg","Pablo"),
				new Event(4,"Parrillada en Vigo", new Timestamp(1433341800),new Timestamp(1433341800),new Timestamp(1434385800),"Cada uno debe pagar 5 euros para comprar comida y bebida","Fiesta", "Ourense","img/prueba4.jpg","Pablo"),
				new Event(2,"Club de lectura", new Timestamp(1433341800),new Timestamp(1433341800),new Timestamp(1434385800),"Hablaremos sobre algun libro, uno diferente cada semana","Fiesta", "Ourense","img/prueba2.jpg","Pablo")
			);

		for (Event event : events) {
			expect(result.next()).andReturn(true);
			expect(result.next()).andReturn(false);
			expect(result.next()).andReturn(true);
			expect(result.getInt("id")).andReturn(event.getId());
			expect(result.getString("nameEvent")).andReturn(event.getNameEvent());
			expect(result.getTimestamp("dateCreate")).andReturn(event.getDateCreate());
			expect(result.getTimestamp("dateInit")).andReturn(event.getDateInit());
			expect(result.getTimestamp("dateFinal")).andReturn(event.getDateFinal());
			expect(result.getString("description")).andReturn(event.getDescription());
			expect(result.getString("category")).andReturn("Fiesta").anyTimes();
			expect(result.getString("place")).andReturn(event.getPlace());
			expect(result.getString("image")).andReturn(event.getImage());
			expect(result.getString("author")).andReturn(event.getAuthor());
		}
			
		expect(result.next()).andReturn(false);
		result.close();
		result.close();
		replayAll();
		final EventDAO eventDAO = new EventDAO();

		assertEquals("Unexpected event data",
			events, eventDAO.listRecomended("Pablo", "Ourense")
		);
	}
	/*
	@Test(expected = DAOException.class)
	public void testListUnexpectedException() throws Exception {
		expect(result.next()).andThrow(new SQLException());
		result.close();
		
		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		peopleDAO.list();
	}

	@Test
	public void testAdd() throws Exception {
		final Person person = new Person(1, "Pepe", "Pérez");
		
		reset(connection);
		expect(connection.prepareStatement(anyString(), eq(1)))
			.andReturn(statement);
		expect(statement.executeUpdate()).andReturn(1);
		expect(statement.getGeneratedKeys()).andReturn(result);
		expect(result.next()).andReturn(true);
		expect(result.getInt(1)).andReturn(person.getId()); // Key retrieval
		connection.close();
		result.close();

		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		final Person newPerson = peopleDAO.add(person.getName(), person.getSurname());
		
		assertEquals(person, newPerson);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullName() throws Exception {
		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		
		resetAll(); // No expectations
		
		peopleDAO.add(null, "Pepe");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullSurname() throws Exception {
		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		
		resetAll(); // No expectations
		
		peopleDAO.add("Pepe", null);
	}

	@Test(expected = DAOException.class)
	public void testAddZeroUpdatedRows() throws Exception {
		reset(connection);
		expect(connection.prepareStatement(anyString(), eq(1)))
			.andReturn(statement);
		expect(statement.executeUpdate()).andReturn(0);
		connection.close();

		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		peopleDAO.add("Paco", "Pérez");
	}

	@Test(expected = DAOException.class)
	public void testAddNoGeneratedKey() throws Exception {
		reset(connection);
		expect(connection.prepareStatement(anyString(), eq(1)))
			.andReturn(statement);
		expect(statement.executeUpdate()).andReturn(1);
		expect(statement.getGeneratedKeys()).andReturn(result);
		expect(result.next()).andReturn(false);
		result.close();
		connection.close();

		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		peopleDAO.add("Paco", "Pérez");
	}
	
	@Test(expected = DAOException.class)
	public void testAddUnexpectedException() throws Exception {
		reset(connection);
		expect(connection.prepareStatement(anyString(), eq(1)))
			.andReturn(statement);
		expect(statement.executeUpdate()).andThrow(new SQLException());
		connection.close();
		
		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		peopleDAO.add("Paco", "Pérez");
	}

	@Test
	public void testDelete() throws Exception {
		expect(statement.executeUpdate()).andReturn(1);
		
		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		peopleDAO.delete(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteInvalidId() throws Exception {
		expect(statement.executeUpdate()).andReturn(0);
		
		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		peopleDAO.delete(1);
	}

	@Test(expected = DAOException.class)
	public void testDeleteUnexpectedException() throws Exception {
		expect(statement.executeUpdate()).andThrow(new SQLException());
		
		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		peopleDAO.delete(1);
	}

	@Test
	public void testModify() throws Exception {
		final Person person = new Person(1, "Pepe", "Pérez");
		
		expect(statement.executeUpdate()).andReturn(1);

		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		final Person newPerson = peopleDAO.modify(
			person.getId(), person.getName(), person.getSurname()
		);
		
		assertEquals(person, newPerson);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullName() throws Exception {
		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		
		resetAll(); // No expectations
		
		peopleDAO.modify(1, null, "Pepe");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullSurname() throws Exception {
		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		
		resetAll(); // No expectations
		
		peopleDAO.modify(1, "Pepe", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyZeroUpdatedRows() throws Exception {
		expect(statement.executeUpdate()).andReturn(0);

		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		peopleDAO.modify(1, "Paco", "Pérez");
	}
	
	@Test(expected = DAOException.class)
	public void testModifyUnexpectedException() throws Exception {
		expect(statement.executeUpdate()).andThrow(new SQLException());
		
		replayAll();
		
		final PeopleDAO peopleDAO = new PeopleDAO();
		peopleDAO.modify(1, "Paco", "Pérez");
	}*/
}
