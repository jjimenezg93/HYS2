package es.uvigo.esei.daa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;
import java.util.List;

import es.uvigo.esei.daa.entities.Event;

public class EventDAO extends DAO {
	private final static Logger LOG = Logger.getLogger("EventDAO");

	public Event get(int id) throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM event WHERE id=?";

			try (final PreparedStatement statement = conn
					.prepareStatement(query)) {
				statement.setInt(1, id);

				try (final ResultSet result = statement.executeQuery()) {
					if (result.next()) {
						return new Event(result.getInt("id"),
								result.getString("nameEvent"),
								result.getTimestamp("dateCreate"),
								result.getTimestamp("dateInit"),
								result.getTimestamp("dateFinal"),
								result.getString("description"),
								result.getString("category"),
								result.getString("place"),
								result.getString("image"),
								result.getString("author"));
					} else {
						throw new IllegalArgumentException("Invalid id");
					}
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error getting an event", e);
			throw new DAOException(e);
		}
	}

	public List<Event> listRecomended(String login, String userCity)
			throws DAOException {
		int numEvents = 4;
		final SortedMap<String, Integer> mapaCategorias = new TreeMap(); // sortedMap devuelve un mapa ordenado
		try (final Connection conn = this.getConnection()) {
			final String eventosUser = "SELECT event.category FROM eventUser, event where eventUser.id = event.id and eventUser.login = "
					+ "'"
					+ login
					+ "'"
					+ " GROUP BY event.category ORDER BY COUNT(1) DESC";

			try (final PreparedStatement statement = conn
					.prepareStatement(eventosUser)) {
				try (final ResultSet pastEventsUser = statement.executeQuery()) {
					while (pastEventsUser.next()) {

						if (!mapaCategorias.containsKey(pastEventsUser
								.getString("category"))) {
							mapaCategorias.put(
									pastEventsUser.getString("category"), 1);
						} else {
							mapaCategorias.replace(pastEventsUser
									.getString("category"),
									mapaCategorias.get(pastEventsUser
											.getString("category")) + 1);
						}
					}

					Iterator<String> iterator = mapaCategorias.keySet()
							.iterator();
					final List<Event> events = new LinkedList<>();

					while (events.size() < numEvents && iterator.hasNext()) {

						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							final String eventoRecomen = "SELECT * FROM event where event.place = "
									+ "'"
									+ userCity
									+ "'"
									+ " and category =  " + "'" + key + "'";

							try (final PreparedStatement stat = conn
									.prepareStatement(eventoRecomen)) {
								try (final ResultSet res = stat.executeQuery()) {

									while (res.next()
											&& events.size() < numEvents) {
										events.add(new Event(res.getInt("id"),
												res.getString("nameEvent"),
												res.getTimestamp("dateCreate"),
												res.getTimestamp("dateInit"),
												res.getTimestamp("dateFinal"),
												res.getString("description"),
												res.getString("category"), res
														.getString("place"),
												res.getString("image"), res
														.getString("author")));

									}

								}
							}
						}
					}
					return events;

				}

			}

		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error listing events", e);
			throw new DAOException(e);
		}
	}

	public void delete(int id) throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "DELETE FROM event WHERE id=?";

			try (final PreparedStatement statement = conn
					.prepareStatement(query)) {
				statement.setInt(1, id);

				if (statement.executeUpdate() != 1) {
					throw new IllegalArgumentException("Invalid id");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error deleting an event", e);
			throw new DAOException(e);
		}
	}

	public Event modify(int id, String nameEvent, Timestamp dateCreate,
			Timestamp dateInit, Timestamp dateFinal, String description,
			String category, String place, String image, String author)
			throws DAOException, IllegalArgumentException {
		if (nameEvent == null) {
			throw new IllegalArgumentException("name cannot be null");
		}

		try (Connection conn = this.getConnection()) {
			// /Sentencia de modificacion, hacer cuando la bd este correcta
			final String query = "UPDATE event SET name=?, surname=? WHERE id=?";

			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setString(1, nameEvent);
				statement.setString(2, dateCreate.toString());
				statement.setString(3, dateInit.toString());
				statement.setString(4, dateFinal.toString());
				statement.setString(5, description);
				statement.setString(6, category);
				statement.setString(7, place);
				statement.setString(8, image);
				statement.setString(9, author);

				if (statement.executeUpdate() == 1) {
					return new Event(id, nameEvent, dateCreate, dateInit,
							dateFinal, description, category, place, image,
							author);
				} else {
					throw new IllegalArgumentException(
							"id and name cannot be null");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error modifying an event", e);
			throw new DAOException();
		}
	}

	public Event add(int id, String nameEvent, Timestamp dateCreate,
			Timestamp dateInit, Timestamp dateFinal, String description,
			String category, String place, String image, String author)
			throws DAOException, IllegalArgumentException {
		if (nameEvent == null) {
			throw new IllegalArgumentException("name cannot be null");
		}

		try (Connection conn = this.getConnection()) {
			final String query = "INSERT INTO event VALUES(null, ?, ?,?,?,?,?)";

			try (PreparedStatement statement = conn.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS)) {
				// /Comprobar que los tostring de los timestampo funcionen
				statement.setString(1, nameEvent);
				statement.setString(2, dateCreate.toString());
				statement.setString(3, dateInit.toString());
				statement.setString(4, dateFinal.toString());
				statement.setString(5, description);
				statement.setString(6, category);
				statement.setString(7, place);
				statement.setString(8, image);
				statement.setString(9, author);

				if (statement.executeUpdate() == 1) {
					try (ResultSet resultKeys = statement.getGeneratedKeys()) {
						if (resultKeys.next()) {
							return new Event(resultKeys.getInt(1), nameEvent,
									dateCreate, dateInit, dateFinal,
									description, category, place, image, author);
						} else {
							LOG.log(Level.SEVERE,
									"Error retrieving inserted id");
							throw new SQLException(
									"Error retrieving inserted id");
						}
					}
				} else {
					LOG.log(Level.SEVERE, "Error inserting value");
					throw new SQLException("Error inserting value");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error adding an event", e);
			throw new DAOException(e);
		}
	}

	public boolean addEventUser(int event, String login) throws DAOException,
			IllegalArgumentException {

		if (login == null || login == "") {
			throw new IllegalArgumentException("No hay login");
		}
		try (Connection conn = this.getConnection()) {
			final String query = "INSERT INTO eventUser VALUES(?, ?)";

			try (PreparedStatement statement = conn.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS)) {
				// /Comprobar que los tostring de los timestampo funcionen
				statement.setInt(1, event);
				statement.setString(2, login);

				if (statement.executeUpdate() != 1) {
					throw new SQLException("Error insertando fila en eventUser");
				} else {
					return true;
				}
			} catch (SQLException e) {
				LOG.log(Level.SEVERE, "Error a�adiendo eventUser", e);
				throw new DAOException(e);
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error a�adiendo eventUser", e);
			throw new DAOException(e);
		}
	}
}
