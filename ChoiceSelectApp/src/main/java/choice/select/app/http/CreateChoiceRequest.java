package choice.select.app.http;

import java.sql.Timestamp;
import java.util.ArrayList;
import rando.randomness.app.demo.model.Alternative;

public class CreateChoiceRequest {
	ArrayList<Alternative> alternatives;
	String id;
	String description;
	Timestamp creationDate;
	Timestamp completionDate;
	int finalChoice;
	
	public CreateChoiceRequest() {
		
	}
	
	public CreateChoiceRequest(String id, String description, Timestamp creationDate) {
		this.id = id;
		this.description = description;
		this.creationDate = creationDate;
	}
	
	public void setID(String id) {this.id = id;}
	public void setDescription(String d) {this.description = d;}
	public void setCreationDate(Timestamp t) {this.creationDate = t;}
	public void setCompletionDate(Timestamp t) {this.completionDate = t;}
	public String getID() {return this.id;}
	public String getDescription() {return this.description;}
	public Timestamp getCreationDate() {return this.creationDate;}
	public Timestamp getCompletionDate() {return this.completionDate;}
	public boolean addAlternative(Alternative alt) {
		if(this.alternatives.size() < 5) {
			alternatives.add(alt);
			return true;
		}
		return false;
	}
	public ArrayList<Alternative> getAlts(){
		return this.alternatives;
	}
	public String toString() {
		return "CreateChoice(" + id + "," + description + "," + creationDate + "," + creationDate.toString() + "," + completionDate.toString() + alternatives.toString() + ")";
	}
}
