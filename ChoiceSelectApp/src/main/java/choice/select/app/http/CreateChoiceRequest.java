package choice.select.app.http;

import java.sql.Timestamp;
import java.util.ArrayList;
import rando.randomness.app.demo.model.Alternative;

public class CreateChoiceRequest {
	ArrayList<Alternative> alternatives;
	String id;
	String description;

	Timestamp creationDate;

	public CreateChoiceRequest() {}
	
	public CreateChoiceRequest(String description) {
		this.description = description;
	}
	
	public void setID(String id) {this.id = id;}
	public void setDescription(String d) {this.description = d;}
	public Timestamp getCreationDate() {return this.creationDate;}
	public String getID() {return this.id;}
	public String getDescription() {return this.description;}
	
	public boolean addAlternative(Alternative alt) {
		if(this.alternatives.size() < 5) {
			alternatives.add(alt);
			return true;
		}
		return false;
	}
	
	public ArrayList<Alternative> getAlts(){return this.alternatives;}
	
	public String toString() {return "CreateChoice(" + id + "," + description + "," + alternatives.toString() + ")";}
}
