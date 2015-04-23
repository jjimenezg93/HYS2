package es.uvigo.esei.daa.entities;
import java.sql.Timestamp;
public class Event {
	
	private int id;
	private String nameEvent;
	private Timestamp dateCreate;
	private Timestamp dateInit;
	private Timestamp dateFinal;
	private String description;
	private String category;
	private String place;
	private String image;
	private String author;
	
	
	public Event(){
	
	}
	
	public Event(int id,String nameEvent, Timestamp dateCreate, Timestamp dateInit, Timestamp dateFinal, String description, String category, String place, String image,String author){
		this.setId(id); 
		this.setDateCreate(dateCreate);
		this.setDateInit(dateInit);
		this.setDateFinal(dateFinal);
		this.setDescription(description);
		this.setCategory(category);
		this.setPlace(place);
		this.setNameEvent(nameEvent);
		this.setImage(image);
		this.setAuthor(author);
		
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getPlace() {
		return place;
	}

	public void setPlace(String p) {
		this.place = p;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}

	public Timestamp getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Timestamp getDateInit() {
		return dateInit;
	}

	public void setDateInit(Timestamp dateInit) {
		this.dateInit = dateInit;
	}

	public Timestamp getDateFinal() {
		return dateFinal;
	}

	public void setDateFinal(Timestamp dateFinal) {
		this.dateFinal = dateFinal;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


}