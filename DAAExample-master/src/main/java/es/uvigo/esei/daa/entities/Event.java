package es.uvigo.esei.daa.entities;
import java.sql.Timestamp;
import java.util.Date;
public class Event {
	
	private int id;
	private Timestamp fechaCreacion;
	private Timestamp fechaInicio;
	private Timestamp fechaFin;
	private String descripcion;
	private String categoria;
	private String nombre;
	
	
	public Event(){
	
	}
	
	public Event(int id,String nombre, Timestamp fechaCreacion, Timestamp fechaInicio, Timestamp fechaFin, String descripcion, String categoria){
		this.id = id; 
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaCreacion = fechaCreacion;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.nombre= nombre;
		
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Timestamp getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}


	public Timestamp getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}