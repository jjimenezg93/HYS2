package es.uvigo.esei.daa.rest;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import es.uvigo.esei.daa.dao.DAOException;
import es.uvigo.esei.daa.dao.EventDAO;

@Path("/event")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {
	private final static Logger LOG = Logger.getLogger("EventResource");
	
	private final EventDAO dao;
	
	public EventResource() {
		this(new EventDAO());
	}
	
	// For testing purposes
	EventResource(EventDAO dao) {
		this.dao = dao;
	}


	//mcpaz y adri
	@GET
	@Path("/{login}")
	public Response listRecomended(@PathParam("login") String login) {
		try {
			return Response.ok(this.dao.listRecomended(login)).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error listing Event", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

}
