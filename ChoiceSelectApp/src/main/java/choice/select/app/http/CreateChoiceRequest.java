package choice.select.app.http;

import java.sql.Timestamp;
import java.util.ArrayList;
import rando.randomness.app.demo.model.Alternative;

public class CreateChoiceRequest {

	String id;
	String description;
	ArrayList<Alternative> alternatives;
	int teamSize;
	Timestamp creationDate;

	// Must include getters, setters, empty constructor to work with AWS

	public String getId() { return id; }
	public void setId(String newId) { this.id = newId; }

	public String getDesc() { return description; }
	public void setDesc(String newDesc) { this.description = newDesc; }

	public ArrayList<Alternatives> getAlts() { return alternatives; }
	public void setAlts(ArrayList<Alternative> newAlts) { this.alternatives = newAlts; }

	public int getTeamSize() { return teamSize; }
	public void setTeamSize(int newSize) { this.teamSize = newSize; }

	public Timestamp getCreationDate() { return creationDate; }
	public void setCreationDate(Timestamp newDate) { this.creationDate = newDate; }

	public boolean addAlternative(Alternative alt) {
		if(this.alternatives.size() < 5) {
			this.alternatives.add(alt);
			return true;
		}
		return false;
	}
	
	public CreateChoiceRequest() {
	}
	
	public CreateChoiceRequest(String desc, ArrayList<Alternative> alts, int teamSize) {
		this.description = desc;
		this.alternatives = alts;
		this.teamSize = teamSize;
	}

	public String toString() {
		if (alternatives == null) {
			return "CreateChoice(" + description + "; without alternatives)";
		}
		return "CreateChoice(" + description + "; with alternatives)";
	}
}
